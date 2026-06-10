<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Course Content</title>
    <base href="${pageContext.request.contextPath}/">
    <%--    <link rel="stylesheet" href="assets/css/default.css?v=${applicationScope.assetVersion}">--%>
    <link rel="stylesheet" href="assets/css/base/base.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/course/course-content.css?v=${applicationScope.assetVersion}">
    <%--    <link rel="stylesheet" href="assets/css/admin/layouts/header-course-admin.css?v=${applicationScope.assetVersion}">--%>
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <%--    <link rel="stylesheet" href="assets/css/fonts.css">--%>
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=${applicationScope.assetVersion}">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="grid layout">
            <div class="grid__column-8 column1">

                <div class="course-header-container">
                    <h1 class="course-title"><c:out value="${enrollmentDetail.title}"/></h1>
                    <input id="enrollment-id" type="hidden" name="enrollmentId" value="${enrollmentDetail.id}">
                    <input id="course-id" type="hidden" name="courseId" value="${enrollmentDetail.courseId}">

                    <div class="circular-progress">
                        <svg class="progress-svg" viewBox="0 0 70 70">
                            <circle class="progress-bg" cx="35" cy="35" r="30"></circle>
                            <circle class="progress-bar" cx="35" cy="35" r="30"
                                    style="stroke-dashoffset: calc(188.4 - (188.4 * ${enrollmentDetail.percentCompleted} / 100));">
                            </circle>
                        </svg>
                        <div class="progress-text">
                            <span class="percent-number"><fmt:formatNumber value="${enrollmentDetail.percentCompleted}"
                                                                           maxFractionDigits="0"/>%</span>
                        </div>
                    </div>
                </div>

                <div class="image-container">
                    <div class="image-container imgg">
                        <iframe id="mainVideoPlayer" width="100%" height="500"
                                src=""
                                title="YouTube video player" frameborder="0"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                referrerpolicy="strict-origin-when-cross-origin"
                                allowfullscreen
                                style="display: none;">
                        </iframe>

                        <video id="cloudinaryPlayer" width="100%" height="500"
                               controls controlsList="nodownload"
                               style="display: none; background-color: #000;">
                            Trình duyệt không hỗ trợ thẻ phát video.
                        </video>

                        <div id="videoPlaceholder" class="placeholder-video">
                            <img src="assets/image/video-not-found.png" alt="No video available" class="imgg1">
                            <p class="text-xl">Bài học này hiện đang được cập nhật video...</p>
                        </div>
                    </div>
                </div>

                <div class="content-container">
                    <nav class="tabs">
                        <button class="tab-item active" data-tab="overview">
                            Tổng quan
                        </button>

                        <button class="tab-item" data-tab="reviews">
                            Đánh giá
                        </button>

                        <button class="tab-item" data-tab="notes">
                            Ghi chú
                        </button>
                    </nav>

                    <div class="tab-content">
                        <div class="tab-pane active" id="overview">
                            <jsp:include
                                    page="/views/pages/personal/course/enrollment/id/enrollment-detail-overview.jsp"/>
                        </div>

                        <div class="tab-pane" id="reviews">
                            <jsp:include
                                    page="/views/pages/personal/course/enrollment/id/enrollment-detail-reviews.jsp"/>
                        </div>

                        <div class="tab-pane" id="notes">
                            <jsp:include
                                    page="/views/pages/personal/course/enrollment/id/enrollment-detail-notes.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
            <%-- LESSON LIST --%>
            <div class="grid__column-4 column2">
                <div class="course-content">
                    <div class="content__header">
                        <span class="text-4xl bold">Danh sách bài học</span>
                        <span class="text-xl light header-subtitle">
                            <c:out value="${enrollmentDetail.listLesson.size()}"/> bài học
                            •
                            <c:out value="${enrollmentDetail.durationText}"/>
                        </span>
                    </div>
                    <hr>
                    <div class="content__box">
                        <%-- CÁI LIST LESSON Ở ĐÂY ĐANG LÀ TABLE LESSON PROGRESS KHÔNG PHẢI LESSON --%>
                        <c:forEach var="l" items="${enrollmentDetail.listLesson}">
                            <%-- Truyền vào các giá trị cần thiết của video --%>
                            <div class="box__content lesson-item"
                                 data-lesson-id="${l.lessonId}" data-video-url="${l.videoUrl}"
                                 data-title="Bài ${l.orderIndex}: ${l.lessonTitle}"
                                 data-last-time="${l.lastWatchedTime != null ? l.lastWatchedTime : 0}">

                                <div class="box__column1">
                                    <div class="column1__tick">
                                        <input type="checkbox" class="tick lesson-checkbox" name="tick"
                                               data-lesson-id="${l.id}"
                                            ${l.completed ? 'checked' : ''}>
                                    </div>
                                </div>
                                <div class="box__column2">
                                    <div class="column2__header">
                                        <span class="text-lg regular header">Bài <c:out value="${l.orderIndex}"/> : <c:out value="${l.lessonTitle}"/></span>
                                    </div>
                                    <div class="column2__duration">
                                        <span class="text-lg light"><c:out value="${l.durationMinutes}"/>p</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <jsp:include page="/views/layouts/footer.jsp"/>
</div>
<jsp:include page="/views/components/modal-confirm.jsp"/>
<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/validation/form-validation.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/enrollment/enrollment.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/validation/personal/course/rating-star.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/enrollment/enrollment-detail-navbar.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/validation/video-helper.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/enrollment/enrollment-note.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/enrollment/enrollment-review.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/utils/formatter/base.js?v=${applicationScope.assetVersion}"></script>
</body>

</html>
