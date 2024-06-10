<%--
  Created by IntelliJ IDEA.
  User: TheAnh
  Date: 6/6/2024
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="text-3xl font-bold mb-2">Danh Sách Tủ Đồ</p>
<form class="sm:col-span-3 grid grid-cols-1 sm:grid-cols-4 gap-2 my-1 px-2 sm:px-0 ">
    <select
            class="col-6 col-md-2 form-select appearance-none block px-3 py-2 border rounded-lg bg-white shadow-lg placeholder-gray-400 text-gray-700 focus:ring focus:outline-none cursor-pointer"
            name="active"
    >
        <option value="" selected>Trạng thái tủ đồ</option>
        <option value="true">Hoạt Động</option>
        <option value="false">Đã khoá</option>
    </select>
    <button type="submit" class="btn btn-primary sm:w-40">
        <div class="flex space-x-1 justify-center items-center">
            <i class="bi bi-search pb-1 flex-1"></i>
            <%--                <span><a class="nav-link text-white flex-3" href="${myUrl}">Tìm kiếm </a></span>--%>
            <span class="nav-link text-white flex-3">Tìm kiếm</span>
        </div>
    </button>
</form>

<div class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 px-2 sm:px-4">
    <div class="h-full w-full pb-4 bt-2">
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-3">

            <c:forEach items="${cabinets}" var="c">
                <div class="relative flex flex-col mt-6 text-gray-700 bg-white shadow-md bg-clip-border rounded-xl ">
                    <div class="px-6 pt-4">
                        <h5 class="block mb-2 font-sans text-xl antialiased font-semibold leading-snug tracking-normal text-blue-gray-900">
                            <i class="bi bi-box-seam"></i> Tủ đồ phòng: 1.1
                        </h5>
                        <a href="<c:url value="/cabinet/${c.id}/detail" />"
                           class="btn relative inline-flex items-center justify-start overflow-hidden font-medium transition-all bg-indigo-100 rounded hover:bg-white group py-1.5 px-2.5">
                            <span class="w-56 h-48 rounded bg-indigo-600 absolute bottom-0 left-0 translate-x-full ease-out duration-500 transition-all translate-y-full mb-9 ml-9 group-hover:ml-0 group-hover:mb-32 group-hover:translate-x-0"></span>
                            <span class="relative w-full text-left text-indigo-600 transition-colors duration-300 ease-in-out group-hover:text-white">Chi Tiết</span>
                        </a>
                    </div>
                    <div class="flex items-center my-1 space-x-2 px-6 pt-2 pb-4">
                        <span class="text-lg text-medium">Số đơn hàng chưa lấy:</span>
                        <span class="text-red-600">69</span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>