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
    <title>Đăng ký</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/auth/sign-up.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/home.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="grid-2">
            <div class="grid__row-2">
                <div class="grid__column-4-in-12 fix-padding-1">
                    <div class="box-1 add-to-fix-box-1-2">
                        <div class="box-1__title text-big-title">THAM GIA SOFTSKILL</div>
                        <div class="box-1__content">Nâng cao kỹ năng của bạn.</div>
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2-2-1">

                        <form action="sign-up" class="form" method="post" id="myForm">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <div class="form__title text-big-title">ĐĂNG KÝ</div>
                            <span class="add-to-fix-3"> <c:out value="${requestScope.error}"/> </span>
                            <div class="form__input input-1">
                                <input  class="input-text text-big" placeholder="Nhập email của bạn"
                                       name="email" value="${param.email}" id="login_email">
                                <span id="error_email" class="error-client"><c:out value="${errors.email}"/></span>
                            </div>
                            <div class="form__input input-2">
                                <input type="text" class="input-text text-big" placeholder="Nhập tên người dùng"
                                       name="username" value="${param.username}" id="name">
                                <span id="error_username" class="error-client"><c:out value="${errors.username}"/></span>
                            </div>
                            <div class="form__input input-3">
                                <div class="password-group">
                                    <input type="password" class="input-text text-big" placeholder="Nhập mật khẩu của bạn"
                                           name="password" value="${param.password}" id="newPass">
                                    <i class="fa-regular fa-eye toggle-password" data-target="#newPass"></i>
                                </div>
                                <span id="error_newPass" class="error-client"><c:out value="${errors.password}"/></span>
                            </div>
                            <div class="form__info text-medium">Mật khẩu phải từ 8 đến 25 ký tự gồm số và chữ cái,
                                trong đó có ít nhất 1 chữ cái viết hoa, 1 chữ viết thuòng và ký tự đặc biệt!
                            </div>
                            <div class="form__input input-4">
                                <div class="password-group">
                                    <input type="password" class="input-text text-big"
                                           placeholder="Nhập lại mật khẩu của bạn" name="confirmPassword"
                                           value="${param.confirmPassword}" id="reNewPass">
                                    <i class="fa-regular fa-eye toggle-password" data-target="#reNewPass"></i>
                                </div>
                                <span id="error_reNewPass" class="error-client"><c:out value="${errors.confirmPassword}"/></span>
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
    <jsp:include page="/views/layouts/footer.jsp"/>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/auth/sign-up.js?v=<%=System.currentTimeMillis()%>"></script>
</html>