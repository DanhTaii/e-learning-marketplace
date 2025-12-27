<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson Management</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.3">
    <link rel="stylesheet" href="assets/css-admin/notification.css?v=1.0.1">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
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
                    <div class="container-1__title">Softskill</div>
                    <div class="container-1__menu">
                        <ul>
                            <li>
                                <a href="admin/dashboard">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/users">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/courses">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/lessons">
                                    <div class="menu-item__student student-list">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/tags">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/categories">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/orders">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/payment-methods">
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
                            <div class="header__title">Bài học</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo bài học mới</div>
                            <form action="admin/lessons" method="post" class="form">
                                <div class="container-2__create">
                                    <div class="create__selection">
                                        <div class="create__selection-input">

                                            <div class="create__selection-items--wide">
                                                <div class="create__selection-items">
                                                    <div class="filter__selection-title filter__item-phone">Tên hoặc ID
                                                        khóa
                                                        học:
                                                    </div>
                                                    <select class="admin-input__long" name="idCourse">
                                                        <option class="text-medium">--- Vui lòng chọn khóa học ---
                                                        </option>
                                                        <c:forEach var="c" items="${listCourse}">
                                                            <option class="text-medium"
                                                                    value="${c.id}">${c.title}</option>

                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="create__selection-items">
                                                    <div class="filter__selection-title filter__item-phone">Tên bài
                                                        học:
                                                    </div>
                                                    <input placeholder="" type="text" class="admin-input__long"
                                                           name="nameLesson">
                                                </div>

                                            </div>
                                        </div>
                                        <div class="create__selection-input">
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Thời lượng:</div>
                                                <input placeholder="" type="number" class="admin-input__long"
                                                       name="duration_minutesLesson">
                                            </div>
                                            <div class="create__selection-items--wide">
                                                <div class="create__selection-items">
                                                    <div class="filter__selection-title filter__item-phone">Video URL:
                                                    </div>
                                                    <input placeholder="" type="text" class="admin-input__long"
                                                           name="urlVideo">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="create__btn-create">
                                            <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="title__admin">Tất cả bài học</div>
                            <form method="get" class="form" action="admin/lesson/search">
                            <div class="container-2__filter">
                                <div class="filter__selection">
                                    <div class="filter__selection-input">
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Tên bài học:</div>
                                            <input placeholder="" type="text"
                                                   class="input__font admin-input__long" name="searchName" value="${param.searchName}">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-items-select">
                                                <div class="filter__selection-title filter__item-phone">Tên khóa
                                                    học:
                                                </div>
                                                <select name="Level" class="combobox admin-input__short ">
                                                    <option class="text-medium">--- Vui lòng chọn khóa học ---
                                                    </option>
                                                    <c:forEach var="c" items="${listCourse}">
                                                        <option class="text-medium" value="${c.id}">${c.title}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
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
                                        <th>Tên bài học</th>
                                        <th>Số thứ tự</th>
                                        <th>Thời lượng</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="lesson" items="${listLessons}">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${lesson.title}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${lesson.orderIndex}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                    ${lesson.durationMinutes} min
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate
                                                            value="${lesson.createdAt}"
                                                            pattern="MMMM d, yyyy – h:mm a"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <a href="">
                                                    <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                                </a>
                                                <form id="delete-form-${lesson.id}" action="admin/lesson/delete" method="POST"
                                                      class="form">
                                                <input type="hidden" name="id" value="${lesson.id}">
                                                <button type="button" class="icon-action-btn"
                                                        onclick="openConfirmModal(${lesson.id})">
                                                    <i
                                                            class="fa-solid fa-trash"></i>
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
<div id="confirm-delete-modal" class="modal"
     style="display: none; position: fixed; z-index: 1001; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); align-items: center; justify-content: center;">
    <div class="modal-content"
         style="background: white; padding: 25px; border-radius: 8px; width: 350px; text-align: center;">
        <h3 style="color: #dc3545; font-size:1.8rem "><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p style="font-size: 1.6rem">Bạn có chắc chắn muốn xóa bài học này không?</p>
        <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
            <button onclick="closeModal('confirm-delete-modal')" class="button btn-cancel" style="padding: 8px 20px;">
                Hủy
            </button>
            <button id="btn-confirm-delete" class="button dark-button"
                    style="background-color: #dc3545; padding: 8px 20px;">Xóa ngay
            </button>
        </div>
    </div>
</div>
<div id="toast"></div>

</body>
<script>
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>

</script>
<script src="assets/javascript/js-admin/admin-tag-detail.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>
</html>