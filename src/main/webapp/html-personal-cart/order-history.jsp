<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/order-history.css">
    <script src="assets/fonts/fontawesome-free-7.1.0-web/js/jquery-3.6.0.min.js"></script>


    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/font.css">

</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="order-history-layout">
                <div class="header__title">
                    <span class="text-big-title">Lịch sử giao dịch</span>
                </div>
                <div class="history-box">
                    <ul>
                        <c:forEach items="${orderList}" var="order">
                        <li>
                            <div class="box__content">
                            <div class="box__row1">
                                <span class="time">${order.createdAt}</span>
                            </div>
                            <div class="box__row2">
                                <div class="row2__column1-order">
                                    <span class="text"><span class="text1">Mã đơn hàng: </span>${order.orderCode}</span></div>
                                <div class="row2__column2-total">
                                    <span class="text"><span class="text1">Tổng cộng: </span><fmt:formatNumber value="${order.finalAmount}" type="number" pattern="###,###"></fmt:formatNumber> đ</span></div>
                                <div class="row2__column3-payment-method">
                                    <span class="text"><span class="text1">Phương thức thanh toán:</span> ${order.paymentMethodName}</span></div>
                                <a href="show-receipt?orderId=${order.id}" class="turn-page">
                                    <div class="row2__column4-btn-receipt header__button receipt-box">
                                        <button type="button" class="btn-receipt button__btn">Biên lai</button>
                                    </div>
                                </a>

                                <div class="row2__column4-success">
                                    <span class="text">${order.status}</span>
                                </div>

                            </div>
                        </div></li>
                        </c:forEach>
                    </ul>

                </div>

            </div>


        </div>
    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>

</body>
</html>