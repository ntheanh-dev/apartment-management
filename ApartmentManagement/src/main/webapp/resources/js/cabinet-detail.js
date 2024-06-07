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
})
