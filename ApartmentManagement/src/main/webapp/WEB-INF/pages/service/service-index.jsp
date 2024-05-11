<%--
  Created by IntelliJ IDEA.
  User: TheAnh
  Date: 11/5/2024
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="h-full w-full">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4 px-2 sm:px-0">
            <p class="text-3xl font-bold">Danh Sách Dịch Vụ</p>
            <div class="flex justify-end">
                <a class="btn btn-success w-full sm:w-40 mr-3 text-white"  href="<c:url value="/service/create" />">
                    <div class="flex space-x-1 justify-center items-center">
                        <i class="bi bi-plus pb-1"></i>
                        <span>Thêm dịch vụ</span>
                    </div>
                </a>
                <button class="btn btn-danger removeCheckedRow">
                    <div class="flex space-x-1 justify-center items-center">
                        <span>Xoá</span>
                    </div>
                </button>
            </div>
        </div>
    </div>
    <div class="w-full overflow-auto">
        <span class="text-lg font-medium text-indigo-900">Lưu ý:</span>
        <br>
        <span class="text-xs text-indigo-900 mb-4">
                    Vui lòng chọn dịch vụ cho khách thuê. Nếu khách có chọn dịch vụ thì khi tính tiền phòng phần mềm sẽ tự tính các khoản phí vào hóa đơn; ngược lại nếu không chọn phần mềm sẽ bỏ qua.
                    Đối với dịch vụ là loại điện/ nước thì sẽ tính theo chỉ số điện/ nước
                    Đối với các dịch vụ khác sẽ tính theo số lượng (ví dụ phòng có 2 xe đạp nhập số lượng là 2)
        </span>
        <table class="border w-full my-2" id="add-service-table">
            <thead>
            <tr class="bg-gray-50 border-b">
                <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                    <div class="flex items-center justify-center">
                        <input type="checkbox" value="" class="allRowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                    </div>
                </th>
                <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">

                </th>
                <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                    <div class="flex items-center justify-center">
                        Tên
                    </div>
                </th>
                <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                    <div class="flex items-center justify-center">
                        Đơn Giá
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
                    <div class="flex justify-center items-center space-x-2">
                        <a class="btn btn-success"  href="<c:url value="/service/4/edit" />">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger"  onclick="removeRow(this)">X</button>
                    </div>
                </td>
                <td class="p-2 border-r">
                    Điện
                </td>
                <td class="p-2 border-r">
                    <input type="number" class="border p-1" value="3000">
                </td>
            </tr>
            <tr class="bg-gray-50 text-center">
                <td class="p-2 border-r">
                    <input type="checkbox" value="" class="rowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                </td>
                <td class="p-2 border-r">
                    <div class="flex justify-center items-center space-x-2">
                        <a class="btn btn-success"  href="<c:url value="/service/4/edit" />">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger"  onclick="removeRow(this)">X</button>
                    </div>
                </td>
                <td class="p-2 border-r">
                    Điện
                </td>
                <td class="p-2 border-r">
                    <input type="number" class="border p-1" value="3000">
                </td>
            </tr>
            <tr class="bg-gray-50 text-center">
                <td class="p-2 border-r">
                    <input type="checkbox" value="" class="rowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                </td>
                <td class="p-2 border-r">
                    <div class="flex justify-center items-center space-x-2">
                        <a class="btn btn-success"  href="<c:url value="/service/4/edit" />">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger"  onclick="removeRow(this)">X</button>
                    </div>
                </td>
                <td class="p-2 border-r">
                    Điện
                </td>
                <td class="p-2 border-r">
                    <input type="number" class="border p-1" value="3000">
                </td>
            </tr>
            <tr class="bg-gray-50 text-center">
                <td class="p-2 border-r">
                    <input type="checkbox" value="" class="rowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                </td>
                <td class="p-2 border-r">
                    <div class="flex justify-center items-center space-x-2">
                        <a class="btn btn-success"  href="<c:url value="/service/4/edit" />">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger"  onclick="removeRow(this)">X</button>
                    </div>
                </td>
                <td class="p-2 border-r">
                    Điện
                </td>
                <td class="p-2 border-r">
                    <input type="number" class="border p-1" value="3000">
                </td>
            </tr>
            <tr class="bg-gray-50 text-center">
                <td class="p-2 border-r">
                    <input type="checkbox" value="" class="rowCheckbox w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                </td>
                <td class="p-2 border-r">
                    <div class="flex justify-center items-center space-x-2">
                        <a class="btn btn-success"  href="<c:url value="/service/4/edit" />">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger"  onclick="removeRow(this)">X</button>
                    </div>
                </td>
                <td class="p-2 border-r">
                    Điện
                </td>
                <td class="p-2 border-r">
                    <input type="number" class="border p-1" value="3000">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/js/service.js"/>"></script>
