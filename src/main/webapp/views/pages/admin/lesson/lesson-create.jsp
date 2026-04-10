<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson Create</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/lesson/lesson-create.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Notification--%>
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

    <%-- Javascript --%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="assets/javascript/admin/lesson/lesson-create.js?v=<%=System.currentTimeMillis()%>"></script>

    <%-- Javascript Validation--%>
    <script src="assets/javascript/validation/base-validator.js?v=<%=System.currentTimeMillis()%>"></script>
    <script src="assets/javascript/validation/admin/create-lesson.js?v=<%=System.currentTimeMillis()%>"></script>

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">

                <jsp:include page="/views/layouts/admin/sidebar-admin.jsp"/>

                <div class="grid__column-10 container-2">
                    <div class="container-2__header-modern">
                        <h2 class="header__title-modern">
                            ${(not empty lesson and lesson.id > 0) ? 'Cập nhật bài học' : 'Tạo mới bài học'}
                        </h2>
                        <a href="admin/lessons" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i> Trở về
                        </a>
                    </div>

                    <div class="form-container">
                        <form id="lessonForm" action="admin/lesson/detail" method="post" class="form-modern"
                        <%--                              enctype="multipart/form-data"--%>
                        >
                            <c:if test="${lesson != null}">
                                <input type="hidden" name="id" value="${lesson.id}"/>
                                <input type="hidden" name="oldOrderIndex" value="${lesson.orderIndex}"/>
                                <input type="hidden" name="oldCourseId" value="${lesson.courseId}"/>
                            </c:if>

                            <div class="lesson-create-card">
                                <div class="form-row">
                                    <div class="form-group flex-2">
                                        <label class="label-style">Khóa học mục tiêu</label>
                                        <select class="input-modern" name="idCourse" id="selectCourse">
                                            <option value="0">--- Chọn khóa học ---</option>
                                            <c:forEach var="c" items="${listCourse}">
                                                <option value="${c.id}" ${lesson.courseId == c.id ? 'selected' : ''}>${c.title}</option>
                                            </c:forEach>
                                        </select>

                                            <span class="error-client" id="error_idCourse">${errors.idCourse}</span>
                                    </div>
                                    <div class="form-group flex-1">
                                        <label class="label-style">Thứ tự bài học</label>
                                        <input type="number" name="orderIndex" class="input-modern" id="orderIndex"
                                               value="${lesson != null ? lesson.orderIndex : ''}"
                                               placeholder="Ví dụ: 1">
                                            <span class="error-client" id="error_orderIndex">
                                                 ${errors.orderIndex}
                                            </span>
                                    </div>
                                </div>

                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">Tiêu đề bài học</label>
                                        <input type="text" name="nameLesson" class="input-modern" id="lessonTitle"
                                               value="${lesson != null ? lesson.title : ''}"
                                               placeholder="Nhập tiêu đề...">
                                            <span class="error-client" id="error_lessonTitle">
                                                 ${errors.nameLesson}
                                            </span>
                                    </div>
                                    <div class="form-group flex-1">
                                        <label class="label-style">Thời lượng (Phút)</label>
                                        <input type="number" name="duration_minutesLesson" class="input-modern" id="durationMinutes"
                                               value="${lesson != null ? lesson.durationMinutes : ''}"
                                               placeholder="Phút">
                                            <span class="error-client" id="error_durationMinutes">
                                                 ${errors.durationMinutes}
                                            </span>
                                    </div>
                                </div>

                                <c:if test="${lesson != null and lesson.id > 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Ngày tạo</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${lesson.createdAt}" readonly>
                                        </div>
                                        <div class="form-group flex-1">
                                            <label class="label-style">Cập nhật lần cuối</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${lesson.updatedAt}" readonly>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="form-group mt-3">
                                    <label class="label-style">Nguồn Video bài giảng</label>
                                    <div class="video-source-tabs">
                                        <button type="button" class="tab-btn active"
                                                onclick="switchVideoSource('link', this)">Dán Link YouTube
                                        </button>
                                        <button type="button" class="tab-btn"
                                                onclick="switchVideoSource('upload', this)">Upload File Video
                                        </button>
                                    </div>

                                    <div id="videoSourceLink" class="video-input-container active mt-2">
                                        <div class="input-with-icon">
                                            <input type="text" id="videoUrlInput" name="urlVideo" class="input-modern"
                                                   value="${lesson != null ? lesson.videoUrl : ''}"
                                                   placeholder="https://www.youtube.com/watch?v=...">
                                            <%--                                            <button type="button" class="btn-secondary" onclick="previewVideo()"><i--%>
                                            <%--                                                    class="fa-solid fa-eye"></i> Xem thử--%>
                                            <%--                                            </button>--%>
                                                <span class="error-client" id="error_videoUrl">${errors.urlVideo}</span>
                                        </div>
                                    </div>

                                    <div id="videoSourceUpload" class="video-input-container mt-2"
                                         style="display:none;">
                                        <div class="upload-video-wrapper">
                                            <i class="fa-solid fa-cloud-arrow-up"></i>
                                            <p>Kéo thả hoặc Click để chọn file Video (.mp4, .mkv)</p>
                                            <input type="file" name="videoFile" class="file-hidden" id="videoFileInput"
                                                   accept="video/*">
                                            <small id="fileNameDisplay"></small>
                                        </div>
                                    </div>
                                </div>

                                <%--                                <div id="videoPreviewContainer" style="display:none;" class="mt-3">--%>
                                <%--                                    <div class="preview-header">--%>
                                <%--                                        <span><i class="fa-solid fa-play-circle"></i> Xem trước bài giảng</span>--%>
                                <%--                                    </div>--%>
                                <%--                                    <iframe id="videoIframe" width="100%" height="315" src="" frameborder="0"--%>
                                <%--                                            allowfullscreen></iframe>--%>
                                <%--                                </div>--%>

                                <div class="form-actions mt-4">
                                    <button type="submit" class="btn-submit-modern w-100">
                                        <i class="fa-solid fa-floppy-disk"></i>
                                        ${(not empty lesson and lesson.id > 0) ? 'Lưu cập nhật' : 'Thêm bài học'}
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>

</body>
</html>
