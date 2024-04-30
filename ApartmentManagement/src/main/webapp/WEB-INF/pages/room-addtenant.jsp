<%--
    Document   : index
    Created on : Apr 26, 2024, 8:38:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="h-full w-full">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4 px-2 sm:px-0">
            <p class="text-3xl font-bold">Thêm Khách Thuê Phòng</p>
            <div class="flex justify-end">
                <a class="btn btn-warning w-full sm:w-32 mr-3"  href="<c:url value="/room/" />"><i class="bi bi-arrow-return-left" ></i>Quay Về
                </a>
                <button class="btn btn-success w-full sm:w-32"><i class="bi bi-check2"></i>Lưu</button>
            </div>
        </div>
        <div class="flex w-full sm:w-1/2 my-3 space-x-1  px-2 sm:px-0">
            <button
                class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-blue-400 text-gray-800 border-gray-200 rounded-sm"
                data-tab="#tab1"
            >
                Thông tin khách thuê
            </button>

            <button
                class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                data-tab="#tab2"
            >
                Dịch vụ
            </button>

            <button
                class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                data-tab="#tab3"
            >
                Thành viên
            </button>
            <button
                    class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                    data-tab="#tab4"
            >
                Hợp đồng
            </button>
        </div>

        <div class="w-full mb-3 rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 px-2 sm:px-4">
            <div id="tab1" class="tab-content w-full pt-3">
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-3">
                    <div>
                        <label class="block mb-3">
                            <span class="text-gray-700">Họ tên *</span>
                            <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <label class="block mb-3">
                            <span class="text-gray-700">Ngày sinh</span>
                            <input type="date" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <div class="block mb-3">
                            <span class="text-gray-700" >Thành Phố</span>
                            <select name="city" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                    id="city"
                            >
                                <option disable value="">Chọn</option>
                            </select>
                        </div>
                        <div class="block">
                            <span class="text-gray-700" >Quận/Huyện</span>
                            <select class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                    id="district" name="district"
                            >
                                <option disable value="">Chọn</option>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div class="py-3 flex flex-col">
                            <span class="text-gray-700">Giới tính:</span>
                            <div>
                                <label class="inline-flex items-center">
                                    <input name="season" type="radio" class=" text-indigo-600 border-gray-300 rounded-full
                                          shadow-sm focus:border-indigo-300 focus:ring focus:ring-offset-0
                                          focus:ring-indigo-200 focus:ring-opacity-50 "
                                           checked
                                    />
                                    <span class="ml-2">Nam</span>
                                </label>
                                <label class="inline-flex items-center">
                                    <input name="season" type="radio" class=" text-indigo-600 border-gray-300 rounded-full
                                          shadow-sm focus:border-indigo-300 focus:ring focus:ring-offset-0
                                          focus:ring-indigo-200 focus:ring-opacity-50 "
                                    />
                                    <span class="ml-2">Nữ</span>
                                </label>
                            </div>
                        </div>
                        <label class="block mb-3">
                            <span class="text-gray-700">CMND/ CCCD</span>
                            <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <label class="block mb-3">
                            <span class="text-gray-700">Điện thoại</span>
                            <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <label class="block">
                            <span class="text-gray-700">Email</span>
                            <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                    </div>
                    <div class="sm:col-span-2">
                        <label class="block mb-3">
                            <span class="text-gray-700">Địa chỉ thường trú</span>
                            <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                    </div>
                    <div>
                        <div class="block mb-3">
                            <span class="text-gray-700">Số xe</span>
                            <div class="flex">
                                <input type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                        focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                />
                                <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                                    <p>Chiếc</p>
                                </div>
                            </div>
                        </div>

                        <div class="block mb-3">
                            <span class="text-gray-700">Tiền phòng*</span>
                            <div class="flex">
                                <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                        focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                       value="1,000,000" disabled
                                />
                                <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                                    <p>VNĐ</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <label class="block mb-3">
                            <span class="text-gray-700">Ngày bắt đầu</span>
                            <input type="date" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                   value="2024-06-01"
                            />
                        </label>
                        <div class="block mb-3">
                            <span class="text-gray-700">Tiền cọc*</span>
                            <div class="flex">
                                <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                        focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                />
                                <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                                    <p>VNĐ</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="tab2" class="tab-content">content 2</div>
            <div id="tab3" class="tab-content">content 3</div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/room.js"/>"></script>

