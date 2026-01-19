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
    <jsp:include page="/header-footer/header.jsp"/>

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
                    <a href="my-course/detail?courseId=${e.courseId}" class="turn-page">
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

    <jsp:include page="/header-footer/footer.jsp"/>

</div>


</body>
</html>