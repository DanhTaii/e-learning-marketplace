<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Forgot password</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/auth/forgot-password.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header-simple.jsp"/>
    <div class="web__container">
        <div class="grid-2">
            <div class="grid__row-2">
                <div class="grid__column-4-in-12 fix-padding-1">
                    <div class="box-1 add-to-fix-box-1">
                        <img src="assets/image/Vector.png" alt="" class="img">
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2-2-2">
                        <form action="forgot-password" method="post" class="form">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <div class="form__title text-big-title">QUÊN MẬT KHẨU</div>
                            <div class="form__span">
                                <span class="span__text text-medium">Vui lòng nhập email của bạn để đặt lại mật khẩu!</span>
                            </div>
                            <div class="form__input">
                                <input type="email" class="input-text text-big" placeholder="Nhập email của bạn" name="email" required>
                            </div>
                            <div class="form__button">
                                <a href="check-email" class="turn-page support">
                                    <button class="box-btn button__btn">
                                        <span class="text-header">Tiếp theo</span>
                                    </button>
                                </a>
                            </div>
                            <div class="form__turn-back">
                                <div class="turn-back__sign-up turn-back">
                                    <a href="sign-up" class="turn-page text-big">Đăng ký</a>
                                </div>
                                <div class="turn-back__sign-in turn-back">
                                    <a href="sign-in" class="turn-page text-big">Đăng nhập</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/views/layouts/footer.jsp"/>
</div>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>
