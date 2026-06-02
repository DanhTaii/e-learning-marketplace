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
        <span><c:out value="${enrollmentDetail.reviewCount}"/> đánh giá</span>
    </div>

    <!-- Form nhập đánh giá -->
<%--    <div class="comment-input-box">--%>
<%--        <div class="comment__user2 header__user">--%>
<%--            <img src="${sessionScope.userSession.avatarUrl}" alt=""--%>
<%--                 class="user__avatar2">--%>
<%--        </div>--%>
<%--        <form action="my-course/review/create" method="post" id="myForm">--%>
<%--            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">--%>
<%--            <div><span id="error_comment" class="error-client"></span></div>--%>
<%--            <div><span id="error_rating" class="error-client"></span></div>--%>
<%--            <div class="box__input">--%>
<%--                <input type="hidden" name="courseId" value="${enrollmentDetail.courseId}">--%>
<%--                <input type="text" name="comment" class="input-style"--%>
<%--                       placeholder="Viết bình luận..." id="user_comment">--%>

<%--                <input type="number" class="input__number" name="rating" id="ratingInput" min="0"--%>
<%--                       max="5" step="0.1" oninput="validateRating(this)">--%>
<%--                <div class="star">--%>
<%--                    <i class="fa-solid fa-star"></i>--%>
<%--                    <span id="ratingDisplay" class="rating-display">0</span>--%>
<%--                    <span class="num">/5</span>--%>
<%--                </div>--%>
<%--                <button class="dark-button button__add" type="submit">Gửi</button>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>

    <c:if test="${!enrollmentDetail.isReviewed}">
        <div class="comment-input-box modern-review-form">
            <div class="comment__user2 header__user">
                <img src="${sessionScope.userSession.avatarUrl}" alt="Avatar" class="user__avatar2">
            </div>

            <form action="my-course/review/create" method="post" id="myForm" class="review-form-container">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                <input type="hidden" name="courseId" value="${enrollmentDetail.courseId}">

                <div class="error-wrapper">
                    <span id="error_rating" class="error-client"></span>
                    <span id="error_comment" class="error-client"></span>
                </div>

                <div class="review-input-wrapper">
                    <div class="rating-selection">
                        <span class="rating-label">Chất lượng khóa học:</span>
                        <div class="star-rating-interactive">
                            <i class="fa-regular fa-star star-item" data-value="1"></i>
                            <i class="fa-regular fa-star star-item" data-value="2"></i>
                            <i class="fa-regular fa-star star-item" data-value="3"></i>
                            <i class="fa-regular fa-star star-item" data-value="4"></i>
                            <i class="fa-regular fa-star star-item" data-value="5"></i>
                        </div>
                        <input type="hidden" name="rating" id="ratingInput" value="0">
                        <span id="ratingDisplay" class="rating-display" style="display: none;">0</span>
                    </div>

                    <textarea name="comment" class="review-textarea"
                              placeholder="Khóa học này thế nào? Hãy chia sẻ cảm nhận của bạn nhé..."
                              id="user_comment"></textarea>

                    <div class="review-actions">
                        <button class="btn-submit-modern" type="submit">
                            <i class="fa-solid fa-paper-plane"></i> Gửi đánh giá
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </c:if>

    <c:if test="${enrollmentDetail.isReviewed}">
        <div id="already-reviewed-msg" class="already-reviewed-msg">
            <i class="fa-solid fa-circle-check"></i>
            <span>Cảm ơn bạn đã để lại đánh giá cho khóa học này!</span>
        </div>
    </c:if>

    <!-- Danh sách đánh giá hoặc empty state -->
    <div id="reviews-list-container" class="reviews-list-container">
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
                                    <span class="review__name"><c:out value="${review.userName}"/></span>
                                    <span class="review__time"><fmt:formatDate
                                            value="${review.createdAt}" pattern="yyyy-MM-dd "/></span>
                                </div>
                            </div>
                            <div class="box__date box">
                                <div class="star">
                                    <div class="text-medium regular"><c:out value="${review.rating}"/></div>
                                    <div class="star-icon">
                                        <i class="fa-solid fa-star"></i>
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
                <div class="empty-state" style="margin-bottom: 20px">
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
