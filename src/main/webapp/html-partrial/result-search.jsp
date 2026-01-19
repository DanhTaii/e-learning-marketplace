<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Result search</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/result-search.css">
    <link rel="stylesheet" href="assets/css/card.css?v=1.0.2">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="container__title text-big-title">
                    <div class="container__title text-big-title">
                        <c:if test="${not empty cate}">
                            ${cate.name}
                        </c:if>
                        <c:if test="${empty cate and not empty search}">
                            Kết quả cho từ khóa: "${search}"
                        </c:if>
                        <c:if test="${empty cate and empty search and not empty tag}">
                            Tag: ${tag.name}
                        </c:if>
                        <c:if test="${empty cate and empty search and empty tag}">
                            Tất cả khóa học
                        </c:if>

                    </div>
                </div>
                <div class="grid__column-3">
                    <c:if test="${mode == 'tag'}">
                        <form action="result-search/by-tag" method="get">
                                <%-- phải có dòng này để khi lọc theo bộ lọc thì vẫn giữ là đã theo tag trước đó
                                      nếu không thì nó sẽ reset và tự lọc lại chỉ theo cái phần lọc vừa được chọn--%>
                            <input type="hidden" name="id" value="${cate.id}">
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Giá cả</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" class="type__checkbox text-big" name="sortPrice" value="desc">
                                            <div class="type__text text-big">Cao đến thấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" class="type__checkbox text-big" name="sortPrice" value="asc">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="beginner">
                                            <div class="type__text text-big">Người mới</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="intermediate">
                                            <div class="type__text text-big">Trung cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="advanced">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="under500">
                                            <div class="type__text text-big">Dưới 500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="under1500">
                                            <div class="type__text text-big">Dưới 1.500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="over1500">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="rating" value="low">
                                            <div class="type__text text-big">Dưới 3<i class=" text-big fa-solid fa-star"
                                                                                      style="color: var(--yellow-color)"></i>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="rating" value="high">
                                            <div class="type__text text-big">Trên 3<i class=" text-big fa-solid fa-star"
                                                                                      style="color: var(--yellow-color)"></i>
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
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="short">
                                            <div class="type__text text-big">Dưới 5 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="medium">
                                            <div class="type__text text-big">5 - 10 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="long">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="popular" value="true">
                                            <div class="type__text text-big">Phổ biến</div>
                                        </div>
                                        <div class="content__number text-big">10</div>
                                    </div>
                                </div>
                            </div>
                            <button class="user__profile-btn button__btn text-header"
                                    type="submit" style="justify-content: center">Lọc
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${mode == 'category'}">
                        <form action="result-search/by-category" method="get">
                                <%-- phải có dòng này để khi lọc theo bộ lọc thì vẫn giữ là đã theo category trước đó
                                      nếu không thì nó sẽ reset và tự lọc lại chỉ theo cái phần lọc vừa được chọn--%>
                            <input type="hidden" name="id" value="${cate.id}">
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Giá cả</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" class="type__checkbox text-big" name="sortPrice" value="desc">
                                            <div class="type__text text-big">Cao đến thấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" class="type__checkbox text-big" name="sortPrice" value="asc">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="beginner">
                                            <div class="type__text text-big">Người mới</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="intermediate">
                                            <div class="type__text text-big">Trung cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="advanced">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="under500">
                                            <div class="type__text text-big">Dưới 500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="under1500">
                                            <div class="type__text text-big">Dưới 1.500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="over1500">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="rating" value="low">
                                            <div class="type__text text-big">Dưới 3<i class=" text-big fa-solid fa-star"
                                                                                      style="color: var(--yellow-color)"></i>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="rating" value="high">
                                            <div class="type__text text-big">Trên 3<i class=" text-big fa-solid fa-star"
                                                                                      style="color: var(--yellow-color)"></i>
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
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="short">
                                            <div class="type__text text-big">Dưới 5 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="medium">
                                            <div class="type__text text-big">5 - 10 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="long">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="popular" value="true">
                                            <div class="type__text text-big">Phổ biến</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button class="user__profile-btn button__btn text-header"
                                    type="submit" style="justify-content: center">Lọc
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${mode == 'title'}">
                        <form action="result-search/by-title" method="get">
                                <%-- phải có dòng này để khi lọc theo bộ lọc thì vẫn giữ là đã theo title trước đó
                                      nếu không thì nó sẽ reset và tự lọc lại chỉ theo cái phần lọc vừa được chọn--%>
                            <input type="hidden" name="title" value="${search}">
                            <div class="container__filter">
                                <div class="filter__box">
                                    <div class="box__title text-big">Giá cả</div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" class="type__checkbox text-big" name="sortPrice" value="desc">
                                            <div class="type__text text-big">Cao đến thấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="radio" class="type__checkbox text-big" name="sortPrice" value="asc">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="beginner">
                                            <div class="type__text text-big">Người mới</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="intermediate">
                                            <div class="type__text text-big">Trung cấp</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="level" value="advanced">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="under500">
                                            <div class="type__text text-big">Dưới 500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="under1500">
                                            <div class="type__text text-big">Dưới 1.500.000đ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="priceRange" value="over1500">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="rating" value="low">
                                            <div class="type__text text-big">Dưới 3<i class=" text-big fa-solid fa-star"
                                                                                      style="color: var(--yellow-color)"></i>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="rating" value="high">
                                            <div class="type__text text-big">Trên 3<i class=" text-big fa-solid fa-star"
                                                                                      style="color: var(--yellow-color)"></i>
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
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="short">
                                            <div class="type__text text-big">Dưới 5 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="medium">
                                            <div class="type__text text-big">5 - 10 giờ</div>
                                        </div>
                                    </div>

                                    <div class="box__content">
                                        <div class="content__type">
                                            <input type="checkbox" class="type__checkbox text-big" name="duration" value="long">
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
                                            <input type="checkbox" class="type__checkbox text-big" name="popular" value="true">
                                            <div class="type__text text-big">Phổ biến</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button class="user__profile-btn button__btn text-header"
                                    type="submit" style="justify-content: center">Lọc
                            </button>
                        </form>
                    </c:if>
                </div>
                <div class="grid__colum-9">
                    <div class="grid__row-2">
                        <c:forEach var="c" items="${listCourse}">
                            <div class="grid__column-4">
                                <a href="course-detail?id=${c.id}" class="turn-page">
                                    <div class="product__small-advertisement">
                                        <div class="small-advertisement__image">
                                            <img src="${c.thumbnailUrl}"
                                                 alt="Lắng Nghe Chủ Động Và Hiểu Ý Người Nói Một Cách Sâu Sắc"
                                                 class="img-2">
                                        </div>
                                        <div class="small-advertisement__content">
                                            <div class="content__top">
                                                <div class="content__author-name text-medium">${c.authorName}</div>
                                                <div class="content__rate">
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
                                                    <div class="users__text text-medium">Số học viên</div>
                                                </div>
                                                <div class="quick-info__time">
                                                    <div class="time__icon icon"><i
                                                            class="text-medium fa-regular fa-clock"></i></div>
                                                    <div class="time__text text-medium">${c.durationHours}h</div>
                                                </div>
                                            </div>
                                            <div class="content__price">
                                                <div class="price__new">${c.price - c.discountPrice}đ</div>
                                                <div class="price__old">${c.price}đ</div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
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
                        <c:forEach var="i" begin="${currentPage - 2 < 1 ? 1 : currentPage - 2}" end="${currentPage + 2}">
                            <c:if test="${i > 1 && i < totalPages}">
                                <li class="pagination-item ${i == currentPage ? 'pagination-item--active' : ''}">
                                    <a href="${paginationUrl}&page=${i}" class="pagination-item__link">${i}</a>
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
                                <a href="${paginationUrl}&page=${totalPages}" class="pagination-item__link">${totalPages}</a>
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
    <jsp:include page="/header-footer/footer.jsp"/>
</div>
</body>
</html>