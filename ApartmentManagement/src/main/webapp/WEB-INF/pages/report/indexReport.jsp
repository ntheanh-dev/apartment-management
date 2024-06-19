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
<div class="m-5">
    <c:forEach items="${reports}" var="r">
        <c:choose>
            <c:when test="${r.status == possitive}">
                <div class="alert alert-success">
                    <strong>(${r.createdDate})</strong>
                    <strong>${r.title}</strong>
                    <p>${r.content}</p>
                </div>
            </c:when>
            <c:when test="${r.status == negative}">
                <div class="alert alert-danger">
                    <strong>(${r.createdDate})</strong>
                    <strong>${r.title}</strong>
                    <p>${r.content}</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    <strong>(${r.createdDate})</strong>
                    <strong>${r.title}</strong>
                    <p>${r.content}</p>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>

</div>

</body>
</html>
