<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course Content</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/course-content.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <%--    <link rel="stylesheet" href="assets/css/fonts.css">--%>

</head>
<body>
<div class="web">
    <jsp:include page="/header-footer/header.jsp"/>
    <div class="web__container">
        <div class="grid layout">
            <div class="grid__column-8 column1">
                <div class="image-container">
                    <iframe id="mainVideoPlayer" width="100%" height="500"
                            src="https://www.youtube.com/embed/sGTkYMrWX6U?si=R75SMusg5_-meU-e"
                            title="YouTube video player" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                            referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
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
                                    <span class="text-xl">${enrollmentDetail.rating}</span></div>
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
                            <div class="time"><span class="text-xl">${enrollmentDetail.durationHours}</span></div>

                            <div class="total"><span class="text-sm light">Tổng cộng</span></div>

                        </div>
                    </div>

                    <div class="section-7__review section__space">
                        <div class="review-box__header style__sub-title">
                            <span class="">${enrollmentDetail.reviewCount} đánh giá</span>
                        </div>
                        <div class="comment-input-box">
                            <div class="comment__user2 header__user">
                                <img src="../assets/image/65472207_145188949876444_2344275901291692032_n.jpg" alt=""
                                     class="user__avatar2">
                            </div>
                            <form action="my-course/review/create" method="post">
                                <div class="box__input ">
                                    <input type="hidden" name="courseId" value="${enrollmentDetail.courseId}">
                                    <input type="text" name="comment" class="input-style"
                                           placeholder="Viết bình luận...">
                                    <input type="number" class="input__number" placeholder="" name="rating">
                                    <div class="star">
                                        <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1.6rem"></i>
                                    </div>
                                    <button class="dark-button button__add" type="submit">
                                        Gửi
                                    </button>
                                </div>
                            </form>
                        </div>

                        <c:forEach var="review" items="${enrollmentDetail.listReviews}">
                            <div class="review-box__comment">
                                <div class="comment__user header__user">
                                    <img src="${review.thumbnailUrl}" alt=""
                                         class="user__avatar1">
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
                                                <i class="fa-solid fa-star" style="color: #FFD43B; font-size: 1rem"></i>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="box__comment box">
                                        <span class="">${review.comment}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
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
                                              style="color: var(--white-color)">${l.durationMinutes}</span>
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
</html>