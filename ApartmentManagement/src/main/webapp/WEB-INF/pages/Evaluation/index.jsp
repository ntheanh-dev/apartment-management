<%--
  Created by IntelliJ IDEA.
  User: An Tien
  Date: 13/06/2024
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="rateRatHailong" value="Rất hài lòng"/>
<c:set var="rateHailong" value="Hài lòng"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/css/collapsible.css"/>" ></link>
</head>
<body>
<p class="text-3xl font-bold mb-2">Quản lý đánh giá</p>
<div class="grid grid-cols-1 sm:grid-cols-4 gap-1 px-2 sm:px-0">
    <div class="sm:col-span-4 flex items-center justify-end">
        <a class="btn btn-success w-full sm:w-40" href="<c:url value="/criterion/" />">
            <div class="flex space-x-1 justify-center items-center">
                <i class="bi bi-people-fill pb-1"></i>
                <span>Tiêu chí</span>
            </div>
        </a>
    </div>
</div>
<div class="gap-1 px-2 sm:px-0 collapsible-list">
       <c:forEach items="${evaluation}" var="c"  varStatus="status">
           <div class="collapsible">
               <div class="d-flex flex-row justify-content-between align-items-center gap-10">
                   <div>
                       <strong>Họ và tên:</strong>${c.residentUser.fullName}
                   </div>
                   <div>
                           ${c.createdDate}
                   </div>
               </div>
               <div >
                   <strong>Đóng góp thêm:</strong> ${c.feedback}
               </div>
           </div>
           <div class="content">
                   <table class="table">
                       <thead>
                       <tr>
                           <th scope="col">#</th>
                           <th scope="col"><i class="bi bi-emoji-angry"></i></th>
                           <th scope="col"><i class="bi bi-emoji-neutral"></i></th>
                           <th scope="col"><i class="bi bi-emoji-laughing"></i></th>
                       </tr>
                       </thead>
                       <tbody>
                        <c:forEach items="${evaluation[status.index].detailEvoluations}" var="d">
                            <tr>
                                <th scope="row">${d.criterion.name}</th>
                                <c:choose>
                                    <c:when test="${d.rate == rateHailong}">
                                        <td></td>
                                        <td><i class="bi bi-check-circle"></i></td>
                                        <td></td>
                                    </c:when>
                                    <c:when test="${d.rate == rateRatHailong}">
                                        <td></td>
                                        <td></td>
                                        <td><i class="bi bi-check-circle"></i></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><i class="bi bi-check-circle"></i></td>
                                        <td></td>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                       </tbody>
                   </table>
           </div>
       </c:forEach>


</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var coll = document.getElementsByClassName("collapsible");
        for (var i = 0; i < coll.length; i++) {
            coll[i].addEventListener("click", function() {
                this.classList.toggle("active");
                var content = this.nextElementSibling;
                if (content.style.display === "block") {
                    content.style.display = "none";
                } else {
                    content.style.display = "block";
                }
            });
        }
    });
</script>
</body>
</html>
