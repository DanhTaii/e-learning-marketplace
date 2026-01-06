<%@ page import="vn.edu.nlu.fit.elearning.controller.auth.GoogleConstants" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Đăng nhập</title>
    <%--    Thư mục bắt đầu mặc định khi chạy servlet --%>

    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/sign-in.css">
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
                <a href="../index.jsp" class="turn-page">
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
                                        <a href="../html-partrial/result-search.jsp" class="turn-page">
                                            <li class="browse__box-category-list-item text-list-item text-li">Tư duy &
                                                Sáng tạo
                                            </li>
                                        </a>
                                        <li class="browse__box-category-list-item text-list-item text-li">Lãnh đạo &
                                            Quản lý
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Năng suất &
                                            Quản lý thời gian
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Giao tiếp
                                            & Thuyết trình
                                        </li>
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
                                <div class="browse__container-box-2-1">
                                    <div class="box__title">
                                        <span class="category__title title">Tìm bằng kiểu khóa học</span>
                                    </div>
                                    <ul class="browse__container-box-2-list list">
                                        <li class="browse__container-box-2-list-item">
                                            <a href="../html-partrial/all-course.jsp" class="turn-page">
                                                <div class="item-box">
                                                    <span class="text-list-item text-list-item-2 text-li">Tất cả khóa học</span>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="browse__container-box-2-2">
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
                <div class="header__search header__search-2">
                    <div class="search__icon">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <div class="search__input">
                        <input type="text" class="input__text text-medium"
                               placeholder="Tìm kiếm khóa học, kỹ năng,...">
                    </div>
                </div>
                <div class="header__button-box">
                    <a href="sign-up.jsp" class="turn-page">
                        <div class="header__button sign-in-box">
                            <button class="button__btn text-header sign-in">Đăng ký</button>
                        </div>
                    </a>
                    <a href="../html-authentication/sign-in.jsp" class="turn-page">
                        <div class="header__button sign-up-box">
                            <button class="button__btn text-header sign-up">Đăng nhập</button>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </header>
    <div class="web__container">
        <div class="grid-2">
            <div class="grid__row-2">
                <div class="grid__column-4-in-12 fix-padding-1">
                    <div class="box-1">
                        <div class="box-1__title text-big-title"> Chào mừng trở lại</div>
                        <div class="box-1__content">Nâng cao kỹ năng của bạn.</div>
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2">
                        <%
                            String error = (String) request.getAttribute("error");
                            if (error == null) error = "";
                            String email = (String) request.getParameter("email");
                            if (email == null) email = "";

                        %>
                        <form action="sign-in" class="form" method="post">
                            <div class="form__title text-big-title">ĐĂNG NHẬP</div>

                            <span style="color: red; font-size: var(--text-xl)"> <%= error%> </span>
                            <div class="form__input input-1">
                                <input type="email" class="input-text text-big" placeholder="Nhập email của bạn"
                                       name="email">
                            </div>

                            <div class="form__input input-2">
                                <input type="password" class="input-text text-big" placeholder="Nhập mật khẩu của bạn"
                                       name="password">
                            </div>
                            <div class="form__sign-in-option">
                                <div class="sign-in-option-1">
                                    <div class="sign-in-option__checkbox">
                                        <input type="checkbox" class="checkbox text-big">
                                    </div>
                                    <div class="sign-in-option__text text-big">
                                        <span class="text text-big">Ghi nhớ đăng nhập</span>
                                    </div>
                                </div>
                                <div class="form__button">
                                    <button class="button button__btn">
                                        <span class="text-header">Đăng nhập</span>
                                    </button>
                                </div>
                            </div>
                            <div class="form__helps">
                                <div class="help__no--account">
                                    <span class="span text-big">Chưa có tài khoản?</span>
                                    <a href="html-authentication/sign-up.jsp" class="text-big turn-page text-fix">Đăng ký</a>
                                </div>
                                <div class="helps__lost-password">
                                    <a href="html-authentication/forgot-password.jsp" class="turn-page text-big text-fix">Quên mật khẩu?</a>
                                </div>
                            </div>
                            <div class="form__socials">
                                <a href="" class="turn-page">
                                    <div class="socials__box">
                                        <img class="image" src="assets/image/facebook.png"></img>
                                    </div>
                                </a>
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=<%=GoogleConstants.GOOGLE_REDIRECT_URI%>&response_type=code&client_id=<%=GoogleConstants.GOOGLE_CLIENT_ID%>&approval_prompt=force" class="turn-page">
                                    <div class="socials__box google">
                                        <img class="image" src="assets/image/search.png"></img>
                                    </div>
                                </a>
                                <a href="https://login.yahoo.com/?.src=ym&pspid=1182037121&activity=header-mail&.lang=vi-VN&.intl=vn&.done=https%3A%2F%2Fvn.mail.yahoo.com%2Fd%3F.intl%3Dvn%26.lang%3Dvi-VN%26pspid%3D1182037121%26activity%3Dheader-mail"
                                   class="turn-page">
                                    <div class="socials__box">
                                        <img class="image" src="assets/image/yahoo.png"></img>
                                    </div>
                                </a>
                            </div>
                        </form>
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
</body>
</html>