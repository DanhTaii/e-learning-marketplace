<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Tạo mới khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-course-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/lesson/lesson-create.css?v=<%=System.currentTimeMillis()%>">

    <%--    BODY--%>
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/course/course-edit.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <jsp:include page="/views/layouts/admin/header-course-admin.jsp"/>

            <div class="body__container">
                <div class="course-editor-header">
                    <div class="sub-header-left">
<%--                        <div class="breadcrumb">--%>
<%--                            <a href="admin/courses">Quản lý khóa học</a>--%>
<%--                            <i class="fa-solid fa-chevron-right"></i>--%>
<%--                            <span class="breadcrumb-current-tab">Chỉnh sửa nội dung</span>--%>
<%--                        </div>--%>

                        <h2 class="course-title">
                            <c:out value="${course != null ? course.title : 'Tạo mới khóa học'}"/>
                        </h2>
                    </div>

<%--                    <div class="form-actions mt-4">--%>
<%--                        <div style="display: flex; gap: 10px; flex: 1;">--%>
<%--                            <a href="admin/courses" class="btn-cancel-modern"--%>
<%--                               style="text-decoration: none;">--%>
<%--                                Hủy bỏ--%>
<%--                            </a>--%>

<%--                            <button type="submit" class="btn-submit-modern w-100">--%>
<%--                                <i class="fa-solid fa-floppy-disk"></i>--%>
<%--                                ${(not empty lesson and lesson.id > 0) ? 'Cập nhật' : 'Thêm bài học'}--%>
<%--                            </button>--%>

<%--                            <c:if test="${lesson != null and lesson.id > 0}">--%>
<%--                                <button type="button" class="btn-delete-modern"--%>
<%--                                        onclick="setupConfirmModal({action: 'archive', ids: ${lesson.id}, url: 'admin/lesson/action', isBulk: false})">--%>
<%--                                    <i class="fa-solid fa-trash-can"></i>--%>
<%--                                    Xóa bài học--%>
<%--                                </button>--%>
<%--                            </c:if>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>

                <!-- TAB CONTENT -->
                <div class="tab-content">
                    <div class="tab-pane ${not empty param.lessonId ? '' : 'active'}" id="overview">
                        <jsp:include page="/views/pages/admin/course/editor/overview.jsp"/>
                    </div>

                    <c:if test="${not empty course and course.id > 0}">
                        <div class="tab-pane ${not empty param.lessonId ? 'active' : ''}" id="curriculum">
                            <jsp:include page="/views/pages/admin/course/editor/curriculum.jsp"/>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
<jsp:include page="/views/components/modal-confirm.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/validation/form-create-course-validation.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/admin/create-course.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/admin/course/course-editor.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/video-helper.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/admin/lesson/lesson-create.js?v=<%=System.currentTimeMillis()%>"></script>
</body>

</html>