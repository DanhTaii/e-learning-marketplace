<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo mới khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/admin.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/course-edit.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-2 container-1">
                    <div class="container-1__logo">
                        <i class="fa-solid fa-graduation-cap"></i>
                        <span>Softskill</span>
                    </div>
                    <div class="container-1__menu">
                        <ul>
                            <li>
                                <a href="admin/dashboard">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/users">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/courses">
                                    <div class="menu-item__student student-list">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/lessons">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/tags">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/categories">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/orders">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/payment-methods">
                                    <div class="menu-item__student">
                                        <span class="container-1__menu-items menu-item__order">

                                            <i class="fa-solid fa-credit-card"></i>
                                            <span>Kiểu thanh toán</span>
                                        </span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <div class="log-out">
                            <a href="html-authentication/sign-in.jsp">
                                <div class="log-out__container">
                                    <div class="log-out__content">
                                        Thoát
                                    </div>
                                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="grid__column-10 container-2">
                    <c:if test="${course == null}">
                        <div class="container-2__header-modern">
                            <h2 class="header__title-modern">Tạo mới khóa học</h2>
                            <a href="admin/courses" class="btn-back">
                                <i class="fa-solid fa-backward-step"></i> Trở về
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${course != null}">
                        <div class="container-2__header-modern">
                            <h2 class="header__title-modern">Cập nhật khóa học</h2>
                            <a href="admin/courses" class="btn-back">
                                <i class="fa-solid fa-backward-step"></i> Trở về
                            </a>
                        </div>
                    </c:if>


                    <div class="user-form-container">
                        <form id="courseForm" action="admin/course/detail" method="post">
                            <c:if test="${course != null}">
                                <input type="hidden" name="courseId" value="${course.id}"/>
                            </c:if>
                            <div class="form-row">
                                <div class="form-column-8">
                                    <div class="form-group">
                                        <label class="course-create__title-style">Tên khóa học</label>
                                        <input name="title" type="text" class="input-modern"
                                               id="courseTitle"
                                               placeholder="Nhập tên khóa học..."
                                               value="${ course != null ? course.title : param.title}">
                                        <span class="error-client" id="error_courseTitle"
                                              style="color: red; font-size: 1.2rem;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label class="course-create__title-style">Phụ đề</label>
                                        <input id="courseSubtitle" name="subtitle" type="text" class="input-modern"
                                               placeholder="Tóm tắt ngắn gọn nội dung..."
                                               value="${ course != null ? course.subtitle : param.subtitle}">
                                        <span class="error-client" id="error_courseSubtitle"
                                              style="color: red; font-size: 1.2rem;"></span>
                                    </div>

                                    <c:if test="${course != null}">
                                        <div class="form-group">
                                            <label class="course-create__title-style">Ngày tạo: </label>
                                            <input name="created_at" type="datetime-local" class="input-modern"
                                                   value="${course != null ? course.createdAt : param.created_at}"
                                                   readonly>
                                        </div>

                                        <div class="form-group">
                                            <label class="course-create__title-style">Ngày cập nhật: </label>
                                            <input name="updated_at" type="datetime-local" class="input-modern"
                                                   value="${course != null ? course.updatedAt : param.updated_at}"
                                                   readonly>
                                        </div>
                                    </c:if>

                                </div>
                                <div class="form-column-4">
                                    <div class="form-group">
                                        <label class="course-create__title-style">Link ảnh khóa học</label>
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
                                                 alt="Preview"
                                                 style="width: 100%; height: 230px; object-fit: cover; border-radius: 8px; border: 1px solid #ddd;">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Giá bán </label>
                                    <input id="coursePrice" name="price" type="number" class="input-modern"
                                           placeholder="Ví dụ: 500000"
                                           value="${course != null ? course.price : param.price}"
                                    >
                                    <span class="error-client" id="error_coursePrice"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Giá giảm</label>
                                    <input id="courseDiscountPrice" name="discount_price" type="number"
                                           class="input-modern"
                                           value="${course != null ? course.discountPrice : param.discount_price}"
                                           placeholder="Để trống nếu không giảm">
                                    <span class="error-client" id="error_courseDiscount"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Mức độ</label>
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
                                    <span class="error-client" id="error_courseLevel"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Danh mục khóa học</label>
                                    <select name="category_id" id="courseCategory" class="input-modern select-custom"
                                    >
                                        <option value="">-- Chọn danh mục --</option>

                                        <c:forEach items="${categories}" var="cat">
                                            <option value="${cat.id}"
                                                ${(param.category_id == cat.id || course.categoryId == cat.id) ? 'selected' : ''}>
                                                    ${cat.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span class="error-client" id="error_courseCategory"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>

                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Trạng thái</label>
                                    <select id="courseStatus" name="status" class="input-modern select-custom">
                                        <option value="">-- Chọn trạng thái --</option>

                                        <option value="true" ${(param.status == 'true' || course.isPublic == true) ? 'selected' : ''}>
                                            Công khai
                                        </option>
                                        <option value="false" ${(param.status == 'false' || course.isPublic == false) ? 'selected' : ''}>
                                            Riêng tư
                                        </option>
                                    </select>
                                    <span class="error-client" id="error_courseStatus"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group style-full-width">
                                    <label class="course-create__title-style">Tag khóa học</label>

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
                                                    ${tag.name}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <%--                            </c:if>--%>

                            <div class="form-row mt-4">
                                <div class="form-group style-full-width">
                                    <label class="course-create__title-style">Mục tiêu khóa học</label>
                                    <textarea id="courseGoals" name="goals" class="input-modern textarea-modern"
                                              style="height: 100px; padding: 10px;"
                                              placeholder="Học viên sẽ đạt được gì sau khóa học?"
                                    >${course != null ? course.goals : param.goals}</textarea>
                                    <span class="error-client" id="error_courseGoals"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group style-full-width">
                                    <label class="course-create__title-style">Mô tả chi tiết</label>
                                    <textarea id="courseDescription" name="description"
                                              class="input-modern textarea-modern"
                                              style="height: 150px; padding: 10px;"
                                              placeholder="Viết mô tả đầy đủ về khóa học tại đây...">${course != null ? course.description : param.description}</textarea>
                                    <span class="error-client" id="error_courseDescription"
                                          style="color: red; font-size: 1.2rem;"></span>
                                </div>
                            </div>

                            <div class="form-actions mt-5">
                                <c:if test="${course == null}">
                                    <button type="submit" class="btn-submit-modern">
                                        <i class="fa-solid fa-floppy-disk"></i> Tạo khóa học
                                    </button>
                                </c:if>
                                <c:if test="${course != null}">
                                    <button type="submit" class="btn-submit-modern">
                                        <i class="fa-solid fa-floppy-disk"></i> Cập nhật khóa học
                                    </button>
                                </c:if>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="toast"></div>

</body>

<script>
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>

</script>
<script src="assets/javascript/ui/notification.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/admin/create-course-validation.js?v=<%=System.currentTimeMillis()%>"></script>
<script>
    $(document).ready(function () {
        Validator.setupAutoClearErrors();

        $('#courseForm').on('submit', function (e) {
            let isValid = true;

            // 1. Lấy giá trị
            const title = $('#courseTitle').val() || "";
            const subtitle = $('#courseSubtitle').val() || "";
            const price = $('#coursePrice').val() || "";
            const discount = $('#courseDiscountPrice').val() || "";
            const category = $('#courseCategory').val() || "";
            const goals = $('#courseGoals').val() || "";
            const description = $('#courseDescription').val() || "";

            // Kiểm tra Tên khóa học
            const titleLengthErr = Validator.checkLength(title, 10, 150, "Tên khóa học");
            if (titleLengthErr) {
                $('#error_courseTitle').text(titleLengthErr);
                isValid = false;
            }

            // Kiểm tra Phụ đề
            const subtitleErr = Validator.checkLength(subtitle, 10, 250, "Phụ đề");
            if (subtitleErr) {
                $('#error_courseSubtitle').text(subtitleErr);
                isValid = false;
            }

            // Kiểm tra Mục tiêu
            const goalsErr = Validator.checkLength(goals, 20, 1000, "Mục tiêu");
            if (goalsErr) {
                $('#error_courseGoals').text(goalsErr);
                isValid = false;
            }


            // Kiểm tra Mô tả
            const descErr = Validator.checkLength(description, 50, 5000, "Mô tả");
            if (descErr) {
                $('#error_courseDescription').text(descErr);
                isValid = false;
            }


            // Kiểm tra Giá bán
            const priceErr = Validator.checkPrice(price);
            if (priceErr) {
                $('#error_coursePrice').text(priceErr);
                isValid = false;
            }

            // Kiểm tra Giá giảm (Nếu có nhập thì mới check logc)
            const discountErr = Validator.checkDiscount(price, discount);
            if (discountErr) {
                $('#error_courseDiscount').text(discountErr);
                isValid = false;
            }

            // Kiểm tra Danh mục
            if (!category) {
                $('#error_courseCategory').text("Vui lòng chọn một danh mục");
                isValid = false;
            }

            if (!isValid) {
                console.log("Phát hiện có lỗi, đang chặn form..."); // Thêm dòng này
                e.preventDefault();
                $('html, body').animate({scrollTop: 0}, 'slow');
            } else {
                console.log("Mọi thứ ok, chuẩn bị gửi lên Server!");
            }
            return isValid;
        });
    });
</script>
</html>