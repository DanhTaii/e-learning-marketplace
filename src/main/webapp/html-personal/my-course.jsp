<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My course</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/my-course.css">
    <link rel="stylesheet" href="assets/css/card.css">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
</head>
<body>

<div class="web">
    <header class="web__header">
        <div class="grid">
            <div class="header__box">
                <a href="../html-partrial/home.jsp" class="turn-page">
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
                                        <a href="../html-partrial/result-search.jsp" class="turn-page">
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
                                            <a href="../html-partrial/all-course.jsp" class="turn-page"></a>
                                            <div class="item-box">
                                                <a href="../html-partrial/all-course.jsp"
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
                                                <a href="../html-partrial/result-search.jsp"
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
                    <a href="../html-personal/my-course.html" class="turn-page text-header">
                        Khóa học của tôi
                    </a>
                </div>
                <div class="header__wishlist">
                    <a href="my-wishlist.jsp" class="turn-page text-header">
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
                                <a href="account-profile.jsp" class="turn-page">
                                    <div class="user__profile-btn">
                                        <button class="user-btn button__btn text-header">Xem thông tin</button>
                                    </div>
                                </a>
                            </div>
                            <div class="user__menu">
                                <ul class="user__menu-list">
                                    <li class="user__menu-list-item">
                                        <a href="../html-personal/my-course.html" class="turn-page">
                                            <div class="user__menu-list-item-box text-li">
                                                Khóa học
                                            </div>
                                        </a>
                                    </li>
                                    <li class="user__menu-list-item">
                                        <a href="my-wishlist.jsp" class="turn-page">
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

    <div class="my-course__container grid">

        <div class="my-course__title">Khóa học đã mua</div>

        <div class="my-course__selection">
            <ul>
                <li>
                    <div class="sidebar__name">
                        <a href="" class="button">Tất cả</a>
                        <div class="sidebar_stroke"></div>
                    </div>
                </li>
                <li><a href="" class="button">Đang</a></li>
                <li><a href="" class="button">Hoàn thành</a></li>
            </ul>
        </div>

        <div class="stroke"></div>

        <div class="search__container grid__row-2">
            <div class="my-course__search">
                <div class="search_blank"></div>
                <div class="my-course__input">
                    <input type="text" placeholder="Tìm khóa học của bạn">
                </div>

                <div class="my-course__button-search">
                    <button class="button"><i class="text-li fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
        </div>

        <div class="grid__row-2">
            <c:forEach var="e" items="${listEnrollments}">
                <div class="grid__column-3">
                    <a href="html-personal/course-content.jsp" class="turn-page">
                        <div class="product__small-advertisement">
                            <div class="small-advertisement__image">
                                <img srcset="${e.thumbnailUrl}"
                                     alt="Tư duy phản biện - Giải quyết tận gốc mọi vấn đề" class="img-2">
                            </div>
                            <div class="small-advertisement__content">
                                <div class="content__top">
                                    <div class="content__author-name text-medium">Quản trị viên</div>
                                    <div class="content__rate">
                                        <div class="rate__icon"><i class="text-medium fa-regular fa-star"></i></div>
                                        <div class="text-medium rate__number">${e.rating}</div>
                                    </div>
                                </div>
                                <div class="text-paragraph test-text"><p>${e.title}
                                </p></div>
                                <div class="progress-display">
                                    <div class="progress-display__crossbar"></div>
                                    <div class="progress-display__percentage" style="width: ${e.percentCompleted}%"></div>
                                </div>
                                <div class="progress__text">Đã hoàn thành ${e.percentCompleted}%</div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>

    </div>

    <footer class="web__footer">
        <div class="grid">
            <div class="grid__row-1">
                <div class="grid__column-2-4">
                    <h3 class="footer__heading text-big">Cơ sở</h3>
                    <ul class="footer-list">
                        <li class="footer-item">
                            <a href="" class="footer-item__link text-big">Về chúng tôi
                            </a>
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
                                <img src="../assets/image/appstore.png" alt="App Store"
                                     class="footer__download-app-img">
                            </a>
                            <a href="" class="footer__download-app-link">
                                <img src="../assets/image/ggplay.png" alt="Google Play"
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