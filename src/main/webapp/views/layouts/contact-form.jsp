<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/contact-button.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/contact-form.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="contact-modal" id="contactModal">
    <div class="contact-modal__overlay"></div>

    <div class="contact-modal__content">
        <div class="contact-modal__header">
            <span class="contact-modal__title">Liên hệ Admin</span>
            <button class="contact-modal__close" id="closeContactModal">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form class="contact-form"
              action="${pageContext.request.contextPath}/api/contact"
              method="post">
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
            <div class="contact-form__group">
                <label>Email</label>
                <input type="email" name="email" class="input-style" value="${sessionScope.userSession.email}"
                    ${sessionScope.userSession != null ? "readonly" : ""} required>
            </div>

            <div class="contact-form__group">
                <label>Lý do</label>
                <select name="subject" class="input-style" required>
                    <option value="">-- Chọn lý do --</option>
                    <option value="ACCOUNT">Vấn đề tài khoản</option>
                    <option value="BUG">Lỗi hệ thống</option>
                    <option value="PAYMENT">Thanh toán</option>
                    <option value="OTHER">Khác</option>
                </select>
            </div>

            <div class="contact-form__group">
                <label>Nội dung</label>
                <textarea name="message" class="input-style contact-form__textarea"></textarea>
            </div>

            <div class="contact-form__actions">
                <button type="button" id="cancelContact">Hủy</button>
                <button type="submit" class="dark-button">Gửi</button>
            </div>

        </form>
    </div>
</div>
</body>
</html>
