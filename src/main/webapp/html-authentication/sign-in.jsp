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
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/sign-in.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
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
    <jsp:include page="/header-footer/footer.jsp"/>
</div>
</body>
</html>