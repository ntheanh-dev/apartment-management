<%--
  Created by IntelliJ IDEA.
  User: TheAnh
  Date: 6/6/2024
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="card rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class=" w-full card-body h-full overflow-y-auto border-b-2">
        <h5 class="card-title text-2xl font-bold border-b-2">Tủ đồ phòng 2.2</h5>

        <div class="flex justify-between items-center">
            <div class="flex w-full sm:w-1/2 my-3 space-x-1 sm:px-0">
                <button
                        data="pending"
                        class="item-filter-btn tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                >
                    Chưa nhận
                </button>

                <button
                        data="received"
                        class="item-filter-btn tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                >
                    Đã nhận
                </button>

                <button
                        data="all"
                        class="item-filter-btn tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-blue-400 text-gray-800 border-gray-200 rounded-sm"
                >
                    Tất cả
                </button>
            </div>
            <button class="btn btn-success btn-add-items" id="add-row">
                <i class="bi bi-plus-circle"></i>
            </button>
        </div>

        <c:choose>
            <c:when test="${cabinetItems.size() == 0}">
                <div class="w-full flex items-center flex-wrap justify-center gap-10">
                    <div class="grid gap-4 w-60">
                        <div class="w-20 h-20 mx-auto bg-gray-50 rounded-full shadow-sm justify-center items-center inline-flex">
                            <svg xmlns="http://www.w3.org/2000/svg" width="33" height="32" viewBox="0 0 33 32" fill="none">
                                <g id="File Serch">
                                    <path id="Vector" d="M19.9762 4V8C19.9762 8.61954 19.9762 8.92931 20.0274 9.18691C20.2379 10.2447 21.0648 11.0717 22.1226 11.2821C22.3802 11.3333 22.69 11.3333 23.3095 11.3333H27.3095M18.6429 19.3333L20.6429 21.3333M19.3095 28H13.9762C10.205 28 8.31934 28 7.14777 26.8284C5.9762 25.6569 5.9762 23.7712 5.9762 20V12C5.9762 8.22876 5.9762 6.34315 7.14777 5.17157C8.31934 4 10.205 4 13.9762 4H19.5812C20.7604 4 21.35 4 21.8711 4.23403C22.3922 4.46805 22.7839 4.90872 23.5674 5.79006L25.9624 8.48446C26.6284 9.23371 26.9614 9.60833 27.1355 10.0662C27.3095 10.524 27.3095 11.0253 27.3095 12.0277V20C27.3095 23.7712 27.3095 25.6569 26.138 26.8284C24.9664 28 23.0808 28 19.3095 28ZM19.3095 16.6667C19.3095 18.5076 17.8171 20 15.9762 20C14.1352 20 12.6429 18.5076 12.6429 16.6667C12.6429 14.8257 14.1352 13.3333 15.9762 13.3333C17.8171 13.3333 19.3095 14.8257 19.3095 16.6667Z" stroke="#4F46E5" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round" />
                                </g>
                            </svg>
                        </div>
                        <div>
                            <h2 class="text-center text-black text-base font-semibold leading-relaxed pb-1">Không có món đồ nào ở đây</h2>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                    <div class="w-full mb-3 rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 px-2 sm:px-4">
                        <div class="w-full pt-3 overflow-auto">
                            <table class="border w-full mb-2" id="add-tenant-table">
                                <thead>
                                    <tr class="bg-gray-50 border-b">
                                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                        <div class="flex items-center justify-center">
                                            Tên
                                        </div>
                                    </th>
                                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                        <div class="flex items-center justify-center">
                                            Mô Tả
                                        </div>
                                    </th>
                                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                        <div class="flex items-center justify-center">
                                            Hình Ảnh
                                        </div>
                                    </th>
                                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                        <div class="flex items-center justify-center">
                                            Ngày Giao Tới
                                        </div>
                                    </th>
                                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                        <div class="flex items-center justify-center">
                                            Ngày Nhận
                                        </div>
                                    </th>
                                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                        <div class="flex items-center justify-center">
                                            Trạng Thái
                                        </div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${cabinetItems}" var="ca">
                                        <tr class="bg-gray-50 text-center border-b-2 border-gray-200">
                                            <td class="p-2 border-r">
                                                <span>${ca.name}</span>
                                            </td>
                                            <td class="p-2 border-r">
                                                <span>${ca.description}</span>
                                            </td>
                                            <td class="p-2 border-r">
                                                <div class="w-20 h-20 mx-auto overflow-hidden">
                                                    <img class="w-full h-full object-cover" src="<c:url value="${ca.image}"/>" alt="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6iUXTyBsGNt8705AjfIKurMe0uOT5ufQQTQ&s">
                                                </div>
                                            </td>
                                            <td class="p-2 border-r">
                                                <span>${ca.deliveryDate}</span>
                                            </td>
                                            <td class="p-2 border-r">
                                                <span>${ca.recievedDate}</span>
                                            </td>
                                            <td class="p-2 border-r">
                                                <c:choose>
                                                    <c:when test="${ca.recievedDate == '' || ca.recievedDate == null}">
                                                        <span class="badge bg-warning">Chưa Nhận</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-success">Đã Nhận</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="p-2">
                                                <c:choose>
                                                    <c:when test="${ca.recievedDate == '' || ca.recievedDate == null}">
                                                        <button class="btn btn-success btn-sm">
                                                            Nhận
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-danger btn-sm">
                                                            Xoá
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<%-------------Form them item---------------%>
<div class="fixed top-0 left-0 w-screen h-screen bg-black bg-opacity-50 flex justify-center items-center overlay-add-item z-100 hidden ">
    <div class="absolute top-96 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white p-6 rounded-lg shadow-md w-full sm:w-6/12 hidden overflow-y-auto max-h-400 add-item-form">
        <div class="flex justify-between">
            <h2 class="text-xl font-bold text-center w-full">Thêm đơn hàng</h2>
            <button
                    type="button"
                    class="btn-close close-add-item-form"
                    aria-label="Close"
            ></button>
        </div>
        <c:url value="/cabinet/${cabinetId}/create" var="action"/>

        <form:form
                action="${action}"
                method="post"
                modelAttribute="items"
                class="w-full mt-4 add_guest_form"
        >
            <div class="flex flex-wrap -mx-3 mb-4">
                <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                            for="name"
                    >
                        Tên
                    </label>
                    <form:input
                            class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                            id="name"
                            type="text"
                            placeholder="Tên món hàng"
                            name="name"
                            path="name"
                    />
                    <!-- <p class="text-red-500 text-xs italic">Please fill out this field.</p> -->
                </div>
                <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                            for="deliveryDate"
                    >
                        Ngày giao tới
                    </label>
                    <form:input
                            class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                            id="deliveryDate"
                            type="date"
                            placeholder=""
                            name="deliveryDate"
                            path="deliveryDate"
                    />
                    <!-- <p class="text-red-500 text-xs italic">Please fill out this field.</p> -->
                </div>
            </div>

            <div class="flex flex-wrap -mx-3 mb-4">
                <div class="w-full px-3 mb-6 md:mb-0">
                    <label
                            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                    >
                        Mô tả
                    </label>
                    <form:textarea
                            path="description"
                            class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                    />
                </div>
            </div>

            <div class="flex items-center justify-center w-full mx-3">
                <label for="dropzone-file" class="flex flex-col items-center justify-center w-full h-52 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer hover:bg-bray-800 ">
                    <div class="flex flex-col items-center justify-center py-3">
                        <svg class="h-8 mb-4 text-black-500 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                        </svg>
                        <p class="mb-2 text-sm text-gray-500 dark:text-gray-400 dropzone-file-content"><span class="font-semibold">Click để tải ảnh</span></p>
                        <p class="text-xs text-gray-500 dark:text-gray-400  dropzone-file-description">SVG, PNG, JPG or GIF (MAX. 800x400px)</p>
                    </div>
                    <input id="dropzone-file" type="file" class="hidden" />
                    <form:input  id="dropzone-file" type="file" class="hidden"  accept=".png,.jpg" path="file"  name="file" />
                </label>
            </div>

            <div class="flex justify-end space-x-3 mt-4 mx-3">
                <button class="btn btn-secondary close-add-item-form" type="reset">
                    Bỏ qua
                </button>
                <button type="submit" class="btn btn-success save_new_guest_btn">
                    Lưu
                    <form:hidden path="id"/>
                </button>
            </div>
        </form:form>
    </div>
</div>
<script src="<c:url value="/js/cabinet-detail.js"/>"></script>