<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Voucher Achievement</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Admin Layout Css--%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/base/base.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/layouts/archive-default.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/pages/voucher/voucher-management.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/pages/voucher/voucher-archive.css?v=${applicationScope.assetVersion}">

    <%--  Admin Component Css  --%>
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=${applicationScope.assetVersion}">
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
                    <jsp:include page="/views/layouts/admin/header-admin.jsp">
                        <jsp:param name="baseUrl" value=""/>
                    </jsp:include>
                    <div class="container-2__content-body">
                        <div class="grid__row-2 container-2__grid">
                            <div class="container-2__header">
                                <div class="header__title">
                                    <a href="admin/vouchers">
                                        <i class="fa-solid fa-chevron-left bc-separator"></i>
                                    </a>
                                    Lưu trữ mã giảm giá
                                    <div class="header__meta">
                                        <span class="header__subtitle">Quản lý và khôi phục các chương trình khuyến mãi đã xóa hoặc tạm hoãn</span>
                                    </div>
                                </div>

                                <div class="archive-summary-card">
                                    <div class="summary-label">TỔNG LƯU TRỮ</div>
                                    <div class="summary-value"><c:out value="${totalArchived != null ? totalArchived : 0}"/></div>
                                    <div class="summary-footer">
                                        <i class="fa-solid fa-clock-rotate-left"></i> Tự động xóa sau 30 ngày
                                    </div>
                                </div>
                            </div>

                            <div class="container-2__body">
                                <%-- Form bộ lọc nâng cao dành cho voucher --%>
                                <form method="get" action="admin/vouchers/archive" class="advanced-filter" id="filterForm">
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
                                                <label>Tìm kiếm voucher</label>
                                                <div class="input-with-icon">
                                                    <i class="fa-solid fa-magnifying-glass"></i>
                                                    <input type="text" name="searchName" value="${param.searchName}"
                                                           placeholder="Nhập mã hoặc tiêu đề voucher...">
                                                </div>
                                            </div>

                                            <%-- Đã loại bỏ phần chọn Khóa học (Course) không liên quan --%>

                                            <div class="filter-group">
                                                <label>Từ ngày xóa</label>
                                                <input type="date" name="deletedFromDate" value="${param.deletedFromDate}">
                                            </div>

                                            <div class="filter-group">
                                                <label>Đến ngày xóa</label>
                                                <input type="date" name="deletedToDate" value="${param.deletedToDate}">
                                            </div>

                                        </div>
                                        <div class="filter-actions">
                                            <a href="admin/vouchers/archive" class="btn-clear">
                                                <i class="fa-solid fa-rotate-left"></i> Đặt lại
                                            </a>
                                            <button type="submit" class="dark-button btn-submit">Áp dụng bộ lọc</button>
                                        </div>
                                    </div>
                                </form>

                                <%-- Form xử lý hành động hàng loạt (POST) --%>
                                <form id="archiveBulkForm" method="POST" action="admin/vouchers/archive">
                                    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                                    <input type="hidden" name="action" id="bulkActionInput" value="">
                                    <input id="currentQueryId" type="hidden" name="currentQuery" value="${pageContext.request.queryString}">

                                    <div class="container-2__dynamic-content" id="voucherTableBody">
                                        <jsp:include page="/views/pages/admin/voucher/archive/voucher-archive-fragment.jsp"/>
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
<script src="assets/javascript/utils/admin-filter.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/utils/admin-toggle-sidebar.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/component/bulk-action.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/component/selection.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/admin/voucher/voucher-archive.js?v=${applicationScope.assetVersion}"></script>
</body>
</html>