<%-- 
    Document   : index
    Created on : Apr 26, 2024, 8:38:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="roomStatus" value="Bảo trì"/>
<c:set var="roomStatusRent" value="đã thuê"/>
<%--Head--%>
<p class="text-3xl font-bold mb-2">Danh Sách Phòng</p>
<div class="grid grid-cols-1 sm:grid-cols-4 gap-1 px-2 sm:px-0">
    <form class="sm:col-span-3 grid grid-cols-1 sm:grid-cols-4 gap-2 my-1">
        <select
                type="search"
                name="status"
                class="col-6 col-md-2 form-select appearance-none block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 focus:ring focus:outline-none cursor-pointer"
        >
            <option value="all" selected>Tất cả</option>
            <option value="Còn trống">Còn trống</option>
            <option value="đã thuê">Đã cho thuê</option>
        </select>
        <input
                type="search"
                name="kw"
                class="col-6 col-md-2 w-auto block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 input-bordered input "
                placeholder="Phòng"
        /><button type="submit" class="btn btn-primary sm:w-40">
        <div class="flex space-x-1 justify-center items-center">
            <i class="bi bi-search pb-1 flex-1"></i>
            <%--                <span><a class="nav-link text-white flex-3" href="${myUrl}">Tìm kiếm </a></span>--%>
            <span class="nav-link text-white flex-3">Tìm kiếm</span>
        </div>
    </button>

    </form>
    <div class="sm:col-span-1 flex items-center justify-end">
        <a class="btn btn-info w-full sm:w-40" href="<c:url value="/room/tenants" />">
            <div class="flex space-x-1 justify-center items-center">
                <i class="bi bi-people-fill pb-1"></i>
                <span>Khách Thuê</span>
            </div>
        </a>
    </div>
</div>

<div class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 px-2 sm:px-4">
    <div class="h-full w-full pb-4 bt-2">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4">
            <div class="justify-center items-center">Tổng số phòng: ${rooms.size()}</div>
            <div class="flex justify-end">
                <a class="btn btn-success w-full sm:w-40" href="<c:url value="/room/create/" />">
                    <div class="flex space-x-1 justify-center items-center">
                        <i class="bi bi-plus-circle pb-1"></i>
                        <span>Thêm Phòng</span>
                    </div>
                </a>
            </div>
        </div>
        <h3 id="notice" class="text-red-500 p-2 text-xl font-bold"></h3>
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-3">
            <c:forEach items="${rooms}" var="r">
                <div class="relative flex flex-col mt-6 text-gray-700 bg-white shadow-md bg-clip-border rounded-xl ">
                    <div class="p-6">
                        <h5 class="block mb-2 font-sans text-xl antialiased font-semibold leading-snug tracking-normal text-blue-gray-900">
                            <i class="bi bi-house"></i> ${r.number}
                        </h5>

                        <c:choose>
                            <c:when test="${r.status == roomStatusRent}">
                                <a href="<c:url value="/room/${r.id}/edit-tenant" />"
                                   class="btn relative inline-flex items-center justify-start overflow-hidden font-medium transition-all bg-indigo-100 rounded hover:bg-white group py-1.5 px-2.5">
                                    <span class="w-56 h-48 rounded bg-indigo-600 absolute bottom-0 left-0 translate-x-full ease-out duration-500 transition-all translate-y-full mb-9 ml-9 group-hover:ml-0 group-hover:mb-32 group-hover:translate-x-0"></span>
                                    <span class="relative w-full text-left text-indigo-600 transition-colors duration-300 ease-in-out group-hover:text-white">Chỉnh sửa hợp đồng</span>
                                </a>
                            </c:when>
                            <c:when test="${r.status != roomStatus}">
                                <a href="<c:url value="/room/${r.id}/add-tenant" />"
                                   class="btn relative inline-flex items-center justify-start overflow-hidden font-medium transition-all bg-indigo-100 rounded hover:bg-white group py-1.5 px-2.5">
                                    <span class="w-56 h-48 rounded bg-indigo-600 absolute bottom-0 left-0 translate-x-full ease-out duration-500 transition-all translate-y-full mb-9 ml-9 group-hover:ml-0 group-hover:mb-32 group-hover:translate-x-0"></span>
                                    <span class="relative w-full text-left text-indigo-600 transition-colors duration-300 ease-in-out group-hover:text-white">Thêm khách</span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <span class="btn cursor-not-allowed relative inline-flex items-center justify-start overflow-hidden font-medium transition-all bg-indigo-100 rounded">
                                    <span class="relative w-full text-left text-indigo-600 transition-colors">Bảo trì</span>
                                </span>
                            </c:otherwise>
                        </c:choose>

                        <div class="flex items-center my-1 space-x-2">
                            <i class="bi bi-cash"></i>
                            <span class="">${String.format("%,d", r.price)} VNĐ</span>
                        </div>
                    </div>
                    <div class="p-6">
                        <a class="btn btn-info" href="<c:url value="/room/${r.id}/edit"/>">
                            <div class="flex space-x-1 justify-center items-center">
                                <i class="bi bi-pencil-square pb-1"></i>
                                <span>Chỉnh sửa</span>
                            </div>
                        </a>
                        <a class="btn btn-danger" href="<c:url value="/room/${r.id}/delete"/>">
                            <div class="flex space-x-1 justify-center items-center">
                                <i class="bi bi-trash pb-1"></i>
                                <span>Xoá</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    const params = new URLSearchParams(document.location.search);
    console.log(params)
    const s = params.get("errMsg");
    document.querySelector("#notice").innerHTML = s;
</script>