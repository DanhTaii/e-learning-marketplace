<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>User Management</title>

    <base href="${pageContext.request.contextPath}/">

    <%-- Admin layout CSS --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet"
          href="assets/css/admin/pages/user/user-management.css?v=<%=System.currentTimeMillis()%>">

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
                                <div class="header__title">
                                    Người dùng
                                    <div class="header__meta">
                                        <span class="header__subtitle">
                                            Quản lý tất cả người dùng
                                        </span>
                                        <span class="header__count">
                                            ${listUsers.size()} người dùng
                                        </span>
                                    </div>
                                </div>
                                <div class="admin-create__buttons">
                                    <button type="button" class="dark-button">
                                        <a href="admin/user/detail" class="admin-create-link">
                                            <i class="fa-solid fa-plus"></i>Tạo mới
                                        </a>
                                    </button>
                                </div>
                            </div>

                            <div class="container-2__body">
                                <form method="get" action="admin/users" class="advanced-filter" id="filterForm">
                                    <script>
                                        if (localStorage.getItem('admin_filter_status') === 'closed') {
                                            document.getElementById('filterForm').classList.add('collapsed');
                                        }
                                    </script>
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
                                                <label>Tên người dùng</label>

                                                <input type="text"
                                                       name="username"
                                                       value="${param.username}"
                                                       placeholder="Nhập username...">
                                            </div>

                                            <div class="filter-group">
                                                <label>Email</label>

                                                <input type="text"
                                                       name="email"
                                                       value="${param.email}"
                                                       placeholder="Nhập email...">
                                            </div>

                                            <div class="filter-group">
                                                <label>Vai trò</label>
                                                <select name="roleName">
                                                    <option value="">Tất cả</option>
                                                    <option value="SUPER_ADMIN"
                                                    ${param.roleName == 'SUPER_ADMIN' ? 'selected' : ''}>
                                                        Super Admin
                                                    </option>
                                                    <option value="ADMIN_USER"
                                                    ${param.roleName == 'ADMIN_USER' ? 'selected' : ''}>
                                                        Quản trị người dùng
                                                    </option>
                                                    <option value="ADMIN_COURSE"
                                                    ${param.roleName == 'ADMIN_COURSE' ? 'selected' : ''}>
                                                        Quản trị khóa học
                                                    </option>

                                                    <option value="ADMIN_ORDER"
                                                    ${param.roleName == 'ADMIN_ORDER' ? 'selected' : ''}>
                                                        Quản trị đơn hàng
                                                    </option>
                                                    <option value="STUDENT"
                                                    ${param.roleName == 'STUDENT' ? 'selected' : ''}>
                                                        Người dùng
                                                    </option>
                                                </select>
                                            </div>

                                            <div class="filter-group">
                                                <label>Trạng thái</label>
                                                <select name="status">
                                                    <option value="">Tất cả</option>
                                                    <option value="ACTIVE"
                                                    ${param.status == 'ACTIVE' ? 'selected' : ''}>
                                                        Hoạt động
                                                    </option>
                                                    <option value="INACTIVE"
                                                    ${param.status == 'INACTIVE' ? 'selected' : ''}>
                                                        Bị khóa
                                                    </option>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="filter-actions">
                                            <a href="admin/categories" class="btn-clear">
                                                <i class="fa-solid fa-rotate-left"></i> Đặt lại
                                            </a>
                                            <button type="submit" class="dark-button btn-submit">
                                                Áp dụng bộ lọc
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <form id="bulkActionForm" method="POST" action="admin/users">
                                    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                                    <input type="hidden" name="action" id="bulkActionInput" value="">

                                    <div class="container-2__dynamic-content" id="userTableBody">
                                        <jsp:include page="/views/pages/admin/user/user-fragment.jsp"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
<jsp:include page="/views/components/modal-confirm.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/bulk-action.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/selection.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/admin/user/user-management.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>