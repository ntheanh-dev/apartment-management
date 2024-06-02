<%-- 
    Document   : index
    Created on : Apr 26, 2024, 8:38:44 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="flex flex-col" style="height:calc(100vh - 65px - 2rem);">
    <%--Head--%>
    <div class="border-b-2 border-blue-600 h-16 flex justify-between items-center">
        <div class="flex justify-center items-center h-10 w-10 cursor-pointer">
            <i class="text-4xl bi bi-arrow-left-short flex text-blue-600 hover:opacity-75"></i>
        </div>
        <div class="flex justify-center items-center h-10 w-10 cursor-pointer">
            <i class="bi bi-three-dots-vertical text-3xl flex text-blue-600 hover:opacity-75"></i>
        </div>
    </div>
    <%--Body--%>
    <div class="flex-1 overflow-y-scroll scroll-smooth"  id="messagesDiv">

    </div>
    <%--Footer--%>
    <div class="border-1 border-blue-600 h-12 flex flex-row">
        <input
                placeholder="Send Message..."
                id="messInput"
                type="text"
                class="w-10/12 focus:outline-none focus:border-none pl-4 text-xl"
        />
        <div class="w-1/12 cursor-pointer relative cursor-pointer flex justify-center items-center">
            <i class="bi bi-image text-xl text-blue-600 cursor-pointer flex"></i>
            <input type="file" name="" value="" id="fileInput" class="w-full cursor-pointer absolute bottom-0 left-0 right-0 top-0 opacity-0"/>
        </div>

        <div class="w-1/12 cursor-pointer bg-blue-300 flex justify-center items-center" id="sendMess">
            <i class="bi bi-chevron-double-right text-xl text-white flex hover:text-2xl"></i>
        </div>
    </div>
</div>
<script type="application/javascript" src="<c:url value="/js/chat.js"/>"></script>