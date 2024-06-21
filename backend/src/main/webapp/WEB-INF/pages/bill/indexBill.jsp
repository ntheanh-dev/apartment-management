<%--
  Created by IntelliJ IDEA.
  User: An Tien
  Date: 13/06/2024
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="status" value="Chưa thu"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p class="text-3xl font-bold mb-2">Quản lý hóa đơn</p>
<div class="grid grid-cols-1 sm:grid-cols-4 gap-1 px-2 sm:px-0">
    <form class="sm:col-span-3 grid grid-cols-1 sm:grid-cols-4 gap-2 my-1">
        <select
                type="search"
                name="status"
                class="col-6 col-md-2 form-select appearance-none block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 focus:ring focus:outline-none cursor-pointer"
        >
            <option value="all" selected>Tất cả</option>
            <option value="Chưa thu">Chưa thu</option>
            <option value="Đã thu">Đã thu</option>
        </select>
        <input
                type="search"
                name="kw"
                class="col-6 col-md-2 w-auto block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 input-bordered input "
                placeholder="Phòng"
        />
        <button type="submit" class="btn btn-primary sm:w-40">
            <div class="flex space-x-1 justify-center items-center">
                <i class="bi bi-search pb-1 flex-1"></i>
                <span class="nav-link text-white flex-3">Tìm kiếm</span>
            </div>
        </button>
    </form>
</div>
<div class="gap-1 px-2 sm:px-0">
    <span class="text-lg font-medium text-indigo-900">Lưu ý:</span>
    <br>
    <span class="text-xs text-indigo-900 mb-4">
        Các hóa đơn sẽ được tự động tạo vào ngày đầu tiên của tháng sẽ gửi thông báo tới các chủ phòng (người kí hợp đồng) để thanh toán hóa đơn và có thể thanh toán qua vnpay hoặc momo
    <table class="table table-bordered table-striped">
  <thead class="thead-dark">
    <tr>
        <th>Phòng</th>
        <th>Tiêu đề</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Trạng thái</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="b" items="${bill}">
      <tr>
        <td>${b.contract.room.number}</td>
          <td>${b.title}</td>
          <td>${b.startedDate}</td>
          <td>${b.endDate}</td>
        <td>
          <c:choose>
              <c:when test="${b.status == status}">
                  <div class="btn btn-danger btn-sm">
                          ${b.status}
                  </div>
              </c:when>
              <c:otherwise>
                  <div class="btn btn-success btn-sm">
                          ${b.status}
                  </div>
              </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
</span>
</div>
</body>
</html>
