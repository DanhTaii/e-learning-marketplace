<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>

    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.4">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css-admin/notification.css?v=1.0.1">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/users-management.css?v=1.0.1">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-2 container-1">
                    <a href="index">
                        <div class="container-1__logo">
                            <i class="fa-solid fa-graduation-cap"></i>
                            <span>Softskill</span>
                        </div>
                    </a>
                    <div class="container-1__menu">
                        <ul>
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

                <div class="grid__column-10 container-2">
                    <div class="container-2__header"></div>
                    <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">Người dùng</div>
                            <div class="admin-create__buttons">
                                <button type="button" class="dark-button">
                                    <a href="html-admin/user-create.jsp">
                                        <i class="fa-solid fa-plus"></i>Tạo mới
                                    </a>
                                </button>
                            </div>
                        </div>

                        <div class="container-2__body">
                            <form action="admin/users/search" method="get">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên người dùng:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="usernameSearch" value="${param.usernameSearch}">
                                            </div>
                                            <div class="filter__selection-items">
                                                <div class="filter__selection-title filter__item-phone">Số điện thoại:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="phoneSearch" value="${param.phoneSearch}">
                                            </div>
                                            <div class="filter__selection-items">
                                                <div class="filter__selection-title filter__item-phone">Từ ngày:</div>
                                                <input placeholder="" type="date" class="admin-input__long"
                                                       name="dateFrom" value="${param.dateFrom}">
                                            </div>
                                            <div class="filter__selection-items-select">
                                                <div class="filter__selection-title filter__item-phone">Vai trò:</div>
                                                <select name="roleSearch" class="combobox admin-input__short">
                                                    <option value="" ${empty param.roleSearch ? 'selected' : ''}>Tất
                                                        cả
                                                    </option>
                                                    <option value="user" ${param.roleSearch == 'user' ? 'selected' : ''}>
                                                        Người dùng
                                                    </option>
                                                    <option value="admin" ${param.roleSearch == 'admin' ? 'selected' : ''}>
                                                        Quản trị viên
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="filter__button-search">
                                            <button type="submit" class="admin-search-btn">
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="container-2__list-student">

                               <style>
                                   .action-wrapper {
                                       display: flex;
                                       align-items: center;
                                       justify-content: flex-start;
                                       gap: 8px;
                                       height: 100%;
                                   }

                                   .icon-action-btn {
                                       width: 32px;
                                       height: 32px;
                                       display: inline-flex;
                                       align-items: center;
                                       justify-content: center;
                                       padding: 0;
                                       border: none;
                                       background: transparent;
                                       cursor: pointer;
                                       border-radius: 4px;
                                       color: var(--dark-blue);
                                       transition: all 0.2s;
                                   }

                                   .icon-action-btn:hover {
                                       background-color: #f0f0f0;
                                       transform: translateY(-1px);
                                   }


                                   table {
                                       border-collapse: collapse !important;
                                   }

                                   table td {
                                       border-bottom: 1px solid var(--light-grey);
                                       height: 55px;
                                   }
                               </style>

                                <table>
                                    <thead>
                                    <tr>
                                        <th>Tên người dùng</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th>Vai trò</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="user" items="${listUsers}">
                                        <tr>
                                            <td>
                                                <div class="title">${user.username}</div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${user.email}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">${user.phone}</div>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${user.role == 'ADMIN'}">
                                                        <div class="course-row__status course-row__font-content role-admin">
                                                                ${user.role}
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="course-row__status course-row__font-content course-row__status-private">
                                                                ${user.role}
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate value="${user.createdAt}"
                                                                    pattern="MMMM d, yyyy"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <div class="action-wrapper">
                                                <button type="button" onclick="showUserDetail(${user.id})"
                                                        class="icon-action-btn">
                                                    <i class="fa-solid fa-pen"></i>
                                                </button>
                                                <form action="admin/user/delete" method="post" class="form">
                                                    <input type="hidden" name="id" value="${user.id}">
                                                    <button type="submit" class="icon-action-btn">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="user-detail" class="modal__course-detail">
                    <div class="modal__course-content">
                        <form action="admin/user/detail" method="post">

                            <div class="course__header">
                                <div class="course__title">
                                    <i class="fa-solid fa-address-card"></i>
                                    <span id="modal-title"></span>
                                </div>
                                <div class="x__icon" onclick="closeModal()">
                                    <i class="fa-solid fa-xmark"></i>
                                </div>
                            </div>
                            <div class="course-body">
                                <div class="user-info-grid">
                                    <input type="hidden" id="detail-id" name="id">

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-user"></i> Tên người dùng</label>
                                        <input id="detail-username" name="username" type="text" class="input__create">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-envelope"></i> Email</label>
                                        <input id="detail-email" name="email" type="text" class="input__create">
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-phone"></i> Số điện thoại</label>
                                        <input id="detail-phone" name="phone" type="text" class="input__create">
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-shield-halved"></i> Vai trò</label>
                                        <select id="detail-role" name="role" class="input__create role-badge">
                                            <option value="user">Người dùng</option>
                                            <option value="admin">Quản trị viên</option>
                                        </select>
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i>Ngày tham gia</label>
                                        <input id="detail-created" type="text" class="input__create" readonly>
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i>Ngày cập nhật</label>
                                        <input id="detail-updated" type="text" class="input__create" readonly>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="button btn-cancel" onclick="closeModal()"
                                            style="margin-right: 1rem;">Hủy
                                    </button>
                                    <button type="submit" class="button dark-button">Lưu thay
                                        đổi
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="notification-modal" class="modal"
     style="display: none; position: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); align-items: center; justify-content: center;">
    <div class="modal-content"
         style="background: white; padding: 20px; border-radius: 8px; width: 400px; text-align: center; box-shadow: 0 4px 15px rgba(0,0,0,0.2);">
        <h2 id="noti-title" style="margin-top: 0;">Thông báo</h2>
        <hr>
        <p id="noti-message" style="font-size: 16px; margin: 20px 0;"></p>
        <button onclick="closeNotiModal()" class="dark-button" style="padding: 8px 25px; cursor: pointer;">Đóng</button>
    </div>
</div>

</body>
<div id="toast"></div>
<script>
    // Ép kiểu về chuỗi để đảm bảo JS không bị lỗi cú pháp nếu giá trị null
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>

</script>
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/js-admin/admin-user-detail.js?v=<%=System.currentTimeMillis()%>"></script>
</html>