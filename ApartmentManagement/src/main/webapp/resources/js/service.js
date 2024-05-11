$(document).ready(function () {
    //-----------Xoa mot hang service-------------
    var serviceCheckbox = []
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

    //--------------Tat ca cac dong duoc chon--------------
    $('.allRowCheckbox').change(function () {
        var isChecked = $(this).prop("checked");
        if(isChecked){
            $(".rowCheckbox").attr('checked', true)
            $(".rowCheckbox").closest("tr").addClass("bg-sky-600"); // Thêm class "highlight" vào dòng chứa checkbox được chọn
        } else {
            $(".rowCheckbox").attr('checked', false)
            $(".rowCheckbox").closest("tr").removeClass("bg-sky-600"); // Loại bỏ class "highlight" khỏi dòng chứa checkbox không được chọn
        }
    })

    //---------------Xoa cac dong duoc chon--------------
    $(".removeCheckedRow").click(function () {
        var numberOfChecked = $(".rowCheckbox:checked").length;
        if(numberOfChecked == 0) {
            Swal.fire({
                title: 'Hãy chọn một it nhất một dịch vụ bạn muốn xoá ',
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Xác nhận'
            })
        } else {
            $(".rowCheckbox:checked").closest("tr").remove();
        }
    })

})
//-----------Xoá một hàng thêm thành viên trong table---------------
function removeRow(button) {
    $(button).closest("tr").remove();
}
