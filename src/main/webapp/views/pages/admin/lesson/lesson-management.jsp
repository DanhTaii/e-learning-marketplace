<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson Management</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Admin Layout Css--%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/lesson/lesson-management.css?v=<%=System.currentTimeMillis()%>">

    <%--  Admin Component Css  --%>
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/action-bar.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

    <%-- Javascript --%>
    <script src="assets/javascript/admin/lesson/action-bar.js?v=<%=System.currentTimeMillis()%>"></script>
    <script src="assets/javascript/utils/admin-filter.js?v=<%=System.currentTimeMillis()%>"></script>

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
                                    Bài học
                                    <div class="header__meta">
                                        <span class="header__subtitle">
                                                Quản lý tất cả bài học trên hệ thống
                                        </span>
                                        <span class="header__count">
                                                ${totalLessons} bài học
                                        </span>
                                    </div>
                                </div>
                                <div class="admin-create__buttons">
                                    <button type="button" class="dark-button">
                                        <a href="admin/lesson/detail" class="admin-create-link">
                                            <i class="fa-solid fa-plus"></i>Tạo mới
                                        </a>
                                    </button>
                                </div>
                            </div>
                            <div class="container-2__body">
                                <form method="get" action="admin/lessons" class="advanced-filter" id="filterForm">
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
                                                <label>Tìm kiếm bài học</label>
                                                <div class="input-with-icon">
                                                    <i class="fa-solid fa-magnifying-glass"></i>
                                                    <input type="text" name="searchName" value="${param.searchName}"
                                                           placeholder="Nhập tên bài học...">
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
                                                        Bản nháp
                                                    </option>
                                                </select>
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

                                            <div class="filter-group">
                                                <label>Đến ngày</label>
                                                <input type="date" name="toDate" value="${param.toDate}">
                                            </div>

                                            <div class="filter-group">
                                                <label>&nbsp;</label>
                                                <div class="checkbox-group">
                                                    <label class="checkbox-container">
                                                        <input type="checkbox" name="missingVideo" ${param.missingVideo != null ? 'checked' : ''}> Thiếu Video
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="filter-actions">
                                            <a href="admin/lessons" class="btn-clear">
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
                                            <th>TÊN BÀI HỌC</th>
                                            <%--                                            <th>KHÓA HỌC</th>--%>
                                            <th>THỜI LƯỢNG</th>
                                            <th>NGÀY TẠO</th>
                                            <th>VIDEO</th>
                                            <th>TRẠNG THÁI</th>
                                            <th>THAO TÁC</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="lesson" items="${listLessons}">
                                            <tr>
                                                <td><input type="checkbox" class="lesson-checkbox" value="${lesson.id}">
                                                </td>
                                                <td>
                                                    <div class="lesson-info">
                                                        <div class="lesson-icon"><i class="fa-solid fa-play"></i></div>
                                                        <div class="lesson-text">
                                                            <div class="lesson-name">${lesson.title}</div>
                                                            <div class="lesson-sub">Chương ${lesson.orderIndex} •
                                                                Bài ${lesson.orderIndex}</div>
                                                        </div>
                                                    </div>
                                                </td>
                                                    <%--                                                <td class="course-name">Soft Skills Masterclass</td>--%>
                                                <td class="text-bold">${lesson.durationMinutes}:00</td>
                                                <td class="text-light">
                                                    <fmt:formatDate value="${lesson.createdAt}" pattern="dd/MM/yyyy"/>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${lesson.videoUrl != null && lesson.videoUrl != ''}">
                                                            <i class="fa-solid fa-circle-check icon-success"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="fa-solid fa-circle-exclamation icon-danger"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${lesson.status eq 'ACTIVE'}">
                                                            <span class="badge course-row__status-public">Hoạt động</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge course-row-status-unactive">Bản nháp</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="action-btns">
                                                    <a href="admin/lesson/detail?id=${lesson.id}" class="js-edit-link">
                                                        <button type="button" class="icon-action-btn"><i
                                                                class="fa-solid fa-pen"></i></button>
                                                    </a>
                                                    <button onclick="openConfirmModal(${lesson.id})"
                                                            class="icon-action-btn">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <jsp:include page="/views/components/bulk-action-bar.jsp">
                                        <jsp:param name="label" value="bài học"/>
                                        <jsp:param name="showDuplicate" value="true"/>
                                    </jsp:include>
                                </div>

                                <jsp:include page="/views/components/pagination-base.jsp">
                                    <jsp:param name="baseUrl" value="admin/lessons"/>
                                    <jsp:param name="currentPageNumber" value="${filter.page}"/>
                                    <jsp:param name="totalPages" value="${totalPages}"/>
                                </jsp:include>
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
        <p style="font-size: 1.6rem">Bạn có chắc chắn muốn xóa bài học này không?</p>
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
<form id="delete-form-id" action="admin/lesson/delete"
      method="POST"
      class="form"
      style="display: none">
    <input id="input-delete-id" type="hidden" name="id">
</form>
<jsp:include page="/views/components/toast.jsp"/>
</body>
</html>