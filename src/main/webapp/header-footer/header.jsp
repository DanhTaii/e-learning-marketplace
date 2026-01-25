<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>header</title>
    <style>
        .cart-icon-wrapper {
            position: relative;
            display: inline-block;
            padding: 0 5px;
        }

        /* Style cho badge (con số) */
        .cart-badge {
            position: absolute;
            top: -8px;          /* Điều chỉnh độ cao thấp */
            right: -10px;       /* Điều chỉnh độ xa gần so với icon */
            background-color: var(--bright-green); /* Màu nền nổi bật (màu xanh của bạn) */
            color: var(--dark-blue);              /* Màu chữ con số */
            font-size: 1.1rem;  /* Kích thước chữ nhỏ lại */
            font-weight: bold;
            height: 18px;       /* Độ cao vòng tròn */
            min-width: 18px;    /* Độ rộng tối thiểu vòng tròn */
            border-radius: 50%; /* Làm tròn */
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 0 4px;
            border: 2px solid var(--dark-blue); /* Tạo viền trùng màu header để trông tách biệt */
            line-height: 1;
        }

        /* Hiệu ứng khi hover vào giỏ hàng */
        .header__cart:hover .cart-badge {
            background-color: var(--white-color);
            color: var(--dark-blue);
        }
    </style>
</head>
<body>
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
                                        <c:forEach var="c" items="${categories}">
                                            <a href="result-search/by-category?id=${c.id}" class="turn-page">
                                                <li class="browse__box-category-list-item text-list-item text-li">${c.name}</li>
                                            </a>
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
                                            <a href="pagination-all-courses?page=1" class="turn-page">
                                                <div class="item-box">
                                                    <span class="text-list-item text-list-item-2 text-li">Tất cả khóa học</span>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="browse__container-box-2-2 box-2">
                                    <div class="box__title">
                                        <span class="category__title title">Loại</span>
                                    </div>
                                    <ul class="browse__container-box-2-list list">
                                        <c:forEach var="t" items="${tags}" begin="0" end="2">
                                            <li class="browse__container-box-2-list-item">
                                                <div class="item-box">
                                                    <a href="result-search/by-tag?id=${t.id}"
                                                       class="text-list-item text-list-item-2 text-li turn-page">${t.name}</a>
                                                </div>
                                            </li>
                                        </c:forEach>
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
                    <form action="result-search/by-title" method="get" style="display: flex; flex: 1;">
                        <div class="search__input">
                            <input type="text" class="input__text text-medium" name="title"
                                   value="${param.title}"
                                   placeholder="Tìm kiếm khóa học, kỹ năng,...">
                        </div>
                    </form>
                </div>
                <c:set var="loginSession" value="${not empty sessionScope.userSession}"/>
                <c:if test="${loginSession}">
                    <div class="header__class">
                        <a href="personal/my-courses" class="turn-page text-header">
                            Khóa học của tôi
                        </a>
                    </div>
                    <div class="header__wishlist">
                        <a href="personal/my-wishlist" class="turn-page text-header">
                            <i class="notification__icon fa-solid fa-heart text-header"></i>
                        </a>
                    </div>
                    <div class="header__cart">
                        <a href="personal/cart" class="turn-page text-header cart-link">
                            <div class="cart-icon-wrapper">
                                <i class="text-header fa-solid fa-cart-shopping"></i>
                                <span id="cart-count" class="cart-badge">
                                        ${not empty sessionScope.cart ? sessionScope.cart.totalQuantity : 0}
                                </span>
                            </div>
                        </a>
                    </div>
                    <div class="header__user">

                                <c:set var="defaultImg" value="https://staudt-gmbh.com/wp-content/uploads/2018/07/person-dummy.jpg"/>

                                <img src="${not empty user.avatarUrl ? user.avatarUrl : defaultImg}"
                                     alt="Avatar"
                                     class="user__avatar"
                                     onerror="this.onerror=null; this.src='${defaultImg}';">

                        <div class="user__display">
                            <div class="user__container">
                                <div class="user__profile">
                                    <div class="user__profile-avatar">
                                        <c:set var="defaultImg" value="https://staudt-gmbh.com/wp-content/uploads/2018/07/person-dummy.jpg"/>

                                        <img src="${not empty user.avatarUrl ? user.avatarUrl : defaultImg}"
                                             alt="Avatar"
                                             class="user__avatar-mini"
                                             onerror="this.onerror=null; this.src='${defaultImg}';">
                                    </div>
                                    <div class="user__profile-name">
                                        <a href="" class="name-text text-header">${user.username}</a>
                                    </div>
                                    <div class="user__profile-bio">
                                        <a href="" class="bio-text">Thêm tiểu sử</a>
                                    </div>
                                    <a href="personal/account-profile" class="turn-page">
                                        <div class="user__profile-btn">
                                            <button class="user-btn button__btn text-header">Xem thông tin</button>
                                        </div>
                                    </a>
                                </div>
                                <div class="user__menu">
                                    <ul class="user__menu-list">
                                        <li class="user__menu-list-item">
                                            <a href="personal/my-courses" class="turn-page">
                                                <div class="user__menu-list-item-box text-li">
                                                    Khóa học
                                                </div>
                                            </a>
                                        </li>
                                        <li class="user__menu-list-item">
                                            <a href="personal/my-wishlist" class="turn-page">
                                                <div class="user__menu-list-item-box text-li">
                                                    Yêu thích
                                                </div>
                                            </a>
                                        </li>
                                        <li class="user__menu-list-item">
                                            <a href="personal/order-history" class="turn-page">
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
                        <a href="sign-up" class="turn-page">
                            <div class="header__button sign-in-box">
                                <button class="button__btn text-header sign-in">Đăng ký</button>
                            </div>
                        </a>
                        <a href="sign-in" class="turn-page">
                            <div class="header__button sign-up-box">
                                <button class="button__btn text-header sign-up">Đăng nhập</button>
                            </div>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </header>
</body>
</html>