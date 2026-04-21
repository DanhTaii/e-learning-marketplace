<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tag Management</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Admin layout CSS --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/tag/tag-management.css?v=<%=System.currentTimeMillis()%>">

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
                                    Thẻ
                                    <div class="header__meta">
                                        <span class="header__subtitle">Quản lý tất cả thẻ</span>
                                        <span class="header__count">${listTags.size()} thẻ</span>
                                    </div>
                                </div>

                                <div class="admin-create__buttons">
                                    <button type="button" class="dark-button">
                                        <a href="admin/tag/detail" class="admin-create-link">
                                            <i class="fa-solid fa-plus"></i>Tạo mới
                                        </a>
                                    </button>
                                </div>
                            </div>

                            <div class="container-2__body">

                                <form method="get" action="admin/tags" class="advanced-filter" id="filterForm">
                                    <div class="filter-header">
                                        <h2 class="filter-title">
                                            <i class="fa-solid fa-filter"></i>
                                            Bộ lọc
                                        </h2>
                                    </div>

                                    <div class="filter-content">
                                        <div class="filter-grid">
                                            <div class="filter-group">
                                                <label>Tìm kiếm thẻ</label>
                                                <input type="text" name="searchName"
                                                       value="${param.searchName}"
                                                       placeholder="Nhập tên thẻ...">
                                            </div>
                                        </div>

                                        <div class="filter-actions">
                                            <a href="admin/tags" class="btn-clear">Đặt lại</a>
                                            <button type="submit" class="dark-button btn-submit">
                                                Áp dụng
                                            </button>
                                        </div>
                                    </div>
                                </form>

                                <div id="tagTableBody">
                                    <jsp:include page="/views/pages/admin/tag/tag-fragment.jsp"/>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
<jsp:include page="/views/components/confirm-delete.jsp"/>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/bulk-action.js?v=<%=System.currentTimeMillis()%>"></script>
</html>