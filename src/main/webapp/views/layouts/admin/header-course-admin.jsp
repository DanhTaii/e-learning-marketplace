<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="admin-header-modern">

    <!-- LEFT -->
    <div class="header-left">

        <a href="admin/courses" class="btn-back-course">
            <i class="fa-solid fa-arrow-left"></i>
            Quay về khoá học
        </a>

        <nav class="header-tabs">
            <a href="javascript:void(0)" class="tab-item ${not empty param.lessonId ? '' : 'active'}"
               data-tab="overview">
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <rect x="3" y="3" width="7" height="7"></rect>
                    <rect x="14" y="3" width="7" height="7"></rect>
                    <rect x="14" y="14" width="7" height="7"></rect>
                    <rect x="3" y="14" width="7" height="7"></rect>
                </svg>
                <span>Tổng quan</span>
            </a>
            <%--            Curriculum--%>
            <c:if test="${not empty course and course.id > 0}">
                <a href="javascript:void(0)" class="tab-item ${not empty param.lessonId ? 'active' : ''}"
                   data-tab="curriculum">
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                        <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                    </svg>
                    <span>Nội dung</span>
                </a>
            </c:if>
        </nav>

    </div>

    <!-- RIGHT -->
    <div class="header-right">

        <div class="header-icon">
            <i class="fa-solid fa-bell"></i>
            <span class="notif-dot"></span>
        </div>

        <div class="profile-avatar-wrapper">
            <img src="${not empty sessionScope.userSession.avatarUrl
                ? sessionScope.userSession.avatarUrl
                : 'assets/img/default-avatar.png'}">
        </div>

    </div>

</header>