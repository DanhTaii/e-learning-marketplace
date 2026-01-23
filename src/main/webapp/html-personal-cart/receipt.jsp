<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Receipt</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/receipt.css?v=1.0.2">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>

<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="payment-success-layout">
                <div class="header-box">
                    <div class="box__row1-header">
                        <span class="text-big-title">Biên lai</span></div>
                    <div class="box__row2-header">
                        <div><span class="text-small-title">Biên nhận cho Giỏ hàng - <fmt:formatDate value="${order.createdAt}" pattern="dd 'tháng' MM, yyyy" /> </span></div>
                        <div>
                            <span class="status text-small-title">
                                <i class="fa-solid fa-circle-check icon-check" style="color: #018d4a;"></i>
                                Thành công</span></div>

                    </div>

                </div>
                <div class="grid summary-box1">
                    <div class="grid summary-box">
                        <div class="box__row1">
                            <span class="row1__id text">Mã đơn hàng:</span>
                            <span class="number">${order.orderCode}</span>
                        </div>
                        <div class="box__row2">
                            <span class="row2__time text">Ngày:</span>
                            <span class="number">${order.createdAt}</span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Giá gốc:</span>
                            <span class="number"><fmt:formatNumber value="${order.totalAmount}" type="number" pattern="###,###" /> đ</span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Số tiền giảm:</span>
                            <span class="number">-<fmt:formatNumber value="${order.discountAmount}" type="number" pattern="###,###" /> đ</span>
                        </div>
                        <div class="box__row3">
                            <span class="row3__total text">Tổng cộng:</span>
                            <span class="number"><fmt:formatNumber value="${order.finalAmount}" type="number" pattern="###,###" /> đ</span>
                        </div>
                        <div class="box__row4">
                            <span class="row4__payment-method text">Phương thức thanh toán:</span>
                            <span class="number">${paymentMethod.name}</span>
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
                                    <div class="content__image"  style="aspect-ratio: 16/9">
                                        <img srcset="${item.thumbnailUrl}"
                                             alt="${item.courseTitle}" class="image">
                                    </div>
                                    <div class="content__name text-paragraph">
                                        <p class="items__name ">${item.courseTitle}</p>
                                    </div>

                                </div>

                                <div class="items__price">
                                    <span class="amount-discounted "><fmt:formatNumber value="${item.priceAtPurchase}" type="number" pattern="###,###" /> đ </span>
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
                    <a href="personal/my-courses" class="turn-page">
                        <div class="header__button button">
                            <button type="button" class="home-btn button__btn">Khóa học của tôi</button>
                        </div>
                    </a>

                </div>
            </div>


        </div>

    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>



</body>
</html>