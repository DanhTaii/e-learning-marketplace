<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="grid__column-2 container-1">
    <a href="index">
        <div class="container-1__logo">
            <i class="fa-solid fa-graduation-cap"></i>
            <span>Intellect</span>
        </div>
    </a>
    <div class="container-1__menu">
        <ul>
            <c:if test="${userRoles.contains('SUPER_ADMIN')}">
                <li>
                    <a href="admin/dashboard">
                        <div class="menu-item__student  ${currentPage == 'dashboard' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                        </div>
                    </a>
                </li>
            </c:if>
            <c:if test="${userRoles.contains('ADMIN_USER') || userRoles.contains('SUPER_ADMIN')}">
                <li>
                    <a href="admin/users">
                        <div class="menu-item__student ${currentPage == 'users' ? 'student-list' : ''} ">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                        </div>
                    </a>
                </li>
            </c:if>
            <c:if test="${userRoles.contains('ADMIN_COURSE') || userRoles.contains('SUPER_ADMIN')}">
                <li>
                    <a href="admin/courses">
                        <div class="menu-item__student ${currentPage == 'courses' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="admin/lessons">
                        <div class="menu-item__student ${currentPage == 'lessons' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="admin/tags">
                        <div class="menu-item__student ${currentPage == 'tags' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="admin/categories">
                        <div class="menu-item__student ${currentPage == 'categories' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                        </div>
                    </a>
                </li>
            </c:if>
            <c:if test="${userRoles.contains('ADMIN_ORDER') || userRoles.contains('SUPER_ADMIN')}">
                <li>
                    <a href="admin/orders">
                        <div class="menu-item__student ${currentPage == 'orders' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="admin/payment-methods">
                        <div class="menu-item__student ${currentPage == 'payment-methods' ? 'student-list' : ''}">
                                        <span class="container-1__menu-items menu-item__order">

                                            <i class="fa-solid fa-credit-card"></i>
                                            <span>Kiểu thanh toán</span>
                                        </span>
                        </div>
                    </a>
                </li>
            </c:if>
            <c:if test="${userRoles.contains('SUPER_ADMIN')}">
                <li>
                    <a href="admin/super/roles">
                        <div class="menu-item__student ${currentPage == 'roles' ? 'student-list' : ''}">
                                        <span class="container-1__menu-items menu-item__order">

                                            <i class="fa-solid fa-user-shield"></i>
                                            <span>Vai trò</span>
                                        </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="admin/super/permissions">
                        <div class="menu-item__student ${currentPage == 'permissions' ? 'student-list' : ''}">
                                        <span class="container-1__menu-items menu-item__order">

                                            <i class="fa-solid fa-credit-card"></i>
                                            <span>Quyền</span>
                                        </span>
                        </div>
                    </a>
                </li>
            </c:if>
        </ul>
        <div class="log-out">
            <a href="html-authentication/sign-in.jsp">
                <div class="log-out__container">
                    <div class="log-out__content">
                        Thoát
                    </div>
                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                </div>
            </a>
        </div>
    </div>
</div>
