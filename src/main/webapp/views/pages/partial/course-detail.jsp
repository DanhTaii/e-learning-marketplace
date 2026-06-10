<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course detail</title>
    <base href="${pageContext.request.contextPath}/">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/default.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/base/base.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/css/course/course-detail.css?v=${applicationScope.assetVersion}">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/base/modal-notification.css">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>
<div class="web">

    <jsp:include page="/views/layouts/header.jsp"/>
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
                                <span class="container-2__sold-price"><c:out value="${c.discountedPrice}"/></span>
                                <span class="container-2__original-price"><c:out value="${c.originPrice}"/></span>
                            </div>
                        </div>
                        <div class="container-2__option-group mb-3">
                            <c:choose>

                                <c:when test="${c.enrolled}">
                                    <a href="personal/my-course/detail?courseId=${c.id}" class="turn-page">
                                        <div class="header__button add__button">
                                            <button type="button" class="container-2__button-add button__btn">
                                                Vào học ngay
                                            </button>
                                        </div>
                                    </a>
                                </c:when>

                                <c:otherwise>
                                    <div class="container-2__option">
                                        <div class="header__button add__button">
                                            <button type="button" class="container-2__button-add button__btn"
                                                    onclick="addToCart(event,${c.id})">
                                                Thêm vào giỏ hàng
                                            </button>
                                        </div>

                                        <button type="button"
                                                class="wishlist-btn ${c.inWishlist ? 'active' : ''}"
                                                onclick="addToWishlist(event, this, ${c.id})"
                                                title="Thêm vào danh sách yêu thích">
                                            <i class="fa-solid fa-heart"></i>
                                        </button>
                                    </div>

                                    <a href="buy-now?id=${c.id}">
                                        <button class="container-2__button-buy dark-button">
                                            Mua ngay
                                        </button>
                                    </a>
                                    <div class="container-2__note">
                                        <span>Đảm bảo hoàn tiền trong 30 ngày</span>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="container-2__summary-information">
                            <div class="container-2__subtitle text-big">
                                Khóa học này gồm có:
                            </div>
                            <ul>
                                <li class="text-li style__text-2">
                                    <span class="text-li style__text ">
                                        Thời lượng:
                                     </span>
                                    <span class="text-li style__text style__text-var">
                                        <c:out value="${c.durationText}"/>
                                    </span>
                                </li>
                                <li class="text-li style__text-2">
                                    <span class="text-li style__text ">
                                         Giáo trình:
                                    </span>
                                    <span class="text-li style__text style__text-var">
                                         <c:out value="${c.lessonCount}"/> bài giảng
                                    </span>
                                </li>
                                <li class="text-li style__text-2">
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
                                <a href="index" class="section-1__breadcrumb-name section-1__breadcrumb-item">
                                    Softskill
                                </a>
                            </div>

                            <c:if test="${not empty category}">
                                <div class="section-1__breadcrumb-item text-li">
                                    <i class="fa-duotone fa-solid fa-angle-right"></i>
                                </div>

                                <div class="">
                                    <a href="result-search/by-category?id=${category.id}"
                                       class="section-1__breadcrumb-name section-1__breadcrumb-item">
                                            <c:out value="${category.name}"/>
                                    </a>
                                </div>
                            </c:if>
                        </div>

                        <div class="section-1__main-title text-big-title"><c:out value="${c.title}"/></div>

                        <div class="section-1__sub-title text-big"><c:out value="${c.subtitle}"/>.
                        </div>

                        <div class="section-1__rating">
                            <%--                            <div class="section-1__rating-item section-1__best-seller text-mini">Bán chạy</div>--%>
                            <!--                            <div class="section-1__rating-item section-1__high-rated text-mini">High Rated</div>-->
                            <div class="section-1__rating-item section-1__rating-star">
                                <div class="section-1__number section-1__rating-star-item text-mini">
                                    <fmt:formatNumber value="${c.avgRating}" type="number"
                                                      maxFractionDigits="1"
                                                      minFractionDigits="1"
                                                      var="formattedRating"/>
                                    <div class="text-medium rate__number"><c:out value="${fn:replace(formattedRating, ',', '.')}"/></div>
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
                            <div class="section-1__rating-item section-1__rating-quantity text-mini">
                                (<c:out value="${fn:length(reviewDtos)}"/>
                                rating)
                            </div>
                        </div>

                        <div class="section-1__creator">
                            <span class="section-1__creator-title text-big font__sub-title">Giảng viên</span>
                            <a href="instructor-profile.jsp"
                               class="section-1__creator-name text-big turn-page"><c:out value="${c.authorName}"/></a>
                        </div>

                        <div class="section-1__updated">
                        <span class="section-1__updated-item text-big"><i
                                class="fa-duotone fa-solid fa-calendar-days"></i></span>
                            <span class="section-1__updated-title section-1__updated-item text-big font__sub-title">Cập nhật lần cuối: </span>
                            <span class="section-1__updated-date section-1__updated-item text-big font__sub-title"> <fmt:formatDate
                                    value="${c.updatedAt}" pattern="yyyy-MM-dd "/></span>
                        </div>


                    </div>

                    <div class="section-2__skills section__space ">
                        <div class="section-2__skills-title style__sub-title">Bạn sẽ học được</div>
                        <div class="section-2__list-container">
                            <div class="section-2__list">
                                <ul>
                                    <li class="text-li style__text">
                                        <div class="goals-wrapper">
                                            <c:forEach var="goal" items="${fn:split(c.goals, ';')}">
                                                <div class="li-skill__container">
                                                    <div class="li-skill__container-item icon__skill">
                                                        <i class="fa-duotone fa-solid fa-check"></i>
                                                    </div>
                                                    <div class="li-skill__container-item content__skill">
                                                            <c:out value="${fn:trim(goal)}"/>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="section-6__description section__space">
                        <div class="section-6__title style__sub-title">Giới thiệu khóa học</div>
                        <c:forEach var="des" items="${fn:split(c.description, ';')}">
                            <div class="section-6__content text-big font__sub-title">
                                    <c:out value="${fn:trim(des)}"/>
                            </div>
                        </c:forEach>
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
                                <div class="section-3__title style__sub-title">Loại</div>
                                <div class="section-3__list-skill">
                                    <ul class="section-3_ul">
                                        <%-- fn:split dùng để tách chuỗi thành mảng--%>
                                        <c:forEach var="t" items="${tagsByCourse}">
                                            <a href="result-search/by-tag?id=${t.id}" class="turn-page">
                                                <li class="section-3_li"><c:out value="${t.name}"/></li>
                                            </a>
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
                                    <c:out value="${c.lessonCount}"/> bài giảng
                                </li>
                                <li class="text-big font__sub-title">
                                    <c:out value="${c.durationText}"/>
                                </li>
                            </ul>
                        </div>

                        <div class="section-4__list-video">
                            <ul>
                                <c:forEach var="lesson" items="${lessons}">
                                    <li>
                                        <div class="section-4__lesson-information">
                                            <div class="section-4__lesson-icon text-medium"><i
                                                    class="fa-solid fa-play"></i></div>
                                            <div class="section-4__lesson-title text-medium">Bài <c:out value="${lesson.orderIndex}"/>
                                                : <c:out value="${lesson.title}"/></div>
                                            <div class="section-4__lesson-time text-medium"><c:out value="${lesson.durationMinutes}"/>
                                                phút
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach></ul>
                        </div>
                    </div>


                    <div class="section-7__review section__space">
                        <div class="review-box__header style__sub-title">
                            <span>Bình luận</span>
                        </div>

                        <c:choose>
                            <c:when test="${not empty reviewDtos}">
                                <c:forEach var="review" items="${reviewDtos}">
                                    <div class="review-box__comment">
                                        <div class="comment__user header__user">
                                            <img src="${review.thumbnailUrl}" alt="" class="user__avatar1">
                                        </div>
                                        <div class="comment__box">
                                            <div class="box__name box">
                                                <div class="review-in4">
                                                    <span class="review__name"><c:out value="${review.userName}"/></span>
                                                    <span class="review__time"><fmt:formatDate
                                                            value="${review.createdAt}" pattern="yyyy-MM-dd "/></span>
                                                </div>
                                            </div>
                                            <div class="box__date box">
                                                <div class="star">
                                                    <div class="text-medium regular"><c:out value="${review.rating}"/></div>
                                                    <div class="star-icon">
                                                        <i class="fa-solid fa-star icon"></i>
                                                        <i class="fa-solid fa-star icon"></i>
                                                        <i class="fa-solid fa-star icon"></i>
                                                        <i class="fa-solid fa-star icon"></i>
                                                        <i class="fa-solid fa-star icon"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="box__comment box">
                                                <span><c:out value="${review.comment}"/></span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <!-- Empty state khi chưa có đánh giá -->
                                <div class="empty-state">
                                    <i class="fa-solid fa-comments empty-icon"></i>
                                    <div class="empty-title">Chưa có đánh giá nào</div>
                                </div>
                            </c:otherwise>
                        </c:choose>
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
                            <a href="../../personal/course/my-wishlist.jsp" class="">
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
                            <a href="../cart/cart.jsp" class="">
                                <button class="button__btn">Tới giỏ hàng</button>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>

    <jsp:include page="/views/layouts/footer.jsp"/>
    <jsp:include page="/views/components/toast.jsp"/>

</div>
<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/cart/add-action.js?v=${applicationScope.assetVersion}"></script>
</body>
</html>