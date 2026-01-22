<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shopping Cart</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/home.css">
    <link rel="stylesheet" href="assets/css/cart.css?v=1.0.5">
    <script src="assets/fonts/fontawesome-free-7.1.0-web/js/jquery-3.6.0.min.js"></script>
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/fonts.css">

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
                            (<span
                                id="cart-count">${not empty sessionScope.cart ? sessionScope.cart.totalQuantity : 0}</span>)
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
        <div class="grid">
            <div class="layout">
                <div class="layout__shopping-cart">
                    <div class="shopping-cart">
                        <span class="shopping-cart__title text-big-title h1">Giỏ hàng</span>
                        <span class="shopping-cart__summary summary text-paragraph ">
                        <span class="text-2xl">Sản phẩm (${sessionScope.cart.totalQuantity})</span>
                    </span>

                        <c:choose>
                            <c:when test="${not empty sessionScope.cart && sessionScope.cart.totalQuantity > 0}">
                                <div class="shopping-cart__sub-title">
                                    <div class="sub-title__column1">
                                        <input type="checkbox" class="tick" name="tick">
                                        <div class="sub_title__title text-paragraph">
                                            <span class="text-2xl">Sản phẩm</span>
                                        </div>
                                    </div>
                                    <div class="sub-title__action-price-group action-price-group">
                                        <div class="sub-title__action text-paragraph">
                                            <span class="text-2xl">Hành động</span>
                                        </div>
                                        <div class="sub-title__price text-paragraph">
                                            <span class="text-2xl">Giá</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="scrollable-order-list">
                                    <form action="update-select" id="cartForm" method="post">
                                        <ul>
                                            <c:forEach var="p" items="${sessionScope.cart.list}">
                                                <li>
                                                    <div class="shopping-cart__cart-items cart-items">
                                                        <div class="cart-items__tick">
                                                            <input type="checkbox" class="tick" name="itemSelected"
                                                                   value="${p.course.id}"
                                                                   <c:if test="${p.selected}">checked</c:if>
                                                                   onchange="this.form.submit()">
                                                        </div>

                                                        <a href="course-detail?id=${p.course.id}" class="turn-page">
                                                            <div class="cart-items__detail">
                                                                <div class="detail__image-container" style="aspect-ratio: 16 / 9;">
                                                                    <img src="${p.course.thumbnailUrl}" alt="${p.course.title}" class="image">
                                                                </div>
                                                                <div class="detail__info">
                                                                    <div class="info__name-group">
                                                                        <span class="name__title text-paragraph"><p>${p.course.title}</p></span>
                                                                    </div>
                                                                    <div class="info__rating-group">
                                                                        <span class="rating-group__tags tags text-mini">Bestseller</span>
                                                                        <span class="rating-group__rating rating text-mini">${p.course.rating}
                                                                        <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                                                    </span>
                                                                        <span class="rating-group__rating-count ratings-count text-mini">
                                                                        (${p.course.studentCount} rating)
                                                                    </span>
                                                                    </div>
                                                                    <div class="info__stats course-stats">
                                                                        <span class="stats__hours text-mini">${p.course.durationHours}h</span>
                                                                        <span class="stats__lecture text-mini">• ${p.course.lessonCount} Bài giảng</span>
                                                                        <span class="stats__level text-mini">• ${p.course.level}</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>

                                                        <div class="cart-items__action-price-group action-price-group">
                                                            <div class="cart-items__action items-action">
                                                                <a href="cart-manager?action=moveToWishlist&id=${p.course.id}" class="action__link">Thêm vào Yêu Thích</a>
                                                                <a href="cart-manager?action=delete&id=${p.course.id}" class="action__link1">Xóa</a>
                                                            </div>
                                                            <div class="cart-items__price items-price">
                                                                <div><span class="price-discounted">
                                                                <fmt:formatNumber value="${p.course.price - p.course.discountPrice}" type="number" pattern="###,###"/> đ
                                                                <i class="fa-solid fa-tag price-icon" style="color: #3722d3;"></i>
                                                            </span></div>
                                                                <div><span class="price-origin">
                                                                <fmt:formatNumber value="${p.course.price}" type="number" pattern="###,###"/> đ
                                                            </span></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </form>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="cart-empty-state">
                                    <i class="fa-solid fa-cart-shopping cart-empty-icon"></i>
                                    <div class="cart-empty-title">Giỏ hàng của bạn đang trống</div>
                                    <div class="cart-empty-description">
                                        Hãy khám phá các khóa học và thêm vào giỏ hàng để bắt đầu học tập.
                                    </div>
                                    <a href="index" class="cart-empty-link">Khám phá khóa học</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layout__checkout-summary grid">
                    <div class="checkout__row2">
                        <div class="grid__column-4">
                            <div class="row2__column1">
                                <div class="tick">
                                    <input type="checkbox" id="checkAll"
                                           <c:if test="${sessionScope.cart.selectedQuantity == sessionScope.cart.totalQuantity && sessionScope.cart.totalQuantity > 0}">checked</c:if>
                                           onchange="handleSelectAll(this)">
                                </div>
                                <label for="checkAll" class="choose text-medium">
                                    Chọn tất cả (${sessionScope.cart.totalQuantity})
                                </label>
                                <a href="cart-manager?action=removeSelected" class="text-medium remove">Xóa</a>
                                <a href="cart-manager?action=moveSelectedToWishlist" class="text-medium wishlisted" style="margin-left: 7px;text-decoration: none">Thêm vào Yêu thích</a>
                            </div>
                        </div>
                        <div class="grid__column-5">
                            <div class="checkout__total">
                                <div class="total_title">
                                    <div class="total__label">
                                        <div class="label">
                                            <div class="label__name">
                                                <span class="text-medium">Tổng cộng (${sessionScope.cart.selectedQuantity}):</span>
                                            </div>
                                            <div class="charge-note">Chưa tính phí</div>
                                        </div>
                                        <div class="total__price">
                                            <span class="price-discounted1"><fmt:formatNumber value="${sessionScope.cart.finalPriceTotal}" type="number" pattern="###,###"/> đ</span>
                                            <span class="price-origin"><fmt:formatNumber value="${sessionScope.cart.total}" type="number" pattern="###,###"/> đ</span>
                                        </div>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.cart && sessionScope.cart.selectedQuantity > 0}">
                                        <a href="payment" class="turn-page">
                                            <div class="checkout__checkout-button header__button">
                                                <button class="button__btn">Tiến hành thanh toán</button>
                                            </div>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a>
                                            <div class="checkout__checkout-button header__button">
                                                <button class="button__btn" style="background-color: #ccc; cursor: not-allowed;" disabled>Tiến hành thanh toán</button>
                                            </div>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="grid">
                    <div class="grid__row-2">
                        <div class="product__small-title text-small-title">Có thể bạn sẽ thích</div>
                        <c:forEach var="c" items="${coursesLastest}" begin="0" end="3">
                            <div class="grid__column-3 product-card-container">
                                <a href="course-detail?id=${c.id}" class="turn-page">
                                    <div class="product__small-advertisement">
                                        <div class="small-advertisement__image">
                                            <img src="${c.thumbnailUrl}" alt="${c.title}" class="img-2">
                                        </div>
                                        <div class="small-advertisement__content">
                                            <div class="content__top">
                                                <div class="content__author-name text-medium content__author-name-2">${c.authorName}</div>
                                                <div class="content__rate content__rate-2">
                                                    <div class="rate__icon"><i class="text-medium fa-regular fa-star"></i></div>
                                                    <div class="text-medium rate__number">${c.avgRating}</div>
                                                </div>
                                            </div>
                                            <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                            <div class="content__quick-info">
                                                <div class="quick-info__level">
                                                    <div class="level__icon icon"><i class="text-medium fa-solid fa-signal"></i></div>
                                                    <div class="level__text text-medium">${c.level}</div>
                                                </div>
                                                <div class="quick-info__users">
                                                    <div class="users__icon icon"><i class="text-medium fa-solid fa-users"></i></div>
                                                    <div class="users__text text-medium">${c.studentCount}</div>
                                                </div>
                                                <div class="quick-info__time">
                                                    <div class="time__icon icon"><i class="text-medium fa-regular fa-clock"></i></div>
                                                    <div class="time__text text-medium">${c.durationHours}h</div>
                                                </div>
                                            </div>
                                            <div class="content__price">
                                                <div class="price__new">
                                                    <fmt:formatNumber value="${c.price - c.discountPrice}" type="number" pattern="###,###"/> đ
                                                </div>
                                                <div class="price__old">
                                                    <fmt:formatNumber value="${c.price}" type="number" pattern="###,###"/> đ
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
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
                            <a href="" class="footer-item__link text-big">Về chúng tôi</a>
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
                                <img src="assets/image/appstore.png" alt="App Store"
                                     class="footer__download-app-img">
                            </a>
                            <a href="" class="footer__download-app-link">
                                <img src="assets/image/ggplay.png" alt="Google Play"
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

<script>
    function submitCartForm() {
        document.getElementById('cartForm').submit();
    }

    function handleSelectAll(checkbox) {
        const isChecked = checkbox.checked;
        // Gửi yêu cầu đến Servlet bạn đã viết
        window.location.href = "cart-manager?action=selectAll&status=" + isChecked;
    }
</script>

</body>
</html>