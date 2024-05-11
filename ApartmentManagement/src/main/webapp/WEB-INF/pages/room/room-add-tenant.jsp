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
                <a class="btn btn-warning w-full sm:w-32 mr-3 text-white"  href="<c:url value="/room/" />">
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
            <%--   Thong tin khách thuê         --%>
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
            <%--   Dịch vụ         --%>
            <div id="tab2" class="tab-content w-full pt-3 overflow-auto">
                <span class="text-lg font-medium text-gray-700">Lưu ý:</span>
                <br>
                <span class="text-xs text-black mb-4">
                    Vui lòng chọn dịch vụ cho khách thuê. Nếu khách có chọn dịch vụ thì khi tính tiền phòng phần mềm sẽ tự tính các khoản phí vào hóa đơn; ngược lại nếu không chọn phần mềm sẽ bỏ qua.
                    Đối với dịch vụ là loại điện/ nước thì sẽ tính theo chỉ số điện/ nước
                    Đối với các dịch vụ khác sẽ tính theo số lượng (ví dụ phòng có 2 xe đạp nhập số lượng là 2)
                </span>
                <table class="border w-full" id="add-service-table">
                    <thead>
                        <tr class="bg-gray-50 border-b">
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Chọn
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Dịch vụ sử dụng
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Đơn Giá
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Số Lượng
                                </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="bg-gray-50 text-center">
                            <td class="p-2 border-r">
                                <input type="checkbox" value="" class="rowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                            </td>
                            <td class="p-2 border-r">
                                Điện
                            </td>
                            <td class="p-2 border-r">
                                <input type="number" class="border p-1" value="3000">
                            </td>
                            <td class="p-2 border-r">
                                <input type="number" class="border p-1" value="1">
                            </td>
                        </tr>
                        <tr class="bg-gray-50 text-center">
                            <td class="p-2 border-r">
                                <input type="checkbox" value="" class="rowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                            </td>
                            <td class="p-2 border-r">
                                Điện
                            </td>
                            <td class="p-2 border-r">
                                <input type="number" class="border p-1" value="3000">
                            </td>
                            <td class="p-2 border-r">
                                <input type="number" class="border p-1" value="1">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%--   Thành viên     --%>
            <div id="tab3" class="tab-content w-full pt-3 overflow-auto">
                <table class="border w-full" id="add-tenant-table">
                    <thead>
                        <tr class="bg-gray-50 border-b">
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Họ Tên
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Ngày sinh
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Giới tính
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    CCCD/CMND
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Địa chỉ
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Điện thoại
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                    Số xe
                                </div>
                            </th>
                            <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                                <div class="flex items-center justify-center">
                                </div>
                            </th>
                        </tr>
                        </thead>
                    <tbody>
                        <tr class="bg-gray-50 text-center">
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="date" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
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
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1">
                            </td>
                            <td class="p-2">
                                <button class="btn btn-danger px-3"  onclick="removeRow(this)"><span class="font-medium text-xl">-</span></button>
                            </td>
                        </tr>
                        <tr class="bg-gray-100 text-center border-b text-sm text-gray-600">
                            <td class="p-2 border-r"></td>
                            <td class="p-2 border-r"></td>
                            <td class="p-2 border-r"></td>
                            <td class="p-2 border-r"></td>
                            <td class="p-2 border-r"></td>
                            <td class="p-2 border-r"></td>
                            <td class="p-2 border-r"></td>
                            <td>
                                <button class="btn btn-success" id="add-row">
                                    <i class="bi bi-plus-circle"></i>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%--   Hợp đồng     --%>
            <div id="tab4" class="tab-content w-full pt-3">
                <span class="text-xs text-black pl-2">
                    Các thông tin nhập ở đây sẽ được sử dụng cho việc xuất/ in hợp đồng thuê phòng.
                </span>
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mt-3">
                    <div>
                        <label class="block mb-3">
                            <span class="text-gray-700">Ngày bắt đầu hợp đồng *</span>
                            <input type="date" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                value="2024-06-01"
                            />
                        </label>
                    </div>
                    <div>
                        <label class="block mb-3">
                            <span class="text-gray-700">Ngày bắt đầu hợp đồng *</span>
                            <input type="date" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                   value="2024-12-01"
                            />
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/room.js"/>"></script>

