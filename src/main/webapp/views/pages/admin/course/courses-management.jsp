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

    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/course/course-management.css?v=<%=System.currentTimeMillis()%>">

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
                                                       name="dateFrom"
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
                                <div class="container-2__list-student">
                                    <table class="modern-table">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" id="selectAll"></th>
                                            <th>Khóa học</th>
<%--                                            <th>Thời lượng</th>--%>
                                            <th>Học viên</th>
<%--                                            <th>Cấp độ</th>--%>
                                            <th>Trạng thái</th>
                                            <th>Ngày tạo</th>
                                            <th>Hành động</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="course" items="${result.data}">
                                            <tr class="course-row">
                                                <td>
                                                    <input type="checkbox" name="item-checkbox" class="course-checkbox item-checkbox" value="${course.id}">
                                                </td>
                                                <td>
                                                    <div class="content__title">
                                                            ${course.title}
                                                    </div>
                                                    <div class="content__sub-title">Cấp độ: ${course.level.vietnameseName} • ${course.durationText}</div>
                                                </td>
<%--                                                <td>--%>
<%--                                                    <div class="course-row__duration course-row__font-content">--%>
<%--                                                            ${course.durationText}--%>
<%--                                                    </div>--%>
<%--                                                </td>--%>
                                                <td>
                                                    <div class="course-row__total__enrollment course-row__font-content">${course.studentCount}</div>
                                                </td>
<%--                                                <td>--%>
<%--                                                    <div class="course-row__level course-row__font-content">--%>
<%--                                                        <div class="level-dot"></div>--%>
<%--                                                            ${course.level.vietnameseName}--%>
<%--                                                    </div>--%>
<%--                                                </td>--%>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${course.isPublic}">
                                                            <div class="course-row__status course-row__font-content course-row__status-public">
                                                                Công khai
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="course-row__status course-row__font-content course-row__status-private">
                                                                Riêng tư
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <div class="course-row__created course-row__font-content">
                                                        <fmt:setLocale value="en_US" scope="page"/>

                                                        <fmt:formatDate
                                                                value="${course.createdAt}"
                                                                pattern="dd-MM-YYYY"/>
                                                    </div>
                                                </td>
                                                <td class="action__button">
                                                    <div class="action-wrapper">
                                                        <a href="admin/course/detail?id=${course.id}">
                                                            <button type="button"
                                                                    class="icon-action-btn">
                                                                <i class="fa-solid fa-pen"></i>
                                                            </button>
                                                        </a>
                                                        <button type="button" class="icon-action-btn"
                                                                onclick="openConfirmModal(${course.id})">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty result.data}">
                                            <tr>
                                                <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                    <div class="search-empty-state"
                                                         style="text-align: center; padding: 40px 0;">
                                                        <i class="fa-solid fa-book-open search-empty-icon"
                                                           style="font-size: 3rem; color: #ccc;"></i>
                                                        <div class="search-empty-title"
                                                             style="font-size: 1.8rem; font-weight: bold; margin-top: 15px;">
                                                            Không tìm thấy khóa học nào
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                        </tbody>
                                    </table>
                                    <jsp:include page="/views/components/bulk-action-bar.jsp">
                                        <jsp:param name="label" value="bài học"/>
                                        <jsp:param name="showDuplicate" value="true"/>
                                    </jsp:include>
                                </div>

                                <jsp:include page="/views/components/pagination-base.jsp">
                                    <jsp:param name="baseUrl" value="admin/courses"/>
                                    <jsp:param name="currentPageNumber" value="${result.currentPage}"/>
                                    <jsp:param name="totalPages" value="${result.totalPage}"/>
                                </jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/confirm-delete.jsp"/>
<jsp:include page="/views/components/toast.jsp"/>
</body>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/pagination/base-pagination.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/formatter/base.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/bulk-action.js?v=<%=System.currentTimeMillis()%>"></script>

</html>