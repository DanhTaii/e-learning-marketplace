<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Tạo mới Danh mục</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/category/category-create.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Notification--%>
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">


<link rel="icon" type="image/png" href="assets/image/logo.jpg">
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
                            <c:out value="${(not empty category and category.id > 0) ? 'Cập nhật danh mục' : 'Tạo mới danh mục'}"/>
                        </h2>
                        <a href="admin/categories" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i> Trở về
                        </a>
                    </div>

                    <div class="form-container">
                        <form id="categoryForm" action="admin/category/detail" method="post" class="form-modern">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <input type="hidden" name="id" value="${category != null ? category.id : ''}" />
                            <div class="category-create-card">

                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">Tiêu đề danh mục</label>
                                        <input type="text" name="nameCategory" class="input-modern" id="categoryTitle"
                                               value="${not empty category.name ? category.name : param.nameCategory}"
                                               placeholder="Nhập tiêu đề..." minlength="3" maxlength="255" required>
                                        <span class="error-client" id="error_categoryTitle">
                                            <c:out value="${errors.nameCategory}"/>
                                        </span>
                                    </div>
                                    <div class="form-group flex-1">
                                        <label class="label-style">Parent ID</label>
                                        <input type="number" name="parentId" class="input-modern" id="parentId"
                                               value="${category != null ? category.parentId : ''}"
                                               placeholder="Ví dụ: 0" min="0" required>
                                        <span class="error-client" id="error_parentId"><c:out value="${errors.parentId}"/></span>
                                    </div>
                                </div>

                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">Tiêu đề slug</label>
                                        <input type="text" name="slug" class="input-modern" id="categorySlug"
                                               value="${not empty category.slug ? category.slug : param.slug}"
                                               placeholder="Nhập tên slug..." minlength="3" maxlength="255" required>
                                        <span class="error-client" id="error_slug"><c:out value="${errors.slug}"/></span>
                                    </div>
                                </div>

                                <div class="form-group mt-3">
                                    <label class="label-style">Trạng thái hiển thị</label>
                                    <select class="input-modern" name="status" required>
                                        <option value="INACTIVE"
                                        ${(category != null && category.status.name() == 'INACTIVE')
                                                || param.status == 'INACTIVE' ? 'selected' : ''}>
                                            Không hoạt động
                                        </option>
                                        <option value="ACTIVE"
                                        ${(category != null && category.status.name() == 'ACTIVE')
                                                || param.status == 'ACTIVE' ? 'selected' : ''}>
                                            Hoạt động
                                        </option>
                                    </select>
                                    <span class="error-client" id="error_status"><c:out value="${errors.status}"/></span>
                                </div>

                                <c:if test="${category != null and category.id > 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Ngày tạo</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${category.createdAt}" readonly>
                                        </div>
                                        <div class="form-group flex-1">
                                            <label class="label-style">Cập nhật lần cuối</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${category.updatedAt}" readonly>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="form-actions mt-4">
                                    <div style="display: flex; gap: 10px; flex: 1;">
                                        <a href="admin/categories" class="btn-cancel-modern"
                                           style="text-decoration: none;">
                                            Hủy bỏ
                                        </a>

                                        <button type="submit" class="btn-submit-modern w-100">
                                            <i class="fa-solid fa-floppy-disk"></i>
                                            <c:out value="${(not empty category and category.id > 0) ? 'Cập nhật' : 'Thêm danh mục'}"/>
                                        </button>
                                    </div>

                                    <c:if test="${category != null and category.id > 0}">
                                        <button type="button" class="btn-delete-modern"
                                                onclick="openConfirmModal(${category.id}, 'admin/category/delete', 'Bạn có chắc chắn muốn xóa danh mục này?')">
                                            <i class="fa-solid fa-trash-can"></i>
                                            Xóa danh mục
                                        </button>
                                    </c:if>
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
<jsp:include page="/views/components/modal-confirm.jsp"/>

<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<%-- Javascript --%>
<script src="assets/javascript/validation/base-validator.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/admin/category/category-create.js?v=<%=System.currentTimeMillis()%>"></script>

<%-- Javascript Validation--%>
<script src="assets/javascript/validation/admin/category-form-validation.js?v=<%=System.currentTimeMillis()%>"></script>

</body>
</html>
