<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>

<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quản lý khóa học</title>
    <base href="${pageContext.request.contextPath}/">

    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/course/courses-management.css?v=<%=System.currentTimeMillis()%>">

    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
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
                                    Khóa học
                                    <div class="header__meta">
                                        <span class="header__subtitle">
                                                Quản lý tất cả khóa học
                                        </span>
                                        <span class="header__count">
                                                <c:out value="${totalAllCourses}"/> khóa học
                                        </span>
                                    </div>
                                </div>
                                <div class="admin-create__buttons">
                                    <a href="admin/courses/archive" class="outline-button">
                                        <i class="fa-solid fa-box-archive"></i>
                                        <span>Kho lưu trữ</span>
                                    </a>
                                    <button type="button" class="dark-button">
                                        <a href="admin/course/editor">
                                            <i class="fa-solid fa-plus"></i>Tạo mới
                                        </a>
                                    </button>
                                </div>
                            </div>

                            <div class="container-2__body">
                                <form action="admin/courses" method="GET" class="advanced-filter" id="filterForm">

                                    <script>
                                        if (localStorage.getItem('admin_filter_status') === 'closed') {
                                            document.getElementById('filterForm').classList.add('collapsed');
                                        }
                                    </script>

                                    <!-- HEADER -->
                                    <div class="filter-header" onclick="toggleFilter()">
                                        <h2 class="filter-title">
                                            <i class="fa-solid fa-filter"></i>
                                            Bộ lọc nâng cao
                                        </h2>
                                        <div class="filter-toggle-icon" id="toggleIcon">
                                            <i class="fa-solid fa-sliders"></i>
                                        </div>
                                    </div>

                                    <!-- CONTENT -->
                                    <div class="filter-content" id="courseFilterContent">
                                        <div class="filter-grid">

                                            <!-- Tên khóa học -->
                                            <div class="filter-group">
                                                <label>Tên khóa học</label>
                                                <div class="input-with-icon">
                                                    <i class="fa-solid fa-magnifying-glass"></i>
                                                    <input type="text"
                                                           name="courseTitle"
                                                           value="${param.courseTitle}"
                                                           placeholder="Nhập tên khóa học...">
                                                </div>
                                            </div>

                                            <!-- Từ ngày -->
                                            <div class="filter-group">
                                                <label>Từ ngày</label>
                                                <input type="date"
                                                       name="fromDate"
                                                       value="${param.dateFrom}">
                                            </div>

                                            <!-- Trạng thái -->
                                            <div class="filter-group">
                                                <label>Trạng thái</label>
                                                <select name="isPublic">
                                                    <option value="" ${empty param.isPublic ? 'selected' : ''}>Tất cả</option>
                                                    <option value="public" ${param.isPublic == 'public' ? 'selected' : ''}>Công khai</option>
                                                    <option value="private" ${param.isPublic == 'private' ? 'selected' : ''}>Riêng tư</option>
                                                </select>
                                            </div>

                                            <!-- Danh mục -->
                                            <div class="filter-group">
                                                <label>Thuộc danh mục</label>
                                                <select name="categoryId">
                                                    <option value="">Tất cả danh mục</option>
                                                    <c:forEach var="c" items="${listCategories}">
                                                        <option value="${c.id}" ${param.categoryId == c.id ? 'selected' : ''}><c:out value="${c.name}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <!-- Đến ngày -->
                                            <div class="filter-group">
                                                <label>Đến ngày</label>
                                                <input type="date"
                                                       name="toDate"
                                                       value="${param.dateTo}">
                                            </div>

                                            <!-- Cấp độ -->
                                            <div class="filter-group">
                                                <label>Cấp độ</label>
                                                <select name="level">
                                                    <option value="" ${empty param.level ? 'selected' : ''}>Tất cả</option>
                                                    <option value="beginner" ${param.level == 'beginner' ? 'selected' : ''}>Sơ cấp</option>
                                                    <option value="intermediate" ${param.level == 'intermediate' ? 'selected' : ''}>Trung cấp</option>
                                                    <option value="advanced" ${param.level == 'advanced' ? 'selected' : ''}>Cao cấp</option>
                                                </select>
                                            </div>
                                        </div>

                                        <!-- ACTIONS -->
                                        <div class="filter-actions">
                                            <a href="admin/courses" class="btn-clear">
                                                <i class="fa-solid fa-rotate-left"></i> Đặt lại
                                            </a>
                                            <button type="submit" class="dark-button btn-submit">
                                                Áp dụng bộ lọc
                                            </button>
                                        </div>
                                    </div>
                                </form>

                                <form action="admin/courses" method="POST" id="bulkActionForm">
                                    <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                                    <input type="hidden" name="action" id="bulkActionInput" value="">

                                    <input id="deleteReasonId" type="hidden" name="deleteReason" value="">

                                    <%-- LẤY RA CÁC PARAMS NGƯỜI ĐANG NHẬP HIỆN TẠI --%>
                                    <input id="currentQueryId" type="hidden" name="currentQuery" value="${pageContext.request.queryString}">

                                    <div class="container-2__dynamic-content" id="courseTableBody">
                                        <jsp:include page="/views/pages/admin/course/course-fragment.jsp"/>
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
<jsp:include page="/views/components/modal-confirm.jsp"/>
<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/admin/course/course-management.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/pagination/base-pagination.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/formatter/base.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/bulk-action.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/selection.js?v=<%=System.currentTimeMillis()%>"></script>
</body>


</html>