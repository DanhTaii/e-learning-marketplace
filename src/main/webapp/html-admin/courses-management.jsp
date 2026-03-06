<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quản lý khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css-admin/notification.css?v=1.0.1">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/dashboard.css">
    <link rel="stylesheet" href="assets/css-admin/courses-management.css">
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
                            <div class="header__title">Các khóa học</div>
                            <div class="admin-create__buttons">
                                <button type="button" class="dark-button">
                                    <a href="admin/course/detail">
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
                                                        Sơ cấp
                                                    </option>
                                                    <option value="intermediate" ${param.level == 'intermediate' ? 'selected' : ''}>
                                                        Trung cấp
                                                    </option>
                                                    <option value="advanced" ${param.level == 'advanced' ? 'selected' : ''}>
                                                        Cao cấp
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

                                <div class="admin-table-responsive">
                                    <table id="admin-course-table">
                                        <thead>
                                        <tr>
                                            <th>Khóa học</th>
                                            <th>Thời lượng</th>
                                            <th>Học viên</th>
                                            <%--                                        <th>Giá</th>--%>
                                            <th>Cấp độ</th>
                                            <th>Trạng thái</th>
                                            <th>Ngày tạo</th>
                                            <th>Hành động</th>
                                        </tr>
                                        </thead>
                                        <tbody id="admin-course-table-body">
<%--                                        <c:if test="${empty listCourses}">--%>
<%--                                            <tr>--%>
<%--                                                <td colspan="7"> &lt;%&ndash; Số 7 này tương ứng với 7 cột của bảng &ndash;%&gt;--%>
<%--                                                    <div class="search-empty-state"--%>
<%--                                                         style="text-align: center; padding: 40px 0;">--%>
<%--                                                        <i class="fa-solid fa-book-open search-empty-icon"--%>
<%--                                                           style="font-size: 3rem; color: #ccc;"></i>--%>
<%--                                                        <div class="search-empty-title"--%>
<%--                                                             style="font-size: 1.8rem; font-weight: bold; margin-top: 15px;">--%>
<%--                                                            Không tìm thấy khóa học nào--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
<%--                                        </c:if>--%>
                                        </tbody>
                                    </table>

                                    <template id="course-row-template">
                                        <tr class="course-row">
                                            <td><div class="course-row__title title course-row__style-text js-title"></div></td>
                                            <td><div class="course-row__duration js-duration"></div></td>
                                            <td><div class="course-row__total__enrollment js-enrollment"></div></td>
                                            <td><div class="course-row__level js-level"></div></td>
                                            <td><div class="course-row__status js-status"></div></td>
                                            <td><div class="course-row__created js-created"></div></td>
                                            <td class="action__button">
                                                <div class="action-wrapper">
                                                    <a href="" class="js-edit-link">
                                                        <button type="button" class="icon-action-btn"><i class="fa-solid fa-pen"></i></button>
                                                    </a>
                                                    <button type="button" class="icon-action-btn js-delete-btn">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </template>

                                    <jsp:include page="/pagination/common-templates.jsp"/>
                                </div>

                                <div class="admin-pagination-container">
                                    <div class="admin-pagination-wrapper">
                                        <ul id="admin-pagination-list" class="pagination home-product__pagination">
                                        </ul>
                                        <div id="pagination-info-text" class="pagination-info">
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
</div>

<%--COMPONENT CONFIRM FOR DELETE--%>
<div id="confirm-delete-modal" class="modal"
     style="display: none; position: fixed; z-index: 1001; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); align-items: center; justify-content: center;">
    <div class="modal-content"
         style="background: white; padding: 25px; border-radius: 8px; width: 350px; text-align: center;">
        <h3 style="color: #dc3545; font-size:1.8rem "><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p style="font-size: 1.6rem">Bạn có chắc chắn muốn xóa khóa học này không?</p>
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
<%--DELETE ACTION--%>
<form id="delete-form-id" action="admin/course/delete" method="post" class="form" style="display: none">
    <input id="input-delete-id" type="hidden" name="id">
</form>
<%--NOTIFICATION ACTION--%>
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
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/pagination/course/course-pagination.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/pagination/base-pagination.js?v=<%=System.currentTimeMillis()%>"></script>
</html>