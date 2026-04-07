<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="admin-header-modern">
    <div class="header-left">
        <nav class="breadcrumb-nav">
            <span class="bc-parent">Admin</span>
            <i class="fa-solid fa-chevron-right bc-separator"></i>
            <span class="bc-current">
                <c:choose>
                    <c:when test="${currentPage == 'dashboard'}">Dashboard</c:when>
                    <c:when test="${currentPage == 'users'}">Người dùng</c:when>
                    <c:when test="${currentPage == 'courses'}">Khóa học</c:when>
                    <c:when test="${currentPage == 'lessons'}">Bài học</c:when>
                    <c:when test="${currentPage == 'tags'}">Thẻ</c:when>
                    <c:when test="${currentPage == 'categories'}">Danh mục</c:when>
                    <c:when test="${currentPage == 'orders'}">Đơn hàng</c:when>
                    <c:otherwise>Hệ thống</c:otherwise>
                </c:choose>
            </span>
        </nav>
    </div>

    <div class="header-right">
        <a href="${pageContext.request.contextPath}/index" class="header-action-item view-site-link">
            <i class="fa-solid fa-house"></i>
            <span>View Website</span>
        </a>

        <div class="header-divider"></div>

        <div class="header-action-item notification-wrapper">
            <i class="fa-solid fa-bell"></i>
            <span class="notif-dot"></span>
        </div>

        <div class="header-divider"></div>

        <div class="admin-profile-box">
            <div class="profile-info">
                <span class="profile-name">${sessionScope.userSession.username}
<%--                    ${sessionScope.userSession.firstName}--%>
                </span>
                <span class="profile-role">Quản trị viên</span>
            </div>
            <div class="profile-avatar-wrapper">
                <img src="${not empty sessionScope.userSession.avatarUrl ? sessionScope.userSession.avatarUrl : 'assets/img/default-avatar.png'}"
                     alt="Avatar" class="profile-avatar-img">
            </div>
        </div>
    </div>
</header>
