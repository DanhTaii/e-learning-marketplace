<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Soft Skill</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/home.css?v=1.0.2">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="web">
    <header class="web__header">
        <div class="grid">
            <div class="header__box">
                <a href="./index.jsp" class="turn-page">
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
                                            <a href="html-personal-cart/order-history.jsp" class="turn-page">
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
        <div class="container__intro">
            <div class="grid">
                <div class="grid__row-2">
                    <div class="grid__column-8">
                        <div class="intro__slogan text-big-title">PHÁT TRIỂN & NÂNG CAO KỸ NĂNG CỦA BẠN</div>
                        <div class="intro__benefits">
                            <div class="benefits-box-1">
                                <div class="benefits__list">
                                    <div class="list-item__icon">
                                        <i class="text-icon fa-solid fa-circle-check"></i>
                                    </div>
                                    <div class="list-item__text">Kỹ năng Giao tiếp</div>
                                </div>
                                <div class="benefits__list">
                                    <div class="list-item__icon">
                                        <i class="text-icon fa-solid fa-circle-check"></i>
                                    </div>
                                    <div class="list-item__text">Quản lý Thời gian</div>
                                </div>
                                <div class="benefits__list">
                                    <div class="list-item__icon">
                                        <i class="text-icon fa-solid fa-circle-check"></i>
                                    </div>
                                    <div class="list-item__text">Tư duy Phát triển</div>
                                </div>
                            </div>
                            <div class="benefits-box-2">
                                <div class="benefits__list">
                                    <div class="list-item__icon">
                                        <i class="text-icon fa-solid fa-circle-check"></i>
                                    </div>
                                    <div class="list-item__text">Lãnh đạo Bản thân</div>
                                </div>
                                <div class="benefits__list">
                                    <div class="list-item__icon">
                                        <i class="text-icon fa-solid fa-circle-check"></i>
                                    </div>
                                    <div class="list-item__text">Giải quyết Vấn đề</div>
                                </div>
                                <div class="benefits__list">
                                    <div class="list-item__icon">
                                        <i class="text-icon fa-solid fa-circle-check"></i>
                                    </div>
                                    <div class="list-item__text">Làm việc Hiệu quả</div>
                                </div>
                            </div>
                        </div>
                        <div class="intro__stats">
                            <div class="stats__box stats__box-1">
                                <div class="stats__number text-big">${totalUsers}</div>
                                <div class="stats__text text-big">Học viên</div>
                            </div>
                            <div class="stats__box stats__box-2">
                                <div class="stats__number text-big">${totalCourses}</div>
                                <div class="stats__text text-big">Khóa học</div>
                            </div>
                            <div class="stats__box stats__box-3">
                                <div class="stats__number text-big">${avgRating}</div>
                                <div class="stats__text text-big">Đánh giá</div>
                            </div>
                        </div>
                    </div>
                    <div class="grid__column-4-in-12">
                        <div class="intro__image-box">
                            <img src="./assets/image/Frame%20118.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-3">
                    <div class="container__category">
                        <a href="all-courses" class="turn-page">
                            <div class="container__category-title">Tất cả khóa học</div>
                        </a>
                        <ul class="container__category-list text-li">
                            <c:forEach var="c" items="${categories}">
                                <a href="result-search/by-category?id=${c.id}" class="turn-page">
                                    <li class="container__category-list-item text-li">${c.name}</li>
                                </a>
                            </c:forEach>
                        </ul>

                    </div>
                </div>
                <div class="grid__colum-9">
                    <div class="container__product">
                        <div class="product__big-title text-big-title">Gợi ý cho bạn</div>
                        <a href="course-detail?id=${courseMostPopular.id}" class="turn-page">
                            <div class="product__big-advertisement">
                                <div class="big-advertisement__image">
                                    <img src="${courseMostPopular.thumbnailUrl}"
                                         alt="" class="img-1">
                                </div>
                                <div class="big-advertisement__content">
                                    <div class="content__title">${courseMostPopular.title}</div>
                                    <div class="content__information">
                                        <div class="content__top content__top-2">
                                            <div class="content__author-name text-medium content__author-name-3">${courseMostPopular.authorName}</div>
                                            <div class="content__rate content__rate-3">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">5</div>
                                            </div>
                                        </div>
                                        <div class="content__quick-info for-fix">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">${courseMostPopular.level}</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">Số lượng học viên</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text text-medium">${courseMostPopular.durationHours}h</div>
                                            </div>
                                        </div>
                                        <div class="content__price content__price-2">
                                            <div class="price__new">${courseMostPopular.price - courseMostPopular.discountPrice}đ</div>
                                            <div class="price__old">${courseMostPopular.price}đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="product__small">
                        <div class="grid">
                            <div class="grid__row-2">
                                <div class="product__small-title text-small-title">Yêu thích</div>
                                <c:forEach var="c" items="${coursesLiked}">
                                    <div class="grid__column-4 product-card-container">

                                        <a href="course-detail?id=${c.id}" class="turn-page">
                                            <div class="product__small-advertisement">
                                                <div class="small-advertisement__image">
                                                    <img src="${c.thumbnailUrl}" alt="${c.title}" class="img-2">
                                                </div>
                                                <div class="small-advertisement__content">
                                                    <div class="content__top">
                                                        <div class="content__author-name text-medium">${c.authorName}</div>
                                                        <div class="content__rate">
                                                            <i class="fa-regular fa-star"></i>
                                                            <span class="rate__number">5</span>
                                                        </div>
                                                    </div>
                                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                                    <div class="content__quick-info">
                                                    </div>
                                                    <div class="content__price">
                                                        <div class="price__new">${c.price - c.discountPrice}đ</div>
                                                        <div class="price__old">${c.price}đ</div>
                                                    </div>
                                                </div>
                                                <div class="home-product-item__favourite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span>Yêu thích</span>
                                                </div>
                                            </div>
                                        </a>

                                        <div class="product-hover-info">
                                            <h4 class="hover-title">${c.title}</h4>
                                            <div class="hover-goals">${c.goals != null ? c.goals : 'Khóa học kỹ năng mềm chuyên sâu...'}</div>
                                            <div class="hover-actions">
                                                <form action="cart-add" method="POST">
                                                    <input type="hidden" name="courseId" value="${c.id}">
                                                    <button type="submit" class="btn-add-cart">Thêm vào giỏ</button>
                                                </form>
                                                <button class="btn-wishlist" onclick="addToWishlist(${c.id})">
                                                    <i class="fa-regular fa-heart"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                            <div class="grid__row-2">
                                <div class="product__small-title text-small-title">Mới nhất</div>
                                <c:forEach var="c" items="${coursesLastest}">
                                    <div class="grid__column-4">
                                        <a href="course-detail?id=${c.id}" class="turn-page">
                                            <div class="product__small-advertisement">
                                                <div class="small-advertisement__image">
                                                    <img src="${c.thumbnailUrl}"
                                                         alt="Từ Chối Mà Vẫn Được Yêu Quý" class="img-2">
                                                </div>
                                                <div class="small-advertisement__content">
                                                    <div class="content__top">
                                                        <div class="content__author-name text-medium content__author-name-2">${c.authorName}</div>
                                                        <div class="content__rate content__rate-2">
                                                            <div class="rate__icon"><i
                                                                    class="text-medium fa-regular fa-star"></i></div>
                                                            <div class="text-medium rate__number">5</div>
                                                        </div>
                                                    </div>
                                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                                    <div class="content__quick-info">
                                                        <div class="quick-info__level">
                                                            <div class="level__icon icon"><i
                                                                    class="text-medium fa-solid fa-signal"></i></div>
                                                            <div class="level__text text-medium">${c.level}</div>
                                                        </div>
                                                        <div class="quick-info__users">
                                                            <div class="users__icon icon"><i
                                                                    class="text-medium fa-solid fa-users"></i></div>
                                                            <div class="users__text text-medium">Số lượng học viên</div>
                                                        </div>
                                                        <div class="quick-info__time">
                                                            <div class="time__icon icon"><i
                                                                    class="text-medium fa-regular fa-clock"></i></div>
                                                            <div class="time__text text text-medium">${c.durationHours}h</div>
                                                        </div>
                                                    </div>
                                                    <div class="content__price">
                                                        <div class="price__new">${c.price - c.discountPrice}đ</div>
                                                        <div class="price__old">${c.price}đ</div>
                                                        <div class="quick-info__save">
                                                            <a href="my-wishlist?courseId=${c.id}" class="turn-page-2">
                                                                <i class="quick-info__save__icon fa-solid fa-heart" style="color: ${c.inWishlist ? 'red' : 'var(--dark-blue)'};"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="home-product-item__favourite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span>Mới nhất</span>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="product-hover-info">
                                            <h4 class="hover-title">${c.title}</h4>
                                            <div class="hover-goals">${c.goals != null ? c.goals : 'Khóa học kỹ năng mềm chuyên sâu...'}</div>
                                            <div class="hover-actions">
                                                <form action="cart-add" method="POST">
                                                    <input type="hidden" name="courseId" value="${c.id}">
                                                    <button type="submit" class="btn-add-cart">Thêm vào giỏ</button>
                                                </form>
                                                <button class="btn-wishlist" onclick="addToWishlist(${c.id})">
                                                    <i class="fa-regular fa-heart"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                            <div class="grid__row-2">
                                <div class="product__small-title text-small-title">Phổ biến</div>
                                <c:forEach var="c" items="${coursesFeature}">
                                    <div class="grid__column-4">
                                        <a href="course-detail?id=${c.id}" class="turn-page">
                                            <div class="product__small-advertisement">
                                                <div class="small-advertisement__image">
                                                    <img src="${c.thumbnailUrl}"
                                                         alt="Từ Chối Mà Vẫn Được Yêu Quý" class="img-2">
                                                </div>
                                                <div class="small-advertisement__content">
                                                    <div class="content__top">
                                                        <div class="content__author-name text-medium content__author-name-2">${c.authorName}</div>
                                                        <div class="content__rate content__rate-2">
                                                            <div class="rate__icon"><i
                                                                    class="text-medium fa-regular fa-star"></i></div>
                                                            <div class="text-medium rate__number">5</div>
                                                        </div>
                                                    </div>
                                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                                    <div class="content__quick-info">
                                                        <div class="quick-info__level">
                                                            <div class="level__icon icon"><i
                                                                    class="text-medium fa-solid fa-signal"></i></div>
                                                            <div class="level__text text-medium">${c.level}</div>
                                                        </div>
                                                        <div class="quick-info__users">
                                                            <div class="users__icon icon"><i
                                                                    class="text-medium fa-solid fa-users"></i></div>
                                                            <div class="users__text text-medium">Số lượng học viên</div>
                                                        </div>
                                                        <div class="quick-info__time">
                                                            <div class="time__icon icon"><i
                                                                    class="text-medium fa-regular fa-clock"></i></div>
                                                            <div class="time__text text text-medium">${c.durationHours}h</div>
                                                        </div>
                                                    </div>
                                                    <div class="content__price">
                                                        <div class="price__new">${c.price - c.discountPrice}đ</div>
                                                        <div class="price__old">${c.price}đ</div>
                                                        <div class="quick-info__save">
                                                            <a href="my-wishlist?courseId=${c.id}" class="turn-page-2">
                                                                <i class="quick-info__save__icon fa-solid fa-heart" style="color: ${c.inWishlist ? 'red' : 'var(--dark-blue)'};"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="home-product-item__favourite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span>Phổ biến</span>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="product-hover-info">
                                            <h4 class="hover-title">${c.title}</h4>
                                            <div class="hover-goals">${c.goals != null ? c.goals : 'Khóa học kỹ năng mềm chuyên sâu...'}</div>
                                            <div class="hover-actions">
                                                <form action="cart-add" method="POST">
                                                    <input type="hidden" name="courseId" value="${c.id}">
                                                    <button type="submit" class="btn-add-cart">Thêm vào giỏ</button>
                                                </form>
                                                <button class="btn-wishlist" onclick="addToWishlist(${c.id})">
                                                    <i class="fa-regular fa-heart"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
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
                                <img src="./assets/image/appstore.png" alt="App Store"
                                     class="footer__download-app-img">
                            </a>
                            <a href="" class="footer__download-app-link">
                                <img src="./assets/image/ggplay.png" alt="Google Play"
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
<script>
    // ép load tại trang khi bấm back
    window.addEventListener( "pageshow", function ( event ) {
        var historyTraversal = event.persisted
        if ( historyTraversal ) {
            window.location.reload();
        }
    });
</script>
</html>