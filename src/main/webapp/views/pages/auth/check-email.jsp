<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Kiểm tra email</title>
    <base href="${pageContext.request.contextPath}/">

    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/auth/check-email.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header-simple.jsp"/>
    <div class="web__container">
        <div class="grid-2">
            <div class="grid__row-2">
                <div class="grid__column-4-in-12 fix-padding-1">
                    <div class="box-1 add-to-fix-box-1">
                        <img src="assets/image/Vector2.png" alt="" class="img">
                    </div>
                </div>
                <div class="grid__column-8 fix-padding-2">
                    <div class="box-2-2-2">
                        <form action="${pageContext.request.contextPath}/check-email"
                              method="POST"
                              class="form"
                              id="otpForm">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <div class="form__title text-big-title">KIỂM TRA EMAIL</div>
                            <div class="form__span">
                                <span class="span__text text-medium">
                                    Chúng tôi đã gửi mã đặt lại đến
                                    <strong class="text-medium"><c:out value="${sessionScope.resetEmail}"/></strong>,
                                    hãy nhập mã gồm 5 ký tự được đề cập trong email!
                                </span>
                            </div>

                            <!-- Hiển thị lỗi nếu có -->
                            <c:if test="${not empty requestScope.error}">
                                <div class="error-message text-big">
                                        <c:out value="${requestScope.error}"/>
                                </div>
                            </c:if>

                            <div class="form__input otp-inputs">
                                <input class="input__number text-small-title" type="text" maxlength="1"
                                       pattern="[A-Z0-9]*" inputmode="numeric" autocomplete="one-time-code"
                                       required autofocus>
                                <input class="input__number text-small-title" type="text" maxlength="1"
                                       pattern="[A-Z0-9]*" inputmode="numeric" required>
                                <input class="input__number text-small-title" type="text" maxlength="1"
                                       pattern="[A-Z0-9]*" inputmode="numeric" required>
                                <input class="input__number text-small-title" type="text" maxlength="1"
                                       pattern="[A-Z0-9]*" inputmode="numeric" required>
                                <input class="input__number text-small-title" type="text" maxlength="1"
                                       pattern="[A-Z0-9]*" inputmode="numeric" required>
                            </div>

                            <!-- Trường ẩn để gửi toàn bộ mã lên server -->
                            <input type="hidden" name="otp" id="fullOtp">

                            <div class="form__button">
                                <button type="submit" class="box-btn button__btn">
                                    <span class="text-header">Xác minh</span>
                                </button>
                            </div>

                            <div class="form__turn-back">
                                <div class="turn-back__sign-up turn-back">
                                    <span class="text__span text-medium">Bạn chưa nhận được email?</span>
                                </div>
                                <div class="resend">
                                    <a href="${pageContext.request.contextPath}/forgot-password"
                                       class="turn-page text-big">Gửi lại</a>
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
</body>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/validation/auth/check-email.js?v=<%=System.currentTimeMillis()%>"></script>
</html>