<%--
  Created by IntelliJ IDEA.
  User: DanhTai
  Date: 5/9/2026
  Time: 1:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
            <div><span id="error_comment" class="error-client"></span></div>
            <div><span id="error_rating" class="error-client"></span></div>
            <div class="box__input">
                <input type="hidden" name="courseId" value="${enrollmentDetail.courseId}">
                <input type="text" name="comment" class="input-style"
                       placeholder="Viết bình luận..." id="user_comment">

                <input type="number" class="input__number" name="rating" id="ratingInput" min="0"
                       max="5" step="0.1" oninput="validateRating(this)">
                <div class="star">
                    <i class="fa-solid fa-star"></i>
                    <span id="ratingDisplay" class="rating-display">0</span>
                    <span class="num">/5</span>
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
                                    <i class="fa-solid fa-star"></i>
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
