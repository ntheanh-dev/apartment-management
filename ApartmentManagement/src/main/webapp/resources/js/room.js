$(document).ready(function () {
    $('.tab-link').click(function(){
        var currentTab = $(this).attr('data-tab');
        $('.tab-link').removeClass('bg-blue-400');

        $(this).removeClass('bg-white');
        $(this).addClass('bg-blue-400');

        $('.tab-content').hide()
        $(currentTab).show()
    });

    // Mặc định ẩn tab2 và tab3
    $('#tab2, #tab3').hide();

    //-----------Lay danh sach tinh thanh-------------
    const host = 'https://vapi.vnappmob.com/api/province/'
    var callAPI = (api) => {
        return fetch(api, {
            method: 'get',
        }).then(res => res.json()).then(data => {
            renderData(data.results, "city", "city");
        })
    }
    callAPI(host);
    var callApiDistrict = (api) => {
        return fetch(api, {
            method: 'get',
        }).then(res => res.json()).then(data => {
            renderData(data.results, "district", "district");
        })
    }

    var renderData = (array, select, type) => {
        let row = ' <option disable value="">Chọn</option>';
        if (type == "city") {
            array.forEach(e => {
                row += `<option data-id="${e.province_id}" value="${e.province_name}">${e.province_name}</option>`
            });
        } else if (type == "district") {
            array.forEach(e => {
                row += `<option data-id="${e.district_id}" value="${e.district_name}">${e.district_name}</option>`
            });
        }
        var a = document.querySelector("#" + select)
        if (a) {
            a.innerHTML = row
        }

    }

    $("#city").change(() => {
        callApiDistrict(host + "district/" + $("#city").find(':selected').data('id'));
    });
})
