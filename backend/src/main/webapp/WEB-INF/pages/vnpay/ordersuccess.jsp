<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Thanh toán thành công</title>
</head>

<body>
<!-- back to top -->

<!-- start body -->
<div class="body py-5">
    <div class="container">
        <div class="w-50 m-auto">
            <!-- Thêm lớp "my-4" để tạo ra khoảng cách trên và dưới tiêu đề trang -->
            <h1 class="my-3 text-success text-center">Thanh toán thành công</h1>
            <!-- Sử dụng lớp "alert" và "alert-danger" để tạo ra thông báo lỗi -->
            <h2 class="my-2">Chi tiết đơn hàng</h2>
            <!-- Sử dụng lớp "table" và "table-bordered" để tạo ra bảng hiển thị thông tin chi tiết đơn hàng -->
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <td>Thông tin đơn hàng:</td>
                    <td>${orderId}</td>
                </tr>
                <tr>
                    <td>Tổng tiền:</td>
                    <td>${totalPrice}</td>
                </tr>
                <tr>
                    <td>Thời gian thanh toán:</td>
                    <td>${paymentTime}</td>
                </tr>
                <tr>
                    <td>Mã giao dịch:</td>
                    <td>${transactionId}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- end body -->


<!-- start footer -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>