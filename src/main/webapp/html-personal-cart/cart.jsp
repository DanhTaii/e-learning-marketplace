<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shopping Cart</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/home.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/card.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/cart.css?v=<%=System.currentTimeMillis()%>">
    <script src="assets/fonts/fontawesome-free-7.1.0-web/js/jquery-3.6.0.min.js"></script>
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/fonts.css">

</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid">
            <div class="layout">
                <div class="layout__shopping-cart">
                    <div class="shopping-cart">
                        <span class="shopping-cart__title text-big-title h1">Giỏ hàng</span>
                        <span class="shopping-cart__summary summary text-paragraph ">
                        <span class="text-2xl">Sản phẩm (${sessionScope.cart.totalQuantity})</span>
                    </span>

                        <c:choose>
                            <c:when test="${not empty sessionScope.cart && sessionScope.cart.totalQuantity > 0}">
                                <div class="shopping-cart__sub-title">
                                    <div class="sub-title__column1">
                                        <input type="checkbox" class="tick" name="tick">
                                        <div class="sub_title__title text-paragraph">
                                            <span class="text-2xl">Sản phẩm</span>
                                        </div>
                                    </div>
                                    <div class="sub-title__action-price-group action-price-group">
                                        <div class="sub-title__action text-paragraph">
                                            <span class="text-2xl">Hành động</span>
                                        </div>
                                        <div class="sub-title__price text-paragraph">
                                            <span class="text-2xl">Giá</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="scrollable-order-list">
                                    <form action="update-select" id="cartForm" method="post">
                                        <ul>
                                            <c:forEach var="p" items="${sessionScope.cart.list}">
                                                <li>
                                                    <div class="shopping-cart__cart-items cart-items">
                                                        <div class="cart-items__tick">
                                                            <input type="checkbox" class="tick" name="itemSelected"
                                                                   value="${p.course.id}"
                                                                   <c:if test="${p.selected}">checked</c:if>
                                                                   onchange="this.form.submit()">
                                                        </div>

                                                        <a href="course-detail?id=${p.course.id}" class="turn-page">
                                                            <div class="cart-items__detail">
                                                                <div class="detail__image-container"
                                                                     style="aspect-ratio: 16 / 9;">
                                                                    <img src="${p.course.thumbnailUrl}"
                                                                         alt="${p.course.title}" class="image">
                                                                </div>
                                                                <div class="detail__info">
                                                                    <div class="info__name-group">
                                                                        <span class="name__title text-paragraph"><p>${p.course.title}</p></span>
                                                                    </div>
                                                                    <div class="info__rating-group">
                                                                        <span class="rating-group__tags tags text-mini">Bestseller</span>
                                                                        <span class="rating-group__rating rating text-mini">${p.course.avgRating}
                                                                        <i class="fa-solid fa-star"
                                                                           style="color: #FFD43B; font-size: 1rem"></i>
                                                                    </span>
                                                                        <span class="rating-group__rating-count ratings-count text-mini">
                                                                        (${p.course.studentCount} rating)
                                                                    </span>
                                                                    </div>
                                                                    <div class="info__stats course-stats">
                                                                        <span class="stats__hours text-mini">${p.course.durationText}</span>
                                                                        <span class="stats__lecture text-mini">• ${p.course.lessonCount} Bài giảng</span>
                                                                        <span class="stats__level text-mini">• ${p.course.level.vietnameseName}</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>

                                                        <div class="cart-items__action-price-group action-price-group">
                                                            <div class="cart-items__action items-action">
                                                                <a href="cart-manager?action=moveToWishlist&id=${p.course.id}"
                                                                   class="action__link">Thêm vào Yêu Thích</a>
                                                                <a href="cart-manager?action=delete&id=${p.course.id}"
                                                                   class="action__link1">Xóa</a>
                                                            </div>
                                                            <div class="cart-items__price items-price">
                                                                <div><span class="price-discounted">
                                                                <fmt:formatNumber
                                                                        value="${p.course.price - p.course.discountPrice}"
                                                                        type="number" pattern="###,###"/> đ
                                                                <i class="fa-solid fa-tag price-icon"
                                                                   style="color: #3722d3;"></i>
                                                            </span></div>
                                                                <div><span class="price-origin">
                                                                <fmt:formatNumber value="${p.course.price}"
                                                                                  type="number" pattern="###,###"/> đ
                                                            </span></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </form>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="cart-empty-state">
                                    <i class="fa-solid fa-cart-shopping cart-empty-icon"></i>
                                    <div class="cart-empty-title">Giỏ hàng của bạn đang trống</div>
                                    <div class="cart-empty-description">
                                        Hãy khám phá các khóa học và thêm vào giỏ hàng để bắt đầu học tập.
                                    </div>
                                    <a href="index" class="cart-empty-link">Khám phá khóa học</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layout__checkout-summary grid">
                    <div class="checkout__row2">
                        <div class="grid__column-4">
                            <div class="row2__column1">
                                <div class="tick">
                                    <input type="checkbox" id="checkAll"
                                           <c:if test="${sessionScope.cart.selectedQuantity == sessionScope.cart.totalQuantity && sessionScope.cart.totalQuantity > 0}">checked</c:if>
                                           onchange="handleSelectAll(this)">
                                </div>
                                <label for="checkAll" class="choose text-medium">
                                    Chọn tất cả (${sessionScope.cart.totalQuantity})
                                </label>
                                <a href="cart-manager?action=removeSelected" class="text-medium remove">Xóa</a>
                                <a href="cart-manager?action=moveSelectedToWishlist" class="text-medium wishlisted"
                                   style="margin-left: 7px;text-decoration: none">Thêm vào Yêu thích</a>
                            </div>
                        </div>
                        <div class="grid__column-5">
                            <div class="checkout__total">
                                <div class="total_title">
                                    <div class="total__label">
                                        <div class="label">
                                            <div class="label__name">
                                                <span class="text-medium">Tổng cộng (${sessionScope.cart.selectedQuantity}):</span>
                                            </div>
                                            <div class="charge-note">Chưa tính phí</div>
                                        </div>
                                        <div class="total__price">
                                            <span class="price-discounted1"><fmt:formatNumber
                                                    value="${sessionScope.cart.finalPriceTotal}" type="number"
                                                    pattern="###,###"/> đ</span>
                                            <span class="price-origin"><fmt:formatNumber
                                                    value="${sessionScope.cart.total}" type="number" pattern="###,###"/> đ</span>
                                        </div>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.cart && sessionScope.cart.selectedQuantity > 0}">
                                        <a href="payment" class="turn-page">
                                            <div class="checkout__checkout-button header__button">
                                                <button class="button__btn">Tiến hành thanh toán</button>
                                            </div>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a>
                                            <div class="checkout__checkout-button header__button">
                                                <button class="button__btn"
                                                        style="background-color: #ccc; cursor: not-allowed;" disabled>
                                                    Tiến hành thanh toán
                                                </button>
                                            </div>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="grid">
                    <div class="grid__row-2">
                        <div class="product__small-title text-small-title">Có thể bạn sẽ thích</div>
                        <c:forEach var="c" items="${coursesLastest}" begin="0" end="3">
                            <div class="grid__column-3 product-card-container">
                                <a href="course-detail?id=${c.id}" class="turn-page">
                                    <div class="product__small-advertisement">
                                        <div class="small-advertisement__image">
                                            <img src="${c.thumbnailUrl}" alt="${c.title}" class="img-2">
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
                                                    <div class="time__text text-medium">${c.durationText}</div>
                                                </div>
                                            </div>
                                            <div class="content__price">
                                                <div class="price__new">
                                                    <fmt:formatNumber value="${c.price - c.discountPrice}" type="number"
                                                                      pattern="###,###"/> đ
                                                </div>
                                                <div class="price__old">
                                                    <fmt:formatNumber value="${c.price}" type="number"
                                                                      pattern="###,###"/> đ
                                                </div>
                                            </div>
                                            <div class="hover-actions">
                                                <button type="submit" style="font-size: 1.5rem"
                                                        class="btn-add-cart dark-button"
                                                        onclick="addToCart(event,${c.id})">Thêm vào giỏ
                                                </button>

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
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>

<script>
    function submitCartForm() {
        document.getElementById('cartForm').submit();
    }

    function handleSelectAll(checkbox) {
        const isChecked = checkbox.checked;
        // Gửi yêu cầu đến Servlet bạn đã viết
        window.location.href = "cart-manager?action=selectAll&status=" + isChecked;
    }
</script>
<script src="assets/javascript/add-wishlist.js?v=<%=System.currentTimeMillis()%>"></script>

</body>
</html>