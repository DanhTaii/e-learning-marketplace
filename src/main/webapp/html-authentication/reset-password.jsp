<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Reset password</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/reset-password.css?v=1.0.1">
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
                        <img src="assets/image/Vector3.png" alt="" class="img">
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2-2">
                        <form action="reset-password" method="post" class="form">
                            <div class="form__title text-big-title">TẠO MẬT KHẨU MỚI</div>
                            <div class="form__span">
                                <span class="span__text text-medium">
                                    Tạo mật khẩu mới. Đảm bảo mật khẩu mới khác với mật khẩu trước đó để đảm bảo an toàn.
                                </span>
                            </div>
                            <c:if test="${error != null}">
                                <span style="color: red; font-size: var(--text-xl)"> ${error} </span>
                            </c:if>
                            <div class="form__input form__input-1">
                                <input type="text" name="password" class="input-text text-big"
                                       placeholder="Nhập mật khẩu mới của bạn">
                            </div>
                            <div class="form__input form__input-2">
                                <input type="text" name="retypePassword" class="input-text text-big"
                                       placeholder="Nhập lại mật khẩu">
                            </div>
                            <div class="form__button">
                                <button type="submit" class="button__btn box-btn">
                                        <span class="text-header">
                                            Đặt lại mật khẩu
                                        </span>
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
</html>