<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh mục</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.4">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/course-edit.css">
    <link rel="stylesheet" href="assets/css-admin/notification.css">
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
                            <div class="header__title">Danh mục</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo danh mục</div>
                            <form action="admin/categories" class="form" method="post">
                                <div class="container-2__create">
                                    <div class="create__selection">
                                        <div class="create__selection-input">
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Tên danh mục:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="categoryName" value="${param.categoryName}">
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">ID danh mục cha:
                                                </div>
                                                <input placeholder="" type="number" class="admin-input__long"
                                                       name="categoryParentId">
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Slug:</div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="categorySlug">
                                            </div>
                                        </div>
                                        <div class="create__btn-create">
                                            <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="title__admin">Tất cả danh mục (${listCategories.size()})</div>
                            <form action="admin/categories/search" class="form" method="get">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên danh mục:
                                                </div>
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
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th>ID danh mục cha</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="cate" items="${listCategories}">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${cate.id}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${cate.name}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${cate.parentId}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>
                                                    <fmt:formatDate value="${cate.createdAt}"
                                                                    pattern="dd-MM-YYYY"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <div class="action-wrapper">
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
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listCategories}">
                                        <tr>
                                            <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                <div class="search-empty-state"
                                                     style="text-align: center; padding: 40px 0;">
                                                    <i class="fa-solid fa-book-open search-empty-icon"
                                                       style="font-size: 3rem; color: #ccc;"></i>
                                                    <div class="search-empty-title"
                                                         style="font-size: 1.8rem; font-weight: bold; margin-top: 15px;">
                                                        Không tìm thấy danh mục nào
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

                <div id="category-detail" class="modal__course-detail">
                    <div class="modal__course-content">
                        <form action="admin/category/update" method="post">

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

                                    <div class="info-group ">
                                        <label><i class="fa-solid fa-phone"></i> Tên</label>
                                        <input id="detail-name" name="name" type="text" class="input__create">
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-envelope"></i> Slug</label>
                                        <input id="detail-slug" name="slug" type="text" class="input__create">
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-phone"></i> Parent Id</label>
                                        <input id="detail-parentId" name="parentId" type="text" class="input__create">
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-shield-halved"></i> Trạng thái</label>
                                        <select id="detail-status" name="status" class="input__create role-badge">
                                            <option value="ACTIVE">ACTIVE</option>
                                            <option value="INACTIVE">INACTIVE</option>
                                        </select>
                                    </div>

                                    <div class="info-group full-width">
                                        <label><i class="fa-solid fa-phone"></i> Icon</label>
                                        <input id="detail-icon" name="icon" type="text" class="input__create">
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày tạo</label>
                                        <input id="detail-created" type="text" class="input__create" readonly>
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày cập nhật</label>
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
<script src="assets/javascript/ui/notification.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/admin/category/admin-category-detail.js?v=<%=System.currentTimeMillis()%>"></script>
</html>