<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Receipt</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/base/default.css">
    <link rel="stylesheet" href="assets/css/cart/receipt.css?v=1.0.2">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>

<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="payment-success-layout">
                <div class="header-box">
                    <div class="box__row1-header">
                        <span class="text-big-title">Biên lai</span></div>
                    <div class="box__row2-header">
                        <div><span class="text-small-title">Biên nhận cho Giỏ hàng - <fmt:formatDate value="${order.createdAt}" pattern="dd 'tháng' MM, yyyy" /> </span></div>
                        <div>
                            <c:choose>
                                <c:when test="${order.status == 'PAID'}">
                <span class="status text-small-title" style="color: #28a745;">
                    <i class="fa-solid fa-circle-check icon-check"></i>
                    Thanh toán thành công
                </span>
                                </c:when>
                                <c:otherwise>
                <span class="status text-small-title" style="color: #dc3545;">
                    <i class="fa-solid fa-circle-xmark icon-check"></i>
                    Thanh toán thất bại
                </span>
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>

                </div>
                <div class="grid summary-box1">
                    <div class="grid summary-box">
                        <div class="box__row1">
                            <span class="row1__id text">Mã đơn hàng:</span>
                            <span class="number"><c:out value="${order.orderCode}"/></span>
                        </div>
                        <div class="box__row2">
                            <span class="row2__time text">Ngày:</span>
                            <span class="number"><fmt:formatDate value="${order.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Giá gốc:</span>
                            <span class="number"><c:out value="${order.formatTotal}"/></span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Số tiền giảm:</span>
                            <span class="number">-<c:out value="${order.formatDiscount}"/></span>
                        </div>
                        <c:if test="${not empty order.voucherCode && order.voucherAmount > 0}">
                            <div class="box__row3">
                                <span class="row3__total text">Voucher áp dụng (<strong ><c:out value="${order.voucherCode}"/></strong>):</span>
                                <span class="number">-<c:out value="${order.formatVoucherAmount}"/></span>
                            </div>
                        </c:if>
                        <div class="box__row3">
                            <span class="row3__total text">Tổng cộng:</span>
                            <span class="number"><c:out value="${order.formatFinal}"/></span>
                        </div>
                        <div class="box__row4">
                            <span class="row4__payment-method text">Phương thức thanh toán:</span>
                            <span class="number"><c:out value="${paymentMethod.name}"/></span>
                        </div>


                    </div>

                </div>
                <div class="order-title">
                    <div class="title__2">
                        <div id="items">Hóa đơn chi tiết</div>
                        <div class=" price-header">
                            <span id="price">Giá</span>
                        </div>
                    </div>
                </div>
                <div class="scrollable-order-list">
                    <ul>
                        <c:forEach var="item" items="${orderItemList}">
                        <li>
                            <div class="grid__row-2">
                            <div class="order-items">
                                <div class="items__content">
                                    <div class="content__image">
                                        <img srcset="${item.thumbnailUrl}"
                                             alt="${item.courseTitle}" class="image">
                                    </div>
                                    <div class="content__name text-paragraph">
                                        <p class="items__name "><c:out value="${item.courseTitle}"/></p>
                                    </div>

                                </div>

                                <div class="items__price">
                                    <span class="amount-discounted "><c:out value="${item.priceAtPurchaseFormat}"/> </span>
                                </div>

                            </div>
                        </div>
                        </li>
                        </c:forEach>
                    </ul>


                </div>

                <div class="button-box ">
                    <a href="index" class="turn-page">
                        <div class="header__button button">
                            <button type="button" class="home-btn dark-button">Trang chủ</button>
                        </div>
                    </a>
                    <c:if test="${order.status == 'PAID'}">
                        <a href="personal/my-courses" class="turn-page">
                            <div class="header__button button">
                                <button type="button" class="home-btn button__btn">Khóa học của tôi</button>
                            </div>
                        </a>
                    </c:if>

                    <c:if test="${order.status != 'PAID'}">
                        <a href="personal/cart" class="turn-page">
                            <div class="header__button button">
                                <button type="button" class="home-btn button__btn" style="background-color: #ff9800;">Quay lại giỏ hàng</button>
                            </div>
                        </a>
                    </c:if>

                </div>
            </div>


        </div>

    </div>
    <jsp:include page="/views/layouts/footer.jsp"/>
</div>

<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>