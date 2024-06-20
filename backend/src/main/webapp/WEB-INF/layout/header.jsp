<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header id="header" class="header fixed-top d-flex align-items-center">
    <div class="d-flex align-items-center justify-content-between">
        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div>
    <!-- End Logo -->
    <a class="mt-2 mr-2" href="<c:url value="/room/"/>">Quản Lý Chung Cư</a>
    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">
            <li class="nav-item dropdown pe-3">
                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <img src="https://res.cloudinary.com/dqpo9h5s2/image/upload/v1711860995/rooms/avatar_vuwmxd.jpg"
                         alt="Profile" class="rounded-circle"/>
                    <span class="d-none d-md-block dropdown-toggle ps-2">${pageContext.request.userPrincipal.name}</span>
                </a>


                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6>${pageContext.request.userPrincipal.name}</h6>
                        <span>Quản Trị Viên</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider"/>
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="#">
                            <i class="bi bi-person"></i>
                            <span>Trang Cá Nhân</span>
                        </a>
                    </li>

                    <li>
                        <hr class="dropdown-divider"/>
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="<c:url value='/logout' />" >
                            <i class="bi bi-box-arrow-right"></i>
                            <span>Đăng Xuất</span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
    <!-- End Icons Navigation -->
</header>