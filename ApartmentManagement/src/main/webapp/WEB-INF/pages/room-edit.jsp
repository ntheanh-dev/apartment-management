<%--
  Created by IntelliJ IDEA.
  User: TheAnh
  Date: 6/5/2024
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="h-full w-full">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4">
            <p class="text-3xl font-bold">Sửa Phòng</p>
            <div class="flex justify-end">
                <a class="btn btn-warning w-full sm:w-32 mr-3"  href="<c:url value="/room/" />"><i class="bi bi-arrow-return-left" ></i>Quay Về
                </a>
                <button class="btn btn-success w-full sm:w-32"><i class="bi bi-check2"></i>Lưu</button>
            </div>
        </div>
        <div class="grid grid-cols-1 sm:grid-cols-2 gap-3">
            <div>
                <label class="block mb-6">
                    <span class="text-gray-700">Phòng Số *</span>
                    <input type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                           value="${room.roomName}"
                    />
                </label>
                <div class="block mb-6">
                    <span class="text-gray-700">Dài *</span>
                    <div class="flex mb-6">
                        <input type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                               value="${room.length}"
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>Mét</p>
                        </div>
                    </div>
                </div>
                <div class="block mb-6">
                    <span class="text-gray-700">Rộng *</span>
                    <div class="flex mb-6">
                        <input type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                               value="${room.width}"
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>Mét</p>
                        </div>
                    </div>
                </div>
                <div class="block mb-6">
                    <span class="text-gray-700">Số lượng tối đa *</span>
                    <div class="flex mb-6">
                        <input type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                               value="${room.capacity}"
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>Người</p>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div class="block mb-6">
                    <span class="text-gray-700">Đơn giá *</span>
                    <div class="flex mb-6">
                        <input type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                        focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                               value="${room.price}"
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>VNĐ</p>
                        </div>
                    </div>
                </div>
                <div class="block mb-6">
                    <span class="text-gray-700" >Tầng</span>
                    <select name="present" class="p-2 block w-full mt-1 border-1 border-gray-600 rounded-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                    >
                        <option>Tầng 1</option>
                        <option>Tầng 2</option>
                        <option>Tầng 3</option>
                    </select>
                </div>
                <div class="mt-2 pt-4 flex flex-row space-x-3">
                    <span class="text-gray-700">Cho Thuê</span>
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

            </div>
        </div>
        <label class="block mb-6">
            <span class="text-gray-700">Ý kiến thêm</span>
            <textarea class="resize-y border-1 border-gray-600 rounded-l-md shadow-sm focus:border-indigo-300
                focus:ring focus:ring-indigo-200 focus:ring-opacity-50  w-full p-2"
                value="${room.description}"
            ></textarea>
        </label>
        <p class="my-4 text-red-700">(*): Thông tin bắt buộc</p>
    </div>
</div>
