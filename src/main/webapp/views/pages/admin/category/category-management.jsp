<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh mục</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Admin layout CSS --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/course-edit.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/category/category-management.css?v=<%=System.currentTimeMillis()%>">

    <%-- Admin component CSS --%>
    <link rel="stylesheet" href="assets/css/admin/notification.css">
    <link rel="stylesheet" href="assets/css/admin/component/action-bar.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">

                <jsp:include page="/views/layouts/admin/sidebar-admin.jsp"/>

                <div class="grid__column-10 container-2">

                    <jsp:include page="/views/layouts/admin/header-admin.jsp"/>

                    <div class="container-2__content-body">
                        <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">Danh mục
                                <div class="header__meta">
                                    <span class="header__subtitle">
                                        Quản lý tất cả danh mục
                                    </span>
                                    <span class="header__count">
                                        ${listCategories.size()} danh mục
                                    </span>
                                </div>
                            </div>
                            <div class="admin-create__buttons">
                                <button type="button" class="dark-button">
                                    <a href="admin/category/detail" class="admin-create-link">
                                        <i class="fa-solid fa-plus"></i>Tạo mới
                                    </a>
                                </button>
                            </div>
                        </div>
                        <div class="container-2__body">
                            <form method="get" action="admin/categories" class="advanced-filter" id="filterForm">
                                <div class="filter-header" onclick="toggleFilter()">
                                    <h2 class="filter-title">
                                        <i class="fa-solid fa-filter"></i>
                                        Bộ lọc nâng cao
                                    </h2>
                                    <div class="filter-toggle-icon" id="toggleIcon">
                                        <i class="fa-solid fa-sliders"></i>
                                    </div>
                                </div>

                                <div class="filter-content" id="filterContent">
                                    <div class="filter-grid">
                                        <div class="filter-group">
                                            <label>Tìm kiếm danh mục</label>
                                            <div class="input-with-icon">
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                                <input type="text" name="searchName" value="${param.searchName}"
                                                       placeholder="Nhập tên danh mục...">
                                            </div>
                                        </div>

                                        <div class="filter-group">
                                            <label>Từ ngày</label>
                                            <input type="date" name="fromDate" value="${param.fromDate}">
                                        </div>

                                        <div class="filter-group">
                                            <label>Trạng thái</label>
                                            <select name="status">
                                                <option value="" ${empty param.status ? 'selected' : ''}>Tất cả
                                                </option>
                                                <option value="ACTIVE" ${param.status == 'ACTIVE' ? 'selected' : ''}>
                                                    Hoạt động
                                                </option>
                                                <option value="INACTIVE" ${param.status == 'INACTIVE' ? 'selected' : ''}>
                                                    Không hoạt động
                                                </option>
                                            </select>
                                        </div>

                                        <div class="filter-group">
                                            <label>Tên slug</label>
                                            <input type="text" name="searchName" value=""
                                                   placeholder="Nhập tên slug...">
                                        </div>

                                        <div class="filter-group">
                                            <label>Đến ngày</label>
                                            <input type="date" name="toDate" value="${param.toDate}">
                                        </div>

                                        <div class="filter-group">
                                            <label>Parent ID</label>
                                                <input type="number" name="parentId" class="input-modern" id="parentId"
                                                       value="${category != null ? category.parentId : ''}"
                                                       placeholder="Ví dụ: 0" min="0">
                                        </div>
                                    </div>
                                    <div class="filter-actions">
                                        <a href="admin/categories" class="btn-clear">
                                            <i class="fa-solid fa-rotate-left"></i> Đặt lại
                                        </a>
                                        <button type="submit" class="dark-button btn-submit">Áp dụng bộ lọc</button>
                                    </div>
                                </div>
                            </form>

                            <div class="container-2__list-student">

                                <table class="modern-table">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="selectAll"></th>
                                        <th>TÊN DANH MỤC</th>
                                        <th>SLUG</th>
                                        <th>PARENT ID</th>
                                        <th>NGÀY TẠO</th>
                                        <th>TRẠNG THÁI</th>
                                        <th>THAO TÁC</th>
                                    </tr>
                                    </thead>

                                    <tbody id="categoryTableBody">
                                    <jsp:include page="/views/pages/admin/category/category-table-body.jsp"/>
                                    <c:if test="${empty listCategories}">
                                        <tr>
                                            <td colspan="7">
                                                <div class="search-empty-state">
                                                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                    <div class="search-empty-title">
                                                        Không tìm thấy danh mục nào
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                                <jsp:include page="/views/components/bulk-action-bar.jsp">
                                    <jsp:param name="label" value="danh mục"/>
                                </jsp:include>
                            </div>
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
                                    <button type="button" class="button btn-cancel" onclick="closeModal()">
                                        Hủy
                                    </button>
                                    <button type="submit" class="button dark-button">
                                        Lưu thay đổi
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
<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/admin/category/admin-category-detail.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/admin/category/category-action-bar.js?v=<%=System.currentTimeMillis()%>"></script>
</body>

</html>