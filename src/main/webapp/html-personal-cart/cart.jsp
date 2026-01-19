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
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/home.css">
    <link rel="stylesheet" href="assets/css/cart.css?v=1.0.1">
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
                                                    <input type="checkbox" class="tick" name="itemSelected" value="${p.course.id}"
                                                           <c:if test="${p.selected}">checked</c:if>
                                                           onchange="this.form.submit()">
                                                </div>

                                            <a href="../html-partrial/course-detail.jsp" class="turn-page">
                                                <div class="cart-items__detail">
                                                    <div class="detail__image-container" style="aspect-ratio: 16 / 9;">
                                                        <img src="${p.course.thumbnailUrl}" alt="${p.course.title}" class="image">
                                                    </div>
                                                    <div class="detail__info">

                                                        <div class="info__name-group">
                                        <span class="name__title text-paragraph">
                                            <p>${p.course.title}</p>
                                        </span>
                                                        </div>
                                                        <div class="info__rating-group">
                                                            <span class="rating-group__tags tags text-mini">Bestseller</span>
                                                            <span class="rating-group__rating rating text-mini">${p.course.rating}
                                        <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                        <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                        </span>
                                                            <span class="rating-group__rating-count ratings-count text-mini ">(${p.course.studentCount} rating)</span>
                                                        </div>
                                                        <div class="info__stats course-stats ">
                                                            <span class="stats__hours text-mini">${p.course.durationHours}</span>

                                                            <span class="stats__lecture text-mini ">• ${p.course.lessonCount} Bài giảng</span>

                                                            <span class="stats__level text-mini">• ${p.course.level}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </a>
                                            <div class="cart-items__action-price-group action-price-group">
                                                <div class="cart-items__action items-action">
                                                    <a href="cart-manager?action=moveToWishlist&id=${p.course.id}" class="action__link">Thêm vào Yêu Thích</a>
                                                     <a href="cart-manager?action=delete&id=${p.course.id}" class="action__link1">Xóa</a>
                                                </div>
                                                <a href="../html-partrial/course-detail.jsp" class="turn-page">
                                                    <div class="cart-items__price items-price">
                                                        <div><span class="price-discounted"><fmt:formatNumber value="${p.course.price - p.course.discountPrice}" type="number" pattern="###,###" /> đ <i
                                                                class="fa-solid fa-tag price-icon"
                                                                style="color: #3722d3;"></i> </span></div>
                                                        <div><span class="price-origin"><fmt:formatNumber value="${p.course.price}" type="number" pattern="###,###" /> đ </span>
                                                        </div>


                                                    </div>
                                                </a>

                                            </div>

                                        </div>

                                    </li>
                                </c:forEach>


                            </ul>
                            </form>
                        </div>

                    </div>

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

                            <a href="cart-manager?action=moveSelectedToWishlist" class="text-medium wishlisted " style="margin-left: 7px;text-decoration: none">Thêm vào Yêu thích</a>
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
                                        <span class="price-discounted1 "><fmt:formatNumber value="${sessionScope.cart.finalPriceTotal}" type="number" pattern="###,###" /> đ</span>
                                        <span class=" price-origin"><fmt:formatNumber value="${sessionScope.cart.total}" type="number" pattern="###,###" /> đ</span>
                                    </div>

                                </div>

                            </div>
                            <a href="show-payment" class="turn-page">
                                <div class="checkout__checkout-button header__button">
                                    <button class="button__btn">Tiến hành thanh toán</button>
                                </div>
                            </a>
                        </div>


                    </div>
                </div>

            </div>
            <div class="grid">
                <div class="grid__row-2">
                    <div class="product__small-title text-small-title">Có thể bạn sẽ thích</div>
                    <div class="grid__column-3">
                        <a href="../html-partrial/course-detail.jsp?id=1" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img srcset="https://static.unica.vn/media/imagesck/1664934097_thuong-hieu-ca-nhan-la-gi.png?v=1664934097"
                                         alt="Xây Dựng Thương Hiệu Cá Nhân" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium">Quản trị viên</div>
                                        <div class="content__rate">
                                            <div class="rate__icon"><i
                                                    class="text-medium fa-regular fa-star"></i></div>
                                            <div class="text-medium rate__number">4.7</div>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text"><p>Xây Dựng Thương Hiệu Cá Nhân Cho Bản
                                        Thân </p></div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <div class="level__icon icon"><i
                                                    class="text-medium fa-solid fa-signal"></i></div>
                                            <div class="level__text text-medium">Người mới</div>
                                        </div>
                                        <div class="quick-info__users">
                                            <div class="users__icon icon"><i
                                                    class="text-medium fa-solid fa-users"></i></div>
                                            <div class="users__text text-medium">13.4k</div>
                                        </div>
                                        <div class="quick-info__time">
                                            <div class="time__icon icon"><i
                                                    class="text-medium fa-regular fa-clock"></i></div>
                                            <div class="time__text text text-medium">12h</div>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new">799.000đ</div>
                                        <div class="price__old">1.199.000đ</div>
                                        <div class="quick-info__save"><i
                                                class="quick-info__save__icon fa-solid fa-heart"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="grid__column-3">
                        <a href="../html-partrial/course-detail.jsp?id=1" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img srcset="https://img.freepik.com/premium-vector/faq-question-mark-with-people-flat-style_1366-316.jpg"
                                         alt="Đặt Câu Hỏi Thông Minh" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium">Quản trị viên</div>
                                        <div class="content__rate">
                                            <div class="rate__icon"><i
                                                    class="text-medium fa-regular fa-star"></i></div>
                                            <div class="text-medium rate__number">4.5</div>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text"><p>Kỹ Năng Đặt Câu Hỏi Thông Minh</p></div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <div class="level__icon icon"><i
                                                    class="text-medium fa-solid fa-signal"></i></div>
                                            <div class="level__text text-medium">Người mới</div>
                                        </div>
                                        <div class="quick-info__users">
                                            <div class="users__icon icon"><i
                                                    class="text-medium fa-solid fa-users"></i></div>
                                            <div class="users__text text-medium">5k6</div>
                                        </div>
                                        <div class="quick-info__time">
                                            <div class="time__icon icon"><i
                                                    class="text-medium fa-regular fa-clock"></i></div>
                                            <div class="time__text text text-medium">12h</div>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new">299.000đ</div>
                                        <div class="price__old">499.000đ</div>
                                        <div class="quick-info__save"><i
                                                class="quick-info__save__icon fa-solid fa-heart"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="grid__column-3">
                        <a href="../html-partrial/course-detail.jsp?id=1" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img srcset="https://suckhoedoisong.qltns.mediacdn.vn/zoom/600_315/324455921873985536/2022/5/4/stress-nang-min-e1620809978914-1651628209648642071280-61-0-482-674-crop-16516282155721052156928.png"
                                         alt="KChống Burnout & Quản Lý Stress" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium">Quản trị viên</div>
                                        <div class="content__rate">
                                            <div class="rate__icon"><i
                                                    class="text-medium fa-regular fa-star"></i></div>
                                            <div class="text-medium rate__number">4.8</div>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text"><p>Chống Burnout Và Quản Lý Stress</p></div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <div class="level__icon icon"><i
                                                    class="text-medium fa-solid fa-signal"></i></div>
                                            <div class="level__text text-medium">Người mới</div>
                                        </div>
                                        <div class="quick-info__users">
                                            <div class="users__icon icon"><i
                                                    class="text-medium fa-solid fa-users"></i></div>
                                            <div class="users__text text-medium">18.9k</div>
                                        </div>
                                        <div class="quick-info__time">
                                            <div class="time__icon icon"><i
                                                    class="text-medium fa-regular fa-clock"></i></div>
                                            <div class="time__text text text-medium">12h</div>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new">450.000đ</div>
                                        <div class="price__old">690.000đ</div>
                                        <div class="quick-info__save"><i
                                                class="quick-info__save__icon fa-solid fa-heart"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="grid__column-3">
                        <a href="../html-partrial/course-detail.jsp?id=1" class="turn-page">
                            <div class="product__small-advertisement">
                                <div class="small-advertisement__image">
                                    <img srcset="https://growupwork.com/uploads/blogs/img/Said-no-with-work.jpg"
                                         alt="Từ Chối Mà Vẫn Được Yêu Quý" class="img-2">
                                </div>
                                <div class="small-advertisement__content">
                                    <div class="content__top">
                                        <div class="content__author-name text-medium">Quản trị viên</div>
                                        <div class="content__rate">
                                            <div class="rate__icon"><i
                                                    class="text-medium fa-regular fa-star"></i></div>
                                            <div class="text-medium rate__number">4.6</div>
                                        </div>
                                    </div>
                                    <div class="text-paragraph test-text"><p>Kỹ Năng Từ Chối Mà Vẫn Được Yêu Quý</p>
                                    </div>
                                    <div class="content__quick-info">
                                        <div class="quick-info__level">
                                            <div class="level__icon icon"><i
                                                    class="text-medium fa-solid fa-signal"></i></div>
                                            <div class="level__text text-medium">Người mới</div>
                                        </div>
                                        <div class="quick-info__users">
                                            <div class="users__icon icon"><i
                                                    class="text-medium fa-solid fa-users"></i></div>
                                            <div class="users__text text-medium">8k3</div>
                                        </div>
                                        <div class="quick-info__time">
                                            <div class="time__icon icon"><i
                                                    class="text-medium fa-regular fa-clock"></i></div>
                                            <div class="time__text text text-medium">12h</div>
                                        </div>
                                    </div>
                                    <div class="content__price">
                                        <div class="price__new">299.000đ</div>
                                        <div class="price__old">449.000đ</div>
                                        <div class="quick-info__save"><i
                                                class="quick-info__save__icon fa-solid fa-heart"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
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

</body>
</html>