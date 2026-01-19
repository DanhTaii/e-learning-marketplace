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
    <header class="web__header">
        <div class="grid">
            <div class="header__box">
                <a href="home.jsp" class="turn-page">
                    <div class="header__logo">
                        SKILL
                    </div>
                </a>
                <div class="header__browse">
                    <div class="browse__text text-header">Danh mục</div>
                    <i class="browse__icon text-header fa-solid fa-angle-down"></i>
                    <div class="browse__display">
                        <div class="browse__container">
                            <div class="browse__container-box-1">
                                <div class="browse__box-category">
                                    <div class="box__title">
                                        <span class="category__title title">Tìm bằng Danh mục</span>
                                    </div>
                                    <ul class="browse__box-category-list list">
                                        <li class="browse__box-category-list-item text-list-item text-li">Giao tiếp
                                            & Thuyết trình
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Lãnh đạo &
                                            Quản lý
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Năng suất &
                                            Quản lý thời gian
                                        </li>
                                        <a href="result-search.jsp" class="turn-page">
                                            <li class="browse__box-category-list-item text-list-item text-li">

                                                Tư duy & Sáng tạo
                                            </li>
                                        </a>
                                        <li class="browse__box-category-list-item text-list-item text-li">Trí tuệ cảm
                                            xúc (EQ)
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Đàm phán &
                                            Thuyết phục
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Phát triển bản
                                            thân
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Kỹ năng học
                                            tập & Tự học
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Kỹ năng công
                                            sở & Networking
                                        </li>
                                        <li class="browse__box-category-list-item text-list-item text-li">Sức khỏe tinh
                                            thần & Chống Burnout
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="browse__container-box-2">
                                <div class="browse__container-box-2-1 box-2">
                                    <div class="box__title">
                                        <span class="category__title title">Tìm bằng kiểu khóa học</span>
                                    </div>
                                    <ul class="browse__container-box-2-list list">
                                        <li class="browse__container-box-2-list-item">
                                            <a href="../html-partrial/all-course.html" class="turn-page"></a>
                                            <div class="item-box">
                                                <a href="../html-partrial/all-course.html"
                                                   class="text-list-item text-list-item-2 text-li">Tất cả khóa học</a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="browse__container-box-2-2 box-2">
                                    <div class="box__title">
                                        <span class="category__title title">Tags</span>
                                    </div>
                                    <ul class="browse__container-box-2-list list">
                                        <li class="browse__container-box-2-list-item">
                                            <div class="item-box">
                                                <a href="result-search.jsp"
                                                   class="text-list-item text-list-item-2 text-li turn-page">Yêu
                                                    thích</a>
                                            </div>
                                        </li>
                                        <li class="browse__container-box-2-list-item">
                                            <div class="item-box">
                                                <a href="" class="text-list-item text-list-item-2 text-li">Nổi bật</a>
                                            </div>
                                        </li>
                                        <li class="browse__container-box-2-list-item">
                                            <div class="item-box">
                                                <a href="" class="text-list-item text-list-item-2 text-li">Phổ biến</a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header__community text-header">Cộng đồng</div>
                <div class="header__search">
                    <div class="search__icon">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <div class="search__input">
                        <input type="text" class="input__text text-medium"
                               placeholder="Tìm kiếm khóa học, kỹ năng,...">
                    </div>
                </div>
                <div class="header__class">
                    <a href="../html-personal/my-course.jsp" class="turn-page text-header">
                        Khóa học của tôi
                    </a>
                </div>
                <div class="header__wishlist">
                    <a href="../html-personal/my-wishlist.jsp" class="turn-page text-header">
                        <i class="notification__icon fa-solid fa-heart text-header"></i>
                    </a>
                </div>
                <div class="header__cart">
                    <a href="../html-personal-cart/cart.jsp" class="turn-page text-header">
                        <i class="text-header fa-solid fa-cart-shopping"></i>
                    </a>
                </div>
                <!-- Toggle checkbox ẩn -->
                <div class="header__notification" tabindex="0">
                    <div class="notification-wrapper text-header">
                        <i class="notification__icon fa-solid fa-bell text-header"></i>
                    </div>
                    <div class="notification__popup">
                        <div class="text-header">Bạn chưa có thông báo!</div>
                    </div>
                </div>
                <div class="header__user">
                    <img src="../assets/image/65472207_145188949876444_2344275901291692032_n.jpg" alt=""
                         class="user__avatar">
                    <div class="user__display">
                        <div class="user__container">
                            <div class="user__profile">
                                <div class="user__profile-avatar">
                                    <img src="../assets/image/65472207_145188949876444_2344275901291692032_n.jpg"
                                         alt=""
                                         class="user__avatar-mini">
                                </div>
                                <div class="user__profile-name">
                                    <a href="" class="name-text text-header">Ngoc Minh</a>
                                </div>
                                <div class="user__profile-bio">
                                    <a href="" class="bio-text">Thêm tiểu sử</a>
                                </div>
                                <a href="../html-personal/account-profile.jsp" class="turn-page">
                                    <div class="user__profile-btn">
                                        <button class="user-btn button__btn text-header">Xem thông tin</button>
                                    </div>
                                </a>
                            </div>
                            <div class="user__menu">
                                <ul class="user__menu-list">
                                    <li class="user__menu-list-item">
                                        <a href="../html-personal/my-course.jsp" class="turn-page">
                                            <div class="user__menu-list-item-box text-li">
                                                Khóa học
                                            </div>
                                        </a>
                                    </li>
                                    <li class="user__menu-list-item">
                                        <a href="../html-personal/my-wishlist.jsp" class="turn-page">
                                            <div class="user__menu-list-item-box text-li">
                                                Yêu thích
                                            </div>
                                        </a>
                                    </li>
                                    <li class="user__menu-list-item">
                                        <a href="../html-personal-cart/order-history.jsp" class="turn-page">
                                            <div class="user__menu-list-item-box text-li">
                                                Lịch sử giao dịch
                                            </div>
                                        </a>
                                    </li>
                                    <li class="user__menu-list-item">
                                        <a href="../index.jsp" class="turn-page">
                                            <div class="user__menu-list-item-box sign-out text-li">
                                                Đăng xuất
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
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
    <footer class="web__footer">
        <div class="grid">
            <div class="grid__row-1">
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">Cơ sở</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-big">Về chúng tôi</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Chăm sóc khách hàng</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Báo chí</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Blog</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">LÀM VIỆC VỚI CHÚNG TÔI</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Chương trình Affiliate</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Quan hệ hợp tác</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">DẠY HỌC CÙNG CHÚNG TÔI</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Trở thành giảng viên</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Trung tâm hỗ trợ giảng viên</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Quy định dành cho giảng viên</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">MUA HÀNG</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Quà tặng học viên</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Sản phẩm kỹ thuật số</a>
                        </li>
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-medium">Buổi học trực tiếp</a>
                        </li>
                    </ul>
                </div>
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">DI ĐỘNG</h3>
                    <div class="footer__download">
                        <div class="footer__download-apps">
                            <a href="" class="footer__download-app-link">
                                <img src="assets/image/appstore.png" alt="App Store"
                                     class="footer__download-app-img">
                            </a>
                            <a href="" class="footer__download-app-link">
                                <img src="assets/image/ggplay.png" alt="Google Play"
                                     class="footer__download-app-img">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer__bottom">
            <div class="grid">
                <ul class="ul__text text-medium">© SOFTSKILL, Inc. 2025
                    <li class="footer__text text-medium">Hỗ trợ</li>
                    <li class="footer__text text-medium">Quyền riêng tư</li>
                    <li class="footer__text text-medium">Điều khoản</li>
                    <li class="footer__text text-medium">Lựa chọn quyền riêng tư của bạn</li>
                </ul>
            </div>
            <div class="grid">
                <p class="ul__text text-medium">Chịu trách nhiệm quản lý nội dung: Nhóm 20</p>
            </div>
            <div class="grid">
                <p class="ul__text text-medium">© 2025 - Bản quyền thuộc về Nhóm 21</p>
            </div>
        </div>
    </footer>
</div>
</body>
</html>