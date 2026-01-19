<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Payment</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/payment.css?v=1.0.1">
    <link rel="stylesheet" href="assets/css/home.css">
    <script src="assets/fonts/fontawesome-free-7.1.0-web/js/jquery-3.6.0.min.js"></script>
    <!--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>-->
    <!--    <script src=""></script>-->

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">


    <link rel="stylesheet" href="assets/fonts/static/." as="font" type="font/ttf" crossorigin>
    <link rel="stylesheet" href="assets/css/modal-notification.css">
</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <form action="confirm-payment" method="post" class="payment-layout">
        <div class="grid">

            <div class="payment-layout">

                <div class="grid__column-8">
                    <div class="main-payment-wrapper">

                        <div class="payment-section">
                            <h2 class="section-title">Chọn phương thức thanh toán</h2>
                            <div class="payment-options-grid">

                                <c:forEach var="method" items="${paymentMethod}" varStatus="status">
                                    <label class="payment-item">
                                        <input type="radio" name="payment-method-id" value="${method.id}"${status.first ? 'checked' : ''} >

                                        <div class="payment-item__content">
                                            <img src="${method.iconUrl}" alt="${method.name}">
                                            <span>${method.name}</span>

                                            <div class="select-badge"><i class="fa-solid fa-circle-check"></i></div>
                                        </div>
                                    </label>
                                </c:forEach>

                            </div>
                        </div>

                        <hr class="divider">

                        <div class="order-section">
                            <h2 class="section-title">Hóa đơn chi tiết</h2>
                            <div class="title__2">

                                <div id="items">Sản phẩm</div>

                                <div class=" price-header">

                                    <span id="price">Giá</span>

                                </div>
                            </div>
                            <div class="scrollable-order-list">
                                <ul>
                                    <c:forEach var="p" items="${sessionScope.cart.selectedItems}">
                                        <li>
                                            <div class="order-item-row">
                                                <div class="order-item__info">
                                                    <div class="content__image" style="aspect-ratio: 16/9">

                                                        <img srcset="${p.course.thumbnailUrl}"

                                                             alt="" class="image">

                                                    </div>
                                                    <div class="content__name">

                                                        <p class="items__name ">${p.course.title}</p>

                                                    </div>
                                                </div>
                                                <div class="order-item__price">
                                                    <span class="amount-discounted"><fmt:formatNumber value="${p.price}" type="number" pattern="###,###" /> đ <i
                                                            class="fa-solid fa-tag price-icon"
                                                            style="color: #3722d3;"></i></span>

                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="grid__column-4">
                    <div class="invoice">
                        <div class="invoice__info">
                            <div class="info__detail info">
                                <span class="detail__title ">Tóm tắt hóa đơn</span>
                                <div class="detail__price">
                                    <div class="price__original">
                                        <span class="price__original text-medium original">Giá gốc: </span>
                                        <span class="price__original text-medium amount"><fmt:formatNumber value="${sessionScope.cart.total}" type="number" pattern="###,###" /> đ</span>
                                    </div>
                                    <div class="price__discount">
                                        <span class="price__discount text-medium discount">Số tiền giảm: </span>
                                        <span class="price__discount text-medium amount">- <fmt:formatNumber value="${sessionScope.cart.discountPriceTotal}" type="number" pattern="###,###" /> đ</span>

                                    </div>
                                    <div class="price__total index">
                                        <span class="price__total ">Tổng cộng (${sessionScope.cart.selectedQuantity}): </span>
                                        <span class="price__total text-medium amount"><fmt:formatNumber value="${sessionScope.cart.finalPriceTotal}" type="number" pattern="###,###" /> đ</span>
                                    </div>


                                </div>


                                    <div class="invoice__pay-btn header__button index-btn">
                                        <button type="submit" id="pay-btn" class="button__btn pay-btn"><i
                                                class="fa-solid fa-bag-shopping shop-icon"
                                                style="color: #000000;"></i> Thanh toán
                                        </button>
                                    </div>

                                <div class="detail__policy">
                                    <span class="text-big main-text">Đảm bảo hoàn tiền trong 30 ngày</span>
                                    <span class="text-medium sub-text">Không hài lòng? Nhận lại đủ tiền trong vòng 30 ngày. Đơn giản và dễ dàng!</span>
                                </div>


                            </div>
                        </div>

                    </div>


            </div>

            </div>

        </div>
        </form>
        <div id="popup__add-payment-confirm-black" class="modal-backdrop">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="header-content modal-title">THÔNG BÁO</div>
                </div>
                <div class="course-body">
                    <div class="body-title-black">BẠN CÓ XÁC NHẬN THANH TOÁN?</div>
                    <div class="body-icon"><i class="fa-solid fa-receipt check-popup-black"></i></div>
                    <div class="body-content">Bạn có chắc chắn muốn thanh toán? Hành động này không thể hoàn tác </div>
                    <div class="body-selection">
                        <div class="body-selection__item x__icon">
                            <a href="#" class="">
                                <button class="dark-button dark-button-2">Hủy</button>
                            </a>
                        </div>
                        <div class="body-selection__item x__icon">
                            <a href="receipt.jsp" class="">
                                <button class="button__btn">Xác nhận</button>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div id="popup__add-payment-method-success" class="modal-backdrop">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="header-content modal-title">THÔNG BÁO</div>
                </div>
                <div class="course-body">
                    <div class="body-title">THAO TÁC HOÀN TẤT</div>
                    <div class="body-icon"><i class="fa-solid fa-check check-popup"></i></div>
                    <div class="body-content">Phương thức thanh toán đã thêm</div>
                    <div class="body-selection">
                        <div class="body-selection__item x__icon">
                            <a href="#" class="">
                                <button class="dark-button">Tiếp tục</button>
                            </a>
                        </div>
<!--                        <div class="body-selection__item x__icon">-->
<!--                            <a href="../html-personal-cart/cart.jsp" class="">-->
<!--                                <button class="button__btn">Tới giỏ hàng</button>-->
<!--                            </a>-->
<!--                        </div>-->
                    </div>

                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>

</body>
</html>