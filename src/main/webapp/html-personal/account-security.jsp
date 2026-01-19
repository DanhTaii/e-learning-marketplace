<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile security</title>
    <base href="${pageContext.request.contextPath}/">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/profile.css?v=1.0.2">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
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
                        <c:out value="${user.username}"/>
                    </div>
                    <div class="profile-block__info">
                        <h2 class="profile-block__title">${user.username}</h2>
                        <p class="profile-block__email">${user.email}</p>
                    </div>
                </div>

                <nav class="profile-menu">
                    <ul>
                        <li>
                            <a href="account-profile"
                               class="menu-link ${param.currentPage == 'profile' ? 'active' : ''}">
                                <i class="fa-regular fa-user"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>
                        <li>
                            <a href="account-security"
                               class="menu-link ${param.currentPage == 'security' ? 'active' : ''}">
                                <i class="fa-solid fa-shield-halved"></i>
                                <span>Bảo mật tài khoản</span>
                            </a>
                        </li>
                        <li>
                            <a href="my-course.jsp" class="menu-link">
                                <i class="fa-solid fa-graduation-cap"></i>
                                <span>Khóa học của tôi</span>
                            </a>
                        </li>
                        <li>
                            <a href="order-history.jsp" class="menu-link">
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

                <form action="reset-password" method="post">
                    <c:set var="user" value="${sessionScope.userSession}"/>

                    <div class="form-section">
                        <div class="section-header">
                            <span class="section-indicator"></span>
                            <h2 class="section-title">Địa chỉ email</h2>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Email</label>
                            <input type="text" placeholder="${userSession.email}" value="" name="">
                        </div>
                    </div>

                    <div class="form-section">
                        <div class="section-header">
                            <span class="section-indicator"></span>
                            <h2 class="section-title">Đổi mật khẩu</h2>
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập mật khẩu cũ: </label>
                            <input type="password" placeholder="Nhập mật khẩu cũ" name="oldPassword"
                                   value="${param.oldPassword}">
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập mật khẩu mới: </label>
                            <input type="password" placeholder="Nhập mật khẩu mới" name="newPassword"
                                   value="${param.newPassword}">
                        </div>

                        <div class="form-group">
                            <label class="style__sub-title">Nhập lại mật khẩu mới: </label>
                            <input type="password" placeholder="Nhập lại mật khẩu mới" name="newPasswordRetype"
                                   value="${param.newPasswordRetype}">
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
</body>
</html>