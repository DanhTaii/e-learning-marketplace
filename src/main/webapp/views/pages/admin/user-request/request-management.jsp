<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Yêu cầu</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Admin layout CSS --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet"
          href="assets/css/admin/pages/category/category-management.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/pages/user-request/request.css?v=${applicationScope.assetVersion}">

    <%-- Admin component CSS --%>
    <link rel="stylesheet" href="assets/css/admin/notification.css">
    <link rel="stylesheet" href="assets/css/admin/component/action-bar.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=${applicationScope.assetVersion}">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
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
                                    Yêu cầu
                                    <div class="header__meta">
                                        <span class="header__subtitle">
                                            Quản lý tất cả yêu cầu
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="container-2__body">
                                <form method="get" action="admin/requests" class="advanced-filter" id="filterForm">
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
                                                <label>Email</label>

                                                <div class="input-with-icon">
                                                    <i class="fa-solid fa-magnifying-glass"></i>

                                                    <input type="text"
                                                           name="email"
                                                           value="${param.email}"
                                                           placeholder="Nhập email...">
                                                </div>
                                            </div>

                                            <div class="filter-group">
                                                <label>Tiêu đề</label>

                                                <input type="text"
                                                       name="subject"
                                                       value="${param.subject}"
                                                       placeholder="Nhập tiêu đề...">
                                            </div>

                                            <div class="filter-group">
                                                <label>Từ ngày</label>

                                                <input type="date"
                                                       name="fromDate"
                                                       value="${param.fromDate}">
                                            </div>

                                            <div class="filter-group">
                                                <label>Đến ngày</label>

                                                <input type="date"
                                                       name="toDate"
                                                       value="${param.toDate}">
                                            </div>

                                            <div class="filter-group">
                                                <label>Trạng thái</label>

                                                <select name="status">

                                                    <option value=""
                                                    ${empty param.status ? 'selected' : ''}>
                                                        Tất cả
                                                    </option>

                                                    <option value="PENDING"
                                                    ${param.status == 'PENDING' ? 'selected' : ''}>
                                                        Chờ xử lý
                                                    </option>

                                                    <option value="IN_PROGRESS"
                                                    ${param.status == 'IN_PROGRESS' ? 'selected' : ''}>
                                                        Đang xử lý
                                                    </option>

                                                    <option value="RESOLVED"
                                                    ${param.status == 'RESOLVED' ? 'selected' : ''}>
                                                        Đã xử lý
                                                    </option>

                                                    <option value="REJECTED"
                                                    ${param.status == 'REJECTED' ? 'selected' : ''}>
                                                        Từ chối
                                                    </option>

                                                </select>
                                            </div>

                                        </div>

                                        <div class="filter-actions">

                                            <a href="admin/requests" class="btn-clear">
                                                <i class="fa-solid fa-rotate-left"></i>
                                                Đặt lại
                                            </a>

                                            <button type="submit" class="dark-button btn-submit">
                                                Áp dụng bộ lọc
                                            </button>

                                        </div>

                                    </div>
                                </form>
                                <form id="bulkActionForm" method="POST" action="admin/requests">
                                    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                                    <input type="hidden" name="action" id="bulkActionInput" value="">

                                    <div class="container-2__dynamic-content" id="requestTableBody">
                                        <jsp:include page="/views/pages/admin/user-request/request-fragment.jsp"/>
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
<div class="sidebar-overlay" id="sidebar-overlay"></div>

<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/utils/admin-filter.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/utils/admin-toggle-sidebar.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/component/bulk-action.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/component/selection.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/admin/user_request/request-management.js?v=${applicationScope.assetVersion}"></script>
</body>

</html>
