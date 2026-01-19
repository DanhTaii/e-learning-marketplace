<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Check your email</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/check-email.css">
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
                        <img src="assets/image/Vector2.png" alt="" class="img">
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2">
                        <form action="" class="form">
                            <div class="form__title text-big-title">KIỂM TRA EMAIL</div>
                            <div class="form__span">
                                <span class="span__text text-medium">
                                    Chúng tôi đã gửi mã đặt lại đến minh@dscode...com, hãy nhập mã gồm 5 chữ số được đề cập trong email!
                                </span>
                            </div>
                            <div class="form__input">
                                <input class="input__number text-small-title" type="text" name="digit" pattern="[0-9]" maxlength="1" required>
                                <input class="input__number text-small-title" type="text" name="digit" pattern="[0-9]" maxlength="1" required>
                                <input class="input__number text-small-title" type="text" name="digit" pattern="[0-9]" maxlength="1" required>
                                <input class="input__number text-small-title" type="text" name="digit" pattern="[0-9]" maxlength="1" required>
                                <input class="input__number text-small-title" type="text" name="digit" pattern="[0-9]" maxlength="1" required>
                            </div>
                            <div class="form__button">
                                <a href="reset-password.jsp" class="turn-page support">
                                    <button class="box-btn button__btn">
                                            <span class="text-header">Xác minh</span>
                                    </button>
                                </a>
                            </div>
                            <div class="form__turn-back">
                                <div class="turn-back__sign-up turn-back">
                                    <span class="text__span text-medium">Bạn chưa nhận được email?</span>
                                </div>
                                <div class="resend">
                                    <a href="" class="turn-page text-big">Gửi lại</a>
                                </div>
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