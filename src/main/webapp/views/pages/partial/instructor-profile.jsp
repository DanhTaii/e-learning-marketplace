<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Instructor Profile</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/profile/profile.css">
    <link rel="stylesheet" href="assets/css/base/default.css">
    <link rel="stylesheet" href="assets/css/base/card.css">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>

<jsp:include page="/views/layouts/header.jsp"/>

<div class="user-profile__container">
    <div class="grid">
        <div class="grid__row-2">

            <div class="grid__column-3 overall">
                <div class="profile-block">
                    <div class="profile-block__avatar text-big-title">IST</div>
                    <div class="profile-block__title">Admin</div>
                    <div class="profile-block__sub-title">Giảng viên</div>
                    <div class="instructor__total-course">187 khóa học</div>
                    <div class="box__rate box">
                    <span class="star">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                    </span>
                        <span class="">4.8</span>
                    </div>
                </div>
            </div>

            <div class="grid__colum-9 course__list">
                <div class="information__container grid__row-2">
                    <div class="instructor_title style__title">
                        Tất cả khóa học
                    </div>
                    <div class="grid__row-2">
                        <!--                    Danh Tài-->
                        <!--                    Nôội dung tư duy và sáng tạo-->
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=3" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2023/07/Screenshot%20(45).png_1690356655.jpg"
                                             alt="Tư duy phản biện - Giải quyết tận gốc mọi vấn đề"
                                             class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">5</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Tư duy phản biện - Giải quyết tận gốc mọi vấn đề

                                        </p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">21k5</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">1h30m</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">399.000đ</div>
                                            <div class="price__old">700.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2019/04/hoc-bi-quyet-sang-tao-quang-cao-viral-content_1555571699.jpg"
                                             alt="Bí quyết sáng tạo quảng cáo viral và livestream bán hàng"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Bí quyết sáng tạo quảng cáo viral và livestream bán hàng</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Nguời mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">1.8k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">6h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">99.000đ</div>
                                            <div class="price__old">300.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2019/06/lam-chu-tu-duy-thay-doi-van-menh_1561370343.jpg"
                                             alt="Làm chủ tư duy - Thay đổi vận mệnh"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Làm chủ tư duy - Thay đổi vận mệnh
                                        </p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Nguời mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">4.5k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">6h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">299.000đ</div>
                                            <div class="price__old">900.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2019/06/dot-pha-tu-duy-thay-doi-cuoc-doi_1561538471.jpg"
                                             alt="Đột phá tư duy - thay đổi cuộc đời"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Đột phá tư duy - thay đổi cuộc đời</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Nguời mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">177</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">3h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">399.000đ</div>
                                            <div class="price__old">800.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2019/09/T%C6%B0-duy-CEO--%20Nguy%E1%BB%85n-V%C4%83n-%C4%90%E1%BB%A9c%201_1568366611.jpg"
                                             alt="Tư duy CEO"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Tư duy CEO
                                        </p></div>
                                        <br>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Nguời mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">1.86k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">2h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">499.000đ</div>
                                            <div class="price__old">900.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2024/06/t%C6%B0%20duy.jpg_m_1718174318.jpg"
                                             alt="Kỹ năng phát triển tư duy tích cực
"
                                             class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number"></div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Kỹ năng phát triển tư duy tích cực

                                        </p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Nguời mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">4</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">3h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">299.000đ</div>
                                            <div class="price__old">990.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=2" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2019/06/sang-tao-hieu-ung-flash-fx_1561427770.jpg"
                                             alt="Sáng tạo hiệu ứng Flash Fx"
                                             class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.9</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Sáng tạo hiệu ứng Flash Fx: Khói - Nước - Lửa - Điện</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">19</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">5h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">299.000đ</div>
                                            <div class="price__old">500.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=3" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2022/03/hoang-mang%20l%E1%BB%91i%20%C4%91i%20t%C6%B0%C6%A1ng%20lai_m_1648546986.jpg"
                                             alt="Lắng Nghe Chủ Động Và Hiểu Ý Người Nói Một Cách Sâu Sắc"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>"Ngưng bình thường" với kỹ năng tư duy sáng tạo</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">52</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">3h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">429.000đ</div>
                                            <div class="price__old">800.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=3" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2024/03/ai.png_1711357506.jpg"
                                             alt="Sáng tạo video AI kiếm tiền online
"
                                             class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.2</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Sáng tạo video AI kiếm tiền online
                                        </p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">34</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">57m</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">429.000đ</div>
                                            <div class="price__old">800.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=3" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2025/02/img_course_3620_m_1740018593.jpg"
                                             alt="Lắng Nghe Chủ Động Và Hiểu Ý Người Nói Một Cách Sâu Sắc"
                                             class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">5</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Content - Sáng Tạo Vô Hạn Nội Dung
                                        </p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">525</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">5h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">1.999.000đ</div>
                                            <div class="price__old">3.000.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=3" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.unica.vn/upload/images/2024/09/img_course_3439_m_1725853359.jpg"
                                             alt="Lắng Nghe Chủ Động Và Hiểu Ý Người Nói Một Cách Sâu Sắc"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Thiết kế và Sáng tạo hình ảnh bằng Canva và AI
                                        </p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">715</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">5h23m</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">599.000đ</div>
                                            <div class="price__old">999.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <!--                    Minh Lộc-->
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
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
                                        <div class="text-paragraph test-text"><p>Xây Dựng Thương Hiệu Cá Nhân Cho Bản Thân </p></div>
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
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
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
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
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
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
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
                                        <div class="text-paragraph test-text"><p>Kỹ Năng Từ Chối Mà Vẫn Được Yêu Quý</p></div>
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
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://tse1.mm.bing.net/th/id/OIP.-qNL8MTdeRuVGRqoTYXzTAHaEJ?cb=ucfimg2ucfimg=1&w=2000&h=1121&rs=1&pid=ImgDetMain&o=7&rm=3"
                                             alt="" class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.9</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Lãnh Đạo Không Cần Chức Danh</p></div>
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
                                            <div class="price__new">599.000đ</div>
                                            <div class="price__old">699.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>

                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://tse3.mm.bing.net/th/id/OIP.k5sDq201q3UKgMwrVdcyBQHaEK?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
                                             alt="Từ Chối Mà Vẫn Được Yêu Quý" class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên </div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.7</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Kỹ năng giải quyết vấn đề cho hiệu quả</p></div>
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
                                            <div class="price__new">399.000đ</div>
                                            <div class="price__old">599.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://static.ybox.vn/2022/11/3/1669791323216-Thi%E1%BA%BFt%20k%E1%BA%BF%20ch%C6%B0a%20c%C3%B3%20t%C3%AAn%20(1).png"
                                             alt="" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Networking Chuyên Nghiệp</p><br></div>
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
                                            <div class="price__new">399.000đ</div>
                                            <div class="price__old">699.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://blog.atrivity.com/hs-fs/hubfs/Blog/Sales%20Enablement/1200x627-11.jpg?width=1866&name=1200x627-11.jpg"
                                             alt="" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Kỹ năng quản lý dự án cá nhân</p><br></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới </div>
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
                                            <div class="price__new">399.000đ</div>
                                            <div class="price__old">599.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://cdn.brvn.vn/news/480px/2019/18006_Quyetdinh.jpg"
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
                                        <div class="text-paragraph test-text"><p>Ra Quyết Định Nhanh & Chuẩn</p> <br></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người bắt đầu</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">6k7</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text text-medium">12h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">899.000đ</div>
                                            <div class="price__old">599.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=1" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://jadiproduktif.com/wp-content/uploads/2024/05/3-1.jpg"
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
                                        <div class="text-paragraph test-text"><p>Lập Trình Tư Duy (Mindset Programming)</p></div>
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

                        <!--                    Ngoc Minh-->
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=2" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-4.0.3&auto=format&fit=crop&w=375&q=80 375w,
                 https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80 600w"
                                             alt="Lãnh Đạo Không Cần Chức Danh Trong Mọi Tổ Chức" class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.9</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Lãnh Đạo Không Cần Chức Danh Trong
                                            Mọi Tổ Chức</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">11.2k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">9h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">999.000đ</div>
                                            <div class="price__old">699.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=3" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://images.unsplash.com/photo-1521737711867-e3b97375f902?ixlib=rb-4.0.3&auto=format&fit=crop&w=375&q=80 375w,
                 https://images.unsplash.com/photo-1521737711867-e3b97375f902?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80 600w"
                                             alt="Quản Lý Đội Nhóm Hiệu Quả Và Tăng Năng Suất Làm Việc"
                                             class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Quản Lý Đội Nhóm Hiệu Quả Và Tăng
                                            Năng Suất Làm Việc</p></div>
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
                                                <div class="time__text text-medium">8h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">1.099.000đ</div>
                                            <div class="price__old">749.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=4" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://images.unsplash.com/photo-1600880292203-757bb62b4baf?ixlib=rb-4.0.3&auto=format&fit=crop&w=375&q=80 375w,
                 https://images.unsplash.com/photo-1600880292203-757bb62b4baf?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80 600w"
                                             alt="Xây Dựng Văn Hóa Đội Nhóm Gắn Kết Và Hiệu Quả Cao" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Xây Dựng Văn Hóa Đội Nhóm Gắn Kết
                                            Và Hiệu Quả Cao</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">9.8k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">7h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">899.000đ</div>
                                            <div class="price__old">599.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=5" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://images.unsplash.com/photo-1552664730-d307ca884978?ixlib=rb-4.0.3&auto=format&fit=crop&w=375&q=80 375w,
                 https://images.unsplash.com/photo-1552664730-d307ca884978?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80 600w"
                                             alt="Lãnh Đạo Thay Đổi Và Quản Lý Khủng Hoảng Hiệu Quả" class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.9</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Lãnh Đạo Thay Đổi Và Quản Lý Khủng
                                            Hoảng Hiệu Quả</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">8.3k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">10h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">1.199.000đ</div>
                                            <div class="price__old">849.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=10" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?ixlib=rb-4.0.3&auto=format&fit=crop&w=375&q=80 375w,
                 https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80 600w"
                                             alt="Chống Trì Hoãn Và Hoàn Thành Công Việc Đúng Hạn" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Chống Trì Hoãn Và Hoàn Thành Công
                                            Việc Đúng Hạn</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">18.7k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">4h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">599.000đ</div>
                                            <div class="price__old">399.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=12" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://images.unsplash.com/photo-1517180102446-f3ece451e9d8?ixlib=rb-4.0.3&auto=format&fit=crop&w=375&q=80 375w,
                 https://images.unsplash.com/photo-1517180102446-f3ece451e9d8?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80 600w"
                                             alt="Tối Ưu Hóa Lịch Làm Việc Với Công Cụ Hiện Đại" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Tối Ưu Hóa Lịch Làm Việc Với Công
                                            Cụ Hiện Đại</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">11.9k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">4h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">549.000đ</div>
                                            <div class="price__old">349.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=14" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="https://png.pngtree.com/png-vector/20230808/ourlarge/pngtree-person-reading-a-book-vector-png-image_6958204.png"
                                             alt="Phát Triển Bản Thân Qua Đọc Sách Hiệu Quả" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Phát Triển Bản Thân Qua Đọc Sách
                                            Hiệu Quả</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">9.8k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">6h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">450.000đ</div>
                                            <div class="price__old">300.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=15" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEBUSEBIWFhUWFRgXGBUWGRsaGxYdHh0YGBgbHxUaHSggGB8lHhgaITIhJSkrMC4uGCEzODMsNygtLi4BCgoKDg0OGxAQGy0lHyYxMDArLi81Ly0tLS0tLy8vLi0tLy4tLS0tLTAtLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAKMBNgMBEQACEQEDEQH/xAAcAAEAAgIDAQAAAAAAAAAAAAAABQYEBwECAwj/xABLEAACAQIEBAMFAwgGBQ0AAAABAhEAAwQSITEFBkFREyJhBzJxgZEUQlIjYnShorGzwTVDcoKS0SRT0uHxFRYXJTRUVWODhJOj0//EABoBAQADAQEBAAAAAAAAAAAAAAADBAUCAQb/xAA1EQACAgEDAgQDBwMFAQEAAAAAAQIDEQQSITFBBRNRYSJxgTKRobHB4fAUI/EGM0JS0UMk/9oADAMBAAIRAxEAPwDeNAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoCu3ea7Ad1bE4ZMjFYa7bmQWUgg3QQQV2IHvD1jltp9D3HGc/Q7JzThSATj8GDGxuW9PTS7FdHhyeaMJ/wCIYP8A+RP/ANaAmcFiRdtpcUgq6hgQZBBEiCNCPWgPagFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoD4557/pXHfpmI/iPQEGykaGgOKA+i+KcUazwnhKqGIfCWyQPzbVmDuJIzGNRVTVp7Vh4L2h273mOeC98mcSuYjCJcugydASACwgakDSZkGO1SUTlKGZdSHUwjCxqHQnanK4oBQCgFAY+Pxa2bT3XBKopY5RJgdhXsVuaSPJPCyzjh2MW9aS6gIV1DAMIPzFJR2tpnkZbllHOKxtu3OdwIVnidSqiWIXcx6USbPW0up14ZxBL9pbtokq0xIg6Eg6fEGvZRcXtfU8hNTW5dCsc9+0TD8Ke0l+1ec3QW/JhYVRodWYSZI0/dpPJ0WrAYxL1q3etNmt3EW4jQRKsAymDqJBGhoD3oBQCgFAKAUAoDgmgMTD8UsXGy271tm7KwM/CDrXCsi3hM7lXOKzJNIzK7OBQCgFAeZvLmyzr2Ok/Dv8AKgPGxcPQ512n7y+hHX9+2h3oDx4txizh0LXWjWAo1ZjAMAfAj0E613CEpvETic4wWZEfy9x+5i3cpaCWkgFmaWYnoFAgab6ncb13dT5WE3ycU3eblpcFgNQkxrj2hvi0xSNYvOqeGCqKxXzBiDoDDk6aEenWqOru8uS5x+xs+F+S4SU4J+rfXHsXfgWKa5h0NwzcCqtwwB58oLaDSJM6d6sae+F8FODyjKthsm0SFTEYoBQHx5zqwHF8aSJAxt8kf+q1D1dSQsYS1jk8OwAMR5QqkQTE6afdg/LrtVKdjo+Kf2S9thdD4ftFf4hwbEWL/wBnu2mW6SAEiS0mBlj3gTsRViu+uyHmRfBTlCUXhrk+gxg7l3h9jB3oVrVjDoBlDKrIoklt5IRh2htJiTkT8R8yqy2K+BNJer9X+Rcqq8uyKfXuSPDLjYe3aTxyCtuJzQDFy6o8jEjZQNqq6vX6mKrlT0cc4xkkhTXOUt3qTXD+NXCyBmV0ZgswJknKDK6bkaRUmh8XutuVVsVz9GR36WMYbosstfRFAUAoDhhI1oDWXOV7EZ0F60toZrgQIffWVAJg+u5jc6CtLSRr5a56Gbq5WcZ46nPJmKxSu4w6C5qgcO0BQCwJGsd9RO2xpq414WeOo0kp8456GBzxj3uXmJsFXQsozFpZAYt+Q6b5mJG/Sd65qU663KHxcdO+TuxwssUJ/Dz17YLl7PMebmDXxHBYEwsQVUeUdPMJBM67xOlZz1Fdk3t4a6r0ZfVUq1hvK7P27EB7QcCuPKBUDZcy6tlDI0ZjEaagQQZid6w9R4vUrMLou/q/TH6mjRp3tee5ZsPxxlAXw0gCAFJWANAI1qGH+oF/zr49n+36nL0PpInsLfDoriYInXcelfQV2KyCnHo+SjKO14Z612eCgFAYXFuIpYTO7KBMCTGY6nKo6sY2plLqDWeA59xhxKlyCjOAbQRQIJjRozT2k66aa1fnpoKL9ShHUTcl6M2zVAvnBE0BrCxgvs3FS9oO9kFoWcqW2iCu590jTQE5u1Yl2r09Fja6rsuv7mpHzLadrfX1/nBsnA4gXLYcCJnQ9CCQf1itai6N1asj0Zmzi4ScWe9SnIoBQHjfXMCuUdDLAFT6RM0BhlkClmB0B86tMAakeIIMDU+aKA1Hxvir4nENeJJEZUzR5VB0ECJPU+pqxLWQ0y2Q5l39DGvu8yRd/Zhh28K7dLyGcLk0gFQDm7yc0fL6Qf1E71ulgu6JfBn1LXxLHi0vdj7q9/8AIDqf5xVXV6qGmrc5/RepoVVSslhFJxVrxbuZ2OYMGkdconr0lwI209BHx1mssszObzn8M+ntwa8K1DiJm4Y3ETOguIuaJB0mcssASCZjVt/Wp6nraqvOhL4Ppj7n059iOXkzlskuSycExbXLZL7q2Wds2gMx31/VX0nh2pnqKFOa5M7UVque1EjV8hFAfHPPf9K479MxH8R6Aw+A8Zu4O+t+wQGXSCJDA7qR2P1qG+iF9brn0Z3XNwluRt/D+0jhty3bvXwVvJJFs2y7IxBU5HAjUE6yN9Yr5iXg+rhJwg8xffOM/NGitXU0nLqWKxzJYNlXa6PN4dxrWjPbz2yySE1AyuBB6k6mtSzwmxaPyaFl5Tfz79fkVVrIK7fY8ehm4Pj2HQo9y/bQZyAHhWIzNcCa+YkuAQP860dNpZ0UwjZHEksFad8LZNweV1MjE8SReJJh1yuWuq+ZGBCy7MVYdGGXUeorLeia8QV2ffH0wX4yb0z9i+VuGeKAUBwwB0NAVHnTB2cyFgAwR8oCAhiCsAmfL8Y/33NG5pvas9ClrNmFueOpHcsNdtu32a0rZsmeYEAEwdx3OusdjVjWxhxl46lbQynztWen3clmblmw167dug3DdjyvBCx+ExI+vpWerpJJLjBo+VFtt85PHF8v6/ksoX8BkBfQEA6eny20Hz+q8Ic7fMpltb6/z9DRq1W2O2Sz6EHx24MLeS2QXZ0knYbtoBr2NUdZ4ZGqMYQlz3fq+C9pHO7MjGxfHLdvAtcuLlb7RltSMwuHw1J1HujzMJPUddKuV6XfoPKk/wCJ9CGcZR1WEWfkvin2jCI+Qrl8pnZjAJKnqNf1VtU0+TVCGey/IzJzUpyx6snqkPBQETzJxC5Ysm5bRngHyopZp6EKASQACSACYGlD2KTfJrfAcdN2+t/E3QMwRUuwSjBWOeGmLZJKlivlIQAqPeNKylW2RlN/Z5x7l6VWyL8vn/wnG4PYt4hL1mM2rgjzLOmuXVR30jp6U8Q1OsioRoy+efl8+xU09FDblPgsNziot4V797OjKDmXONTpEFvKoOZdRG8b1YhN7N0lj2PNuZYRVuHc/WrFlMmFfK5YgtdUliIVukyCANqr12wrjiKeH9S09NKcnmS4K7d5ouiSyKrm5nEz7pBEZTBJ9RWRdoFO1yeec/i8lxThCPMlx7mzORuIG9hAWEFWZTpAJ0fQSdBmjXtW3o4bKYw9ODHd0bnvj0ZYKsngoBQHhiD6NG8r0+XX4QfhQFT58W6cG7Wri5RAvALDvbkAqW+7E5jtIUiNa6g0pcrJFdny3g1uuTrI+JP75irtT0M+ij9TF5ZcuWL1+1hgcFaDm47eIxYtBGiAKDCGIMneRrFZ+u3QnimK/T8zd8NhS68zeCXscPxMk4hTJOtxfOSNOkAA6kdgB8qwbfCLL7t9s+P509DSerrgsVR+8pV7jN/xG8FrPhgvll7ZYhmkTL6mI2EVzPw2lt4/PsuhZhPhbs/cTGF45eOGFtspuG6X8o0ygQFABbPsGLAga+hqSylR060sU+efxyeV1xlY7pSWF9/Qv/AsK1uyq3GVnPmZkBCknsCSYiBv0rV0unjp6lXHojLtsdknJkhVgjFAfHPPf9K479MxH8R6AgqAUBum5fFyxhQPu4XC29OuS2kzHZ2I+Vammhipe7MzUzzY/Ysfs/shsasiQBceCNo8s/U/U01j/t/U80a/ufQsHLHIP2bEtiL10XCGLW1UFcpJaS2pzaHT/hWLGlKW59TcnqJShsXQu9TFcUAoDhhI1oDV/OVy+biG9ZW1DXAmVpzqCu8HT46e9sIrT0cY87Xnp+pmauUuNyx1/Q55MxOIS5c+z2hczFM8tGVZbaTruddYjYzTWRjhbnjqNJKXO1ehtAVmGmKA15zrcnGRPugD9ljH7U1ieIP+79F+Z9B4ZHFPzb/Ir93h5ui1a8TOHuyFYHLaY5VQk6xIPYbDQmt3w2ymdCbisx7d289fmYHisL4alpSeJd+yWOny7ElynevWb7tZtm+oItt4bHJ5m0caQwhDBMCDqRNaGp2Tim+H1MvTb4SaXK6G0RWaaYoDF4hh2dYVo1nqJ9MykMvxB+tARGH4TYVnPh+csHLhPPmiJYqCzGNJJMjrOtBkkcHhltLneAx3gbSSYGpJMmNzMCgO9m14kuR5XC+X8W+rfEEaemu8ADK8JdNBpt6UBpfmXEm/jLt0AkC4dRqAq/klb0B017sO9ULG5SeDGvbnZJr+IzOE8fxa2kw2DEMCxlFzuxLEyZEAQQNumprqFksKMUSVXzSUII2LwBcQoAxCnM1tS5zZgHgBtfXsNAQY0NXFnHJrPGeCar08FAKAweL8PF+09skDMrLmjUAgjQgiD1+Vep4eTxrKwaWv4N7bFGHmUlWXaCNDHpU+o8OVq8yp9exhTjiTT6m2ORgn2G1kQroc0gglpIZtd5Ox7RVaMHBbX2Nmhf20e3N+O8HA37g3yZR6FyEB+RaflXFktsGyzTDfYommeF27RvWkvMUtFgHYfdXrr06CekzrFZdaUpLcbdrlGD2dTej4ci2qWQgAGUAjQLEACO2lbCRgdT2wtnIirMwImgPWgFAfHPPf9K479MxH8R6AgqA7W0LEAakkADuToK9SyOhujhnDWOSxZXMUQADqci52+JKqIHUxWy2qorPbCMZJ2SeO+WS3LPGfsl/xCmdWVl0MaMytodp02O/eub6nZFJP5HVFqrk3g2pwjjFnEqWsvMRmU6MhPRl6fHY9JrKnCUHiSNSFkZrMWZ9cHYoBQHDqCIIkdjQERxbgNq+rL4dsMUIVyitkJ6gHr6iD613Ce1pnE4bk0deEcLXCJlVFLZEUsqqniMM+8b7gSZNJzcnkQgorBJ4a9mmRBEaeh2169fpXB2exNAa34mv2jidyygJOZSxBGi/kgXB2MCDH6t6ybqJWah+nH3G3RqYVaVc884+Z78X4DisKRdw03IYRlOVhJGhE9dtDB6gV6tFOqxSg+Dl+IV3VuNiw/vX7Fx4G4eytzwRaLgFlgAz6x16a7R861ct9TGwl0JGgFAKAoPtI5kxOCvWThbIYsjHMyO/iMrLlsKEIhmzMRM/CRIAudvDsQGdvym89F7jLO3fWT30EAdzKJA1bWIHUydtY360BiYvEG1bIh5bMFaJAOgkwNBmM/WgK57POXnsi5dvMj5h4a5dQVBOY5tiGIXT0M1DVXtzkgq08apPa85LXgAnm8O3k11hcs+uwqbBMkl0Muh6KAUAoBQFa5o5UXEnxLZCXYgk+68bZo1BH4u2kHSLNGodXHYrX6ZW89yY4WGSwi3VCFFCkAgroAJB7fGDVeTyyxFYRCe0Vx/ybd1HvWx/9iVX1H+2yzpP95GrMDwu5etXr1oZlsBS4Gphs0kRvlyajsZ6VnxqlKLaNWd8YTUX3Nk+zLily7hjbuf1RAQk6lDtI7CIB7adDV7TWbo4z0M3WVKE8pcMuVWSoKAUB8c89/wBK479MxH8R6AgqAnuB8Ivq1jEtZfwWuHI50DlDrBPvAGAdO43r1WqtqUuxJXprNR8EOr/A3/7MeG3GdsU6+TKyqdNWzANAk7BYnTep7NVG6C2/iQS8PnpLpQsayvQnuPcmWb+Z7f5NzJOkox6ynSe4jeSDSrUzr46oit00J89GUfh2Fx+Ax62EZZuBGJXM9tkBOYEMB5xqQAQfOgkZhVXUW2TuyuhsaSnSx0Mt6+Nd++fRextO5fuKolROUEkAkBuvyrozzMVpEigOaAUAoBQHVUAiABAgeg7UBxetBlKtsd6Aw8Nwi0l03lXzlcpbSSOmsSY6dpNAZeIsh1KnY9vrQHa2gAAGwECgO1AKAUAoBQCgFAcAUBzQCgFAKAUAoBQHnft5hE6dY3jtPSgIXjuAu3LPgpaRkbR5jadCubYjRhvqI03rmcdywdwltkpeh15V4QcNYceGquzE6RrAGWSPWfqajoqdcNreSTU3K6e5LHsOX+A+C3ivAeCIXQQe4kgH0WBoNBoB1CqEG2lyzmy6c0lJ8LoWCpCIUAoDVvGPYlhMRiL2IfE3w16690gZIBdixAldtaAw/wDoCwX/AHrEfsf7NAWLAezcWkS02Mu3rVsFUt3rdpgoMAhXCi4g0HusNq5lFS6ktV9lTzXJr5Fw4Pw5MNYSzbEKggRPUknck7k7k16kksI4nOU5OUnyzMr05Md8IC4aTuDl6EjQEjuP5DsKA9TbBIbqAQPnQHNtAogbUB2oBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQHS9eVFLOwVQJLMYA+JO1AdMLikuoHtuGU7MpkHpvXiaayj1xcXhntXp4UTmvnPEYXEPbFpVtqFhriOfEkT5SCBvpAnUfKp64UtfHLD+hBOV274IZXqWflvipxWHW8UyElhlDZhoxAIaBMgA7aTGsTUdkNknEkrnvjuJSuDsUAoBQEPxjmK1YuCzBuXmXOLSRKpMG47MQtq2D95iJiBJ0rxvCywYn/ONsstb1mITzD/FcNo/s/Ws+XiukUtu/8ywtLa1nB7cL5osXna3myuglkYMrKPxFHAOT89cy7+bSrtVsLY7oPKIZRcXiRO1IclR4vz1bsYk2fDLKhCu4aIMAwFIht+rDWpYVxlxuSfZepFKclztbiur7I78J50W/ihZW0QjlglzNqYBMlI0Bg9T076ST0zjXvyRw1KlZswWuqxZFAKAUAmgOq3AZggwYMdD2oDtQCgFAKAUAoBQCgFAKAUB1uOACTsASflQGKDdZtRkWJEEMZ7MCNNI92euu0gcNfuLGY2pmMuYrB/tGZ7xHXc9QMjD3cyzEakEdiCQf1igPWgFAKAjOY0uNhnW0uZiAIgHSRmIU6EhZIHeK4sTcGkSVNKabKryfizhXOHZXh2Jgg5kaNTk6DLEx2B6k1n6S2UbHVJP2NLXUxlWrote/7F6NwZc0jLEz0jeZrTMkp3OeCa6bd0WpKyp3Zgragm2FkSVOs6fGqOurcoJxTz7Gj4daoTak0k/UneAYZ7eFtW1RbeVB5WJYjqSdBqTqfU1di20m+pnySUml0MzxHzZfETN2CMY+Pn0+denh7Ye4WXUQQSD8jH0O/wA6A9aAxOL49cPh7t9/dtW3uH4KpY/uoDWvL1t/AXEXwWv4nLibzKNSzqHtLBM5LdtkRV2BLHrp874xqXKf9PGSSxl57+xf0leFvxknFuqQSCNN/T4jp86+a2yWFg0k0Q3MHD3vW/FtNkv2ZuYZxoyuBsfzW90qdCG1EitHQapaW2KXd4l6Y9vdFe+rzIv8Cy8C5xsNgLWJunKHtK4RZYiZDKABsrq6ydIUTX2c7Iw+0zI2ycXJLhcv2Nf8Xx1m5dvNaS4viszedlaWJzbBRkAnu2/yNS23dbCxL7OOvdEen8RpjRbXLKT6fN/kT/LOBu4i+2Kw4tWzbuz4TFo1XXzBdAZbpp61qV6xWU7JIo0w8yfmx49jZwqE0BQCgFAVzi/GHzlLRyhdC0AknrE6ADbbf4a4mv8AEpVT8uvr3ZoabRqcd0/oRfAbww1wnUq+UNJ2iYaevvGZn/OpoPEHCzbPpLv7lrWUuyCa/wCP5Gdj+c7dnGrg3RmbJ4lxxAWypOVAx6ltTE6DvX0U7FDGe7wZNdUrE3HsslmF0ETIjXWdNN9akIyJ4RzVgsVdezhsVauXE95VaTpoSPxgd1kaigJmgFAKAUAoBQHS+WCsVEsAYHcxoJoD5rx3MPFsa3iX+InDaiLVtms5TmZCuRSGLAofeJNcuXOCxHTtw3NpH0hhbTeEq3SHbIA7AQGMQxCyYBM6TXRXPN7VxASjF4BhHgT284Ej4maAxblkG3nhvELxmEBi2bLGje7pETt660BI2FhQMuXT3e3cT1+NAelAKA1HxzA8Yv4vELYxOKypdIy2rlpFVWh7YEwfcZe9VpSuTe1Jotxjp3Fbm0zBTlfjk/8AaOIfPFW/9qud+o/6r+fU62ab/s/59C2+zzBYi19ou4gtccuqszkPd0RfvjcAFPL8Y13mq34+PqQXbOFXnBcDbtgZ8/knNl+7m6EfPp31ialITxt8StKzm9cS2c0AO6gwFEaE9yaHmSpc4ccyv5LyNaYQPBfMRHvZtYTVumpA9Koauy1YUGlk0tBCieXNZx9xZ+AC/wDZ0W5a8FlzK0lSdGKqwCErLKA0k9dquw3bVu6lGxxcm49OxLWbYUQPX5k6k/M10cHegIjm7h7YjAYqwnvXLF1F/tFSF/XFAUnlXFLdwli4N2sWgd9Gtotm4IOxD22+RHevkPHKpR1G/tL9DV0Uk4Y9CTu2FYgkAkbVlVXzqTUX1WH8i1OuMsZMfiGOVEf7zBSQi6kmJA02J/nXdFE5yXplcnTUmnhZK/wTBW04bYw9w3hcTD5DlCZVZme405pmDcjTTymvrNRqNNKSk2216FbT6XVRhKCSSfXJD8TS1afyMSWPukgldNCWAAA0jUb7VzVY7G3jC7GR4j4Kqa4+U3KXdJN/XjoW72V4tRfvWg4Ja2HidfI2WQOg/KAfSrunfUo6WqyttTi1800bMq0XRQCgOGaNTXgKHnzSx3Ylj8Tqf1mviL577ZS9Wz6KqO2CR1dCwIG5ED56CvKouVkYru0ezeItnpxrGWb+NOGuWsmUZRfPUx5c2wKye536aivsLq4WfDJc9mZmnVsIeZW8rq0TfB8EMFYuPfcR7zROUACBGmpPw10HSvdJppVR2Zy2yDW6qN09+MJGuOEXcJhca2Mw9glgt1VTS2IcqfNlDAxBUR0O3a/DR27mn09StfrdO6YSj9ro139my/cE5tF51Vwqq8ZHUyDO2p6Hoe+lU42/FtksGhdoNtfmVy3Lv7FpqUzxQCgFAKAqvO3MtzCZEsqudwSWYSFAgaCRJM/KPWobbNi4Kup1DqSx1ZqjmDDWsbdW/eX8sj5jkAVG1DQV6zE95Y661Crmnn1LFniGnekilnzF27f4/E29yVzAcXZJuR4iGGy6Ag6q0SYnUfFTViqe9EWnu8yOX17ljqQnMBrgKrlDEZw85W2zZu3rQGXZvBpidDBkEawD19CKA9KAUBTMdj7mHu4y5bUHNiLaEkSF/IWSDA3k+UepG+1VtRbKqDlFFrTVQtnGM3hHm/FMY6QXVJ/CsvrpAiIJ20M61hvxi2yXl1rLfHH+S69LRB7sPC9X+xMco2Si37bEsUvKpY6kkWMPJn13r6GtNLDMux5ef51Js2VnNlWfxQJ7b/CpDg0LzFihfxN2+ABndm23GXIs+uUIJ/NqjOWWzHts3ybLF7N+H2r2KuC9bVwqs4zdGzgDTrt17V3TGMnyi1oZyi5YfU25VsvCgFAKAo/FuVLlm89/BIHt3X8S7hswQi5pmu2XbyhmgZkeFYgHMpAIhvohfBwmuDuE3B5Rwlq8whLZDaSLtu8nxGZbbp/hZh6189L/AE/Ld8M+C8tescrkwE4Njb2KyvbCIAJua5cvoSAXaZABA21ABq3T4V5fwZyvUsQ8QjCDkuvoXHD8t4Vf6oN/bJb9kmP1VqQ0tMOkUZ89ZfP7Umat9oIA4lcCgAKqLAED3EbYfE1W1H2sH1Hgi/8Azp+rf6E57Jl/LYg/+Wgn4luvyqXSdyn/AKi/+f1/Q2bVw+aFAKAq/P8AexiYcNgsOcQQT4ltbnhtljcaecbyu+o3qDUVTsjtjLaSVzUXlrJqZPagtpjbxeCv2WG6zmYfFbgQisWzwWfWM19Vg0IeIR7xLfwnmfDXLtuHgxbu+E4KXCpAuKyhoDyIIjT1qvVpbNLdGdsXhPqufl+JJO6N0HGD59zYGL4NZu2hbZdBOVvvKTqTmOsk6mZnrNfTyipLkzKrp0y3Qf7/ADKrjuXMY6/Zs82gZVywygjaVnP/AHdR++vKnbVNNPhFvUT0l9UnKOJPsumfUq/HOO4fw0sW7cnDs83CoOeCYMAeYNGY71dhq6YuTnPr254M5+D6ycYuurhc5yuV2+82bxLg9rE2V0yEL5DEFZGxXt3X91UpwUlyWNPqJUSzHp3XZkbyhzAbpOGuhvFRA0kHVdPenrqNeoM15XJvh9SbV0wi99b4f4FpqQpCgFAY5xDEnIkgGCSYnuF01PTWB670BS/aXh/9E+13JXwW1ETlttC7Dclgh6xt61DdDcuCrqqnZHjqUM4RxDASGGy6x1BJ21Hy03qnjsSW+A6mFcXH4m+y7fUmOXLt7CP4qkA5WUq2qxMiQI2gGc3fvUkJ7Ohs6TwOMK07G1LvysFi4bzfiLt77M1pW8TRLikrlWQrNEMHgtMiOg9asRsb7HOo0ddeXGXQtt7D3vHtsjgWVVg9uIk/dII7dtPnU6xj3Mxp59jJwOqZvxkt9dR9BA+VeHRkUAoCucJJOJx4vKot+ImszI8JAZHTyhT/AHiOk1HjduUlwSSwlFrr+44Jh1F984PkbLbzeqK+vZsrx8AfWqOj8Oq09kpx+nsv5+BJbqJTik/8nflh7hv48OqiMXpBn+psx0/B4bfFmHTW/DOXn1I54xHHoTmIt5kZZjMCJ7SIrsjNAcSwrWXe1cEMhZT9GP0Mkj4DuKoNYeDFcHF4ZdPZMv8ApGIP5sftk1Np+5b0S5Zs+rJfFAKAUAoBQCKAUBpT2gH/AKzv/wBq3/CQfvrO1D/uM+z8HWNJB+7/AFM72fces4S7dF+QLoQBwJC5S85o11zDUA7a17prIxymR+NaO3UKMq1nbnPqbZsX1dQyMGUiQymQR6EaGtBHyLTTwz0oeCgFAYfFOHWr9spdtW7ggwtxFcT00YEUBE3eUMDevLdu4S2WtrbVC41UJMCJiBpHwNAWKgIXm/if2bB3LgMORkT+02gPy1b+7Uds9kGy1odP/UXxr7Pr8lyzSNhFDDODkLLmjcrMNE7/AHh8RWauGsn3M8uM/L+10Xs8fubDw/POIxN/wrFkKCCREu4gEk/hH0PbUkRYnqbJJ+VHL9z5jU+Ew01W+yfP8+peOGOtxBdCgOwAYxBOWdCdyAZj41chLck/UxHxwZtdHgoDEFoXGfOAQpyhTqNgZK7E6/SO5oD2w+HCAhdASSB0E7gekyfmaAjeZOGHF2Ww4bIrZGLxOzBgAsifd110kb9OJw3xcSSqeyaljJrriXBMbhPILRvIDCXEUtOhIBRSWER8NNzpVOdU49sm1R4jFxw3j5lXxN+4zlbrNInSCmQggHy/dInfcVA5STz0O5T81fE8oi+DWcXZUNirmIFxwCEvNdBVScoIDHrEzHSO9WNRZLO1cFDSUwxufJvDkJBd4faa6qsZuCWAJIDsoknfQR8qsUSbrTZU1MVG1pFoqYgFAKApmM4uuHxeIDIz5riMFGglbVmJPQSZ2JkDtVa/URpWZfQs06ey7iC6HbAcZLNcLqHW6wabehQhVUaMZnyqehB79MqrxlRk/Ojj8f4vcms0UlxHsZ3KN/O+Nf8AFikO0a/ZMIDp01mtuElJbl0ZTmsYT/nLLHXZwUH2pcDVrP2tdHTyv+ep8o+YJHy+AqC6KxkqaqtOO70KvyFxz7NiyrAlLrZDAlgSxCQJ7kzUVU1B4fcn8M0dl1dlkcYisv8AY3HauBlDDUESDVwkO9AKAUAoBQGNdxYBga7z33AAjrJMUB62LmYTp8taA0zzzbduI4gC1caWQCFJzeRIjvWbf/utdz7Dwu+uOjipP1z95mPyLiDhbd+zLs1tWay4CXFJEkCYB+Bg/Gu5aVtZRHX45CNkoWLhN4a9Pf8AYkvZ/jWwrfZ7iNmulrhXKwKhQA28KpBKDLoxNwDWKk00pcxa4RleLTrtsU4tPj+fz0NmVbMgUAoBQCgOCYoDW3tFxVzFXLFjDL4i6t5dcznQfEKskkdGqjbZG17IST9Te8Hsr06ndZ8l/PuLJZ5Ywl21as3bWbwkCq3mRmH3jKkaFiTHqateVFpJroZsdfqI2SshLDk8smuHcMs4dMli2qL1gan1J3Y+pruMVFYRXtustlum237mZXpGKAUB4XLJksjZSd5Eg+sSNekz9YFAdQ1xT5hnnYrAjuCGb5zJ39NQOFu3BJa3IOwUiV9GkwT1kd46SQOTduf6v4eYftdvlmoDR3NwjGYkEgtnu7bSSD/P9VZd6+Nm1pn/AGo/In/aqAMXbj/UWh9LlyptUviXyINE/ga9y8+z9Y4dY/vn63HP86s0LFaKepebZFiqUgFAKA1HzbhuJfbbz2rbFGbyRYe4IAVQZRgZIUbmqGprdjxKLaXozT0lqqWYzSb65/yQ4w/FCdbDH/2d4frL1V/pY9Nkv59C29Xu6zj937l89mWCxdoYgYtWAd7bpmRU1y+GwCjWAtu0Nfqda0qNyjhrGDJ1GzdmLyXipyuU/nTh+PxQaxZt2BYOWWdmzuRDaQIQSI1BJjpUVsZSWEXtFbp6pqdqba7YWPrkj+D8islywbwUraDscoALOxRhMElgIgH0J8ux8hTjGex3dr1LzPLjtU8cL0X/AKX9RAqYzjmgFAKAUB0uMQNBPoP99AeK2mCnbMx31IGsbHqB8NqA6X7DqD4W5j5agEgEx7ukaRFAV/Ecu3Xxa385VQFzJG5DA5/F8YsDl8sQRptsailTBzU8ck0dRZGt154ZbKlITAt4NvFzMQVBJHfUzBHzP+FOxoDPoBQCgFAKA4InegMS1wy0tzxFQBoO22vXLtO+vqa48uO7fjn1PdzxgyfCEzGvp67/AB2H0rs8O9AKAUAoBQCgFADQFA45wofarrAtGdWyBioYwrMC0mAxJmB1rH1ULlf8FSceMvj690adOr207HLD59T045bTEG24VrbAMrQTsMmUAggdW1I/lUmrVs4KVdWZdGnjj8cficaTUeS2nLj6k1yxiwqLh8hGUNDTmz6kkkwIYyTG2/pNnSznsUZwcXj2x74w3+JVve6TlnOSw1bIRQCgOIoBFAc0AoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKAUAoBQCgFAKA/9k="
                                             alt="Kỹ Năng Thuyết Trình Chuyên Nghiệp Trước Đám Đông" class="img-2">
                                    </div>
                                    <div class="small-advertisement__content">
                                        <div class="content__top">
                                            <div class="content__author-name text-medium">Quản trị viên</div>
                                            <div class="content__rate">
                                                <div class="rate__icon"><i
                                                        class="text-medium fa-regular fa-star"></i></div>
                                                <div class="text-medium rate__number">4.9</div>
                                            </div>
                                        </div>
                                        <div class="text-paragraph test-text"><p>Kỹ Năng Thuyết Trình Chuyên Nghiệp
                                            Trước Đám Đông</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">15.3k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">8h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">899.000đ</div>
                                            <div class="price__old">599.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=17" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img srcset="data:image/jpeg;base64,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"
                                             alt="Quản Lý Căng Thẳng Và Duy Trì Năng Lượng Làm Việc" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Quản Lý Căng Thẳng Và Duy Trì Năng
                                            Lượng Làm Việc</p></div>
                                        <div class="content__quick-info">
                                            <!-- ĐÃ SỬA TỪ divohol → div -->
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i
                                                        class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">13.7k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">5h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">599.000đ</div>
                                            <div class="price__old">399.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="grid__column-4">
                            <a href="course-detail.jsp?id=19" class="turn-page">
                                <div class="product__small-advertisement">
                                    <div class="small-advertisement__image">
                                        <img src="data:image/jpeg;base64,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"
                                             alt="Kỹ Năng Đặt Câu Hỏi Thông Minh Trong Giao Tiếp" class="img-2">
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
                                        <div class="text-paragraph test-text"><p>Kỹ Năng Đặt Câu Hỏi Thông Minh
                                            Trong Giao Tiếp</p></div>
                                        <div class="content__quick-info">
                                            <div class="quick-info__level">
                                                <div class="level__icon icon"><i class="text-medium fa-solid fa-signal"></i></div>
                                                <div class="level__text text-medium">Người mới</div>
                                            </div>
                                            <div class="quick-info__users">
                                                <div class="users__icon icon"><i
                                                        class="text-medium fa-solid fa-users"></i></div>
                                                <div class="users__text text-medium">8.7k</div>
                                            </div>
                                            <div class="quick-info__time">
                                                <div class="time__icon icon"><i
                                                        class="text-medium fa-regular fa-clock"></i></div>
                                                <div class="time__text text-medium">4h</div>
                                            </div>
                                        </div>
                                        <div class="content__price">
                                            <div class="price__new">499.000đ</div>
                                            <div class="price__old">299.000đ</div>
                                            <div class="quick-info__save"><i
                                                    class="quick-info__save__icon fa-solid fa-heart"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="grid">
        <div class="grid__row-2">
            <div class="grid__column-3"></div>
            <div class="grid__colum-9">
                <ul class="pagination home-product__pagination">
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">
                            <i class="pagination-item__icon fa-solid fa-angle-left"></i>
                        </a>
                    </li>
                    <li class="pagination-item pagination-item&#45;&#45;active">
                        <a href="" class="pagination-item__link">1</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">2</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">3</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">4</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">5</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">...</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">14</a>
                    </li>
                    <li class="pagination-item">
                        <a href="" class="pagination-item__link">
                            <i class="pagination-item__icon fa-solid fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/views/layouts/footer.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>

</body>
</html>