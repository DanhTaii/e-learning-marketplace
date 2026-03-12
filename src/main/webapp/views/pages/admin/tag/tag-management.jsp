<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tag Management</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.4">
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
                            <div class="header__title">Thẻ</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo thẻ mới</div>
                            <form action="admin/tags" class="form" method="post">

                                <div class="container-2__create">
                                    <div class="create__selection">
                                        <div class="create__selection-input">
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Tên của thẻ:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="nameTag" value="${oldName}">
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Slug:</div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="slugTag" value="${oldSlug}">
                                            </div>

                                        </div>
                                        <div class="create__btn-create">
                                            <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="title__admin">Tất cả thẻ (${listTags.size()})</div>
                            <form action="admin/tags/search" class="form" method="get">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên thẻ:</div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="searchName" value="${param.searchName}">
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
                                        <th>Tên thẻ</th>
                                        <th>Số lượng dùng</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="t" items="${listTags}">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${t.name}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${t.courseCount}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate
                                                            value="${t.createdAt}"
                                                            pattern="dd-MM-YYYY"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <div class="action-wrapper">
                                                    <button type="button" onclick="showTagDetail(${t.id})"
                                                            class="icon-action-btn">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </button>
                                                    <form id="delete-form-${t.id}" action="admin/tags/delete"
                                                          method="POST"
                                                          class="form">

                                                        <input type="hidden" name="id" value="${t.id}">
                                                        <button type="button" class="icon-action-btn"
                                                                onclick="openConfirmModal(${t.id})">
                                                            <i
                                                                    class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listTags}">
                                        <tr>
                                            <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                <div class="search-empty-state"
                                                     style="text-align: center; padding: 40px 0;">
                                                    <i class="fa-solid fa-book-open search-empty-icon"
                                                       style="font-size: 3rem; color: #ccc;"></i>
                                                    <div class="search-empty-title"
                                                         style="font-size: 1.8rem; font-weight: bold; margin-top: 15px;">
                                                        Không tìm thấy thẻ nào
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <div id="tag-detail" class="modal modal__course-detail">
                    <div class="modal__course-content">
                        <form action="admin/tag/update" method="post">

                            <div class="course__header">
                                <div class="course__title">
                                    <i class="fa-solid fa-address-card"></i>
                                    <span id="modal-title"></span>
                                </div>
                                <div class="x__icon" onclick="closeModal('tag-detail')">
                                    <i class="fa-solid fa-xmark"></i>
                                </div>
                            </div>
                            <div class="course-body">
                                <div class="user-info-grid">
                                    <%--                                    Tạm lưu id của user để update--%>
                                    <input id="detail-id" type="text" class="input__create" name="id"
                                           style=" display: none ">
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-user"></i> Tên thẻ</label>
                                        <input id="detail-nameTag" type="text" class="input__create" name="nameTag">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-envelope"></i>Tên slug</label>
                                        <input id="detail-slugTag" type="text" class="input__create" name="slugTag">
                                    </div>


                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày tạo thẻ</label>
                                        <input id="detail-created" type="text" class="input__create" name="">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày cập nhật</label>
                                        <input id="detail-updated" type="text" class="input__create" name="">
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="button btn-cancel" onclick="closeModal('tag-detail')"
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
<div id="confirm-delete-modal" class="modal"
     style="display: none; position: fixed; z-index: 1001; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); align-items: center; justify-content: center;">
    <div class="modal-content"
         style="background: white; padding: 25px; border-radius: 8px; width: 350px; text-align: center;">
        <h3 style="color: #dc3545; font-size:1.8rem "><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p style="font-size: 1.6rem">Bạn có chắc chắn muốn xóa thẻ này không?</p>
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