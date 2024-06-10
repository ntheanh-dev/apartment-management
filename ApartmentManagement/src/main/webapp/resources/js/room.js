$(document).ready(function () {
    //----Thay đổi màu sắc của tab--------------
    $('.tab-link').click(function(){
        var currentTab = $(this).attr('data-tab');
        $('.tab-link').removeClass('bg-blue-400');

        $(this).removeClass('bg-white');
        $(this).addClass('bg-blue-400');

        $('.tab-content').hide()
        $(currentTab).show()
    });

    // Mặc định ẩn tab2 và tab3
    $('#tab2, #tab3, #tab4').hide();

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
                row += `<option id="${e.province_name}" data-id="${e.province_id}" value="${e.province_name}">${e.province_name}</option>`
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


    //------------------------Tab thêm thành viên-----------------------------
    //-----------Thêm một hàng thêm thành viên trong table---------------
    let rowCount = 0;
    $("#add-row").click(function () {
        rowCount++;
        const nameRadio = 'made' + rowCount;
        $("#add-tenant-table tbody").prepend(`
            <tr class="bg-gray-50 text-center">
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="date" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <div>
                                    <label class="inline-flex items-center">
                                        <input name=${nameRadio} type="radio" class=" text-indigo-600 border-gray-300 rounded-full
                                              shadow-sm focus:border-indigo-300 focus:ring focus:ring-offset-0
                                              focus:ring-indigo-200 focus:ring-opacity-50 "
                                               checked
                                        />
                                        <span class="ml-2">Nam</span>
                                    </label>
                                    <label class="inline-flex items-center">
                                        <input name=${nameRadio} type="radio" class=" text-indigo-600 border-gray-300 rounded-full
                                              shadow-sm focus:border-indigo-300 focus:ring focus:ring-offset-0
                                              focus:ring-indigo-200 focus:ring-opacity-50 "
                                        />
                                        <span class="ml-2">Nữ</span>
                                    </label>
                                </div>
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2">
                                <button class="btn btn-danger px-3"   onclick="removeRow(this)"><span class="font-medium text-xl">-</span></button>
                            </td>
                        </tr>
        `)
    })


    //------------------------Tab chọn dịch vụ-----------------------------
    // Bắt sự kiện khi checkbox thay đổi để doi màu nền của dòng đó
    $(".rowCheckbox").change(function () {
        var isChecked = $(this).prop("checked");
        if(isChecked){
            $(this).closest("tr").addClass("bg-sky-600"); // Thêm class "highlight" vào dòng chứa checkbox được chọn
        } else {
            $(this).closest("tr").removeClass("bg-sky-600"); // Loại bỏ class "highlight" khỏi dòng chứa checkbox không được chọn
        }
    })
})
//-----------Xoá một hàng thêm thành viên trong table---------------
function removeRow(button) {
    $(button).closest("tr").remove();
}

//-----------Xoá một khách đã thuê phòng trong table---------------
function removeTenant(button) {
    Swal.fire({
        title: 'Bạn có xoá người này?',
        text: 'Khi xoá các tiền chưa thanh toán trong tháng này sẽ không được tính.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Xác nhận',
        cancelButtonText: 'Huỷ bỏ'
    }).then((result) => {
        if (result.isConfirmed) {
            $(button).closest("tr").remove();
        }
    })
}