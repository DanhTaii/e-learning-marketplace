<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile security</title>
    <base href="${pageContext.request.contextPath}/">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/profile.css?v=1.0.2">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>
<header class="web__header">
    <div class="grid">
        <div class="header__box">
            <a href="html-partrial/home.jsp" class="turn-page">
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
                                    <li class="browse__box-category-list-item text-list-item text-li">Giao tiếp
                                        & Thuyết trình
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Lãnh đạo &
                                        Quản lý
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Năng suất &
                                        Quản lý thời gian
                                    </li>
                                    <a href="../html-partrial/result-search.jsp" class="turn-page">
                                        <li class="browse__box-category-list-item text-list-item text-li">

                                            Tư duy & Sáng tạo
                                        </li>
                                    </a>
                                    <li class="browse__box-category-list-item text-list-item text-li">Trí tuệ cảm
                                        xúc (EQ)
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Đàm phán &
                                        Thuyết phục
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Phát triển bản
                                        thân
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Kỹ năng học
                                        tập & Tự học
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Kỹ năng công
                                        sở & Networking
                                    </li>
                                    <li class="browse__box-category-list-item text-list-item text-li">Sức khỏe tinh
                                        thần & Chống Burnout
                                    </li>
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
                                        <a href="../html-partrial/all-course.jsp" class="turn-page"></a>
                                        <div class="item-box">
                                            <a href="../html-partrial/all-course.jsp"
                                               class="text-list-item text-list-item-2 text-li">Tất cả khóa học</a>
                                        </div>
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
                                            <a href="../html-partrial/result-search.jsp"
                                               class="text-list-item text-list-item-2 text-li turn-page">Yêu
                                                thích</a>
                                        </div>
                                    </li>
                                    <li class="browse__container-box-2-list-item">
                                        <div class="item-box">
                                            <a href="" class="text-list-item text-list-item-2 text-li">Nổi bật</a>
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
            <div class="header__community text-header">Cộng đồng</div>
            <div class="header__search">
                <div class="search__icon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <div class="search__input">
                    <input type="text" class="input__text text-medium"
                           placeholder="Tìm kiếm khóa học, kỹ năng,...">
                </div>
            </div>
            <div class="header__class">
                <a href="my-course.jsp" class="turn-page text-header">
                    Khóa học của tôi
                </a>
            </div>
            <div class="header__wishlist">
                <a href="my-wishlist.jsp" class="turn-page text-header">
                    <i class="notification__icon fa-solid fa-heart text-header"></i>
                </a>
            </div>
            <div class="header__cart">
                <a href="html-personal-cart/cart.jsp" class="turn-page text-header">
                    <i class="text-header fa-solid fa-cart-shopping"></i>
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
                                <img src="../assets/image/65472207_145188949876444_2344275901291692032_n.jpg"
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
                                    <a href="my-course.jsp" class="turn-page">
                                        <div class="user__menu-list-item-box text-li">
                                            Khóa học
                                        </div>
                                    </a>
                                </li>
                                <li class="user__menu-list-item">
                                    <a href="my-wishlist.jsp" class="turn-page">
                                        <div class="user__menu-list-item-box text-li">
                                            Yêu thích
                                        </div>
                                    </a>
                                </li>
                                <li class="user__menu-list-item">
                                    <a href="../html-personal-cart/order-history.jsp" class="turn-page">
                                        <div class="user__menu-list-item-box text-li">
                                            Lịch sử giao dịch
                                        </div>
                                    </a>
                                </li>
                                <li class="user__menu-list-item">
                                    <a href="index" class="turn-page">
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
        </div>
    </div>
</header>

<div class="user-profile__container grid">

    <div class="grid__row-2">
        <c:set var="user" value="${sessionScope.userSession}"/>
        <div class="grid__column-3 overall-card">
            <div class="profile-sidebar">
                <div class="profile-block">
                    <div class="profile-block__avatar">
                        <c:out value="${user.username}"/>
                    </div>
                    <div class="profile-block__info">
                        <h2 class="profile-block__title">${user.username}</h2>
                        <p class="profile-block__email">${user.email}</p>
                    </div>
                </div>

                <nav class="profile-menu">
                    <ul>
                        <li>
                            <a href="account-profile"
                               class="menu-link ${param.currentPage == 'profile' ? 'active' : ''}">
                                <i class="fa-regular fa-user"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>
                        <li>
                            <a href="account-security"
                               class="menu-link ${param.currentPage == 'security' ? 'active' : ''}">
                                <i class="fa-solid fa-shield-halved"></i>
                                <span>Bảo mật tài khoản</span>
                            </a>
                        </li>
                        <li>
                            <a href="my-course.jsp" class="menu-link">
                                <i class="fa-solid fa-graduation-cap"></i>
                                <span>Khóa học của tôi</span>
                            </a>
                        </li>
                        <li>
                            <a href="order-history.jsp" class="menu-link">
                                <i class="fa-solid fa-clock-rotate-left"></i>
                                <span>Lịch sử giao dịch</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="grid__colum-9">
            <div class="information-card">
                <div class="card-header">
                    <h2 class="text__title">Bảo mật</h2>
                </div>

                <form action="reset-password" method="post">
                    <c:set var="user" value="${sessionScope.userSession}"/>

                    <div class="form-section">
                        <div class="section-header">
                            <span class="section-indicator"></span>
                            <h2 class="section-title">Địa chỉ email</h2>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Email</label>
                            <input type="text" placeholder="${userSession.email}" value="" name="">
                        </div>
                    </div>

                    <div class="form-section">
                        <div class="section-header">
                            <span class="section-indicator"></span>
                            <h2 class="section-title">Đổi mật khẩu</h2>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập mật khẩu cũ: </label>
                            <input type="password" placeholder="Nhập mật khẩu cũ" name="oldPassword"
                                   value="${param.oldPassword}">
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập mật khẩu mới: </label>
                            <input type="password" placeholder="Nhập mật khẩu mới" name="newPassword"
                                   value="${param.newPassword}">
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập lại mật khẩu mới: </label>
                            <input type="password" placeholder="Nhập lại mật khẩu mới" name="newPasswordRetype"
                                   value="${param.newPasswordRetype}">
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="reset" class="btn-secondary">Hủy bỏ</button>
                        <button type="submit" class="btn-primary">Lưu thay đổi</button>
                    </div>

                </form>
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
</body>
</html>