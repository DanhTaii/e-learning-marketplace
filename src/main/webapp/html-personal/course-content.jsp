<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course Content</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/course-content.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <%--    <link rel="stylesheet" href="assets/css/fonts.css">--%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="assets/javascript/form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid layout">
            <div class="grid__column-8 column1">
                <div class="image-container">
                    <div class="image-container" style="position: relative; min-height: 500px;">
                        <iframe id="mainVideoPlayer" width="100%" height="500"
                                src=""
                                title="YouTube video player" frameborder="0"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                referrerpolicy="strict-origin-when-cross-origin"
                                allowfullscreen
                                style="display: none;"></iframe>
                        <div id="videoPlaceholder" class="placeholder-video"
                             style="display: none; width: 100%; height: 500px; background: #000; color: #fff; flex-direction: column; justify-content: center; align-items: center;">
                            <img src="assets/image/video-not-found.png" alt="No video available"
                                 style="width: 100px; margin-bottom: 15px;">
                            <p class="text-xl">Bài học này hiện đang được cập nhật video...</p>
                        </div>
                    </div>
                </div>

                <div class="content-container">
                    <div class="container__title-video">
                        <div class="title-video">
                            <span id="mainLessonTitle"
                                  class="text-3xl regular">${enrollmentDetail.listLesson[0].lessonTitle}</span>
                        </div>
                    </div>
                    <div class="container__sub-header">
                        <div class="sub-header__rating-star sub__header">
                            <div class="star1">
                                <div class="star1__number">
                                    <span class="text-xl"><fmt:formatNumber value="${enrollmentDetail.rating}"
                                                                            pattern="#.#"/></span></div>
                                <div class="star1__star-icon"><i class="fa-solid fa-star icon-star"
                                                                 style="color: #FFD43B; font-size: var(--text-sm)"></i>
                                </div>
                            </div>
                            <div class="number-rating">
                                <span class="text-sm light">${enrollmentDetail.reviewCount} đánh giá</span></div>
                        </div>

                        <div class="sub-header__student-count sub__header">
                            <div class="count"><span class="text-xl">${enrollmentDetail.studentCount}</span></div>

                            <div class="student"><span class="text-sm light">Học viên</span></div>

                        </div>
                        <div class="sub-header__duration sub__header">
                            <div class="time"><span class="text-xl">${enrollmentDetail.durationText}</span></div>

                            <div class="total"><span class="text-sm light">Tổng cộng</span></div>

                        </div>
                    </div>

                    <div class="section-7__review section__space">
                        <!-- Header -->
                        <div class="review-box__header style__sub-title">
                            <span>${enrollmentDetail.reviewCount} đánh giá</span>
                        </div>

                        <!-- Form nhập đánh giá -->
                        <div class="comment-input-box">
                            <div class="comment__user2 header__user">
                                <img src="${sessionScope.userSession.avatarUrl}" alt=""
                                     class="user__avatar2">
                            </div>
                            <form action="my-course/review/create" method="post" id="myForm">
                                <div><span id="error_comment" class="error-client"
                                           style="color: red;font-size: 1.5rem;padding-left: 1rem"></span></div>
                                <div><span id="error_rating" class="error-client"
                                           style="color: red;font-size: 1.5rem;padding-left: 1rem"></span></div>
                                <div class="box__input">
                                    <input type="hidden" name="courseId" value="${enrollmentDetail.courseId}">
                                    <input type="text" name="comment" class="input-style"
                                           placeholder="Viết bình luận..." id="user_comment">

                                    <input type="number" class="input__number" name="rating" id="ratingInput" min="0"
                                           max="5" step="0.1" oninput="validateRating(this)">
                                    <div class="star">
                                        <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1.6rem"></i>
                                        <span id="ratingDisplay"
                                              style="font-weight: bold;font-size: 1.5rem;margin-left: 5px">0</span>
                                        <span style="font-size: 1.5rem">/5</span>
                                    </div>
                                    <button class="dark-button button__add" type="submit">Gửi</button>
                                </div>
                            </form>
                        </div>

                        <!-- Danh sách đánh giá hoặc empty state -->
                        <c:choose>
                            <c:when test="${not empty enrollmentDetail.listReviews}">
                                <c:forEach var="review" items="${enrollmentDetail.listReviews}">
                                    <div class="review-box__comment">
                                        <div class="comment__user header__user">
                                            <img src="${review.thumbnailUrl}" alt="" class="user__avatar1">
                                        </div>
                                        <div class="comment__box">
                                            <div class="box__name box">
                                                <div class="review-in4">
                                                    <span class="review__name">${review.userName}</span>
                                                    <span class="review__time"><fmt:formatDate
                                                            value="${review.createdAt}" pattern="yyyy-MM-dd "/></span>
                                                </div>
                                            </div>
                                            <div class="box__date box">
                                                <div class="star">
                                                    <div class="text-medium regular">${review.rating}</div>
                                                    <div class="star-icon">
                                                        <i class="fa-solid fa-star"
                                                           style="color:#FFD43B; font-size:1rem"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="box__comment box">
                                                <span>${review.comment}</span>
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
                                    <div class="empty-description">
                                        Hãy là người đầu tiên để lại đánh giá cho khóa học này.
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </div>
            <div class="grid__column-4 column2">
                <div class="course-content">
                    <div class="content__header">
                        <span class="text-4xl bold">Danh sách bài học</span>
                    </div>
                    <div class="content__box">
                        <c:forEach var="l" items="${enrollmentDetail.listLesson}">
                            <div class="box__content lesson-item"
                                 style="background: var(--dark-blue); cursor: pointer;"
                                 data-video-url="${l.videoUrl}"
                                 data-title="Bài ${l.orderIndex}: ${l.lessonTitle}">

                                <div class="box__column1">
                                    <div class="column1__tick">
                                        <input type="checkbox" class="tick lesson-checkbox" name="tick"
                                               data-lesson-id="${l.id}"
                                            ${l.completed ? 'checked' : ''}>
                                    </div>
                                </div>
                                <div class="box__column2">
                                    <div class="column2__header">
                                        <span class="text-lg regular"
                                              style="color: var(--white-color)">Bài ${l.orderIndex} : ${l.lessonTitle}</span>
                                    </div>
                                    <div class="column2__duration">
                                        <span class="text-lg light"
                                              style="color: var(--white-color)">${l.durationMinutes}p</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <jsp:include page="/header-footer/footer.jsp"/>
</div>

<form action=""></form>

</body>
<script src="assets/javascript/enrollment.js?v=<%=System.currentTimeMillis()%>"></script>
<script>
    function validateRating(input) {
        if (input.value < 0) input.value = 0;
        if (input.value > 5) input.value = 5;
        let displayValue = input.value === '' ? 0 : input.value;
        document.getElementById('ratingDisplay').innerText = displayValue;
    }

    $(document).ready(function () {
        Validator.setupAutoClearErrors();

        $('#myForm').on('submit', function (e) {
            let comment = $('#user_comment').val().trim();
            let rating = $('#ratingInput').val().trim();
            let isValid = true;


            if (comment === "") {
                $('#error_comment').text("Vui lòng nhập bình luận!");
                isValid = false;
            }
            if (rating < 1) {
                $('#error_rating').text("Vui lòng đánh giá sao lớn hơn 1");
                isValid = false;
            }
            if (!isValid) {
                e.preventDefault();
            }
            return isValid;
        });

    });
</script>
</html>