<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>My wishlist</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/course/my-course.css?v=${applicationScope.assetVersion}">
<%--    <link rel="stylesheet" href="assets/css/default.css?v=${applicationScope.assetVersion}">--%>
    <link rel="stylesheet" href="assets/css/base/card.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base/base.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>

<jsp:include page="/views/layouts/header.jsp"/>

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
                <c:forEach var="c" items="${wishlistCourses}">
                    <div class="grid__column-3">
                        <a href="course-detail?id=${c.id}" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img src="${c.thumbnailUrl}"
                                         alt="${c.title}" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium content__author-name-2"><c:out value="${c.authorName}"/></div>
                                        <div class="content__rate content__rate-2">
                                            <div class="rate__icon"><i
                                                    class="text-medium fa-regular fa-star"></i></div>
                                            <fmt:formatNumber value="${c.avgRating}" type="number" maxFractionDigits="1" minFractionDigits="1" var="formattedRating"/>
                                            <div class="text-medium rate__number"><c:out value="${fn:replace(formattedRating, ',', '.')}"/></div>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text"><p><c:out value="${c.title}"/></p></div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <div class="level__icon icon"><i
                                                    class="text-medium fa-solid fa-signal"></i></div>
                                            <div class="level__text text-medium"><c:out value="${c.level.vietnameseName}"/></div>
                                        </div>
                                        <div class="quick-info__users">
                                            <div class="users__icon icon"><i
                                                    class="text-medium fa-solid fa-users"></i></div>
                                            <div class="users__text text-medium"><c:out value="${c.studentCount}"/></div>
                                        </div>
                                        <div class="quick-info__time">
                                            <div class="time__icon icon"><i
                                                    class="text-medium fa-regular fa-clock"></i></div>
                                            <div class="time__text text text-medium"><c:out value="${c.durationText}"/></div>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new">
                                             <c:out value="${c.discountedPrice}"/>
                                        </div>
                                        <div class="price__old"><c:out value="${c.originPrice}"/>
                                        </div>
                                    </div>
                                    <div class="hover-actions">
                                        <c:choose>
                                            <c:when test="${c.enrolled}">

                                                <button type="button"  class="btn-add-cart dark-button" style="font-size: 1.5rem;background-color: #01FF85;color: #002333" onclick="goToCourseContent(event,'${pageContext.request.contextPath}/personal/my-course/detail?courseId=${c.id}')">
                                                    Vào học ngay
                                                </button>

                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" style="font-size: 1.5rem"
                                                        class="btn-add-cart dark-button"
                                                        onclick="addToCart(event,${c.id})">Thêm vào giỏ
                                                </button>


                                            </c:otherwise>
                                        </c:choose>

                                        <button type="button"
                                                class="wishlist-btn ${c.inWishlist ? 'active' : ''}"
                                                onclick="addToWishlist(event, this, ${c.id})"
                                                title="Thêm vào danh sách yêu thích">
                                            <i class="fa-solid fa-heart"></i>
                                        </button>
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

<jsp:include page="/views/layouts/footer.jsp"/>
<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/cart/add-action.js?v=${applicationScope.assetVersion}"></script>
</body>
</html>