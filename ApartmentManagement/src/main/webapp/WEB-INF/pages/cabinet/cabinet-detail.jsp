<%--
  Created by IntelliJ IDEA.
  User: TheAnh
  Date: 6/6/2024
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="card-body max-h-screen border-b-2">
        <h5 class="card-title text-2xl font-bold border-b-2">Tủ đồ phòng 2.2</h5>

        <div class="flex justify-between items-center">
            <div class="flex w-full sm:w-1/2 my-3 space-x-1 sm:px-0">
                <button
                        class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-blue-400 text-gray-800 border-gray-200 rounded-sm"
                        onclick="event.preventDefault();"
                >
                    Chưa nhận
                </button>

                <button
                        class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                        onclick="event.preventDefault();"
                >
                    Đã nhận
                </button>

                <button
                        class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                        onclick="event.preventDefault();"
                >
                    Tất cả
                </button>
            </div>
            <button class="btn btn-success btn-add-items" id="add-row">
                <i class="bi bi-plus-circle"></i>
            </button>
        </div>

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
                        <tr class="bg-gray-50 text-center">
                            <td class="p-2 border-r">
                                <span>Quần Áo</span>
                            </td>
                            <td class="p-2 border-r">
                                <span>Nếu bạn là một dân văn phòng "chính hiệu" thì không thể bỏ qua chiếc áo thun Cotton Compact in Care&Share phiên bản Văn Phòng với thiết kế là những vật dụng rất "dính" với dân văn phòng, công sở. Cùng xem chi tiết hơn thiết kế của mẫu áo này và ý nghĩa mà chương trình Care & Share đem lại nhé!</span>
                            </td>
                            <td class="p-2 border-r">
                                <div class="w-20 h-20 mx-auto bg-white rounded-lg shadow-lg overflow-hidden">
                                    <img class="w-full h-full object-cover" src="https://media3.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/September2022/3_27.jpg" alt="Sample Image">
                                </div>
                            </td>
                            <td class="p-2 border-r">
                                <span>12:00 20/4/2024</span>
                            </td>
                            <td class="p-2 border-r">
<%--                                <span>12:00 26/4/2024</span>--%>
                            </td>
                            <td class="p-2 border-r">
                                <span class="badge bg-warning">Chưa Nhận</span>
<%--                                <span class="badge bg-success">Đã Nhận</span>--%>
                            </td>
                            <td class="p-2">
                                <button class="btn btn-success">
                                    Nhận
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div
        class="fixed top-0 left-0 w-screen h-screen bg-black bg-opacity-50 flex justify-center items-center overlay-add-item z-100 hidden "
>
    <div
            class="fixed top-96 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white p-6 rounded-lg shadow-md w-full sm:w-6/12 hidden max-h-400  overflow-y-auto add-item-form"
    >
        <div class="flex justify-between">
            <h2 class="text-xl font-bold text-center w-full">Thêm đơn hàng</h2>
            <button
                    type="button"
                    class="btn-close close-add-item-form"
                    aria-label="Close"
            ></button>
        </div>

        <form class="w-full mt-4 add_guest_form">
            <div class="flex flex-wrap -mx-3 mb-4">
                <div class="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                            for="name"
                    >
                        Tên
                    </label>
                    <input
                            class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                            id="name"
                            type="text"
                            placeholder="Tên món hàng"
                            name="name"
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
                    <input
                            class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                            id="deliveryDate"
                            type="date"
                            placeholder=""
                            name="deliveryDate"
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
                    <textarea
                            class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                    ></textarea>
            </div>

            <div class="flex items-center justify-center w-full mx-3">
                <label for="dropzone-file" class="flex flex-col items-center justify-center w-full h-52 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer hover:bg-bray-800 ">
                    <div class="flex flex-col items-center justify-center py-3">
                        <svg class="w-8 h-8 mb-4 text-black-500 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                        </svg>
                        <p class="mb-2 text-sm text-gray-500 dark:text-gray-400 dropzone-file-content"><span class="font-semibold">Click để tải ảnh</span></p>
                        <p class="text-xs text-gray-500 dark:text-gray-400  dropzone-file-description">SVG, PNG, JPG or GIF (MAX. 800x400px)</p>
                    </div>
                    <input id="dropzone-file" type="file" class="hidden" />
                </label>
            </div>

            <div class="flex justify-end space-x-3 mt-4 mx-3">
                <button class="btn btn-secondary close-add-item-form" type="reset">
                    Bỏ qua
                </button>
                <div class="btn btn-success save_new_guest_btn">Lưu</div>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value="/js/cabinet-detail.js"/>"></script>