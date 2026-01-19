<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My wishlist</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/my-course.css?v=3">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/card.css">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>

<jsp:include page="/header-footer/header.jsp"/>

<div class="my-course__container grid">
    <div class="my-course__title">Danh sách yêu thích</div>
    <div class="stroke"></div>

    <c:choose>
        <c:when test="${empty wishlistCourses}">
            <div class="wishlist-empty-state">
                <i class="wishlist-empty-icon fa-regular fa-heart"></i>
                <h3 class="wishlist-empty-title">Chưa có khóa học nào trong danh sách yêu thích</h3>
                <p class="wishlist-empty-description">Hãy khám phá và thêm những khóa học bạn yêu thích nhé!</p>
                <a href="${pageContext.request.contextPath}/index" class="wishlist-empty-link">
                    Quay về trang chủ
                </a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="grid__row-2">
                <c:forEach var="course" items="${wishlistCourses}">
                    <div class="grid__column-3">
                        <a href="course-detail?id=${course.id}" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img src="${course.thumbnailUrl}" alt="${course.title}" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium">${course.authorName}</div>
                                        <div class="content__rate">
                                            <i class="text-medium fa-regular fa-star"></i>
                                            <span class="text-medium rate__number">${course.rating}</span>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text">
                                        <p>${course.title}</p>
                                    </div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <i class="fa-solid fa-signal icon"></i>
                                            <span class="level__text text-medium">${course.level}</span>
                                        </div>
                                        <div class="quick-info__users">
                                            <i class="fa-solid fa-users icon"></i>
                                            <span class="users__text text-medium">${course.studentCount}</span>
                                        </div>
                                        <div class="quick-info__time">
                                            <i class="fa-regular fa-clock icon"></i>
                                            <span class="time__text text-medium">${course.durationHours}h</span>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new">${course.price - course.discountPrice}đ</div>
                                        <div class="price__old">${course.price}đ</div>
                                        <div class="quick-info__save">
                                            <a href="my-wishlist?id=${course.wishlistId}&courseId=${course.id}" class="turn-page">
                                                <i class="quick-info__save__icon fa-solid fa-heart" style="color:red;"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/header-footer/footer.jsp"/>


</body>
</html>