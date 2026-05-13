<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
        ${(not empty user and user.id > 0) ? 'Cập nhật người dùng' : 'Tạo mới người dùng'}
    </title>

    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">

    <%-- CSS Page --%>
    <link rel="stylesheet" href="assets/css/admin/pages/category/category-create.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Component --%>
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
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
                            ${(not empty user and user.id > 0) ? 'Chi tiết người dùng' : 'Tạo mới người dùng'}
                        </h2>

                        <a href="admin/users" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i>
                            Trở về
                        </a>
                    </div>

                    <div class="form-container">
                        <form action="admin/user/detail" method="post" class="form-modern">
                            <input type="hidden" name="id"
                                   value="${user != null ? user.id : ''}"/>

                            <div class="category-create-card">
                                <%-- Họ --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Họ</label>
                                        <input type="text" class="input-modern readonly-field"
                                               value="${user.firstName}" readonly>
                                    </div>

                                    <div class="form-group flex-1">
                                        <label class="label-style">Tên</label>

                                        <input type="text" class="input-modern readonly-field"
                                               value="${user.lastName}" readonly>
                                    </div>
                                </div>

                                <%-- Username --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">Username</label>
                                        <input type="text" class="input-modern readonly-field"
                                               value="${user.username}" readonly>
                                    </div>
                                </div>

                                <%-- Email & Phone --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Email</label>
                                        <input type="text" class="input-modern readonly-field"
                                               value="${user.email}" readonly>
                                    </div>

                                    <div class="form-group flex-1">
                                        <label class="label-style">Số điện thoại</label>
                                        <input type="text" class="input-modern readonly-field"
                                               value="${user.phone}" readonly>
                                    </div>

                                </div>

                                <%-- Vai trò --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Vai trò</label>

                                        <input type="text" class="input-modern readonly-field"
                                               value="${empty user.roleName ? 'Chưa có vai trò' : user.roleName}" readonly>
                                    </div>

                                    <div class="form-group flex-1">
                                        <label class="label-style">Trạng thái</label>
                                        <select class="input-modern"name="status">
                                            <option value="ACTIVE"
                                            ${(user.status.name() == 'ACTIVE') ? 'selected' : ''}>
                                                Hoạt động
                                            </option>
                                            <option value="INACTIVE"
                                            ${(user.status.name() == 'INACTIVE') ? 'selected' : ''}>
                                                Bị khóa
                                            </option>
                                        </select>
                                    </div>
                                </div>

                                <%-- Avatar --%>
                                <c:if test="${not empty user.avatarUrl}">
                                    <div class="form-group mt-3">
                                        <label class="label-style">Ảnh đại diện</label>
                                        <div style="margin-top: 10px;">
                                            <img src="${user.avatarUrl}"
                                                 alt="avatar"
                                                 style="width: 120px; height: 120px; border-radius: 12px; object-fit: cover; border: 1px solid #ddd;">
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${user != null and user.id > 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Ngày tạo</label>
                                            <input type="text" class="input-modern readonly-field" value="${user.createdAt}" readonly>
                                        </div>

                                        <div class="form-group flex-1">
                                            <label class="label-style">Cập nhật lần cuối</label>
                                            <input type="text" class="input-modern readonly-field" value="${user.updatedAt}" readonly>
                                        </div>

                                    </div>
                                </c:if>

                                <%-- Action --%>
                                <div class="form-actions mt-4">
                                    <div style="display: flex; gap: 10px; flex: 1;">
                                        <a href="admin/users" class="btn-cancel-modern" style="text-decoration: none;">
                                            Hủy bỏ
                                        </a>
                                        <button type="submit" class="btn-submit-modern w-100">
                                            <i class="fa-solid fa-floppy-disk"></i>
                                            ${(not empty user and user.id > 0) ? 'Cập nhật' : 'Thêm người dùng'}
                                        </button>
                                    </div>

                                    <c:if test="${user != null and user.id > 0}">

                                        <button type="button"
                                                class="btn-delete-modern"
                                                onclick="openConfirmModal(
                                                    ${user.id},
                                                        'admin/user/delete',
                                                        'Bạn có chắc chắn muốn xóa người dùng này?'
                                                        )">

                                            <i class="fa-solid fa-trash-can"></i>
                                            Xóa người dùng

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
</body>
<script src="assets/javascript/validation/base-validator.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="assets/javascript/admin/user/user-create.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/admin/user-form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</html>