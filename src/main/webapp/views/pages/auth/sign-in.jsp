<%@ page import="vn.edu.nlu.fit.elearning.feature.google.service.GoogleConstants" %>
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
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/auth/sign-in.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="assets/javascript/validation/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="grid-2">
            <div class="grid__row-2">
                <div class="grid__column-4-in-12 fix-padding-1">
                    <div class="box-1 add-to-fix-box-1-2">
                        <div class="box-1__title text-big-title"> Chào mừng trở lại</div>
                        <div class="box-1__content">Nâng cao kỹ năng của bạn.</div>
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2-2-1">
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

                            <div class="add-to-fix-1">
                                <span class="add-to-fix-2"> <%= error %> </span>
                            </div>
                            <div class="form__input input-1">
                                <input class="input-text text-big" placeholder="Nhập email của bạn"
                                       id='login_email' name="email"
                                       value="${param.email}">
                                <span id="error_email" class="error-client"></span>
                            </div>

                            <div class="form__input input-2">
                                <div class="password-wrapper">
                                    <input type="password" class="input-text text-big"
                                           placeholder="Nhập mật khẩu của bạn"
                                           name="password" id="pass" value="${param.password}">

                                    <i class="fa-regular fa-eye" id="togglePassword"></i>
                                </div>

                                <span id="error_pass" class="error-client"></span>
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
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=<%=GoogleConstants.getRedirectUri()%>&response_type=code&client_id=<%=GoogleConstants.GOOGLE_CLIENT_ID%>&approval_prompt=force"
                                   class="social-link">
                                    <div class="socials__box google">
                                        <img class="image" src="assets/image/search.png" alt="Google">
                                    </div>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/views/layouts/footer.jsp"/>
</div>
<jsp:include page="/views/components/toast.jsp"/>
</body>
<script src="assets/javascript/validation/auth/sign-in.js?v=<%=System.currentTimeMillis()%>"></script>
</html>