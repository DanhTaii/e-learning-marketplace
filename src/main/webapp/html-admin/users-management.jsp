<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>

    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/users-management.css">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-2 container-1">
                    <div class="container-1__title">Softskill</div>
                    <div class="container-1__menu">
                        <ul>
                            <li>
                                <a href="./dashboard.jsp">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./users-management.jsp">
                                    <div class="menu-item__student student-list">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./courses-management.jsp">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./lesson-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./tag-management.jsp">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./category-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./order-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./payment-method-management.jsp">
                                    <div class="menu-item__student">
                                        <span class="container-1__menu-items menu-item__order">

                                            <i class="fa-solid fa-credit-card"></i>
                                            <span>Kiểu thanh toán</span>
                                        </span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <div class="log-out">
                            <a href="../html-authentication/sign-in.jsp">
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
                        </div>

                        <div class="container-2__body">
                            <div class="container-2__filter">
                                <div class="filter__selection">
                                    <div class="filter__selection-input">
                                        <div class="filter__selection-items filter__selection-name">
                                            <div class="filter__selection-title filter__item-name">Tên người dùng:</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Số điện thoại:</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Từ ngày:</div>
                                            <input placeholder="" type="datetime-local" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items-select">
                                            <div class="filter__selection-title filter__item-phone">Vai trò:</div>
                                            <select name="Level" class="combobox admin-input__short">
                                                <option value="">Tất cả</option>
                                                <option value="1">Người dùng</option>
                                                <option value="2">Quản trị viên</option>
                                            </select>
                                        </div>

                                    </div>

                                    <div class="filter__button-search">
                                        <button class="button dark-button" type="submit">
                                            <i class="fa-solid fa-magnifying-glass"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="container-2__list-student">
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
                                                    <c:when test="${user.role == 'admin'}">
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
<%--                                                        ${user.created_at}--%>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <a href="#user-detail">
                                                    <span class="icon-action"><i class="fa-solid fa-eye"></i></span>
                                                </a>
                                                <a href="">
                                                    <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                                </a>
                                                <a href="">
                                                    <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                                </a>
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
                        <div class="course__header">
                            <div class="course__title">USER DETAIL</div>
                            <div class="x__icon">
                                <a href="#" class=""><i class="fa-solid fa-x"></i></a>
                            </div>
                        </div>

                        <div class="course-body">
                            <div class="create__selection-input">
                                <div class="create__selection-items">
                                    <div class="filter__item-name">Order index:</div>
                                    <input placeholder="" type="text" class="input__create">
                                </div>
                                <div class="create__selection-items--wide">
                                    <div class="create__selection-items">
                                        <div class="filter__item-phone">Course ID:</div>
                                        <input placeholder="" type="text" class="input__create">
                                    </div>
                                    <div class="create__selection-items">
                                        <div class="filter__item-phone">Title:</div>
                                        <input placeholder="" type="text" class="input__create">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>