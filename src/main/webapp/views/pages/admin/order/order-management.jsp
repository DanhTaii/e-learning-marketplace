<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Order Management</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet"
          href="assets/css/admin/pages/order/order-management.css?v=<%=System.currentTimeMillis()%>">

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
                    <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">
                                Đơn hàng
                                <div class="header__meta">
                                        <span class="header__subtitle">
                                            Quản lý tất cả đơn hàng
                                        </span>
                                    <span class="header__count">
                                            ${totalOrders} đơn hàng
                                        </span>
                                </div>
                            </div>
                        </div>
                        <div class="container-2__body">
                            <form method="get" action="admin/orders" class="advanced-filter" id="filterForm">
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
                                            <label>Mã đơn hàng</label>
                                            <div class="input-with-icon">
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                                <input type="text" name="code"
                                                       value="${param.code}"
                                                       placeholder="Nhập mã đơn hàng...">
                                            </div>
                                        </div>

                                        <div class="filter-group">
                                            <label>Từ ngày</label>
                                            <input type="date" name="fromDate" value="${param.fromDate}">
                                        </div>

                                        <div class="filter-group">
                                            <label>Trạng thái</label>
                                            <select name="status">
                                                <option value="" ${empty param.status ? 'selected' : ''}>Tất cả</option>
                                                <option value="PAID" ${param.status == 'PAID' ? 'selected' : ''}>Thanh toán thành công</option>
                                                <option value="FAILED" ${param.status == 'FAILED' ? 'selected' : ''}>Thanh toán thất bại</option>
                                                <option value="PENDING" ${param.status == 'PENDING' ? 'selected' : ''}>Đang thanh toán</option>
                                            </select>
                                        </div>

                                        <div class="filter-group">
                                            <label>Tên người dùng</label>
                                            <input type="text" name="searchName"
                                                   value="${param.searchName}"
                                                   placeholder="Nhập tên người dùng...">
                                        </div>

                                        <div class="filter-group">
                                            <label>Đến ngày</label>
                                            <input type="date" name="toDate" value="${param.toDate}">
                                        </div>

                                        <div class="filter-group">
                                            <label>Thuộc khóa học</label>
                                            <select name="courseId">
                                                <option value="">Tất cả khóa học</option>
                                                <c:forEach var="c" items="${listCourse}">
                                                    <option value="${c.id}" ${param.courseId == c.id ? 'selected' : ''}>${c.title}</option>
                                                </c:forEach>
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
                            <form id="bulkActionForm" method="POST" action="admin/orders">
                                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                                <input type="hidden" name="action" id="bulkActionInput" value="">

                                <div class="container-2__dynamic-content" id="orderTableBody">
                                    <jsp:include page="/views/pages/admin/order/order-fragment.jsp"/>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/bulk-action.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/selection.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/admin/order/order-management.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>