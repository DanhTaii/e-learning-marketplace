<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile user</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/default.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/profile.css?v=1.0.3">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/notification.css?v=1.0.1">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="assets/javascript/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>

</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <c:set var="user" value="${sessionScope.userSession}"/>
    <div class="user-profile__container grid">
        <div class="grid__row-2">
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
                            <c:if test="${user.role == 'ADMIN'}">
                                <li>
                                    <a href="admin/dashboard" class="menu-link">
                                        <i class="fa-solid fa-chart-line"></i>
                                        <span>Admin</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="grid__colum-9">
                <div class="information-card">
                    <div class="card-header">
                        <h2 class="text__title">Cài đặt tài khoản</h2>
                    </div>

                    <form action="personal/account-profile" method="POST" id="myForm" class="personal-detail-form">
                        <div class="form-section">
                            <div class="section-header">
                                <span class="section-indicator"></span>
                                <h2 class="section-title">Thông tin cơ bản</h2>
                            </div>

                            <div class="form-group">
                                <label class="style__sub-title">Tên người dùng</label>
                                <input type="text" name="username" id="user_name" value="${user.username}">
                                <span id="error_username" class="error-client"
                                      style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>
                        </div>

                        <div class="form-section">
                            <div class="section-header">
                                <span class="section-indicator"></span>
                                <h2 class="section-title">Thông tin liên lạc</h2>
                            </div>

                            <div class="form-group">
                                <label class="style__sub-title">Địa chỉ Email</label>
                                <input type="email" name="email" value="${user.email}" placeholder="example@gmail.com">
                            </div>
                            <div class="form-group">
                                <label class="style__sub-title">Số điện thoại</label>
                                <input type="tel" id="user_phone" name="phone" value="${user.phone}"
                                       placeholder="">
                                <span id="error_phone" class="error-client"
                                      style="color: red;font-size: 1.5rem;padding-left: 1.6rem"></span>
                            </div>
                        </div>

                        <div class="form-section">
                            <div class="section-header">
                                <span class="section-indicator"></span>
                                <h2 class="section-title">Link ảnh URL</h2>
                            </div>

                            <div class="form-group">
                                <label class="style__sub-title">Link ảnh avatar</label>
                                <input type="text" name="avatarUrl" value="${user.avatarUrl}" placeholder="">
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="reset" class="btn-secondary ">Hủy bỏ</button>
                            <button type="submit" class="btn-primary">Lưu thay đổi</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/header-footer/footer.jsp"/>
    <div id="toast"></div>
</div>
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
        const initialName = $('#user_name').val().trim();
        const initialPhone = $('#user_phone').val().trim();

        Validator.setupAutoClearErrors();

        $('#myForm').on('submit', function (e) {
            let name = $('#user_name').val().trim();
            let phone = $('#user_phone').val().trim();
            let isValid = true;
            if (name === initialName && phone === initialPhone) {
                e.preventDefault();
                alert("Bạn chưa thay đổi thông tin nào!");
                return false;
            }

            let usernameError = Validator.checkUsername(name);
            if (usernameError) {
                $('#error_username').text(usernameError);
                isValid = false;
            }

            let phoneError = Validator.checkPhone(phone);
            if (phoneError) {
                $('#error_phone').text(phoneError);
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
</html>