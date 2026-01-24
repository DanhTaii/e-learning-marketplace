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
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/sign-in.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="assets/javascript/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header-simple.jsp"/>
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
                    <div class="box-2-2">
                        <%
                            String error = (String) request.getAttribute("error");
                            if (error == null) {
                                error = request.getParameter("error");
                            }

                            if ("auth_required".equals(error)) {
                                error = "Vui lòng đăng nhập để thực hiện tính năng này!";
                            }

                            if (error == null) error = "";
                            String email = (String) request.getParameter("email");
                            if (email == null) email = "";

                        %>
                        <form action="sign-in" class="form" method="post" id="myForm">
                            <div class="form__title text-big-title">ĐĂNG NHẬP</div>

                            <div style="min-height: 25px;">
                                <span style="color: red; font-size: var(--text-xl)"> <%= error %> </span>
                            </div>
                            <div class="form__input input-1">
                                <input type="email" class="input-text text-big" placeholder="Nhập email của bạn"
                                       id='login_email' name="email">
                                <span id="error_email" class="error-client"
                                      style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>

                            <div class="form__input input-2">
                                <input type="password" class="input-text text-big" placeholder="Nhập mật khẩu của bạn"
                                       name="password" id="pass">
                                <span id="error_pass" class="error-client"
                                      style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
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
                                    <button type="submit" class="button button__btn">
                                        <span class="text-header">Đăng nhập</span>
                                    </button>
                                </div>
                            </div>
                            <div class="form__helps">
                                <div class="help__no--account">
                                    <span class="span text-big">Chưa có tài khoản?</span>
                                    <a href="sign-up" class="text-big turn-page text-fix">Đăng
                                        ký</a>
                                </div>
                                <div class="helps__lost-password">
                                    <a href="forgot-password" class="turn-page text-big text-fix">Quên mật khẩu?</a>
                                </div>
                            </div>
                            <div class="form__socials">
                                <a href="URL_FACEBOOK_CUA_BAN" class="social-link">
                                    <div class="socials__box facebook">
                                        <i class="fa-brands fa-facebook-f"></i>
                                    </div>
                                </a>

                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=<%=GoogleConstants.getRedirectUri()%>&response_type=code&client_id=<%=GoogleConstants.GOOGLE_CLIENT_ID%>&approval_prompt=force"
                                   class="social-link">
                                    <div class="socials__box google">
                                        <img class="image" src="assets/image/search.png" alt="Google">
                                    </div>
                                </a>

                                <a href="URL_YAHOO_CUA_BAN" class="social-link">
                                    <div class="socials__box yahoo">
                                        <i class="fa-brands fa-yahoo"></i>
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
<script>
    $(document).ready(function () {
        Validator.setupAutoClearErrors();

        $('#myForm').on('submit', function (e) {
            let email = $('#login_email').val().trim();
            let pass = $('#pass').val().trim();
            let isValid = true;

            let emailError = Validator.checkEmail(email);
            if (emailError) {
                $('#error_email').text(emailError);
                isValid = false;
            }


            if (pass === '') {
                $('#error_pass').text('Vui lòng nhập mật khẩu');
                isValid = false;
            }

            if (!isValid) {
                e.preventDefault();
            }
            return isValid;
        });

    });
</script>
</html>