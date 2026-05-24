<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="vi">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,
          user-scalable=no,
          initial-scale=1.0,
          maximum-scale=1.0,
          minimum-scale=1.0">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>
        Chi tiết yêu cầu 
    </title>

    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">

    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/tag/tag-create.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Notification --%>
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

    <%-- Normalize --%>
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
                            Chi tiết yêu cầu hỗ trợ
                        </h2>
                        <a href="admin/requests" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i>
                            Trở về
                        </a>
                    </div>

                    <div class="form-container">

                        <form id="requestForm" action="admin/request/detail" method="post" class="form-modern">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <input type="hidden" name="id" value="${contact != null ? contact.id : ''}" />

                            <div class="tag-create-card">
                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">
                                            Email người gửi
                                        </label>
                                        <input type="text" class="input-modern readonly-field"
                                               value="${contact.email}"
                                               readonly>
                                    </div>
                                </div>

                                <div class="form-row mt-3">
                                    <div class="form-group flex-2">
                                        <label class="label-style">
                                            Tiêu đề
                                        </label>
                                        <input type="text" class="input-modern readonly-field"
                                               value="${contact.subject}"
                                               readonly>
                                    </div>
                                </div>

                                <div class="form-group mt-3">
                                    <label class="label-style">
                                        Nội dung yêu cầu
                                    </label>
                                    <textarea class="input-modern readonly-field" rows="8" readonly>${contact.message}
                                    </textarea>
                                </div>

                                <div class="form-group mt-3">
                                    <label class="label-style">Trạng thái xử lý</label>
                                    <select class="input-modern" name="status">
                                        <option value="PENDING"
                                        ${contact.status.name() == 'PENDING' ? 'selected' : ''}>
                                            Chờ xử lý
                                        </option>
                                        <option value="IN_PROGRESS"
                                        ${contact.status.name() == 'IN_PROGRESS' ? 'selected' : ''}>
                                            Đang xử lý
                                        </option>
                                        <option value="RESOLVED"
                                        ${contact.status.name() == 'RESOLVED' ? 'selected' : ''}>
                                            Đã xử lý
                                        </option>
                                        <option value="REJECTED"
                                        ${contact.status.name() == 'REJECTED' ? 'selected' : ''}>
                                            Từ chối
                                        </option>
                                    </select>
                                </div>

                                <div class="form-group mt-3">
                                    <label class="label-style">Phản hồi của quản trị viên</label>
                                    <textarea name="adminReply"
                                              class="input-modern"
                                              rows="6"
                                              placeholder="Nhập phản hồi cho người dùng...">${contact.adminReply}</textarea>
                                </div>

                                <c:if test="${contact != null and contact.id > 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Ngày tạo</label>
                                            <input type="text"
                                                   class="input-modern readonly-field"
                                                   value="${contact.createdAt}"
                                                   readonly>
                                        </div>
                                        <div class="form-group flex-1">
                                            <label class="label-style">Cập nhật lần cuối</label>
                                            <input type="text"
                                                   class="input-modern readonly-field"
                                                   value="${contact.updatedAt}"
                                                   readonly>
                                        </div>

                                        <div class="form-group flex-1">

                                            <label class="label-style">
                                                Thời gian xử lý
                                            </label>

                                            <input type="text"
                                                   class="input-modern readonly-field"
                                                   value="${contact.resolvedAt}"
                                                   readonly>
                                        </div>
                                    </div>

                                </c:if>

                                <%-- ACTIONS --%>
                                <div class="form-actions mt-4">
                                    <div style="display: flex; gap: 10px; flex: 1;">
                                        <a href="admin/requests" class="btn-cancel-modern" style="text-decoration: none;">
                                            Hủy bỏ
                                        </a>
                                        <button type="submit" class="btn-submit-modern w-100">
                                            <i class="fa-solid fa-floppy-disk"></i>
                                            Cập nhật yêu cầu
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

<%-- Javascript --%>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/base-validator.js?v=<%=System.currentTimeMillis()%>"></script>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</body>


</html>