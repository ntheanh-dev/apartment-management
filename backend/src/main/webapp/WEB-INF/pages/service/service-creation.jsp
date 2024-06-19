<%--
  Created by IntelliJ IDEA.
  User: TheAnh
  Date: 12/5/2024
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/service/updateOrCreate" var="action"/>
<form:form modelAttribute="service" method="post" action="${action}" class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="h-full w-full">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4 px-2 sm:px-0">
            <c:choose>
                <c:when test="${service.id > 0}">
                    <p class="text-3xl font-bold">Sửa Dịch Vụ</p>
                </c:when>
                <c:otherwise>
                    <p class="text-3xl font-bold">Thêm Dịch Vụ</p>
                </c:otherwise>
            </c:choose>
            <div class="flex justify-end">
                <a class="btn btn-warning w-full sm:w-32 mr-3 text-white"  href="<c:url value="/service/" />">
                    <div class="flex space-x-1 justify-center items-center">
                        <i class="bi bi-arrow-return-left"></i>
                        <span>Quay Về</span>
                    </div>
                </a>
                <button class="btn btn-success w-full sm:w-20">
                    <div class="flex space-x-1 justify-center items-center">
                        <i class="bi bi-check2 pb-1"></i>
                        <span>Lưu</span>
                    </div>
                </button>
                <form:hidden path="id"/>
            </div>
        </div>
        <div class="grid grid-cols-1 sm:grid-cols-2 gap-3">
            <div>
                <label class="block mb-6">
                    <span class="text-gray-700">Tên *</span>
                    <form:input path="name" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                    />
                </label>
                <div class="block mb-6">
                    <span class="text-gray-700">Đơn Giá *</span>
                    <div class="flex mb-6">
                        <form:input path="price" type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>VNĐ</p>
                        </div>
                    </div>
                </div>
                <div class="block mb-6">
                    <span class="text-gray-700">Đơn Vị *</span>
                    <div class="flex mb-6">
                        <form:input path="unit" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                        />

                    </div>
                </div>
            </div>
            <div>
                <label class="block mb-6">
                    <span class="text-gray-700">Ý kiến thêm</span>
                    <form:textarea path="description" class="resize-y border-1 border-gray-600 rounded-l-md shadow-sm focus:border-indigo-300
                        focus:ring focus:ring-indigo-200 focus:ring-opacity-50  w-full p-2"
                    />
                </label>
            </div>
        </div>
        <p class="my-4 text-red-700">(*): Thông tin bắt buộc</p>
    </div>
</form:form>
<script src="<c:url value="/js/service.js"/>"></script>
