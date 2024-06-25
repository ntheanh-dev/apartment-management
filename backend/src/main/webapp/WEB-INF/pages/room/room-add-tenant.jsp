<%--
    Document   : index
    Created on : Apr 26, 2024, 8:38:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/room/${room.id}/add-tenant" var="action"/>
<c:set var="emptyString" value=""/>
<form:form modelAttribute="resident" action="${action}"
           class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="h-full w-full">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4 px-2 sm:px-0">
            <p class="text-3xl font-bold">Thêm Khách Thuê Phòng</p>
            <div class="flex justify-end">
                <a class="btn btn-warning w-full sm:w-32 mr-3 text-white" href="<c:url value="/room/" />">
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
                <form:hidden path="id" />
                <form:hidden path="idContract"/>
            </div>
        </div>
        <div class="flex w-full sm:w-1/2 my-3 space-x-1  px-2 sm:px-0">
            <button
                    class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-blue-400 text-gray-800 border-gray-200 rounded-sm"
                    data-tab="#tab1"
                    onclick="event.preventDefault();"
            >
                Thông tin khách thuê
            </button>

            <button
                    class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                    data-tab="#tab2"
                    onclick="event.preventDefault();"
            >
                Dịch vụ
            </button>

            <button
                    class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                    data-tab="#tab3"
                    onclick="event.preventDefault();"
            >
                Thành viên
            </button>
            <button
                    class="tab-link flex justify-center font-medium rounded-l px-2 py-2 border bg-white text-gray-800 border-gray-200 rounded-sm"
                    data-tab="#tab4"
                    onclick="event.preventDefault();"
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
                            <span class="text-gray-700">Họ tên</span>
                            <form:errors path="fullName" cssClass="text-danger font-bold"/>
                            <form:input path="fullName" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <label class="block mb-3">
                            <span class="text-gray-700">Ngày sinh</span>
                            <form:errors path="dateOfBirth" cssClass="text-danger font-bold"/>
                            <form:input path="dateOfBirth" type="date" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <div class="block mb-3">
                            <span class="text-gray-700">Thành Phố</span>
                            <form:select path="city" name="city" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                         id="city"
                            >
                                <option disable value="">Chọn</option>
                            </form:select>
                        </div>
                        <div class="block">
                            <span class="text-gray-700">Quận/Huyện</span>
                            <form:select path="ward" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                                         id="district" name="district"
                            >
                                <option disable value="">Chọn</option>
                            </form:select>
                        </div>
                    </div>
                    <div>
                        <div class="py-3 flex flex-col">
                            <span class="text-gray-700">Giới tính:</span>
                            <div>
                                <label class="inline-flex items-center">
                                    <form:radiobutton path="gender" name="season" value="1" class="text-indigo-600 border-gray-300 rounded-full
                                          shadow-sm focus:border-indigo-300 focus:ring focus:ring-offset-0
                                          focus:ring-indigo-200 focus:ring-opacity-50" checked="checked"
                                    />
                                    <span class="ml-2">Nam</span>
                                </label>
                                <label class="inline-flex items-center">
                                    <form:radiobutton path="gender" name="season" value="0" class="text-indigo-600 border-gray-300 rounded-full
                                          shadow-sm focus:border-indigo-300 focus:ring focus:ring-offset-0
                                          focus:ring-indigo-200 focus:ring-opacity-50 "
                                    />
                                    <span class="ml-2">Nữ</span>
                                </label>
                            </div>
                        </div>
                        <label class="block mb-3">
                            <span class="text-gray-700">CMND/ CCCD</span>
                            <form:errors path="identity" cssClass="text-danger font-bold"/>
                            <form:input path="identity" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <label class="block mb-3">
                            <span class="text-gray-700">Điện thoại</span>
                            <form:errors path="phone" cssClass="text-danger font-bold"/>
                            <form:input path="phone" type="phone" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                        <label class="block">
                            <span class="text-gray-700">Email</span>
                            <form:input path="email" type="email" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                    </div>
                    <div class="sm:col-span-2">
                        <label class="block mb-3">
                            <span class="text-gray-700">Địa chỉ thường trú</span>
                            <form:errors path="address" cssClass="text-danger font-bold"/>
                            <form:input path="address" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            />
                        </label>
                    </div>
                    <div>
                        <div class="block mb-3">
                            <span class="text-gray-700">Số xe</span>
                            <form:errors path="numberPlate" cssClass="text-danger font-bold"/>
                            <div class="flex">
                                <form:input path="numberPlate" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
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
                                       value="${String.format("%,d", room.price)}" disabled
                                />

                                <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                                    <p>VND</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>

                        <div class="block mb-3">
                            <span class="text-gray-700">Tiền cọc*</span>
                            <div class="flex">
                                <form:input path="securityDeposit" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
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
                <span class="text-lg font-medium text-indigo-900">Lưu ý:</span>
                <br>
                <span class="text-xs text-indigo-900 mb-4">
                    Danh sách điện nước
                </span>
                <table class="border w-full mb-2" id="add-service-table">
                    <thead>
                    <tr class="bg-gray-50 border-b">
                        <th class="p-2 border-r cursor-pointer text-sm font-thin text-gray-500">
                            <div class="flex items-center justify-center">
                                Thứ tự
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
                                Đơn vị
                            </div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${service}" var="s">
                        <tr class="bg-gray-50 text-center">
                            <td class="p-2 border-r">
                                 ${s.id}
                            </td>
                            <td class="p-2 border-r">
                                ${s.name}
                            </td>
                            <td class="p-2 border-r">
                                <input type="number" class="border p-1" value="${s.price}" disabled>
                            </td>
                            <td class="p-2 border-r">
                                <input type="text" class="border p-1" value="${s.unit}" disabled>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
                <%--   Thành viên     --%>
            <div id="tab3" class="tab-content w-full pt-3 overflow-auto">
                <table class="border w-full mb-2" id="add-tenant-table">
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
                    <c:forEach items="${resident.listMember}" var="m" varStatus="status">
                        <tr class="bg-gray-50 text-center">
                            <form:hidden path="listMember[${status.index}].id"/>
                            <td class="p-2 border-r">
                                <form:input path="listMember[${status.index}].fullName" type="text" class="border p-1"/>
                            </td>
                            <td class="p-2 border-r">
                                <form:input path="listMember[${status.index}].dateOfBirth" type="date" class="border p-1"/>
                            </td>
                            <td class="p-2 border-r">
                                <form:input path="listMember[${status.index}].identity" type="text" class="border p-1"/>
                            </td>
                            <td class="p-2 border-r">
                                <form:input path="listMember[${status.index}].address" type="text" class="border p-1"/>
                            </td>
                            <td class="p-2 border-r">
                                <form:input path="listMember[${status.index}].phone"  type="number" class="border p-1"/>
                            </td>
                            <td class="p-2 border-r">
                                <form:input path="listMember[${status.index}].numberPlate" type="text" class="border p-1"/>
                            </td>
                            <td class="p-2">
                                <a class="btn btn-danger px-3" href="../../user/delete/${resident.listMember[status.index].id}"><span class="font-medium text-xl">-</span></a>
                            </td>
                        </tr>
                    </c:forEach>
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
                            <span class="text-gray-700">Ngày bắt đầu hợp đồng</span>
                            <form:input path="startedDate" id="startedDate" type="date" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                            />
                                <script>
                                    if(!document.querySelector("#startedDate").value){
                                        document.addEventListener('DOMContentLoaded', function() {
                                            const today = new Date();
                                            const yyyy = today.getFullYear();
                                            const mm = String(today.getMonth() + 1).padStart(2, '0'); // Months start at 0
                                            const dd = String(today.getDate()).padStart(2, '0');

                                            const formattedToday = yyyy+"-"+mm+"-"+dd;
                                            document.getElementById('startedDate').value = formattedToday;
                                        });
                                    }
                                </script>

                        </label>
                    </div>
                    <div>
                        <label class="block mb-3">
                            <span class="text-gray-700">Thời hạn</span>
                            <form:input path="totalMonth" type="number" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 " value="12"
                            />
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>
<script src="<c:url value="/js/room.js"/>"></script>
<script>
        const myTimeout = setTimeout(getcity, 1500);
        const myTimeout1 = setTimeout(getward, 3000)
        function getcity(){
            document.getElementById("city").value = "${resident.city}"
            document.getElementById("city").dispatchEvent(new Event('change'));
            clearTimeout(myTimeout);
        }
        function getward(){
            document.getElementById("district").value ="${resident.ward}"
            clearTimeout(myTimeout1);
        }
</script>

