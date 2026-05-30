<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết phương thức #<c:out value="${paymentMethod.code}"/></title>
    <base href="${pageContext.request.contextPath}/">

    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/order-edit.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/payment-method/payment-method-detail.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
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
                            Chi tiết cấu hình phương thức thanh toán
                        </h2>
                        <a href="${pageContext.request.contextPath}/admin/payment-methods" class="btn-back">
                            <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
                        </a>
                    </div>

                    <div class="order-detail-card payment-detail-layout">
                        <form action="admin/payment-method/detail" method="POST" style="width: 100%;">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <input type="hidden" name="id" value="${paymentMethod.id}">

                            <div class="order-info-section" style="width: 100%;">
                                <div class="order-info-header">
                                    <h3 class="text-header">Mã Code: <span class="text-header"><c:out value="${paymentMethod.code}"/></span></h3>
                                    <span class="order-status ${paymentMethod.status == 'ACTIVE' ? 'status-paid' : 'status-pending'}">
                                        <c:out value="${paymentMethod.status == 'ACTIVE' ? 'Đang hoạt động' : 'Tạm khóa'}"/>
                                    </span>
                                </div>

                                <div class="order-info-grid">
                                    <div class="info-item">
                                        <label>Tên phương thức thanh toán</label>
                                        <p><c:out value="${paymentMethod.name}"/></p>
                                    </div>
                                    <div class="info-item">
                                        <label>Logo / Icon đại diện</label>
                                        <p style="display: flex; align-items: center; gap: 10px;">
                                            <c:choose>
                                                <c:when test="${not empty paymentMethod.iconUrl}">
                                                    <img src="${paymentMethod.iconUrl}" alt="${paymentMethod.name}" style="height: 28px; max-width: 120px; object-fit: contain;">
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: gray; font-style: italic;">Chưa cấu hình icon</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                    <div class="info-item">
                                        <label>Thời gian tạo</label>
                                        <p><fmt:formatDate value="${paymentMethod.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                                    </div>
                                    <div class="info-item">
                                        <label>Cập nhật gần nhất</label>
                                        <p><fmt:formatDate value="${paymentMethod.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                                    </div>

                                    <div class="info-item" style="grid-column: span 2; background-color: #f8fafc; padding: 15px; border-radius: 8px; border: 1px dashed #cbd5e1; margin-top: 15px;">
                                        <label style="color: #4f46e5; font-weight: bold; font-size: 1.4rem; display: block; margin-bottom: 8px;">
                                            <i class="fa-solid fa-power-off"></i> Thay đổi trạng thái hoạt động phương thức
                                        </label>
                                        <select name="status" style="width: 100%; max-width: 320px; padding: 10px; border-radius: 6px; border: 1px solid #cbd5e1; font-size: 1.3rem; outline: none; background-color: #fff;">
                                            <option value="ACTIVE" ${paymentMethod.status == 'ACTIVE' ? 'selected' : ''}>Hoạt động</option>
                                            <option value="INACTIVE" ${paymentMethod.status == 'INACTIVE' ? 'selected' : ''}>Tạm dừng </option>
                                        </select>
                                    </div>
                                </div>

                                <div class="order-amount" style="margin-top: 30px; border-top: 1px solid #f1f5f9; padding-top: 20px; display: flex; justify-content: flex-end;">
                                    <button type="submit" class="dark-button" style="padding: 12px 28px; font-size: 1.3rem; font-weight: 500; cursor: pointer; border-radius: 6px;">
                                        <i class="fa-solid fa-floppy-disk" style="margin-right: 6px;"></i> Lưu thay đổi
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

<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>