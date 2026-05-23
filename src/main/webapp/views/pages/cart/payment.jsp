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
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/cart/payment.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/home.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/cart/voucher.css?v=<%=System.currentTimeMillis()%>">
    <script src="assets/fonts/fontawesome-free-7.1.0-web/js/jquery-3.6.0.min.js"></script>
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">


    <link rel="stylesheet" href="assets/fonts/static/." as="font" type="font/ttf" crossorigin>
    <link rel="stylesheet" href="assets/css/base/modal-notification.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header-simple.jsp"/>
    <div class="web__container">
        <form id="payment-main-form" action="confirm-payment" method="post" class="payment-layout">
            <div class="grid">

                <div class="payment-layout">

                    <div class="grid__column-8">
                        <div class="main-payment-wrapper">

                            <div class="payment-section">
                                <h2 class="section-title">Chọn phương thức thanh toán</h2>
                                <div class="payment-options-grid">

                                    <c:forEach var="method" items="${paymentMethod}" varStatus="status">
                                        <label class="payment-item">
                                            <input type="radio" name="payment-method-id"
                                                   value="${method.id}"${status.first ? 'checked' : ''} >

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
                                                        <div class="content__image">

                                                            <img srcset="${p.course.thumbnailUrl}"

                                                                 alt="" class="image">

                                                        </div>
                                                        <div class="content__name">

                                                            <p class="items__name ">${p.course.title}</p>

                                                        </div>
                                                    </div>
                                                    <div class="order-item__price">
                                                    <span class="amount-discounted">${p.priceFormat} <i
                                                            class="fa-solid fa-tag price-icon"></i></span>

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
                                    <div class="checkout__discount checkout-discount-wrapper">
                                        <div class="checkout-discount-header">
        <span class="text-medium checkout-discount-title">
            <i class="fa-solid fa-ticket"></i> Voucher / Khuyến mãi
        </span>
                                            <a href="#" onclick="event.preventDefault(); openModal('voucherModal')" class="checkout-discount-link">
                                                Chọn mã <i class="fa-solid fa-chevron-right"></i>
                                            </a>
                                        </div>

                                        <div id="applied-voucher-info" class="applied-voucher-box" style="display: ${not empty sessionScope.appliedVoucher ? 'flex' : 'none'};">
                                            <div class="applied-voucher-box-left">
                                                <span id="applied-voucher-code" class="applied-voucher-code">${sessionScope.appliedVoucher.code}</span>
                                            </div>
                                            <a href="#" onclick="removeVoucher(event)" class="btn-remove-voucher">Bỏ chọn</a>
                                        </div>
                                    </div>
                                    <span class="detail__title ">Tóm tắt hóa đơn</span>
                                    <div class="detail__price">
                                        <div class="price__original">
                                            <span class="price__original text-medium original">Giá gốc: </span>
                                            <span class="price__original text-medium amount">${sessionScope.cart.formatedTotal}</span>
                                        </div>
                                        <div class="price__discount">
                                            <span class="price__discount text-medium discount">Số tiền giảm: </span>
                                            <span class="price__discount text-medium amount">- ${sessionScope.cart.formatedDiscountPriceTotal}</span>

                                        </div>
                                        <c:if test="${not empty summary.appliedVoucher}">
                                            <div class="price__discount voucher-row">
                                                <span class="price__discount text-medium discount voucher-label">
                                                    <i class="fa-solid fa-ticket"></i> Voucher (${summary.appliedVoucher.code}):
                                                </span>
                                                <span class="price__discount text-medium amount voucher-amount"> - ${summary.discountStr} </span>
                                            </div>
                                        </c:if>
                                        <div class="price__total index">
                                            <span class="price__total ">Tổng cộng (${sessionScope.cart.selectedQuantity}): </span>
                                            <span class="price__total text-medium amount">${summary.totalToPayStr}</span>
                                        </div>


                                    </div>


                                    <div class="invoice__pay-btn header__button index-btn">
                                        <button type="submit" id="pay-btn" class="button__btn pay-btn"><i
                                                class="fa-solid fa-bag-shopping shop-icon"
                                        ></i> Thanh toán
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
        <div id="popup__add-payment-confirm-black" class="modal-backdrop modal">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="header-content modal-title">THÔNG BÁO</div>
                </div>
                <div class="course-body">
                    <div class="body-title-black">BẠN CÓ XÁC NHẬN THANH TOÁN?</div>
                    <div class="body-icon"><i class="fa-solid fa-receipt check-popup-black"></i></div>
                    <div class="body-content">Bạn có chắc chắn muốn thanh toán? Hành động này không thể hoàn tác</div>
                    <div class="body-selection">
                        <div class="body-selection__item x__icon">
                            <button type="button" class="dark-button dark-button-2"
                                    onclick="closeModal('popup__add-payment-confirm-black')">Hủy
                            </button>
                        </div>
                        <div class="body-selection__item x__icon">
                            <button type="button" id="btn-confirm-payment" class="button__btn">Xác nhận</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="voucherModal" class="voucher-modal">
        <div class="voucher-modal-overlay" onclick="closeModal('voucherModal')"></div>
        <div class="voucher-modal-content">
            <div class="voucher-modal-header">
                <h3>Mã khuyến mãi</h3>
                <button class="btn-close-modal" onclick="closeModal('voucherModal')">&times;</button>
            </div>
            <div class="voucher-modal-body">
                <c:choose>
                    <c:when test="${not empty sessionScope.userId}">
                        <div class="voucher-input-group">
                            <input type="text" id="manualVoucherCode" placeholder="Nhập mã giảm giá...">
                            <button onclick="applyManualVoucher()">Áp dụng</button>
                        </div>
                        <input type="hidden" id="savedVoucherCode" value="${not empty sessionScope.appliedVoucher ? sessionScope.appliedVoucher.code : ''}">
                        <div class="voucher-list">
                            <c:forEach var="v" items="${listVoucher}">
                                <div class="voucher-item">
                                    <div class="voucher-icon">
                                        <div class="voucher-title">${v.code}</div>
                                    </div>
                                    <div class="voucher-info">
                                        <div class="voucher-title">${v.title}</div>
                                        <div class="voucher-desc">${v.description}</div>
                                        <div class="voucher-exp">
                                            HSD: <fmt:formatDate value="${v.endDate}" pattern="dd-MM-yyyy" />
                                        </div>

                                        <c:if test="${not empty v.usageLimit and v.usageLimit > 0}">
                                            <div class="voucher-usage-wrapper">
                                                <div class="voucher-usage-bar">
                                                    <div class="voucher-usage-progress" style="width: ${(v.usedCount / v.usageLimit) * 100}%;"></div>
                                                </div>
                                                <div class="voucher-usage-text">
                                                    <span>Đã dùng: ${v.usedCount} / ${v.usageLimit}</span>
                                                    <c:if test="${(v.usedCount / v.usageLimit) > 0.8}">
                                                        <span style="color: #dc3545; font-weight: 600;">Sắp hết!</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div>
                                        <c:choose>
                                            <%-- Trường hợp 1: User đã dùng voucher này rồi (Check qua DB) --%>
                                            <c:when test="${v.usedByCurrentUser}">
                                                <button class="btn-select-voucher" disabled
                                                        style="background-color: #d6d6d6; color: #888; border: none; cursor: not-allowed; opacity: 0.7;">
                                                    Đã sử dụng
                                                </button>
                                            </c:when>

                                            <%-- Trường hợp 2: Voucher đã hết lượt sử dụng trên hệ thống --%>
                                            <c:when test="${not empty v.usageLimit and v.usedCount >= v.usageLimit}">
                                                <button class="btn-select-voucher" disabled
                                                        style="background-color: #d6d6d6; color: #888; border: none; cursor: not-allowed; opacity: 0.7;">
                                                    Hết lượt
                                                </button>
                                            </c:when>

                                            <%-- Trường hợp 3: Voucher khả dụng --%>
                                            <c:otherwise>
                                                <button class="btn-select-voucher" onclick="selectVoucher('${v.code}')">
                                                    Dùng
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:if test="${empty listVoucher}">
                                <p style="text-align: center; padding: 20px; color: #888;">Hiện không có mã giảm giá nào khả dụng.</p>
                            </c:if>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="auth-required-state">
                            <div class="auth-icon-wrapper">
                                <i class="fa-solid fa-user-lock"></i>
                            </div>
                            <h4 class="auth-title">Bạn chưa đăng nhập</h4>
                            <p class="auth-desc">
                                Vui lòng đăng nhập để xem các mã giảm giá dành riêng cho thành viên.
                            </p>
                            <a href="sign-in" class="btn-select-voucher btn-login-now">
                                Đăng nhập ngay
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <jsp:include page="/views/layouts/footer.jsp"/>
    <jsp:include page="/views/components/toast.jsp"/>

</div>
<script src="assets/javascript/features/cart/payment-modal.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/features/cart/cart-action.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/features/cart/voucher.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/modal/modal.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>