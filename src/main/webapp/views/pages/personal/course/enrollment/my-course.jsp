<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>My course</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/course/my-course.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/card.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>

<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>

    <div class="my-course__container grid">

        <div class="my-course__title">Khóa học đã mua</div>

        <div class="stroke"></div>

        <c:choose>
            <c:when test="${not empty listEnrollments}">
                <div class="grid__row-2">
                    <c:forEach var="e" items="${listEnrollments}">
                        <div class="grid__column-3">
                            <a href="personal/my-course/detail?courseId=${e.courseId}" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="${e.thumbnailUrl}"
                                             alt="${e.title}" class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i class="text-medium fa-regular fa-star"></i></div>
<%--                                                <div class="text-medium rate__number"><c:out value="${e.rating}"/></div>--%>
                                                <fmt:formatNumber value="${e.rating}" type="number" maxFractionDigits="1" minFractionDigits="1" var="formattedRating"/>
                                                <div class="text-medium rate__number"><c:out value="${fn:replace(formattedRating, ',', '.')}"/></div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p><c:out value="${e.title}"/></p></div>
                                        <div class="progress-display">
                                            <div class="progress-display__crossbar"></div>
                                            <div class="progress-display__percentage" style="width: ${e.percentCompleted}%"></div>
                                        </div>
                                        <div class="progress__text">Đã hoàn thành <c:out value="${e.percentCompleted}"/>%</div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:when>

            <c:otherwise>
                <div class="my-course-empty-state">
                    <i class="fa-solid fa-book-open my-course-empty-icon"></i>
                    <div class="my-course-empty-title">Bạn chưa mua khóa học nào</div>
                    <div class="my-course-empty-description">
                        Hãy khám phá và chọn cho mình một khóa học phù hợp nhé!
                    </div>
                    <a href="index" class="my-course-empty-link">Khám phá khóa học</a>
                </div>
            </c:otherwise>
        </c:choose>

    </div>

    <jsp:include page="/views/layouts/footer.jsp"/>

</div>


<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>