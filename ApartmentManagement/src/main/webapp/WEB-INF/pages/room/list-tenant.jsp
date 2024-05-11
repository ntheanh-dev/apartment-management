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
            <p class="text-3xl font-bold">Khách Thuê</p>
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
        <div class="w-full mb-3 overflow-auto rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 px-2 sm:px-4">
            <table class="border w-full">
                <thead>
                <tr class="bg-gray-50 border-b">
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Tên Phòng
                        </div>
                    </th>
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Khu Vực
                        </div>
                    </th>
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Họ Tên
                        </div>
                    </th>
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Số Điện Thoại
                        </div>
                    </th>
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Ngày Bắt Đầu
                        </div>
                    </th>
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Tiền Phòng
                        </div>
                    </th>
                    <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                        <div class="flex items-center justify-center">
                            Tiền Cọc
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
                        P01
                    </td>
                    <td class="p-2 border-r">
                        Tầng 2
                    </td>
                    <td class="p-2 border-r">
                        <input type="text" class="border p-1" value="Thế Anh" readonly>
                    </td>
                    <td class="p-2 border-r">
                        <input type="text" class="border p-1" value="0384023948" readonly>
                    </td>
                    <td class="p-2 border-r">
                        <input type="date" class="border p-1" value="2024-06-01" readonly>
                    </td>
                    <td class="p-2 border-r">
                        <input type="text" class="border p-1" value="3000000" readonly>
                    </td>
                    <td class="p-2 border-r">
                        <input type="text" class="border p-1" value="3000000" readonly>
                    </td>
                    <td class="p-2">
                        <button class="btn btn-danger px-3"  onclick="removeTenant(this)"><span class="font-medium text-xl">-</span></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<c:url value="/js/room.js"/>"></script>
