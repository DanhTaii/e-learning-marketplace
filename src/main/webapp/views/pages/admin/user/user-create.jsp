<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">

    <title>
        <c:out value="${(not empty user and user.id > 0)
                ? 'Cập nhật người dùng'
                : 'Tạo mới người dùng'}"/>
    </title>

    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/card.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/user/user-create.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/order-edit.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Component --%>
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
                            <c:out value="${(not empty user and user.id > 0) ? 'Chi tiết người dùng' : 'Tạo mới người dùng'}"/>
                        </h2>

                        <a href="admin/users" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i>
                            Trở về
                        </a>
                    </div>

                    <div class="form-container">
                        <form id="userForm" action="admin/user/detail" method="post" class="form-modern">
                            <div class="form-actions mt-4">
                                <div style="display: flex; gap: 10px; flex: 1; justify-content: flex-end !important;">
                                    <a href="admin/users" class="btn-cancel-modern"
                                       style="text-decoration: none;">
                                        Hủy bỏ
                                    </a>

                                    <button type="submit" class="btn-submit-modern w-100">
                                        <i class="fa-solid fa-floppy-disk"></i>
                                        <c:out value="${(not empty user and user.id > 0) ? 'Cập nhật' : 'Thêm người dùng'}"/>
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <input type="hidden" name="id" value="${user != null ? user.id : ''}"/>

                            <div class="category-create-card">
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Họ</label>
                                        <input type="text" id="firstName" name="firstName" class="input-modern
                                               ${(not empty user and user.id > 0) ? 'readonly-field' : ''}"
                                               value="<c:out value="${not empty user.firstName ? user.firstName : param.firstName}"/>"
                                               placeholder="Nhập họ..."
                                        <c:out value="${(not empty user and user.id > 0) ? 'readonly' : ''}"/>>

                                        <span class="error-client" id="error_firstName"></span>
                                    </div>

                                    <div class="form-group flex-1">
                                        <label class="label-style">Tên</label>

                                        <input type="text" id="lastName" name="lastName" class="input-modern
                                               ${(not empty user and user.id > 0) ? 'readonly-field' : ''}"
                                               value="<c:out value="${not empty user.lastName ? user.lastName : param.lastName}"/>"
                                               placeholder="Nhập tên..."
                                        <c:out value="${(not empty user and user.id > 0) ? 'readonly' : ''}"/>>

                                        <span class="error-client" id="error_lastName"></span>
                                    </div>
                                </div>

                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">Username</label>
                                        <input type="text" id="username" name="username" class="input-modern
                                               ${(not empty user and user.id > 0) ? 'readonly-field' : ''}"
                                               value="<c:out value="${not empty user.username ? user.username : param.username}"/>"
                                               placeholder="Nhập username..."
                                        <c:out value="${(not empty user and user.id > 0) ? 'readonly' : ''}"/>>
                                        <span class="error-client" id="error_username"></span>
                                    </div>
                                </div>

                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Email</label>
                                        <input type="email" id="email" name="email" class="input-modern
                                               ${(not empty user and user.id > 0) ? 'readonly-field' : ''}"
                                               value="<c:out value="${not empty user.email ? user.email : param.email}"/>"
                                               placeholder="Nhập email..."
                                        <c:out value="${(not empty user and user.id > 0) ? 'readonly' : ''}"/>>
                                        <span class="error-client" id="error_email"></span>
                                    </div>

                                    <div class="form-group flex-1">
                                        <label class="label-style">Số điện thoại</label>
                                        <input type="text" id="phone" name="phone" class="input-modern
                                               ${(not empty user and user.id > 0) ? 'readonly-field' : ''}"
                                               value="<c:out value="${not empty user.phone ? user.phone : param.phone}"/>"
                                               placeholder="Nhập số điện thoại..."
                                        <c:out value="${(not empty user and user.id > 0) ? 'readonly' : ''}"/>>

                                        <span class="error-client" id="error_phone"></span>
                                    </div>
                                </div>

                                <c:if test="${empty user or user.id <= 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Mật khẩu</label>
                                            <input type="password" name="password" id="password"
                                                   class="input-modern" placeholder="Nhập mật khẩu...">
                                            <span class="error-client" id="error_password"><c:out value="${errors.password}"/></span>
                                        </div>

                                        <div class="form-group flex-1">
                                            <label class="label-style">Xác nhận mật khẩu</label>
                                            <input type="password" name="confirmPassword" id="confirmPassword"
                                                   class="input-modern" placeholder="Nhập lại mật khẩu...">
                                            <span class="error-client" id="error_confirmPassword"><c:out value="${errors.confirmPassword}"/></span>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Vai trò</label>
                                        <select class="input-modern" name="roleId">
                                            <option value="5"
                                            ${(user.roleId == 5 || param.roleId == '5') ? 'selected' : ''}>
                                                Người dùng
                                            </option>
                                            <option value="1"
                                            ${(user.roleId == 1 || param.roleId == '1') ? 'selected' : ''}>
                                                Super Admin
                                            </option>
                                            <option value="2"
                                            ${(user.roleId == 2 || param.roleId == '2') ? 'selected' : ''}>
                                                Quản trị người dùng
                                            </option>
                                            <option value="3"
                                            ${(user.roleId == 3 || param.roleId == '3') ? 'selected' : ''}>
                                                Quản trị khóa học
                                            </option>
                                            <option value="4"
                                            ${(user.roleId == 4 || param.roleId == '4') ? 'selected' : ''}>
                                                Quản trị đơn hàng
                                            </option>
                                        </select>
                                        <span class="error-client"><c:out value="${errors.roleId}"/></span>
                                    </div>

                                    <div class="form-group flex-1">
                                        <label class="label-style">Trạng thái</label>
                                        <select class="input-modern" name="status">
                                            <option value="ACTIVE"
                                            ${(user.status.name() == 'ACTIVE') || param.status == 'ACTIVE' ? 'selected' : ''}>
                                                Hoạt động
                                            </option>
                                            <option value="INACTIVE"
                                            ${(user.status.name() == 'INACTIVE') || param.status == 'INACTIVE' ? 'selected' : ''}>
                                                Bị khóa
                                            </option>
                                        </select>
                                        <span class="error-client"><c:out value="${errors.status}"/></span>
                                    </div>
                                </div>

                                <%-- Avatar --%>
                                <c:if test="${not empty user.avatarUrl}">
                                    <div class="form-group mt-3">
                                        <label class="label-style">Ảnh đại diện</label>
                                        <div style="margin-top: 10px;">
                                            <img src="${user.avatarUrl}"
                                                 alt="avatar"
                                                 style=" width: 120px; height: 120px; border-radius: 12px;
                                                    object-fit: cover; border: 1px solid #ddd;">
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${user != null and user.id > 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Ngày tạo</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${user.createdAt}" readonly>
                                        </div>

                                        <div class="form-group flex-1">
                                            <label class="label-style">Cập nhật lần cuối</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${user.updatedAt}" readonly>
                                        </div>
                                    </div>
                                </c:if>
                                <%-- Danh sách khóa học của người dùng --%>
                                <c:if test="${not empty user and user.id > 0}">
                                    <div class="user-course-section mt-4">
                                        <div class="container-2__header-modern">
                                            <h2 class="header__title-modern">
                                                Khóa học đã đăng ký
                                            </h2>
                                        </div>
                                        <c:choose>
                                            <c:when test="${not empty user.courses}">
                                                <div class="order-items-section">
                                                    <table class="order-items-table">
                                                        <thead>
                                                        <tr>
                                                            <th>Ảnh</th>
                                                            <th>Tên khóa học</th>
                                                            <th>Giá hiện tại</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${user.courses}" var="c">
                                                            <tr onclick="window.location.href='admin/course/editor?id=${c.id}'"
                                                                style="cursor: pointer;">
                                                                <td class="thumbnail-cell">
                                                                    <img src="<c:out value='${c.thumbnailUrl}'/>"
                                                                         alt="<c:out value='${c.title}'/>"
                                                                         class="item-thumbnail">
                                                                </td>
                                                                <td class="title-cell">
                                                                        <c:out value="${c.title}"/>
                                                                </td>
                                                                <td class="price-cell">
                                                                    <c:out value="${c.discountedPrice}"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="search-empty-state">
                                                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                    <div class="search-empty-title">Người dùng chưa sở hữu khóa học nào</div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </c:if>

                                <%-- Action --%>
                                <div class="form-actions mt-4">
                                    <div style="display: flex; gap: 10px; flex: 1;">
                                        <a href="admin/users" class="btn-cancel-modern"
                                           style="text-decoration: none;">
                                            Hủy bỏ
                                        </a>

                                        <button type="submit" class="btn-submit-modern w-100">
                                            <i class="fa-solid fa-floppy-disk"></i>
                                            <c:out value="${(not empty user and user.id > 0) ? 'Cập nhật' : 'Thêm người dùng'}"/>
                                        </button>
                                    </div>
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
<script src="assets/javascript/validation/base-validator.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="assets/javascript/admin/user/user-create.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/admin/user-form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>