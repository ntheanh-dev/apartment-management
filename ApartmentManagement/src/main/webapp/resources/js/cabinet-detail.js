$(document).ready(function () {

    // -------Mở form thêm item-------------
    $(".btn-add-items").click(function () {
        $(".overlay-add-item").fadeIn();
        $(".add-item-form").fadeIn();
    });

    // -------Đóng form thêm item-------------
    $(".close-add-item-form").click(function () {
        $(".overlay-add-item").fadeOut();
        $(".add-item-form").fadeOut();
    });

    // -------Chọn file-----------------
    $('#dropzone-file').on('change', function() {
        var dropzone = $(this).closest('label');
        var textElement = dropzone.find('p:first');
        if (this.files.length > 0) {
            dropzone.addClass('bg-gray-200');
            textElement.html('<span class="font-semibold text-white">Đã tải !</span>');
            $(".dropzone-file-description").fadeOut();
        } else {
            dropzone.removeClass('bg-gray-700');
            textElement.html('<span class="font-semibold">Click để tải ảnh</span>');
            $(".dropzone-file-description").fadeIn();
        }
    });

    //---------- Loc item trong tu do theo trang thai--------------
    $('.item-filter-btn').on('click', function() {
        // Remove bg-blue-400 class from all buttons and add bg-white
        $('.item-filter-btn').removeClass('bg-blue-400').addClass('bg-white');

        // Add bg-blue-400 class to the clicked button and remove bg-white
        $(this).removeClass('bg-white').addClass('bg-blue-400');

        // Get the data attribute value of the clicked button
        var status = $(this).attr('data');

        // Reload the page with the new query parameter
        window.location.href = window.location.pathname + '?status=' + status;
    });

    // ----------------------Giữ màu nen cua button khi tai lai trang--------------
    var urlParams = new URLSearchParams(window.location.search);
    var status = urlParams.get('status');

    if (status) {
        // Remove bg-blue-400 class from all buttons and add bg-white
        $('.item-filter-btn').removeClass('bg-blue-400').addClass('bg-white');

        // Find the button with the matching data attribute and add bg-blue-400 class
        $('.item-filter-btn[data="' + status + '"]').removeClass('bg-white').addClass('bg-blue-400');
    }
})
