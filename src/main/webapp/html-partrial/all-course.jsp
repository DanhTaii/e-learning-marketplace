<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Classes</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/all-course.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/card.css">
</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="container__title text-big-title">TẤT CẢ KHÓA HỌC</div>
                <div class="container__filter">
                    <div class="filter__text">
                        <span>Sắp xếp theo:</span>
                    </div>
                    <div class="filter__button">
                        <button class="btn">
                            <a class="turn-page text-big" href="pagination-all-courses?popular=true&page=1">Phổ biến</a>
                        </button>
                    </div>
                    <div class="filter__button">
                        <button class="btn">
                            <a class="turn-page text-big" href="pagination-all-courses?newest=true&page=1">Mới nhất</a>
                        </button>
                    </div>
                    <div class="filter__price">
                        <div class="price__text">
                            <span class="text-big">Giá</span>
                        </div>
                        <div class="price__icon">
                            <i class="fa-solid fa-angle-down"></i>
                        </div>
                        <div class="price__list-box">
                            <ul class="price__list">
                                <li class="price__list-item">
                                    <a class="turn-page text-big" href="pagination-all-courses?sortPrice=asc&page=1">Giá thấp đến cao</a>
                                </li>
                                <li class="price__list-item">
                                    <a class="turn-page text-big" href="pagination-all-courses?sortPrice=desc&page=1">Giá cao đến thấp</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- Danh mục -->
                    <div class="filter__category">
                        <div class="category__text">Tất cả khóa học</div>
                        <div class="category__icon">
                            <i class="text-big fa-solid fa-angle-down"></i>
                        </div>
                        <div class="category__list-box">
                            <ul class="category__list">
                                <!-- Mục cố định: Tất cả khóa học -->
                                <li class="category__list-item">
                                    <a class="turn-page text-big" href="pagination-all-courses?page=1">Tất cả khóa học</a>
                                </li>
                                <c:forEach var="cate" items="${categories}">
                                    <li class="category__list-item">
                                        <a class="turn-page text-big" href="pagination-all-courses?category=${cate.id}&page=1">
                                                ${cate.name}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="product__small">
                    <div class="grid">
                        <div class="grid__row-2">
                            <c:forEach var="c" items="${listCourse}">
                                <div class="grid__column-3">
                                    <a href="course-detail?id=${c.id}" class="turn-page">
                                        <div class="product__small-advertisement">
                                            <div class="small-advertisement__image">
                                                <img src="${c.thumbnailUrl}"
                                                     alt="Bí quyết sáng tạo quảng cáo viral và livestream bán hàng"
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
                                                        <div class="users__text text-medium">Số lượng học viên</div>
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
                                                    <div class="quick-info__save"><i
                                                            class="quick-info__save__icon fa-solid fa-heart"></i></div>
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
        </div>
        <ul class="pagination home-product__pagination">
            <c:if test="${currentPage > 1}">
                <li class="pagination-item">
                    <a href="pagination-all-courses?page=${currentPage - 1}" class="pagination-item__link">
                        <i class="pagination-item__icon fa-solid fa-angle-left"></i>
                    </a>
                </li>
            </c:if>

            <!-- Hiển thị trang đầu -->
            <li class="pagination-item ${currentPage == 1 ? 'pagination-item--active' : ''}">
                <a href="pagination-all-courses?page=1" class="pagination-item__link">1</a>
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
                        <a href="pagination-all-courses?page=${i}" class="pagination-item__link">${i}</a>
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
                    <a href="pagination-all-courses?page=${totalPages}" class="pagination-item__link">${totalPages}</a>
                </li>
            </c:if>

            <c:if test="${currentPage < totalPages}">
                <li class="pagination-item">
                    <a href="pagination-all-courses?page=${currentPage + 1}" class="pagination-item__link">
                        <i class="pagination-item__icon fa-solid fa-angle-right"></i>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>
</body>
</html>