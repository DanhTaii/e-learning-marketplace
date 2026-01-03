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
    <header class="web__header">
        <div class="grid">
            <div class="header__box">
                <a href="index" class="turn-page">
                    <div class="header__logo">
                        SKILL
                    </div>
                </a>
                <div class="header__browse">
                    <div class="browse__text text-header">Danh mục</div>
                    <i class="browse__icon text-header fa-solid fa-angle-down"></i>
                    <div class="browse__display">
                        <div class="browse__container">
                            <div class="browse__container-box-1">
                                <div class="browse__box-category">
                                    <div class="box__title">
                                        <span class="category__title title">Tìm bằng Danh mục</span>
                                    </div>
                                    <ul class="browse__box-category-list list">
                                        <c:forEach var="c" items="${categories}" begin="1">
                                            <%--                                        <a href="html-partrial/result-search.jsp" class="turn-page">--%>
                                            <li class="browse__box-category-list-item text-list-item text-li">${c.name}
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="browse__container-box-2">
                                <div class="browse__container-box-2-1 box-2">
                                    <div class="box__title">
                                        <span class="category__title title">Tìm bằng kiểu khóa học</span>
                                    </div>
                                    <ul class="browse__container-box-2-list list">
                                        <li class="browse__container-box-2-list-item">
                                            <a href="html-partrial/all-course.jsp" class="turn-page">
                                                <div class="item-box">
                                                    <span class="text-list-item text-list-item-2 text-li">Tất cả khóa học</span>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="browse__container-box-2-2 box-2">
                                    <div class="box__title">
                                        <span class="category__title title">Tags</span>
                                    </div>
                                    <ul class="browse__container-box-2-list list">
                                        <li class="browse__container-box-2-list-item">
                                            <div class="item-box">
                                                <a href="html-partrial/result-search.jsp"
                                                   class="text-list-item text-list-item-2 text-li turn-page">Yêu
                                                    thích</a>
                                            </div>
                                        </li>
                                        <li class="browse__container-box-2-list-item">
                                            <div class="item-box">
                                                <a href="" class="text-list-item text-list-item-2 text-li">Mới nhất</a>
                                            </div>
                                        </li>
                                        <li class="browse__container-box-2-list-item">
                                            <div class="item-box">
                                                <a href="" class="text-list-item text-list-item-2 text-li">Phổ biến</a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header__search header__search-2">
                    <div class="search__icon">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <form action="result-search/by-title" method="get">
                        <div class="search__input">
                            <input type="text" class="input__text text-medium" name="title"
                                   placeholder="Tìm kiếm khóa học, kỹ năng,...">
                        </div>
                    </form>
                </div>
                <c:set var="loginSession" value="${not empty sessionScope.userSession}"/>
                <c:if test="${loginSession}">
                    <div class="header__class">
                        <a href="my-courses" class="turn-page text-header">
                            Khóa học của tôi
                        </a>
                    </div>
                    <div class="header__wishlist">
                        <a href="my-wishlist" class="turn-page text-header">
                            <i class="notification__icon fa-solid fa-heart text-header"></i>
                        </a>
                    </div>
                    <div class="header__cart">
                        <a href="cart" class="turn-page text-header">
                            <i class="text-header fa-solid fa-cart-shopping"></i>
                            (<span id="cart-count">${not empty sessionScope.cart ? sessionScope.cart.totalQuantity : 0}</span>)
                        </a>
                    </div>
                    <!-- Toggle checkbox ẩn -->
                    <div class="header__notification" tabindex="0">
                        <div class="notification-wrapper text-header">
                            <i class="notification__icon fa-solid fa-bell text-header"></i>
                        </div>
                        <div class="notification__popup">
                            <div class="text-header">Bạn chưa có thông báo!</div>
                        </div>
                    </div>
                    <div class="header__user">
                        <img src="assets/image/65472207_145188949876444_2344275901291692032_n.jpg" alt=""
                             class="user__avatar">
                        <div class="user__display">
                            <div class="user__container">
                                <div class="user__profile">
                                    <div class="user__profile-avatar">
                                        <img src="assets/image/65472207_145188949876444_2344275901291692032_n.jpg"
                                             alt=""
                                             class="user__avatar-mini">
                                    </div>
                                    <div class="user__profile-name">
                                        <a href="" class="name-text text-header">Ngoc Minh</a>
                                    </div>
                                    <div class="user__profile-bio">
                                        <a href="" class="bio-text">Thêm tiểu sử</a>
                                    </div>
                                    <a href="account-profile" class="turn-page">
                                        <div class="user__profile-btn">
                                            <button class="user-btn button__btn text-header">Xem thông tin</button>
                                        </div>
                                    </a>
                                </div>
                                <div class="user__menu">
                                    <ul class="user__menu-list">
                                        <li class="user__menu-list-item">
                                            <a href="html-personal/my-course.jsp" class="turn-page">
                                                <div class="user__menu-list-item-box text-li">
                                                    Khóa học
                                                </div>
                                            </a>
                                        </li>
                                        <li class="user__menu-list-item">
                                            <a href="my-wishlist" class="turn-page">
                                                <div class="user__menu-list-item-box text-li">
                                                    Yêu thích
                                                </div>
                                            </a>
                                        </li>
                                        <li class="user__menu-list-item">
                                            <a href="order-history" class="turn-page">
                                                <div class="user__menu-list-item-box text-li">
                                                    Lịch sử giao dịch
                                                </div>
                                            </a>
                                        </li>
                                        <li class="user__menu-list-item">
                                            <a href="logout" class="turn-page">
                                                <div class="user__menu-list-item-box sign-out text-li">
                                                    Đăng xuất
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not loginSession}">
                    <div class="header__button-box">
                        <a href="html-authentication/sign-up.jsp" class="turn-page">
                            <div class="header__button sign-in-box">
                                <button class="button__btn text-header sign-in">Đăng ký</button>
                            </div>
                        </a>
                        <a href="html-authentication/sign-in.jsp" class="turn-page">
                            <div class="header__button sign-up-box">
                                <button class="button__btn text-header sign-up">Đăng nhập</button>
                            </div>
                        </a>
                    </div>
                </c:if>
                <%--                <div class="header__button-box">--%>
                <%--                    <a href="html-authentication/sign-up.jsp" class="turn-page">--%>
                <%--                        <div class="header__button sign-in-box">--%>
                <%--                            <button class="button__btn text-header sign-in">Đăng ký</button>--%>
                <%--                        </div>--%>
                <%--                    </a>--%>
                <%--                    <a href="html-authentication/sign-in.jsp" class="turn-page">--%>
                <%--                        <div class="header__button sign-up-box">--%>
                <%--                            <button class="button__btn text-header sign-up">Đăng nhập</button>--%>
                <%--                        </div>--%>
                <%--                    </a>--%>
                <%--                </div>--%>
            </div>
        </div>
    </header>
    <div class="web__container">
        <form action="confirm-payment" method="post" class="payment-layout">
        <div class="grid">

            <div class="payment-layout">

                <div class="grid__column-8">
                    <div class="main-payment-wrapper">

                        <div class="payment-section">
                            <h2 class="section-title">Chọn phương thức thanh toán</h2>
                            <div class="payment-options-grid">

                                <c:forEach var="method" items="${paymentMethod}">
                                    <label class="payment-item">
                                        <input type="radio" name="payment-method-id" value="${method.id}">

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
                                        <button type="submit" class="button__btn pay-btn"><i
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
    <footer class="web__footer">
        <div class="grid">
            <div class="grid__row-1">
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">Cơ sở</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-big">Về chúng tôi
                            </a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Chăm sóc khách hàng</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Báo chí</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Blog</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">LÀM VIỆC VỚI CHÚNG TÔI</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Chương trình Affiliate</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Quan hệ hợp tác</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">DẠY HỌC CÙNG CHÚNG TÔI</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Trở thành giảng viên</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Trung tâm hỗ trợ giảng viên</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Quy định dành cho giảng viên</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">MUA HÀNG</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Quà tặng học viên</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Sản phẩm kỹ thuật số</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Buổi học trực tiếp</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">DI ĐỘNG</h3>
                    <div class="footer__download">
                        <div class="footer__download-apps">
                            <a href="" class="footer__download-app-link">
                                <img src="../assets/image/appstore.png" alt="App Store"
                                     class="footer__download-app-img">
                            </a>
                            <a href="" class="footer__download-app-link">
                                <img src="../assets/image/ggplay.png" alt="Google Play"
                                     class="footer__download-app-img">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer__bottom">
            <div class="grid">
                <ul class="ul__text text-medium">© SOFTSKILL, Inc. 2025
                    <li class="footer__text text-medium">Hỗ trợ</li>
                    <li class="footer__text text-medium">Quyền riêng tư</li>
                    <li class="footer__text text-medium">Điều khoản</li>
                    <li class="footer__text text-medium">Lựa chọn quyền riêng tư của bạn</li>
                </ul>
            </div>
            <div class="grid">
                <p class="ul__text text-medium">Chịu trách nhiệm quản lý nội dung: Nhóm 20</p>
            </div>
            <div class="grid">
                <p class="ul__text text-medium">© 2025 - Bản quyền thuộc về Nhóm 21</p>
            </div>
        </div>
    </footer>
</div>

</body>
</html>