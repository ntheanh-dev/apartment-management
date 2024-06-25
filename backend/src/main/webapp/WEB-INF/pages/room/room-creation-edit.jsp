<%-- 
    Document   : index
    Created on : Apr 26, 2024, 8:38:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/room/create" var="action"/>
<c:set var="roomStatus" value="đã thuê" />
<form:form action="${action}" modelAttribute="room"
           method="post"
           class="rounded-sm border border-gray-200 bg-white shadow-md dark:border-gray-700 dark:bg-gray-900 flex-col flex h-full items-center justify-center mt-2 sm:px-4">
    <div class="h-full w-full">
        <div class="grid grid-cols-1 sm:grid-cols-2 py-2 border-b-2 gap-1 mb-4">
            <p class="text-3xl font-bold">Thêm Phòng</p>
            <div class="flex justify-end">
                <a class="btn btn-warning w-full sm:w-32 mr-3 text-white" href="<c:url value="/room/" />">
                    <div class="flex space-x-1 justify-center items-center">
                        <i class="bi bi-arrow-return-left"></i>
                        <span>Quay Về</span>
                    </div>
                </a>
                <form:button class="btn btn-success w-full sm:w-32">
                    <div class="flex space-x-1 justify-center items-center">
                        <i class="bi bi-check2 pb-1"></i>
                        <c:choose>
                            <c:when test="${room.id > 0}"> Cập nhật</c:when>
                            <c:otherwise> Tạo</c:otherwise>
                        </c:choose>
                        <form:hidden path="id"/>
                    </div>
                </form:button>
            </div>
        </div>
        <div class="grid grid-cols-1 sm:grid-cols-2 gap-3">
            <div>
                <label class="block mb-6">
                    <span class="text-gray-700">Phòng Số *</span>
                    <form:input path="number" type="text" class="p-2 block w-full border-1 border-gray-600 rounded-md shadow-sm
                                focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                    />
                </label>
                <form:errors path="number" cssClass="text-danger"/>

                <div class="block mb-6">
                    <span class="text-gray-700">Dài *</span>
                    <div class="flex mb-6">
                        <form:input path="length" type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>Mét</p>
                        </div>
                    </div>
                </div>
                <div class="block mb-6">
                    <span class="text-gray-700">Rộng *</span>
                    <div class="flex mb-6">
                        <form:input path="width" type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>Mét</p>
                        </div>
                    </div>
                    <form:errors path="width" cssClass="text-danger"/>

                </div>
                <div class="block mb-6">
                    <span class="text-gray-700">Số lượng tối đa *</span>
                    <div class="flex mb-6">
                        <form:input path="maximum" type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>Người</p>
                        </div>
                    </div>
                    <form:errors path="maximum" cssClass="text-danger"/>
                </div>
            </div>
            <div>
                <div class="block mb-6">
                    <span class="text-gray-700">Đơn giá *</span>
                    <div class="flex mb-6">
                        <form:input path="price" type="number" class="p-2 block w-full border-1 border-gray-600 rounded-l-md shadow-sm
                                        focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                        />
                        <div class="inline-flex items-center p-2 text-sm text-gray-900 border rounded-r-md bg-gray-400 shadow-sm">
                            <p>VNĐ</p>
                        </div>
                    </div>
                    <form:errors path="price" cssClass="text-danger"/>
                </div>
                <div class="block mb-6">
                    <span class="text-gray-700">Tầng</span>
                    <form:select path="floor" name="present" class="p-2 block w-full mt-1 border-1 border-gray-600 rounded-md shadow-sm
                                    focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                    >
                        <c:forEach items="${floors}" var="c">
                            <c:choose>
                                <c:when test="${c.id == room.floor.id}">
                                    <option value="${c.id}" selected>${c.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.id}">${c.name}</option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </form:select>
                    <form:errors path="floor" cssClass="text-danger"/>
                </div>
                <c:choose>
                    <c:when test="${room.id > 0 && room.status != roomStatus}">
                        <div class="block mb-6">
                            <span class="text-gray-700">Trạng Thái</span>
                            <form:select path="status" name="status" class="p-2 block w-full mt-1 border-1 border-gray-600 rounded-md shadow-sm
                                                            focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50 "
                            >
                                <c:forEach items="${status}" var="s">
                                    <c:choose>
                                        <c:when test="${s == room.status}">
                                            <option value="${s}" selected>${s}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${s}">${s}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="status"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <label class="block mb-6">
            <span class="text-gray-700">Ý kiến thêm</span>
            <form:textarea path="description" name="idea" class="resize-y border-1 border-gray-600 rounded-l-md shadow-sm focus:border-indigo-300
                focus:ring focus:ring-indigo-200 focus:ring-opacity-50  w-full p-2"
            />
        </label>
        <form:errors path="description" cssClass="text-danger"/>
    </div>
</form:form>


