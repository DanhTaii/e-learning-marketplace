<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo mới khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/course-edit.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css-admin/notification.css?v=<%=System.currentTimeMillis()%>">

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
                    <div class="container-2__header-modern">
                        <h2 class="header__title-modern">Tạo mới khóa học</h2>
                        <a href="admin/courses" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i> Trở về
                        </a>
                    </div>


                    <div class="user-form-container">
                        <form action="admin/course/create" method="post">
                            <%--                              enctype="multipart/form-data">--%>
                                <c:if test="${course != null}">
                                    <input type="hidden" name="courseId" value="${course.id}" />
                                </c:if>
                            <div class="form-row">
                                <div class="form-column-8">
                                    <div class="form-group">
                                        <label class="course-create__title-style">Tên khóa học</label>
                                        <input name="title" type="text" class="input-modern"
                                               placeholder="Nhập tên khóa học..."
                                               value="${ course != null ? course.title : param.title}" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="course-create__title-style">Phụ đề</label>
                                        <input name="subtitle" type="text" class="input-modern"
                                               placeholder="Tóm tắt ngắn gọn nội dung..."
                                               value="${ course != null ? course.subtitle : param.subtitle}">
                                    </div>

                                    <div class="form-group">
                                        <label class="course-create__title-style">Ngày tạo: </label>
                                        <input name="created_at" type="datetime-local" class="input-modern"
                                               value="${course != null ? course.createdAt : param.created_at}">
                                    </div>

                                    <div class="form-group">
                                        <label class="course-create__title-style">Ngày cập nhật: </label>
                                        <input name="updated_at" type="datetime-local" class="input-modern"
                                               value="${course != null ? course.updatedAt : param.updated_at}"
                                               readonly>
                                    </div>
                                </div>
                                <div class="form-column-4">
                                    <div class="form-group">
                                        <label class="course-create__title-style">Link ảnh khóa học</label>
                                        <input name="thumbnail" type="text" id="thumbnail-input" class="input-modern"
                                               placeholder="Dán link ảnh (https://...)"
                                               value="${course != null ? course.thumbnailUrl : param.thumbnail}">

                                        <div class="image-preview-container mt-3">
                                            <img id="image-preview"
                                                 src="${course != null ? course.thumbnailUrl : (param.thumbnail != null ? param.thumbnail : 'assets/img/no-image.png')}"
                                                 alt="Preview"
                                                 style="width: 100%; height: 230px; object-fit: cover; border-radius: 8px; border: 1px solid #ddd;">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Giá bán </label>
                                    <input name="price" type="number" class="input-modern" placeholder="Ví dụ: 500000"
                                           value="${course != null ? course.price : param.price}"
                                           required>
                                </div>
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Giá giảm (VNĐ)</label>
                                    <input name="discount_price" type="number" class="input-modern"
                                           value="${course != null ? course.discountPrice : param.discount_price}"
                                           placeholder="Để trống nếu không giảm">
                                </div>
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Mức độ</label>
                                    <select name="level" class="input-modern select-custom">
                                        <option value="">-- Chọn mức độ --</option>

                                        <option value="BEGINNER"
                                                <c:if test="${course != null && course.level == 'BEGINNER'}">
                                                    selected
                                                </c:if>>
                                            Sơ cấp
                                        </option>

                                        <option value="INTERMEDIATE"
                                                <c:if test="${course != null && course.level == 'INTERMEDIATE'}">
                                                    selected
                                                </c:if>>
                                            Trung cấp
                                        </option>

                                        <option value="ADVANCED"
                                                <c:if test="${course != null && course.level == 'ADVANCED'}">
                                                    selected
                                                </c:if>>
                                            Cao cấp
                                        </option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Danh mục khóa học</label>
                                    <select name="category_id" class="input-modern select-custom" required>
                                        <option value="">-- Chọn danh mục --</option>

                                        <c:forEach items="${categories}" var="cat">
                                            <option value="${cat.id}"
                                                ${course != null && course.categoryId == cat.id ? "selected" : ""}>
                                                    ${cat.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group flex-1">
                                    <label class="course-create__title-style">Trạng thái</label>
                                    <select name="status" class="input-modern select-custom">
                                        <option value="">-- Chọn trạng thái --</option>

                                        <option value="true"
                                                <c:if test="${course != null && course.isPublic}">
                                                    selected
                                                </c:if>>
                                            Hoạt động
                                        </option>

                                        <option value="false"
                                                <c:if test="${course != null && !course.isPublic}">
                                                    selected
                                                </c:if>>
                                            Bị khóa
                                        </option>
                                    </select>
                                </div>
                            </div>

<%--                                CHỉ khi cập nhật mơới có thể thêm tag vô thôi--%>
                            <c:if test="${course != null && course.id != null}">
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
                            </c:if>

                            <div class="form-row mt-4">
                                <div class="form-group style-full-width">
                                    <label class="course-create__title-style">Mục tiêu khóa học</label>
                                    <textarea name="goals" class="input-modern textarea-modern"
                                              style="height: 100px; padding: 10px;"
                                              placeholder="Học viên sẽ đạt được gì sau khóa học?"
                                    >${course != null ? course.goals : param.goals}</textarea>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group style-full-width">
                                    <label class="course-create__title-style">Mô tả chi tiết</label>
                                    <textarea name="description" class="input-modern textarea-modern"
                                              style="height: 150px; padding: 10px;"
                                              placeholder="Viết mô tả đầy đủ về khóa học tại đây...">${course != null ? course.description : param.description}</textarea>
                                </div>
                            </div>

                            <div class="form-actions mt-5">
                                <button type="submit" class="btn-submit-modern">
                                    <i class="fa-solid fa-floppy-disk"></i> Lưu khóa học
                                </button>
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
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>

</html>