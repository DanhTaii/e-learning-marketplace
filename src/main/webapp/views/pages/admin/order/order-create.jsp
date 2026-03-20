<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết đơn hàng #${order.orderCode}</title>
    <base href="${pageContext.request.contextPath}/">

    <link rel="stylesheet" href="assets/css/admin/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/order-edit.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
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
                                        <span class="container-1__menu-items">
                                            <i class="fa-solid fa-table-columns"></i>
                                            <span>Dashboard</span>
                                        </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/users">
                                    <div class="menu-item__student">
                                        <span class="container-1__menu-items">
                                            <i class="fa-solid fa-user"></i>
                                            <span>Người dùng</span>
                                        </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/courses">
                                    <div class="menu-item__student">
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
                                    <div class="menu-item__student">
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
                                <a href="admin/orders" class="active">
                                    <div class="menu-item__student student-list">
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
                                    <div class="log-out__content">Thoát</div>
                                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>

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
                                    <%--                                    <p>${order.userName}</p>--%>
                                </div>
                                <div class="info-item">
                                    <label>Phương thức thanh toán</label>
                                    <%--                                    <p>${order.paymentMethodName}</p>--%>
                                </div>
                                <div class="info-item">
                                    <label>Thời gian tạo</label>
                                    <p><fmt:formatDate value="${order.createdAt}"
                                                       pattern="dd-MM-YYYY"/>
                                    </p>
                                </div>
                                <div class="info-item">
                                    <label>Thời gian thanh toán</label>
                                    <p>${order.paidAt != null ? '<fmt:formatDate value="${order.paidAt}" pattern="dd-MM-YYYY"/>' : 'Chưa thanh toán'}</p>
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

</body>
</html>