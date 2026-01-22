<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/home.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/card.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="web">
    <jsp:include page="header-footer/header.jsp"/>
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
                <div class="grid__colum-9">
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
                                        <div class="content__top content__top-2">
                                            <div class="content__author-name text-medium content__author-name-3">${courseMostPopular.authorName}</div>
                                            <div class="content__rate content__rate-3">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">${courseMostPopular.avgRating}</div>
                                            </div>
                                        </div>
                                        <div class="content__quick-info for-fix">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">${courseMostPopular.level}</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">${courseMostPopular.studentCount}</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text text-medium">${courseMostPopular.durationHours}h</div>
                                            </div>
                                        </div>
                                        <div class="content__price content__price-2">
                                            <div class="price__new"><fmt:formatNumber
                                                    value="${courseMostPopular.price - courseMostPopular.discountPrice}"
                                                    type="number" pattern="###,###"></fmt:formatNumber> đ
                                            </div>
                                            <div class="price__old"><fmt:formatNumber value="${courseMostPopular.price}"
                                                                                      type="number"
                                                                                      pattern="###,###"></fmt:formatNumber>
                                                đ
                                            </div>
                                        </div>
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
                                                                                                  pattern="###,###"></fmt:formatNumber>
                                                            đ
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
    <jsp:include page="header-footer/footer.jsp"/>
</div>
</body>
<script>

    function addToCart(courseId) {

        fetch('add-cart?id=' + courseId, {
            method: 'GET',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(response => {
                if (response.status === 401) {
                    alert("Bạn cần đăng nhập để thêm vào giỏ hàng!");
                    window.location.href = "html-authentication/sign-in.jsp";
                    return null;
                }
                if (response.ok) return response.text();
                throw new Error('Network response was not ok.');
            })
            .then(newCount => {
                if (newCount === null) return;
                const cartElement = document.getElementById('cart-count');
                if (cartElement) {
                    cartElement.innerText = newCount;
                }

                alert("Đã thêm khóa học vào giỏ hàng!");
            })
            .catch(error => {
                console.error('Lỗi AJAX:', error);
                alert("Không thể thêm vào giỏ hàng, vui lòng thử lại.");
            });
    }

    // ép load tại trang khi bấm back
    window.addEventListener("pageshow", function (event) {
        var historyTraversal = event.persisted
        if (historyTraversal) {
            window.location.reload();
        }
    });
</script>
<script src="assets/javascript/add-wishlist.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>

</html>