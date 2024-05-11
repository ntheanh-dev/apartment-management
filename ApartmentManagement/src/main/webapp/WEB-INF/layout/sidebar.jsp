<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">
        <li class="nav-item ">
            <a class="nav-link" href="<c:url value="/"/>"">
                <i class="bi bi-box"></i>
                <span>Trang chủ</span>
            </a>
        </li>
        <li class="nav-item ">
            <a class="nav-link collapsed" href="<c:url value="/room/"/>"">
                <i class="bi bi-house-fill"></i>
                <span>Phòng</span>
            </a>
        </li>
        <li class="nav-item ">
            <a class="nav-link collapsed" href="<c:url value="/service/"/>"">
                <i class="bi bi-list-check"></i>
                <span>Dich vụ</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="<c:url value="/report/"/>">
                <i class="bi bi-clipboard-data"></i>
                <span>Báo Cáo</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="<c:url value="/statistic/"/>">
                <i class="bi bi-file-earmark-bar-graph"></i>
                <span>Thống Kê</span>
            </a>
        </li>
    </ul>
</aside>

