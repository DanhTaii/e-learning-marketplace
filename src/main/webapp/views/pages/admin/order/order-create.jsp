<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng #${order.orderCode}</title>
    <base href="${pageContext.request.contextPath}/">

    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/order-edit.css?v=<%=System.currentTimeMillis()%>">
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
                            Chi tiết đơn hàng
                        </h2>
                        <a href="${pageContext.request.contextPath}/admin/orders" class="btn-back">
                            <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
                        </a>
                    </div>

                    <div class="order-detail-card">
                        <!-- Thông tin đơn hàng -->
                        <div class="order-info-section">
                            <div class="order-info-header">
                                <h3 class="text-header">Mã đơn hàng: <span class="text-header">${order.orderCode}</span>
                                </h3>
                                <span class="order-status ${order.status == 'PAID' ? 'status-paid' : 'status-pending'}">
                                    ${order.status}
                                </span>
                            </div>

                            <div class="order-info-grid">
                                <div class="info-item">
                                    <label>Khách hàng</label>
                                                                        <p>${order.usernameSnapshot}</p>
                                </div>
                                <div class="info-item">
                                    <label>Phương thức thanh toán</label>
                                                                        <p> ${order.paymentMethodId == 1 ? 'Momo' :
                                                                                (order.paymentMethodId == 2 ? 'VNPAY' :
                                                                                        (order.paymentMethodId == 3 ? 'ZaloPay' : 'Chưa chọn'))}</p>
                                </div>
                                <div class="info-item">
                                    <label>Thời gian tạo</label>
                                    <p><fmt:formatDate value="${order.createdAt}"
                                                       pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </p>
                                </div>
                                <div class="info-item">
                                    <label>Thời gian thanh toán</label>
                                    <p><fmt:formatDate value="${order.paidAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                                </div>
                            </div>

                            <div class="order-amount">
                                <div class="amount-row">
                                    <span class="text-big">Tổng tiền gốc:</span>
                                    <span class="price text-big"><fmt:formatNumber value="${order.totalAmount}"
                                                                                   groupingUsed="true"/> VNĐ</span>
                                </div>
                                <div class="amount-row">
                                    <span class="text-big">Giảm giá:</span>
                                    <span class="price text-big"><fmt:formatNumber value="${order.discountAmount}"
                                                                                   groupingUsed="true"/> VNĐ</span>
                                </div>
                                <div class="amount-row total">
                                    <span class="text-big">Thành tiền:</span>
                                    <span class="price text-big"><fmt:formatNumber value="${order.finalAmount}"
                                                                                   groupingUsed="true"/> VNĐ</span>
                                </div>
                            </div>
                        </div>

                        <!-- Bảng khóa học đã mua -->
                        <div class="order-items-section">
                            <h3>Khóa học đã mua (${orderItems.size()} khóa)</h3>
                            <table class="order-items-table">
                                <thead>
                                <tr>
                                    <th>Ảnh</th>
                                    <th>Tên khóa học</th>
                                    <th>Giá tại thời điểm mua</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orderItems}" var="item">
                                    <tr>
                                        <td class="thumbnail-cell">
                                            <img src="${item.thumbnailUrl}" alt="${item.courseTitle}"
                                                 class="item-thumbnail">
                                        </td>
                                        <td class="title-cell">${item.courseTitle}</td>
                                        <td class="price-cell">
                                            <fmt:formatNumber value="${item.priceAtPurchase}" groupingUsed="true"/> VNĐ
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty orderItems}">
                                    <tr>
                                        <td colspan="3" class="no-data">Không có khóa học nào trong đơn hàng</td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
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