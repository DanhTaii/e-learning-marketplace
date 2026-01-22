<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My wishlist</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/my-course.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/card.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
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
                <c:forEach var="c" items="${wishlistCourses}">
                    <div class="grid__column-3">
                        <a href="course-detail?courseId=${c.id}" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img src="${c.thumbnailUrl}"
                                         alt="${c.title}" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium content__author-name-2">${c.authorName}</div>
                                        <div class="content__rate content__rate-2">
                                            <div class="rate__icon"><i
                                                    class="text-medium fa-regular fa-star"></i></div>
                                            <div class="text-medium rate__number">${c.avgRating}</div>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <div class="level__icon icon"><i
                                                    class="text-medium fa-solid fa-signal"></i></div>
                                            <div class="level__text text-medium">${c.level}</div>
                                        </div>
                                        <div class="quick-info__users">
                                            <div class="users__icon icon"><i
                                                    class="text-medium fa-solid fa-users"></i></div>
                                            <div class="users__text text-medium">${c.studentCount}</div>
                                        </div>
                                        <div class="quick-info__time">
                                            <div class="time__icon icon"><i
                                                    class="text-medium fa-regular fa-clock"></i></div>
                                            <div class="time__text text text-medium">${c.durationHours}h</div>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new"><fmt:formatNumber
                                                value="${c.price - c.discountPrice}" type="number"
                                                pattern="###,###"></fmt:formatNumber> đ
                                        </div>
                                        <div class="price__old"><fmt:formatNumber value="${c.price}"
                                                                                  type="number"
                                                                                  pattern="###,###"></fmt:formatNumber>đ
                                        </div>
                                    </div>
                                    <a href="" class="turn-page">
                                        <div class="hover-actions">
                                            <button type="submit" style="font-size: 1.5rem"
                                                    class="btn-add-cart dark-button"
                                                    onclick="addToCart(${c.id})">Thêm vào giỏ
                                            </button>

                                            <button type="button"
                                                    class="wishlist-btn ${c.inWishlist ? 'active' : ''}"
                                                    onclick="addToWishlist(this, ${c.id})"
                                                    title="Thêm vào danh sách yêu thích">
                                                <i class="fa-solid fa-heart"></i>
                                            </button>
                                        </div>
                                    </a>
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

<script src="assets/javascript/add-wishlist.js?v=<%=System.currentTimeMillis()%>"></script>

</html>