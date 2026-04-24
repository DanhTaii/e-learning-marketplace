<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo / Cập nhật Role</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/role/role-create.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Notification--%>
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

                    <!-- HEADER -->
                    <div class="container-2__header-modern">
                        <h2 class="header__title-modern">
                            ${(not empty role and role.id > 0) ? 'Cập nhật Role' : 'Tạo mới Role'}
                        </h2>

                        <a href="admin/super/roles" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i> Trở về
                        </a>
                    </div>

                    <!-- FORM -->
                    <div class="form-container">
                        <form action="admin/role/detail" method="post" class="form-modern">

                            <input type="hidden" name="id"
                                   value="${role != null ? role.id : ''}"/>

                            <div class="category-create-card">

                                <!-- NAME -->
                                <div class="form-group mt-3">
                                    <label class="label-style">Tên Role</label>
                                    <input type="text" name="name"
                                           class="input-modern"
                                           value="${role != null ? role.name : ''}"
                                           placeholder="Ví dụ: ADMIN, MODERATOR"
                                           required>

                                    <span class="error-client">${errors.name}</span>
                                </div>

                                <div class="form-group mt-3">
                                    <label class="label-style">Mô tả</label>
                                    <textarea name="description"
                                              class="input-modern"
                                              rows="3"
                                              placeholder="Nhập mô tả...">${role != null ? role.description : ''}</textarea>
                                </div>

                                <div class="form-group mt-4">
                                    <label class="label-style text-big">Phân quyền</label>

                                    <div class="permission-group-wrapper">

                                        <c:forEach var="group" items="${permissionGroups}">

                                            <div class="permission-group">

                                                <h4 class="permission-group-title">
                                                        ${group.key}
                                                </h4>

                                                <div class="permission-list">

                                                    <c:forEach var="perm" items="${group.value}">
                                                        <label class="permission-item">

                                                            <input type="checkbox"
                                                                   name="permissionIds"
                                                                   value="${perm.id}"

                                                                    <c:if test="${selectedPermissions.contains(perm.id)}">
                                                                        checked
                                                                    </c:if>
                                                            />

                                                                ${perm.name}
                                                        </label>
                                                    </c:forEach>

                                                </div>
                                            </div>

                                        </c:forEach>

                                    </div>
                                </div>

                                <div class="form-actions mt-4 fix">

                                    <a href="admin/super/roles" class="btn-cancel-modern">
                                        Hủy
                                    </a>

                                    <button type="submit" class="btn-submit-modern">
                                        <i class="fa-solid fa-floppy-disk"></i>
                                        ${(role != null && role.id > 0) ? 'Cập nhật' : 'Tạo Role'}
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

</body>
</html>