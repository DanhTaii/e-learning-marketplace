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

    <%-- Management Css --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">

    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/component/action-bar.css?v=<%=System.currentTimeMillis()%>">

    <script src="assets/javascript/admin/lesson/action-bar.js?v=<%=System.currentTimeMillis()%>"></script>

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
                                                ${listLessons.size()} bài học
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

                                <form method="get" action="admin/lessons" class="advanced-filter">
                                    <h2 class="filter-title">Bộ lọc nâng cao</h2>

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
                                            <label>Thuộc khóa học</label>
                                            <select name="courseId">
                                                <option value="">Tất cả khóa học</option>
                                                <c:forEach var="c" items="${listCourse}">
                                                    <option value="${c.id}" ${param.courseId == c.id ? 'selected' : ''}>${c.title}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="filter-group">
                                            <label>Trạng thái</label>
                                            <select name="status">
                                                <option value="">Tất cả trạng thái</option>
                                            </select>
                                        </div>

                                        <div class="filter-group">
                                            <label>Từ ngày</label>
                                            <input type="date" name="fromDate" value="${param.fromDate}">
                                        </div>

                                        <div class="filter-group">
                                            <label>Đến ngày</label>
                                            <input type="date" name="toDate" value="${param.toDate}">
                                        </div>

                                        <div class="filter-group">
                                            <label>&nbsp;</label>
                                            <div class="checkbox-group">
                                                <label class="checkbox-container">
                                                    <input type="checkbox" name="missingVideo"> Thiếu Video
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="filter-actions">
                                        <button type="submit" class="dark-button btn-submit">Áp dụng bộ lọc</button>
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
<%--                                                        <div class="lesson-icon"><i class="fa-solid fa-play"></i></div>--%>
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
                                                        <c:when test="${not empty lesson.videoUrl}">
                                                            <i class="fa-solid fa-circle-check icon-success"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="fa-solid fa-circle-exclamation icon-danger"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
<%--                                                    <span class="badge ${lesson.isPublic ? 'badge-blue' : 'badge-gray'}">--%>
<%--&lt;%&ndash;                                                            ${lesson.isPublic ? 'Công khai' : 'Bản nháp'}&ndash;%&gt;--%>
<%--                                                    </span>--%>
                                                </td>
                                                <td class="action-btns">
                                                    <a href="" class="js-edit-link">
                                                        <button type="button" class="icon-action-btn"><i class="fa-solid fa-pen"></i></button>
                                                    </a>
                                                    <button onclick="openConfirmModal(${lesson.id})" class="icon-action-btn">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <div class="floating-action-bar" id="actionBar">
                                        <div class="action-info">
                                            <span class="count-badge" id="selectedCount">0</span>
                                            <span>Đã chọn bài học</span>
                                        </div>

                                        <div class="action-buttons">
                                            <button class="btn-bar" type="button">
                                                <i class="fa-regular fa-copy"></i> Nhân bản
                                            </button>
                                            <button class="btn-bar" type="button">
                                                <i class="fa-solid fa-arrows-rotate"></i> Đổi trạng thái
                                            </button>
                                            <button class="btn-bar btn-bar-danger" type="button">
                                                <i class="fa-solid fa-trash"></i> Xóa
                                            </button>
                                        </div>

                                        <button class="btn-close-bar" type="button" onclick="deselectAll()">
                                            <i class="fa-solid fa-xmark"></i>
                                        </button>
                                    </div>
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