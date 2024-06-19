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
<c:set var="possitive" value="positive"/>
<c:set var="negative" value="negative"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p class="text-3xl font-bold mb-2">Đóng góp người dân</p>
<div class="grid grid-cols-1 sm:grid-cols-4 gap-1 px-2 sm:px-0">
    <form class="sm:col-span-3 grid grid-cols-1 sm:grid-cols-4 gap-2 my-1">
        <select
                type="search"
                name="status"
                class="col-6 col-md-2 form-select appearance-none block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 focus:ring focus:outline-none cursor-pointer"
        >
            <option value="all" selected>Tất cả</option>
            <option value="positive">Tích cực</option>
            <option value="Neutral">Bình thường</option>
            <option value="negative">Tiêu cực</option>
        </select>
        <input
                type="date"
                name="kw"
                class="col-6 col-md-2 w-auto block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 input-bordered input "
                placeholder="Phòng"
        /><button type="submit" class="btn btn-primary sm:w-40">
        <div class="flex space-x-1 justify-center items-center">
            <i class="bi bi-search pb-1 flex-1"></i>
            <span class="nav-link text-white flex-3">Tìm kiếm</span>
        </div>
    </button>

    </form>
</div>
<div class="row">
    <div class="col-md-3">
        <div class="card mb-4 shadow btn-outline-success">
            <div class="card-body">
                <h5 class="card-title">Renter</h5>
                <p class="card-text fw-bolder ">${usersByRole[2][1]}</p>
                <p class="text-success">3.65% Since last week</p>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card mb-4 shadow btn-outline-success">
            <div class="card-body">
                <h5 class="card-title">Landlord</h5>
                <p class="card-text fw-bolder ">${usersByRole[1][1]}</p>
                <p class="text-success">6.65% Since last week</p>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card mb-4 shadow btn-outline-success">
            <div class="card-body">
                <h5 class="card-title">Published Post</h5>
                <p class="card-text fw-bolder ">${post[0][1]}</p>
                <p class="text-success">5.25% Since last week</p>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card mb-4 shadow btn-outline-success">
            <div class="card-body">
                <h5 class="card-title">Uncensored Post</h5>
                <c:choose>
                    <c:when test="${post[1] != null}">
                        <p class="card-text fw-bolder ">${post[1][1]}</p>
                    </c:when>
                    <c:otherwise>
                        <p class="card-text fw-bolder ">0</p>
                    </c:otherwise>
                </c:choose>
                <p class="text-danger">-2.25% Since last week</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
