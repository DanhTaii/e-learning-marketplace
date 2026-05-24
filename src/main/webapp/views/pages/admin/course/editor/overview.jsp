<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="form-container">
    <form id="courseForm" action="admin/course/editor" method="post">
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <c:if test="${course != null}">
            <input type="hidden" name="courseId" value="${course.id}"/>
        </c:if>
        <div class="form-row">
            <div class="form-column-8">
                <div class="form-group">
                    <label class="title-style">Tên khóa học</label>
                    <input name="title" type="text" class="input-modern"
                           id="courseTitle"
                           placeholder="Nhập tên khóa học..."
                           value="${ course != null ? course.title : param.title}">
                    <span class="error-client" id="error_courseTitle"></span>
                </div>
                <div class="form-group">
                    <label class="title-style">Phụ đề</label>
                    <input id="courseSubtitle" name="subtitle" type="text" class="input-modern"
                           placeholder="Tóm tắt ngắn gọn nội dung..."
                           value="${ course != null ? course.subtitle : param.subtitle}">
                    <span class="error-client" id="error_courseSubtitle"></span>
                </div>

                <c:if test="${course != null}">
                    <div class="form-group">
                        <label class="title-style">Ngày tạo: </label>
                        <input name="created_at" type="datetime-local" class="input-modern"
                               value="${course != null ? course.createdAt : param.created_at}"
                               readonly>
                    </div>

                    <div class="form-group">
                        <label class="title-style">Ngày cập nhật: </label>
                        <input name="updated_at" type="datetime-local" class="input-modern"
                               value="${course != null ? course.updatedAt : param.updated_at}"
                               readonly>
                    </div>
                </c:if>

            </div>
            <div class="form-column-4">
                <div class="form-group">
                    <label class="title-style">Link ảnh khóa học</label>
                    <input name="thumbnail" type="text" id="thumbnail-input" class="input-modern"
                           placeholder="Dán link ảnh . . ."
                           value="${course != null ? course.thumbnailUrl : param.thumbnail}">

                    <div class="image-preview-container mt-3">
                        <%-- Tính toán URL hiển thị: Ưu tiên URL từ khóa học -> Nếu rỗng thì lấy ảnh mặc định --%>
                        <c:set var="previewUrl"
                               value="${not empty course.thumbnailUrl ? course.thumbnailUrl : 'assets/image/image-not-found.webp'}"/>

                        <%-- Nếu là trang tạo mới (course null) và chưa có link ảnh từ param, cũng dùng ảnh mặc định --%>
                        <c:if test="${empty course && empty param.thumbnail}">
                            <c:set var="previewUrl" value="assets/image/image-not-found.webp"/>
                        </c:if>

                        <img id="image-preview"
                             src="${previewUrl}"
                             alt="Preview">
                    </div>
                </div>
            </div>
        </div>

        <div class="form-row mt-4">
            <div class="form-group flex-1">
                <label class="title-style">Giá bán </label>
                <input id="coursePrice" name="price" type="number" class="input-modern"
                       placeholder="Ví dụ: 500000"
                       value="${course != null ? course.price : param.price}"
                >
                <span class="error-client" id="error_coursePrice"></span>
            </div>
            <div class="form-group flex-1">
                <label class="title-style">Giá giảm</label>
                <input id="courseDiscountPrice" name="discount_price" type="number"
                       class="input-modern"
                       value="${course != null ? course.discountPrice : param.discount_price}"
                       placeholder="Để trống nếu không giảm">
                <span class="error-client" id="error_courseDiscount"></span>
            </div>
            <div class="form-group flex-1">
                <label class="title-style">Mức độ</label>
                <select id="courseLevel" name="level" class="input-modern select-custom">
                    <option value="">-- Chọn mức độ --</option>

                    <option value="BEGINNER" ${(param.level == 'BEGINNER' || course.level == 'BEGINNER') ? 'selected' : ''}>
                        Sơ cấp
                    </option>
                    <option value="INTERMEDIATE" ${(param.level == 'INTERMEDIATE' || course.level == 'INTERMEDIATE') ? 'selected' : ''}>
                        Trung cấp
                    </option>
                    <option value="ADVANCED" ${(param.level == 'ADVANCED' || course.level == 'ADVANCED') ? 'selected' : ''}>
                        Cao cấp
                    </option>
                </select>
                <span class="error-client" id="error_courseLevel"></span>
            </div>
        </div>

        <div class="form-row mt-4">
            <div class="form-group flex-1">
                <label class="title-style">Danh mục khóa học</label>
                <select name="category_id" id="courseCategory" class="input-modern select-custom"
                >
                    <option value="">-- Chọn danh mục --</option>

                    <c:forEach items="${categories}" var="cat">
                        <option value="${cat.id}"
                            ${(param.category_id == cat.id || course.categoryId == cat.id) ? 'selected' : ''}>
                                <c:out value="${cat.name}"/>
                        </option>
                    </c:forEach>
                </select>
                <span class="error-client" id="error_courseCategory"></span>
            </div>

            <div class="form-group flex-1">
                <label class="title-style">Trạng thái</label>
                <select id="courseStatus" name="status" class="input-modern select-custom">
                    <option value="">-- Chọn trạng thái --</option>

                    <option value="true" ${(param.status == 'true' || course.isPublic == true) ? 'selected' : ''}>
                        Công khai
                    </option>
                    <option value="false" ${(param.status == 'false' || course.isPublic == false) ? 'selected' : ''}>
                        Riêng tư
                    </option>
                </select>
                <span class="error-client" id="error_courseStatus"></span>
            </div>
        </div>

        <div class="form-row mt-4">
            <div class="form-group style-full-width">
                <label class="title-style">Tag khóa học</label>

                <div class="tag-container">
                    <c:forEach items="${tags}" var="tag">
                        <label class="tag-item">
                            <input type="checkbox"
                                   name="tags"
                                   value="${tag.id}"
                                   class="checkbox__item"
                                    <c:if test="${course != null && courseTagIdList.contains(tag.id)}">
                                        checked
                                    </c:if>/>
                                <c:out value="${tag.name}"/>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="form-row mt-4">
            <div class="form-group style-full-width">
                <label class="title-style">Mục tiêu khóa học</label>
                <textarea id="courseGoals" name="goals" class="input-modern textarea-modern"
                          placeholder="Học viên sẽ đạt được gì sau khóa học?"
                ><c:out value="${course != null ? course.goals : param.goals}"/></textarea>
                <span class="error-client" id="error_courseGoals"></span>
            </div>
        </div>

        <div class="form-row mt-4">
            <div class="form-group style-full-width">
                <label class="title-style">Mô tả chi tiết</label>
                <textarea id="courseDescription" name="description"
                          class="input-modern textarea-modern"
                          placeholder="Viết mô tả đầy đủ về khóa học tại đây..."><c:out value="${course != null ? course.description : param.description}"/></textarea>
                <span class="error-client" id="error_courseDescription"></span>
            </div>
        </div>

        <div class="form-actions mt-5">
            <div style="display: flex; gap: 10px; flex: 1;">
                <a href="admin/courses" class="btn-cancel-modern"
                   style="text-decoration: none;">
                    Hủy bỏ
                </a>

                <button type="submit" class="btn-submit-modern w-100">
                    <i class="fa-solid fa-floppy-disk"></i>
                    <c:out value="${(not empty course and course.id > 0) ? 'Cập nhật' : 'Thêm khóa học'}"/>
                </button>
            </div>

            <c:if test="${course != null and course.id > 0}">
                <button type="button" class="btn-delete-modern"
                        onclick="setupConfirmModal({action: 'archive', ids: ${course.id}, url: 'admin/course/action', isBulk: false})">
                    <i class="fa-solid fa-trash-can"></i>
                    Xóa khóa học
                </button>
            </c:if>
        </div>
    </form>
</div>
