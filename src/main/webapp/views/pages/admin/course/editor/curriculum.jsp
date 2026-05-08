<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="course-editor-body">

    <!-- LEFT: CURRICULUM -->
    <div class="curriculum-sidebar">
        <h4>Danh sách bài học</h4>

        <div class="lesson-list">
            <c:forEach var="l" items="${lessons}">
                <a href="admin/course/editor?id=${course.id}&lessonId=${l.id}"
                   class="lesson-item ${lesson != null && lesson.id == l.id ? 'active' : ''}">
                    <i class="fa-solid fa-play"></i>
                        ${l.title}
                </a>
            </c:forEach>
        </div>

        <hr>
        <!-- Nút Thêm bài học đã được bọc bằng thẻ a và truyền lessonId=0 -->
        <a href="admin/course/editor?id=${course.id}&lessonId=0" class="btn-add">
            <i class="fa-solid fa-plus"></i> Thêm bài học
        </a>
    </div>

    <!-- RIGHT: LESSON EDITOR -->
    <div class="lesson-editor no-shadow">

        <form id="lessonForm" action="admin/course/curriculum" method="post" class="form-modern"
              enctype="multipart/form-data">

            <input type="hidden" name="courseId" value="${course.id}">
            <input type="hidden" name="lessonId" value="${lesson.id}">

            <c:if test="${lesson != null}">
                <input type="hidden" name="id" value="${lesson.id}"/>
                <input type="hidden" name="oldOrderIndex" value="${lesson.orderIndex}"/>
                <input type="hidden" name="oldCourseId" value="${lesson.courseId}"/>
            </c:if>

            <div class="lesson-create-card">

                <div class="form-row">
                    <div class="form-group flex-2">
                        <label class="label-style">Tiêu đề bài học</label>
                        <input type="text" name="nameLesson" class="input-modern" id="lessonTitle"
                               value="${not empty param.nameLesson ? param.nameLesson : (lesson != null ? lesson.title : '')}"
                               placeholder="Nhập tiêu đề...">
                        <span class="error-client" id="error_lessonTitle">
                            ${errors.nameLesson}
                        </span>
                    </div>
                </div>

                <div class="form-row mt-3">
                    <div class="form-group flex-1">
                        <label class="label-style">Thứ tự bài học</label>
                        <input type="number" name="orderIndex" class="input-modern" id="orderIndex"
                               value="${lesson != null ? lesson.orderIndex : param.orderIndex}"
                               placeholder="Ví dụ: 1">
                        <span class="error-client" id="error_orderIndex">
                            ${errors.orderIndex}
                        </span>
                    </div>
                    <div class="form-group flex-1">
                        <label class="label-style">Thời lượng (Phút)</label>
                        <input type="number" name="duration_minutesLesson" class="input-modern"
                               id="durationMinutes"
                               value="${not empty param.duration_minutesLesson ? param.duration_minutesLesson : (lesson != null ? lesson.durationMinutes : '')}"
                               placeholder="Phút">
                        <span class="error-client" id="error_durationMinutes">
                            ${errors.durationMinutes}
                        </span>
                    </div>
                </div>

                <div class="form-group mt-3">
                    <label class="label-style">Trạng thái hiển thị</label>
                    <select class="input-modern" name="status">
                        <option value="INACTIVE" ${(lesson.status == 'INACTIVE' || param.status == 'INACTIVE' ) ? 'selected' : ''}>
                            Bản nháp - Đang biên soạn
                        </option>
                        <option value="ACTIVE" ${(lesson.status == 'ACTIVE' || param.status == 'ACTIVE' )? 'selected' : ''}>
                            Hoạt động - Học viên có thể xem
                        </option>
                    </select>
                    <span class="error-client" id="error_status">${errors.status}</span>
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
                                   value="${not empty param.urlVideo ? param.urlVideo : (lesson != null ? lesson.videoUrl : '')}"
                                   placeholder="https://www.youtube.com/watch?v=...">
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

                <div id="videoPreviewContainer" style="display:none;" class="mt-3">
                    <iframe id="videoIframe" width="100%" height="500" src="" frameborder="0"
                            allowfullscreen style="display:none;"></iframe>

                    <video id="videoLocalPlayer" width="100%" height="500" controls
                           style="display:none;">
                        Trình duyệt của bạn không hỗ trợ xem video.
                    </video>
                </div>
            </div>
            <div class="form-actions mt-4">
                <div style="display: flex; gap: 10px; flex: 1;">
                    <a href="admin/lessons" class="btn-cancel-modern"
                       style="text-decoration: none;">
                        Hủy bỏ
                    </a>

                    <button type="submit" class="btn-submit-modern w-100">
                        <i class="fa-solid fa-floppy-disk"></i>
                        ${(not empty lesson and lesson.id > 0) ? 'Cập nhật' : 'Thêm bài học'}
                    </button>
                </div>

                <c:if test="${lesson != null and lesson.id > 0}">
                    <button type="button" class="btn-delete-modern"
                            onclick="setupConfirmModal({action: 'archive', ids: ${lesson.id}, url: 'admin/course/curriculum/action', isBulk: false})">
                        <i class="fa-solid fa-trash-can"></i>
                        Xóa bài học
                    </button>
                </c:if>
            </div>
        </form>
    </div>
</div>
