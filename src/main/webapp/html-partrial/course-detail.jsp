<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course detail</title>
    <base href="${pageContext.request.contextPath}/">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/course-detail.css?v=1.0.5">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/modal-notification.css">

</head>
<body>
<div class="web">

    <jsp:include page="/header-footer/header.jsp"/>
    <div class="dark-bg"></div>

    <div class="web__container">
        <div class="course-detail__container grid">

            <div class="grid__row-2">
                <div class="container-2 grid__column-4-in-12">
                    <div class="container-2__information">
                        <div class="container-2__img">
                            <img src="${c.thumbnailUrl}" alt="${c.title}" class="">
                        </div>

                        <div class="container-2__price">
                            <div class="container-2__price">
                                <span class="container-2__sold-price">${c.price - c.discountPrice}đ</span>
                                <span class="container-2__original-price">${c.price}đ</span>
                            </div>
                        </div>

                        <div class="container-2__option-group">
                            <div class="container-2__option">

                                    <div class="header__button add__button">
                                        <button type="button" class="container-2__button-add button__btn"
                                                onclick="addToCart(${c.id})">
                                            Thêm vào giỏ hàng
                                        </button>
                                    </div>

                                <a href="#popup__add-to-wishlist-success" class="turn-page">
                                    <div class="header__button bookmark__button">
                                        <button class="dark-button">
                                            <i class="fa-solid fa-heart container-2__icon"></i>
                                        </button>
                                    </div>
                                </a>
                            </div>
                            <a href="buy-now?id=${c.id}">
                                <button class="container-2__button-buy dark-button">
                                    Mua ngay
                                </button>
                            </a>
                        </div>

                        <div class="container-2__note">
                            <span>Đảm bảo hoàn tiền trong 30 ngày</span>
                        </div>

                        <div class="container-2__summary-information">
                            <div class="container-2__subtitle text-big">
                                Khóa học này gồm có:
                            </div>
                            <ul>
                                <li class="text-li">
                                    <span class="text-li style__text">
                                        Thời lượng:
                                    </span>
                                    <span class="text-li style__text style__text-var">
                                         ${c.durationHours}h
                                    </span>
                                </li>
                                <li class="text-li">
                                    <span class="text-li style__text">
                                         Giáo trình:
                                    </span>
                                    <span class="text-li style__text style__text-var">
                                         ${c.lessonCount} bài giảng
                                    </span>
                                </li>
                                <li class="text-li">
                                    Sở hữa khóa học trọn đời
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="container-1 grid__column-8">
                    <div class="section-1 section__space">
                        <div class="section-1__breadcrumb">

                            <div class="">
                                <a href="home" class="section-1__breadcrumb-name section-1__breadcrumb-item">
                                    Softskill
                                </a>
                            </div>

                            <div class="section-1__breadcrumb-item text-li">
                                <i class="fa-duotone fa-solid fa-angle-right"></i>
                            </div>

                            <div class="">
                                <a href="result-search" class="section-1__breadcrumb-name section-1__breadcrumb-item">
                                    ${category.name}
                                </a>
                            </div>

                            <div class="section-1__breadcrumb-item text-li">
                                <i class="fa-duotone fa-solid fa-angle-right "></i>
                            </div>

                            <div class="section-1__breadcrumb-item section-1__breadcrumb-name">
                                ${category2.name}
                            </div>
                        </div>

                        <div class="section-1__main-title text-big-title">${c.title}</div>

                        <div class="section-1__sub-title text-big">${c.subtitle}.
                        </div>

                        <div class="section-1__rating">
                            <div class="section-1__rating-item section-1__best-seller text-mini">Best seller</div>
                            <!--                            <div class="section-1__rating-item section-1__high-rated text-mini">High Rated</div>-->
                            <div class="section-1__rating-item section-1__rating-star">
                                <div class="section-1__number section-1__rating-star-item text-mini">
                                    5
                                </div>
                                <div class="section-1__star section-1__rating-star-item text-mini">
                                    <i class="fa-duotone fa-solid fa-star"></i>
                                </div>
                                <div class="section-1__star section-1__rating-star-item text-mini">
                                    <i class="fa-duotone fa-solid fa-star"></i>
                                </div>
                                <div class="section-1__star section-1__rating-star-item text-mini">
                                    <i class="fa-duotone fa-solid fa-star"></i>
                                </div>
                                <div class="section-1__star section-1__rating-star-item text-mini">
                                    <i class="fa-duotone fa-solid fa-star"></i>
                                </div>
                                <div class="section-1__star section-1__rating-star-item text-mini">
                                    <i class="fa-duotone fa-solid fa-star"></i>
                                </div>
                            </div>
                            <div class="section-1__rating-item section-1__rating-quantity text-mini">(${c.studentCount} rating)
                            </div>
                        </div>

                        <div class="section-1__creator">
                            <span class="section-1__creator-title text-big font__sub-title">Giảng viên</span>
                            <a href="instructor-profile.jsp"
                               class="section-1__creator-name text-big turn-page">${c.authorName}</a>
                        </div>

                        <div class="section-1__updated">
                        <span class="section-1__updated-item text-big"><i
                                class="fa-duotone fa-solid fa-calendar-days"></i></span>
                            <span class="section-1__updated-title section-1__updated-item text-big font__sub-title">Cập nhật lần cuối: </span>
                            <span class="section-1__updated-date section-1__updated-item text-big font__sub-title">${c.updatedAt}</span>
                        </div>
                    </div>

                    <div class="section-2__skills section__space ">
                        <div class="section-2__skills-title style__sub-title">Bạn sẽ học được</div>
                        <div class="section-2__list-container">
                            <div class="section-2__list">
                                <ul>
                                    <li class="text-li style__text">
                                        <div class="li-skill__container">
                                            <div class="li-skill__container-item icon__skill">
                                                <i class="fa-duotone fa-solid fa-check"></i>
                                            </div>
                                            <div class="li-skill__container-item content__skill">${c.goals}</div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="section-6__description section__space">
                        <div class="section-6__title style__sub-title">Giới thiệu khóa học</div>
                        <div class="section-6__content text-big font__sub-title">
                            ${c.description}
                        </div>
                        <br>
                        <div class="section-6__content text-big font__sub-title">

                        </div>
                        <br>
                        <div class="section-6__content text-big font__sub-title">
                            Nhanh tay đăng ký khoá học để nhận ưu đãi từ Softskill nhé!
                        </div>
                    </div>

                    <div class="section-3 section__space">
                        <div class="grid">
                            <div class="section-3__content">
                                <div class="section-3__title style__sub-title">Tags</div>
                                <div class="section-3__list-skill">
                                    <ul class="section-3_ul">
                                        <%-- fn:split dùng để tách chuỗi thành mảng--%>
                                            <c:forEach var="tag" items="${tags}" varStatus="loop">
                                                <c:if test="${loop.index < 3}">
                                                    <li class="section-3_li">${tag.name}</li>
                                                </c:if>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="section-4__courses section__space">
                        <div class="section-4__title style__sub-title">
                            Nội dung bài học
                        </div>
                        <div class="section-4__sub-duration">
                            <ul class="">
                                <li class=" text-big first font__sub-title">
                                    ${c.lessonCount} bài giảng
                                </li>
                                <li class="text-big font__sub-title">
                                    ${c.durationHours}h
                                </li>
                            </ul>
                        </div>

                        <div class="section-4__list-video">
                            <ul>
                                <c:forEach var="lesson" items="${lessons}">
                                    <li>
                                        <div class="section-4__lesson-information">
                                            <div class="section-4__lesson-icon text-medium"><i class="fa-solid fa-play"></i></div>
                                            <div class="section-4__lesson-title text-medium">Bài ${lesson.orderIndex} : ${lesson.title}</div>
                                            <div class="section-4__lesson-time text-medium">${lesson.durationMinutes} phút</div>
                                        </div>
                                    </li>
                                </c:forEach> </ul>
                        </div>
                    </div>


                    <div class="section-7__review section__space">
                        <div class="review-box__header style__sub-title">
                            <span class="">Đánh giá</span>
                        </div>

                        <c:forEach var="review" items="${reviewDtos}">
                            <div class="review-box__comment">
                                <div class="comment__user header__user">
                                    <img src="${review.thumbnailUrl}" alt="" class="user__avatar1">
                                </div>
                                <div class="comment__box">
                                    <div class="box__name box">
                                        <div class="review-in4">
                                            <span class="review__name">${review.userName}</span>
                                            <span class="review__time">${review.createdAt}</span>
                                        </div>
                                    </div>
                                    <div class="box__date box">
                                        <div class="star">
                                            <div class="text-medium regular">${review.rating}</div>
                                            <div class="star-icon">
                                                <i class="fa-solid fa-star" style="color:#FFD43B; font-size:1rem"></i>
                                                <i class="fa-solid fa-star" style="color:#FFD43B; font-size:1rem"></i>
                                                <i class="fa-solid fa-star" style="color:#FFD43B; font-size:1rem"></i>
                                                <i class="fa-solid fa-star" style="color:#FFD43B; font-size:1rem"></i>
                                                <i class="fa-solid fa-star" style="color:#FFD43B; font-size:1rem"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="box__comment box">
                                        <span>${review.comment}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    <%--                        <div class="review-box__comment">--%>
<%--                            <div class="comment__user header__user">--%>
<%--                                <img src="assets/image/65472207_145188949876444_2344275901291692032_n.jpg" alt=""--%>
<%--                                     class="user__avatar1">--%>
<%--                            </div>--%>
<%--                            <div class="comment__box">--%>
<%--                                <div class="box__name box">--%>
<%--                                    <div class="review-in4">--%>
<%--                                        <span class="review__name">Hoang Danh Tai</span>--%>
<%--                                        <span class="review__time">5 tháng trước</span>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="box__date box">--%>
<%--                                    <div class="star">--%>
<%--                                        <div class="text-medium regular">4.6</div>--%>
<%--                                        <div class="star-icon"><i class="fa-solid fa-star"--%>
<%--                                                                  style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                        </div>--%>

<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="box__comment box">--%>
<%--                                    <span class="">Bài học bổ ích quá, e cảm ơn Thầy</span>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="review-box__comment">--%>
<%--                            <div class="comment__user header__user">--%>
<%--                                <img src="assets/image/65472207_145188949876444_2344275901291692032_n.jpg" alt=""--%>
<%--                                     class="user__avatar1">--%>
<%--                            </div>--%>
<%--                            <div class="comment__box">--%>
<%--                                <div class="box__name box">--%>
<%--                                    <div class="review-in4">--%>
<%--                                        <span class="review__name">Hoang Danh Tai</span>--%>
<%--                                        <span class="review__time">5 tháng trước</span>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="box__date box">--%>
<%--                                    <div class="star">--%>
<%--                                        <div class="text-medium regular">4.6</div>--%>
<%--                                        <div class="star-icon"><i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                            <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>--%>
<%--                                        </div>--%>

<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="box__comment box">--%>
<%--                                    <span class="">Tôi cảm thấy hứng thú và động viên hơn để tiếp tục học hỏi sau khi hoàn thành khoá học này.</span>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

                    </div>
                </div>
            </div>
        </div>

        <div id="popup__add-to-wishlist-success" class="modal-backdrop">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="header-content modal-title">THÔNG BÁO</div>
                </div>
                <div class="course-body">
                    <div class="body-title">THAO TÁC HOÀN TẤT</div>
                    <div class="body-icon"><i class="fa-solid fa-circle-check check-popup"></i></div>
                    <div class="body-content">Bạn có thể kiểm tra trong danh sách yêu thích của bạn</div>
                    <div class="body-selection">
                        <div class="body-selection__item x__icon">
                            <a href="#" class="">
                                <button class="dark-button dark-button-2">Tiếp tục</button>
                            </a>
                        </div>
                        <div class="body-selection__item x__icon">
                            <a href="../html-personal/my-wishlist.jsp" class="">
                                <button class="button__btn">Tới danh sách</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="popup__add-cart-success" class="modal-backdrop">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="header-content modal-title">THÔNG BÁO</div>
                </div>
                <div class="course-body">
                    <div class="body-title">THAO TÁC HOÀN TẤT</div>
                    <div class="body-icon"><i class="fa-solid fa-circle-check check-popup"></i></div>
                    <div class="body-content">Bạn có thể kiểm tra trong giỏ hàng của bạn</div>
                    <div class="body-selection">
                        <div class="body-selection__item x__icon">
                            <a href="#" class="">
                                <button class="dark-button">Tiếp tục</button>
                            </a>
                        </div>
                        <div class="body-selection__item x__icon">
                            <a href="../html-personal-cart/cart.jsp" class="">
                                <button class="button__btn">Tới giỏ hàng</button>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>

    <jsp:include page="/header-footer/footer.jsp"/>

</div>

</body>
<script>
    function addToCart(courseId) {

        fetch('add-cart?id=' + courseId)
            .then(response => {
                if (response.ok) {
                    return response.text();
                }
                throw new Error('Network response was not ok.');
            })
            .then(newCount => {
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
</script>
</html>