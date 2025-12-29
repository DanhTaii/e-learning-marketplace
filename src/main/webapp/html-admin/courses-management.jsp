<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quản lý khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.1">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/dashboard.css">
    <link rel="stylesheet" href="assets/css-admin/courses-management.css">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-2 container-1">
                    <div class="container-1__logo">
                        <i class="fa-solid fa-graduation-cap"></i>
                        <span>Softskill</span>
                    </div>
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
                            <div class="header__title">Các khóa học</div>
                            <div class="admin-create__buttons">
                                <button type="button" class="dark-button">
                                    <a href="html-admin/course-create.jsp">
                                        <i class="fa-solid fa-plus"></i>Tạo mới
                                    </a>
                                </button>
                            </div>
                        </div>

                        <div class="container-2__body">
                            <form action="admin/course/search" method="GET">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title  filter__item-label">Tên khóa học:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="courseTitle" value="${param.courseTitle}">
                                            </div>
                                            <div class="filter__selection-items">
                                                <div class="filter__selection-title">Từ ngày</div>
                                                <input placeholder="" type="date" class="admin-input__long"
                                                       name="dateFrom" value="${param.dateFrom}">
                                            </div>
                                            <div class="filter__selection-items-select">
                                                <div class="filter__selection-title">Trạng thái:</div>
                                                <select name="isPublic" class="combobox admin-input__short">
                                                    <option value="" ${empty param.isPublic ? 'selected' : ''}>Tất cả
                                                    </option>
                                                    <option value="public" ${param.isPublic == 'public' ? 'selected' : ''}>
                                                        Công khai
                                                    </option>
                                                    <option value="private" ${param.isPublic == 'private' ? 'selected' : ''}>
                                                        Riêng tư
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="filter__selection-items-select">
                                                <div class="filter__selection-title">Cấp độ:</div>
                                                <select name="level" class="combobox admin-input__short">
                                                    <option value="" ${empty param.level ? 'selected' : ''}>Tất cả
                                                    </option>
                                                    <option value="beginner" ${param.level == 'beginner' ? 'selected' : ''}>
                                                        Người mới
                                                    </option>
                                                    <option value="intermediate" ${param.level == 'intermediate' ? 'selected' : ''}>
                                                        Trung cấp
                                                    </option>
                                                    <option value="advanced" ${param.level == 'advanced' ? 'selected' : ''}>
                                                        Nâng cao
                                                    </option>
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
                            </form>

                            <div class="container-2__list-student">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Khóa học</th>
                                        <th>Thời lượng</th>
                                        <th>Học viên</th>
                                        <th>Giá</th>
                                        <th>Level</th>
                                        <th>Trạng thái</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="course" items="${listCourses}">
                                        <tr class="course-row">
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${course.title}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__duration course-row__font-content">
                                                        ${course.durationHours}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__total__enrollment course-row__font-content">${course.studentCount}</div>
                                            </td>
                                            <td>
                                                <div class="course-row__price course-row__font-content">${course.price}</div>
                                            </td>
                                            <td>
                                                <div class="course-row__level course-row__font-content">
                                                    <div class="level-dot"></div>
                                                        ${course.level}
                                                </div>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${course.isPublic}">
                                                        <div class="course-row__status course-row__font-content course-row__status-public">
                                                            Công khai
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="course-row__status course-row__font-content course-row__status-private">
                                                            Riêng tư
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate
                                                            value="${course.createdAt}"
                                                            pattern="MMMM d, yyyy"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <button type="button" onclick="showCategoryDetail(${cate.id})"
                                                        class="icon-action-btn">
                                                    <i class="fa-solid fa-pen"></i>
                                                </button>
                                                <form action="admin/category/delete" method="post" class="form">
                                                    <input type="hidden" name="id" value="${cate.id}">
                                                    <button type="submit" class="icon-action-btn">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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