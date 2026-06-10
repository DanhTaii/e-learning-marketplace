<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Result search</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/course/result-search.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/card.css?v=<%=System.currentTimeMillis()%>">
    <script src="assets/javascript/features/cart/add-action.js?v=<%=System.currentTimeMillis()%>"></script>

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="container__title text-big-title">
                    <c:if test="${not empty cate}">
                        Danh mục: <c:out value="${cate.name}"/>
                    </c:if>
                    <c:if test="${empty cate and not empty search}">
                        Kết quả cho từ khóa: "<c:out value="${search}"/>"
                    </c:if>
                    <c:if test="${empty cate and empty search and not empty tag}">
                        Loại: <c:out value="${tag.name}"/>
                    </c:if>
                    <c:if test="${empty cate and empty search and empty tag}">
                        Tất cả khóa học
                    </c:if>
                </div>

                <div class="grid__column-3">
                    <c:if test="${mode == 'tag'}">
                        <form action="result-search/by-tag" method="get">
                                <%-- phải có dòng này để khi lọc theo bộ lọc thì vẫn giữ là đã theo tag trước đó
                                      nếu không thì nó sẽ reset và tự lọc lại chỉ theo cái phần lọc vừa được chọn--%>
                            <input type="hidden" name="id" value="${tag.id}">
                                    <div style="display:flex; gap:10px;">
                                        <button class="user__profile-btn button__btn text-header"
                                                type="submit">
                                            Lọc
                                        </button>

                                        <a href="result-search/by-tag?id=${tag.id}"
                                           class="user__profile-btn button__btn text-header fix-btn-2">
                                            Đặt lại
                                        </a>
                                    </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Giá cả</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" name="sortPrice"
                                                   value="desc"${sortPrice == 'desc' ? 'checked' : ''}>
                                            <div class="type__text text-big">Cao đến thấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" name="sortPrice"
                                                   value="asc"${sortPrice == 'asc' ? 'checked' : ''}>
                                            <div class="type__text text-big">Thấp đến cao</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Mức độ</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="beginner"${level == 'beginner' ? 'checked' : ''}>
                                            <div class="type__text text-big">Sơ cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="intermediate"${level == 'intermediate' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trung cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="advanced"${level == 'advanced' ? 'checked' : ''}>
                                            <div class="type__text text-big">Nâng cao</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Mức giá</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="under500"${priceRange == 'under500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="under1500"${priceRange == 'under1500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 1.500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="over1500"${priceRange == 'over1500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 1.500.000đ</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Đánh giá</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="rating"
                                                   value="low"${rating == 'low' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 3<i class=" text-big fa-solid fa-star"
                                            ></i>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="rating"
                                                   value="high"${rating == 'high' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 3<i class=" text-big fa-solid fa-star"
                                            ></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Thời lượng</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="short"${duration == 'short' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 5 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="medium"${duration == 'medium' ? 'checked' : ''}>
                                            <div class="type__text text-big">5 - 10 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="long"${duration == 'long' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 10 giờ</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Phổ biến</div>
                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="popular"
                                                   value="true"${popular == 'true' ? 'checked' : ''}>
                                            <div class="type__text text-big">Phổ biến</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="display:flex; gap:10px;">
                                <button class="user__profile-btn button__btn text-header"
                                        type="submit">
                                    Lọc
                                </button>

                                <a href="result-search/by-tag?id=${tag.id}"
                                   class="user__profile-btn button__btn text-header fix-btn-2">
                                    Đặt lại
                                </a>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${mode == 'category'}">
                        <form action="result-search/by-category" method="get">
                                <%-- phải có dòng này để khi lọc theo bộ lọc thì vẫn giữ là đã theo category trước đó
                                      nếu không thì nó sẽ reset và tự lọc lại chỉ theo cái phần lọc vừa được chọn--%>
                            <input type="hidden" name="id" value="${cate.id}">
                            <div style="display:flex; gap:10px;">
                                <button class="user__profile-btn button__btn text-header"
                                        type="submit">
                                    Lọc
                                </button>

                                <a href="result-search/by-category?id=${cate.id}"
                                   class="user__profile-btn button__btn text-header fix-btn-2">
                                    Đặt lại
                                </a>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Giá cả</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" name="sortPrice"
                                                   value="desc"${sortPrice == 'desc' ? 'checked' : ''}>
                                            <div class="type__text text-big">Cao đến thấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" name="sortPrice"
                                                   value="asc"${sortPrice == 'asc' ? 'checked' : ''}>
                                            <div class="type__text text-big">Thấp đến cao</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Mức độ</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="beginner"${level == 'beginner' ? 'checked' : ''}>
                                            <div class="type__text text-big">Sơ cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="intermediate"${level == 'intermediate' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trung cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="advanced"${level == 'advanced' ? 'checked' : ''}>
                                            <div class="type__text text-big">Nâng cao</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Mức giá</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="under500"${priceRange == 'under500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="under1500"${priceRange == 'under1500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 1.500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="over1500"${priceRange == 'over1500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 1.500.000đ</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Đánh giá</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="rating"
                                                   value="low"${rating == 'low' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 3<i class=" text-big fa-solid fa-star"
                                            ></i>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="rating"
                                                   value="high"${rating == 'high' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 3<i class=" text-big fa-solid fa-star"
                                            ></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Thời lượng</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="short"${duration == 'short' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 5 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="medium"${duration == 'medium' ? 'checked' : ''}>
                                            <div class="type__text text-big">5 - 10 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="long"${duration == 'long' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 10 giờ</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Phổ biến</div>
                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="popular"
                                                   value="true"${popular == 'true' ? 'checked' : ''}>
                                            <div class="type__text text-big">Phổ biến</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="display:flex; gap:10px;">
                                <button class="user__profile-btn button__btn text-header"
                                        type="submit">
                                    Lọc
                                </button>

                                <a href="result-search/by-category?id=${cate.id}"
                                   class="user__profile-btn button__btn text-header fix-btn-2">
                                    Đặt lại
                                </a>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${mode == 'title'}">
                        <form action="result-search/by-title" method="get">
                                <%-- phải có dòng này để khi lọc theo bộ lọc thì vẫn giữ là đã theo title trước đó
                                      nếu không thì nó sẽ reset và tự lọc lại chỉ theo cái phần lọc vừa được chọn--%>
                            <input type="hidden" name="title" value="${search}">
                            <div style="display:flex; gap:10px;">
                                <button class="user__profile-btn button__btn text-header"
                                        type="submit">
                                    Lọc
                                </button>

                                <a href="result-search/by-title?title=${search}"
                                   class="user__profile-btn button__btn text-header fix-btn-2">
                                    Đặt lại
                                </a>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Giá cả</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" name="sortPrice"
                                                   value="desc"${sortPrice == 'desc' ? 'checked' : ''}>
                                            <div class="type__text text-big">Cao đến thấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" name="sortPrice"
                                                   value="asc"${sortPrice == 'asc' ? 'checked' : ''}>
                                            <div class="type__text text-big">Thấp đến cao</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Mức độ</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="beginner"${level == 'beginner' ? 'checked' : ''}>
                                            <div class="type__text text-big">Sơ cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="intermediate"${level == 'intermediate' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trung cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="level"
                                                   value="advanced"${level == 'advanced' ? 'checked' : ''}>
                                            <div class="type__text text-big">Nâng cao</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Mức giá</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="under500"${priceRange == 'under500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="under1500"${priceRange == 'under1500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 1.500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="priceRange"
                                                   value="over1500"${priceRange == 'over1500' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 1.500.000đ</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Đánh giá</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="rating"
                                                   value="low"${rating == 'low' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 3<i class=" text-big fa-solid fa-star"
                                            ></i>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="rating"
                                                   value="high"${rating == 'high' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 3<i class=" text-big fa-solid fa-star"
                                            ></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Thời lượng</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="short"${duration == 'short' ? 'checked' : ''}>
                                            <div class="type__text text-big">Dưới 5 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="medium"${duration == 'medium' ? 'checked' : ''}>
                                            <div class="type__text text-big">5 - 10 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="duration"
                                                   value="long"${duration == 'long' ? 'checked' : ''}>
                                            <div class="type__text text-big">Trên 10 giờ</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Phổ biến</div>
                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" name="popular"
                                                   value="true"${popular == 'true' ? 'checked' : ''}>
                                            <div class="type__text text-big">Phổ biến</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="display:flex; gap:10px;">
                                <button class="user__profile-btn button__btn text-header"
                                        type="submit">
                                    Lọc
                                </button>

                                <a href="result-search/by-title?title=${search}"
                                   class="user__profile-btn button__btn text-header fix-btn-2">
                                    Đặt lại
                                </a>
                            </div>
                        </form>
                    </c:if>
                </div>

                <div class="grid__colum-9">
                    <div class="grid__row-2">
                        <!-- Nếu có khóa học -->
                        <c:forEach var="c" items="${listCourse}">
                            <div class="grid__column-4 product-card-container">

                                <a href="course-detail?id=${c.id}" class="turn-page">
                                    <div class="product__small-advertisement">
                                        <div class="small-advertisement__image">
                                            <img src="${c.thumbnailUrl}"
                                                 alt="${c.title}" class="img-2">
                                        </div>
                                        <div class="small-advertisement__content">
                                            <div class="content__top">
                                                <div class="content__author-name text-medium content__author-name-2">
                                                    <c:out value="${c.authorName}"/></div>
                                                <div class="content__rate content__rate-2">
                                                    <div class="rate__icon"><i
                                                            class="text-medium fa-regular fa-star"></i></div>
                                                    <fmt:formatNumber value="${c.avgRating}" type="number"
                                                                      maxFractionDigits="1" minFractionDigits="1"
                                                                      var="formattedRating"/>
                                                    <div class="text-medium rate__number"><c:out
                                                            value="${fn:replace(formattedRating, ',', '.')}"/></div>
                                                </div>
                                            </div>
                                            <div class="text-paragraph test-text"><p><c:out value="${c.title}"/></p>
                                            </div>
                                            <div class="content__quick-info">
                                                <div class="quick-info__level">
                                                    <div class="level__icon icon"><i
                                                            class="text-medium fa-solid fa-signal"></i></div>
                                                    <div class="level__text text-medium"><c:out
                                                            value="${c.level.vietnameseName}"/></div>
                                                </div>
                                                <div class="quick-info__users">
                                                    <div class="users__icon icon"><i
                                                            class="text-medium fa-solid fa-users"></i></div>
                                                    <div class="users__text text-medium"><c:out
                                                            value="${c.studentCount}"/></div>
                                                </div>
                                                <div class="quick-info__time">
                                                    <div class="time__icon icon"><i
                                                            class="text-medium fa-regular fa-clock"></i></div>
                                                    <div class="time__text text text-medium"><c:out
                                                            value="${c.durationText}"/></div>
                                                </div>
                                            </div>
                                            <div class="content__price">
                                                <div class="price__new"><c:out value="${c.discountedPrice}"/>
                                                </div>
                                                <div class="price__old"><c:out value="${c.originPrice}"/></div>
                                            </div>

                                            <div class="hover-actions">
                                                <c:choose>
                                                    <c:when test="${c.enrolled}">

                                                        <button type="button" class="btn-add-cart dark-button"
                                                                style="font-size: 1.5rem;background-color: #01FF85;color: #002333"
                                                                onclick="goToCourseContent(event,'${pageContext.request.contextPath}/personal/my-course/detail?courseId=${c.id}')">
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

                        <!-- Nếu không có khóa học -->
                        <c:if test="${empty listCourse}">
                            <div class="search-empty-state">
                                <i class="fa-solid fa-book-open search-empty-icon"></i>
                                <div class="search-empty-title">Không tìm thấy khóa học nào</div>
                                <div class="search-empty-description">
                                    Vui lòng thử lại với từ khóa khác hoặc khám phá các khóa học phổ biến.
                                </div>
                                <a href="index" class="search-empty-link">Khám phá khóa học</a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-3"></div>
                <div class="grid__colum-9">
                    <ul class="pagination home-product__pagination">
                        <!-- Previous -->
                        <c:if test="${currentPage > 1}">
                            <li class="pagination-item">
                                <a href="${paginationUrl}&page=${currentPage - 1}" class="pagination-item__link">
                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
                                </a>
                            </li>
                        </c:if>

                        <!-- Trang 1 -->
                        <li class="pagination-item ${currentPage == 1 ? 'pagination-item--active' : ''}">
                            <a href="${paginationUrl}&page=1" class="pagination-item__link">1</a>
                        </li>

                        <!-- Dấu ... nếu currentPage > 4 -->
                        <c:if test="${currentPage > 4}">
                            <li class="pagination-item">
                                <span class="pagination-item__link">...</span>
                            </li>
                        </c:if>

                        <!-- Các trang gần currentPage -->
                        <c:forEach var="i" begin="${currentPage - 2 < 1 ? 1 : currentPage - 2}"
                                   end="${currentPage + 2}">
                            <c:if test="${i > 1 && i < totalPages}">
                                <li class="pagination-item ${i == currentPage ? 'pagination-item--active' : ''}">
                                    <a href="${paginationUrl}&page=${i}" class="pagination-item__link"><c:out
                                            value="${i}"/></a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <!-- Dấu ... nếu currentPage < totalPages - 3 -->
                        <c:if test="${currentPage < totalPages - 3}">
                            <li class="pagination-item">
                                <span class="pagination-item__link">...</span>
                            </li>
                        </c:if>

                        <!-- Trang cuối -->
                        <c:if test="${totalPages > 1}">
                            <li class="pagination-item ${currentPage == totalPages ? 'pagination-item--active' : ''}">
                                <a href="${paginationUrl}&page=${totalPages}" class="pagination-item__link"><c:out
                                        value="${totalPages}"/></a>
                            </li>
                        </c:if>

                        <!-- Next -->
                        <c:if test="${currentPage < totalPages}">
                            <li class="pagination-item">
                                <a href="${paginationUrl}&page=${currentPage + 1}" class="pagination-item__link">
                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/views/layouts/footer.jsp"/>
    <jsp:include page="/views/components/toast.jsp"/>
</div>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>