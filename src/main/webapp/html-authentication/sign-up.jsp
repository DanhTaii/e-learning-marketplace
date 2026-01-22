<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Đăng ký</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/sign-up.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/home.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="assets/javascript/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid-2">
            <div class="grid__row-2">
                <div class="grid__column-4-in-12 fix-padding-1">
                    <div class="box-1">
                        <div class="box-1__title text-big-title">THAM GIA SOFTSKILL</div>
                        <div class="box-1__content">Nâng cao kỹ năng của bạn.</div>
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2-2">

                        <form action="sign-up" class="form" method="post" id="myForm">
                            <div class="form__title text-big-title">ĐĂNG KÝ</div>
                            <span style="color: red; font-size: var(--text-xl)"> ${requestScope.error} </span>
                            <div class="form__input input-1">
                                <input type="email" class="input-text text-big" placeholder="Nhập email của bạn"
                                       name="email" value="${param.email}" id="login_email">
                                <span id="error_email" class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>
                            <div class="form__input input-2">
                                <input type="text" class="input-text text-big" placeholder="Nhập tên người dùng"
                                       name="username" value="${param.username}" id="name">
                                <span id="error_username" class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>
                            <div class="form__input input-3">
                                <input type="password" class="input-text text-big" placeholder="Nhập mật khẩu của bạn"
                                       name="password" value="${param.password}" id="newPass">
                                <span id="error_newPass" class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>
                            <div class="form__info text-medium">Mật khẩu phải có tối thiểu 8 ký tự gồm số và chữ cái,
                                trong đó có ít nhất 1 chữ cái viết hoa và ký tự đặc biệt!
                            </div>
                            <div class="form__input input-4">
                                <input type="password" class="input-text text-big"
                                       placeholder="Nhập lại mật khẩu của bạn" name="confirmPassword"
                                       value="${param.confirmPassword}" id="reNewPass">
                                <span id="error_reNewPass" class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>
                            <div class="form__button">
                                <button class="button button__btn" type="submit">
                                    <span class="text-header">Đăng ký</span>
                                </button>
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
            let newPass = $('#newPass').val().trim();
            let reType = $('#reNewPass').val().trim();
            let email = $('#login_email').val().trim();
            let username = $('#name').val().trim();
            let isValid = true;


            let usernameError = Validator.checkUsername(username);
            if (usernameError) {
                $('#error_username').text(usernameError);
                isValid = false;
            }

            let emailError = Validator.checkEmail(email);
            if (emailError) {
                $('#error_email').text(emailError);
                isValid = false;
            }


            let newPassError = Validator.checkPassword(newPass);
             if (newPassError) {
                $('#error_newPass').text(newPassError);
                isValid = false;
            }

            if (newPass !== reType) {
                $('#error_reNewPass').text('Mật khẩu nhập lại không khớp');
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