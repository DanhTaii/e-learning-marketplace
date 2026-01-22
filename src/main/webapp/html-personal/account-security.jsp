<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile security</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css-admin/notification.css?v=1.0.1">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/profile.css?v=1.0.3">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<jsp:include page="/header-footer/header.jsp"/>

<div class="user-profile__container grid">

    <div class="grid__row-2">
        <c:set var="user" value="${sessionScope.userSession}"/>
        <div class="grid__column-3 overall-card">
            <div class="profile-sidebar">
                <div class="profile-block">
                    <div class="profile-block__avatar">
                        <div class="fix-image-box">
                            <img src="${user.avatarUrl}" alt="" class="turn-page fix-image">
                        </div>
                    </div>
                    <div class="profile-block__info">
                        <h2 class="profile-block__title">${user.username}</h2>
                        <p class="profile-block__email">${user.email}</p>
                    </div>
                </div>

                <nav class="profile-menu">
                    <ul>
                        <li>
                            <a href="personal/account-profile"
                               class="menu-link ${param.currentPage == 'profile' ? 'active' : ''}">
                                <i class="fa-regular fa-user"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>
                        <li>
                            <a href="personal/account-security"
                               class="menu-link ${param.currentPage == 'security' ? 'active' : ''}">
                                <i class="fa-solid fa-shield-halved"></i>
                                <span>Bảo mật tài khoản</span>
                            </a>
                        </li>
                        <li>
                            <a href="personal/my-courses" class="menu-link">
                                <i class="fa-solid fa-graduation-cap"></i>
                                <span>Khóa học của tôi</span>
                            </a>
                        </li>
                        <li>
                            <a href="personal/order-history" class="menu-link">
                                <i class="fa-solid fa-clock-rotate-left"></i>
                                <span>Lịch sử giao dịch</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="grid__colum-9">
            <div class="information-card">
                <div class="card-header">
                    <h2 class="text__title">Bảo mật</h2>
                </div>

                <form action="change-password" method="post" id="myForm">
                    <c:set var="user" value="${sessionScope.userSession}"/>
                    <div class="form-section">
                        <div class="section-header">
                            <span class="section-indicator"></span>
                            <h2 class="section-title">Địa chỉ email</h2>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Email</label>
                            <input type="text" id="email" name="email" value="${userSession.email}" readonly >
                        </div>
                    </div>

                    <div class="form-section">
                        <div class="section-header">
                            <span class="section-indicator"></span>
                            <h2 class="section-title">Đổi mật khẩu</h2>

                        </div>
                        <div class="form-group">
                            <label class="style__sub-title">Nhập mật khẩu cũ: </label>
                            <input type="password" placeholder="Nhập mật khẩu cũ" id="oldPass" name="oldPassword"
                                   value="${param.oldPassword}"  >
                            <span id="error_oldPass" class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập mật khẩu mới: </label>
                            <input type="password" placeholder="Nhập mật khẩu mới" id="newPass" name="newPassword"
                                   value="${param.newPassword}" >
                            <span id="error_newPass" class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập lại mật khẩu mới: </label>
                            <input type="password" placeholder="Nhập lại mật khẩu mới"  id="reNewPass" name="newPasswordRetype"
                                   value="${param.newPasswordRetype}">
                            <span id="error_reType"  class="error-client" style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="reset" class="btn-secondary">Hủy bỏ</button>
                        <button type="submit" class="btn-primary">Lưu thay đổi</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/header-footer/footer.jsp"/>
<div id="toast"></div>
</body>
<script>
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>

</script>
<script>
    $(document).ready(function () {


     Validator.setupAutoClearErrors();

        $('#myForm').on('submit', function (e) {
            let oldPass = $('#oldPass').val().trim();
            let newPass = $('#newPass').val().trim();
            let reType = $('#reNewPass').val().trim();
            let isValid = true;

            let oldPassError =Validator.checkPassword(oldPass);
             if (oldPassError) {
                $('#error_oldPass').text( oldPassError);
                isValid = false;
            }

            let newPassError = Validator.checkPassword(newPass);
             if (newPassError) {
                $('#error_newPass').text(newPassError);
                isValid = false;
            }else if(newPass === oldPass){
                $('#error_newPass').text('Mật khẩu mới trùng mật khẩu cũ');
                isValid = false;
            }

            if (newPass !== reType) {
                $('#error_reType').text('Mật khẩu nhập lại không khớp');
                isValid = false;
            }

            if (!isValid) {
                e.preventDefault();
            }
            return isValid;
        });

    });
</script>
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</html>