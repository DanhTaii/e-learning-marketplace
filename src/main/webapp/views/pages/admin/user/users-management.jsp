<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Management</title>

    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/admin.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/users-management.css?v=<%=System.currentTimeMillis()%>">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <jsp:include page="/views/layouts/sidebar-admin.jsp"/>
                <div class="grid__column-10 container-2">
                    <div class="container-2__header"></div>
                    <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">Người dùng</div>
                            <%--                            <div class="admin-create__buttons">--%>
                            <%--                                <button type="button" class="dark-button">--%>
                            <%--                                    <a href="html-admin/user-create.jsp">--%>
                            <%--                                        <i class="fa-solid fa-plus"></i>Tạo mới--%>
                            <%--                                    </a>--%>
                            <%--                                </button>--%>
                            <%--                            </div>--%>
                        </div>

                        <div class="container-2__body">
                            <form action="admin/users/search" method="get">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên người dùng:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="usernameSearch" value="${param.usernameSearch}">
                                            </div>
                                            <div class="filter__selection-items">
                                                <div class="filter__selection-title filter__item-phone">Số điện thoại:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="phoneSearch" value="${param.phoneSearch}">
                                            </div>
                                            <div class="filter__selection-items">
                                                <div class="filter__selection-title filter__item-phone">Từ ngày:</div>
                                                <input placeholder="" type="date" class="admin-input__long"
                                                       name="dateFrom" value="${param.dateFrom}">
                                            </div>
                                            <div class="filter__selection-items-select">
                                                <div class="filter__selection-title filter__item-phone">Vai trò:</div>
                                                <select name="roleSearch" class="combobox admin-input__short">
                                                    <option value="" ${empty param.roleSearch ? 'selected' : ''}>Tất
                                                        cả
                                                    </option>
                                                    <option value="user" ${param.roleSearch == 'user' ? 'selected' : ''}>
                                                        Người dùng
                                                    </option>
                                                    <option value="admin" ${param.roleSearch == 'admin' ? 'selected' : ''}>
                                                        Quản trị viên
                                                    </option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="filter__button-search">
                                            <button type="submit" class="admin-search-btn">
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="container-2__list-student">

                                <table>
                                    <thead>
                                    <tr>
                                        <th>Tên người dùng</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th>Vai trò</th>
                                        <th>Hoạt động</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="user" items="${listUsers}">
                                        <tr>
                                            <td>
                                                <div class="title">
                                                        ${empty user.username ? "Chưa cập nhật" : user.username}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${user.email}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${empty user.phone ? "Chưa cập nhật" : user.phone}
                                                </div>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${user.role == 'ADMIN'}">
                                                        <div class="course-row__status course-row__font-content role-admin">
                                                            Quản trị viên
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="course-row__status course-row__font-content course-row__status-private">
                                                            Người dùng
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${user.status == 'ACTIVE'}">
                                                        <div class="course-row__status course-row__font-content course-row__status-public">
                                                                Hoạt động
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="course-row__status course-row__font-content course-row-status-unactive">
                                                                Bị khóa
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate value="${user.createdAt}"
                                                                    pattern="dd-MM-YYYY"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <div class="action-wrapper">
                                                    <button type="button" onclick="showUserDetail(${user.id})"
                                                            class="icon-action-btn">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </button>
                                                    <button type="button" class="icon-action-btn"
                                                            onclick="openConfirmModal(${user.id})">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listUsers}">
                                        <tr>
                                            <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                <div class="search-empty-state">
                                                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                    <div class="search-empty-title">
                                                        Không tìm thấy người dùng nào
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="user-detail" class="modal__course-detail">
                    <div class="modal__course-content">
                        <form id="updateUserForm" onsubmit="updateUser(event)">

                            <div class="course__header">
                                <div class="course__title">
                                    <i class="fa-solid fa-address-card"></i>
                                    <span id="modal-title"></span>
                                </div>
                                <div class="x__icon" onclick="closeModal()">
                                    <i class="fa-solid fa-xmark"></i>
                                </div>
                            </div>
                            <div class="course-body">
                                <div class="user-info-grid">
                                    <input type="hidden" id="detail-id" name="id">

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-user"></i> Tên người dùng</label>
                                        <input id="detail-username" name="username" type="text" class="input__create"
                                               readonly >
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-envelope"></i> Email</label>
                                        <input id="detail-email" name="email" type="text" class="input__create" readonly
                                               >
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-phone"></i> Số điện thoại</label>
                                        <input id="detail-phone" name="phone" type="text" class="input__create" readonly
                                               >
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-shield-halved"></i> Vai trò</label>
                                        <select id="detail-role" name="role" class="input__create role-badge">
                                            <option value="user">Người dùng</option>
                                            <option value="admin">Quản trị viên</option>
                                        </select>
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-user-check"></i> Hoạt động</label>
                                        <select id="detail-active" name="status" class="input__create role-badge">
                                            <option value="active">Hoạt động</option>
                                            <option value="inactive">Bị khóa</option>
                                        </select>
                                    </div>

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i>Ngày tham gia</label>
                                        <input id="detail-created" type="text" class="input__create" readonly >
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i>Ngày cập nhật</label>
                                        <input id="detail-updated" type="text" class="input__create" readonly >
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="button btn-cancel" onclick="closeModal()">
                                        Hủy
                                    </button>
                                    <button type="submit" class="button dark-button">
                                        Lưu thay đổi
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

<%--COMPONENT CONFIRM FOR DELETE--%>
<div id="confirm-delete-modal" class="modal">
    <div class="modal-content">
        <h3><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p>Bạn có chắc chắn muốn xóa người dùng này không?</p>
        <div>
            <button onclick="closeModal()" class="button btn-cancel">
                Hủy
            </button>
            <button id="btn-confirm-delete" class="button dark-button">
                Xóa ngay
            </button>
        </div>
    </div>
</div>
<%--DELETE ACTION--%>
<form id="delete-form-id" action="admin/user/delete" method="post" class="form">
    <input id="input-delete-id" type="hidden" name="id">
</form>
<jsp:include page="/views/components/toast.jsp"/>
</body>
<script src="assets/javascript/admin/user/admin-user-detail.js?v=<%=System.currentTimeMillis()%>"></script>
</html>