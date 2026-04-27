<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Soft Skill</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/home.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/card.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="web">
    <jsp:include page="views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-3">
                    <div class="container__category">
                        <a href="pagination-all-courses?page=1" class="turn-page">
                            <div class="container__category-title">Tất cả khóa học</div>
                        </a>
                        <ul class="container__category-list text-li">
                            <c:forEach var="c" items="${categories}">
                                <a href="result-search/by-category?id=${c.id}" class="turn-page">
                                    <li class="container__category-list-item text-li">${c.name}</li>
                                </a>
                            </c:forEach>
                        </ul>

                    </div>
                </div>
                <div class="grid__colum-9"  >
                    <div class="container__product">
                        <div class="product__big-title text-big-title">Gợi ý cho bạn</div>
                        <a href="course-detail?id=${courseMostPopular.id}" class="turn-page">
                            <div class="product__big-advertisement">
                                <div class="big-advertisement__image">
                                    <img src="${courseMostPopular.thumbnailUrl}"
                                         alt="" class="img-1">
                                </div>
                                <div class="big-advertisement__content">
                                    <div class="content__title">${courseMostPopular.title}</div>
                                    <div class="content__information">
                                        <div class="content__quick-info for-fix">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">${courseMostPopular.level.vietnameseName}</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">${courseMostPopular.studentCount}</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text text-medium">${courseMostPopular.durationText}</div>
                                            </div>
                                            <div class="quick-info__time ">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <fmt:formatNumber value="${courseMostPopular.avgRating}" type="number"
                                                                  maxFractionDigits="1" minFractionDigits="1"
                                                                  var="formattedRating"/>
                                                <div class="text-medium rate__number">${fn:replace(formattedRating, ',', '.')}</div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="content__price content__price-2">
                                            <div class="price__new">
                                                    ${courseMostPopular.discountedPrice}
                                            </div>
                                            <div class="price__old">${courseMostPopular.originPrice}
                                            </div>
                                        </div>
                                        <br>
                                        <div class="hover-actions">
                                            <c:choose>
                                                <c:when test="${courseMostPopular.enrolled}">

                                                    <button type="button" class="btn-add-cart dark-button"
                                                            style="font-size: 1.5rem;background-color: #01FF85;color: #002333"
                                                            onclick="goToCourseContent(event,'${pageContext.request.contextPath}/my-course/detail?courseId=${courseMostPopular.id}')">
                                                        Vào học ngay
                                                    </button>

                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" style="font-size: 1.5rem"
                                                            class="btn-add-cart dark-button"
                                                            onclick="addToCart(event,${courseMostPopular.id})">Thêm vào
                                                        giỏ
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>

                                            <button type="button"
                                                    class="wishlist-btn ${courseMostPopular.inWishlist ? 'active' : ''}"
                                                    onclick="addToWishlist(event, this, ${courseMostPopular.id})"
                                                    title="Thêm vào danh sách yêu thích">
                                                <i class="fa-solid fa-heart"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="product__small">
                        <div class="grid">
                            <div class="grid__row-2">
                                <div class="product__small-title text-small-title">Yêu thích</div>
                                <c:forEach var="c" items="${coursesLiked}">
                                    <div class="grid__column-4 product-card-container">

                                        <a href="course-detail?id=${c.id}" class="turn-page">
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
                                                            <fmt:formatNumber value="${c.avgRating}" type="number"
                                                                              maxFractionDigits="1"
                                                                              minFractionDigits="1"
                                                                              var="formattedRating"/>
                                                            <div class="text-medium rate__number">${fn:replace(formattedRating, ',', '.')}</div>
                                                        </div>
                                                    </div>
                                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                                    <div class="content__quick-info">
                                                        <div class="quick-info__level">
                                                            <div class="level__icon icon"><i
                                                                    class="text-medium fa-solid fa-signal"></i></div>
                                                            <div class="level__text text-medium">${c.level.vietnameseName}</div>
                                                        </div>
                                                        <div class="quick-info__users">
                                                            <div class="users__icon icon"><i
                                                                    class="text-medium fa-solid fa-users"></i></div>
                                                            <div class="users__text text-medium">${c.studentCount}</div>
                                                        </div>
                                                        <div class="quick-info__time">
                                                            <div class="time__icon icon"><i
                                                                    class="text-medium fa-regular fa-clock"></i></div>
                                                            <div class="time__text text text-medium">${c.durationText}</div>
                                                        </div>
                                                    </div>
                                                    <div class="content__price">
                                                        <div class="price__new">${c.discountedPrice}

                                                        </div>
                                                        <div class="price__old">${c.originPrice}
                                                        </div>
                                                    </div>

                                                    <div class="hover-actions">
                                                        <c:choose>
                                                            <c:when test="${c.enrolled}">

                                                                <button type="button" class="btn-add-cart dark-button"
                                                                        style="font-size: 1.5rem;background-color: #01FF85;color: #002333"
                                                                        onclick="goToCourseContent(event,'${pageContext.request.contextPath}/my-course/detail?courseId=${c.id}')">
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
                                                <div class="home-product-item__favourite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span>Yêu thích</span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="grid__row-2">
                                <div class="product__small-title text-small-title">Mới nhất</div>
                                <c:forEach var="c" items="${coursesLastest}">
                                    <div class="grid__column-4 product-card-container">

                                        <a href="course-detail?id=${c.id}" class="turn-page">
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
                                                            <fmt:formatNumber value="${c.avgRating}" type="number"
                                                                              maxFractionDigits="1"
                                                                              minFractionDigits="1"
                                                                              var="formattedRating"/>
                                                            <div class="text-medium rate__number">${fn:replace(formattedRating, ',', '.')}</div>
                                                        </div>
                                                    </div>
                                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                                    <div class="content__quick-info">
                                                        <div class="quick-info__level">
                                                            <div class="level__icon icon"><i
                                                                    class="text-medium fa-solid fa-signal"></i></div>
                                                            <div class="level__text text-medium">${c.level.vietnameseName}</div>
                                                        </div>
                                                        <div class="quick-info__users">
                                                            <div class="users__icon icon"><i
                                                                    class="text-medium fa-solid fa-users"></i></div>
                                                            <div class="users__text text-medium">${c.studentCount}</div>
                                                        </div>
                                                        <div class="quick-info__time">
                                                            <div class="time__icon icon"><i
                                                                    class="text-medium fa-regular fa-clock"></i></div>
                                                            <div class="time__text text text-medium">${c.durationText}</div>
                                                        </div>
                                                    </div>
                                                    <div class="content__price">
                                                        <div class="price__new">${c.discountedPrice}
                                                        </div>
                                                        <div class="price__old">${c.originPrice}

                                                        </div>
                                                    </div>
                                                    <div class="hover-actions">
                                                        <c:choose>
                                                            <c:when test="${c.enrolled}">

                                                                <button type="button" class="btn-add-cart dark-button"
                                                                        style="font-size: 1.5rem;background-color: #01FF85;color: #002333"
                                                                        onclick="goToCourseContent(event,'${pageContext.request.contextPath}/my-course/detail?courseId=${c.id}')">
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
                                                <div class="home-product-item__favourite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span>Mới nhất</span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>

                            </div>
                            <div class="grid__row-2">
                                <div class="product__small-title text-small-title">Phổ biến</div>
                                <c:forEach var="c" items="${coursesFeature}">
                                    <div class="grid__column-4 product-card-container">
                                        <a href="course-detail?id=${c.id}" class="turn-page">
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
                                                            <fmt:formatNumber value="${c.avgRating}" type="number"
                                                                              maxFractionDigits="1"
                                                                              minFractionDigits="1"
                                                                              var="formattedRating"/>
                                                            <div class="text-medium rate__number">${fn:replace(formattedRating, ',', '.')}</div>
                                                        </div>
                                                    </div>
                                                    <div class="text-paragraph test-text"><p>${c.title}</p></div>
                                                    <div class="content__quick-info">
                                                        <div class="quick-info__level">
                                                            <div class="level__icon icon"><i
                                                                    class="text-medium fa-solid fa-signal"></i></div>
                                                            <div class="level__text text-medium">${c.level.vietnameseName}</div>
                                                        </div>
                                                        <div class="quick-info__users">
                                                            <div class="users__icon icon"><i
                                                                    class="text-medium fa-solid fa-users"></i></div>
                                                            <div class="users__text text-medium">${c.studentCount}</div>
                                                        </div>
                                                        <div class="quick-info__time">
                                                            <div class="time__icon icon"><i
                                                                    class="text-medium fa-regular fa-clock"></i></div>
                                                            <div class="time__text text text-medium">${c.durationText}</div>
                                                        </div>
                                                    </div>
                                                    <div class="content__price">
                                                        <div class="price__new">${c.discountedPrice}
                                                        </div>
                                                        <div class="price__old">${c.originPrice}</div>
                                                    </div>
                                                    <div class="hover-actions">
                                                        <c:choose>
                                                            <c:when test="${c.enrolled}">

                                                                <button type="button" class="btn-add-cart dark-button"
                                                                        style="font-size: 1.5rem;background-color: #01FF85;color: #002333"
                                                                        onclick="goToCourseContent(event,'${pageContext.request.contextPath}/my-course/detail?courseId=${c.id}')">
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
                                                <div class="home-product-item__favourite">
                                                    <i class="fa-solid fa-check"></i>
                                                    <span>Phổ biến</span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/views/components/contact-button.jsp" />
    <jsp:include page="views/layouts/footer.jsp"/>
</div>
<jsp:include page="views/components/toast.jsp"/>
<jsp:include page="views/layouts/contact-form.jsp"/>
</body>

<script src="assets/javascript/features/cart/add-action.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/component/contact-form.js?v=<%=System.currentTimeMillis()%>"></script>

</html>