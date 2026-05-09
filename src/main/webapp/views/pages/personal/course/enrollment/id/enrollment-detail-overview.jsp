<%--
  Created by IntelliJ IDEA.
  User: DanhTai
  Date: 5/9/2026
  Time: 2:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="container__overview">
    <div class="container__title-video mt-4">
        <div class="title-video">
            <span id="mainLessonTitle" class="text-3xl regular">${enrollmentDetail.title}</span>
        </div>
    </div>

    <div class="container__sub-header mt-3">
        <div class="sub-header__rating-star sub__header">
            <div class="star1">
                <div class="star1__number">
                                    <span class="text-xl"><fmt:formatNumber value="${enrollmentDetail.rating}"
                                                                            pattern="#.#"/></span></div>
                <div class="star1__star-icon"><i class="fa-solid fa-star icon-star"></i>
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

    <div class="container__description mt-5">

        <div class="info-row">
            <div class="info-label">
                Chứng chỉ
            </div>

            <div class="info-content">
                <div class="text-lg mb-4">
                    Hoàn thành khóa học để lấy chứng chỉ
                </div>
                <a class="dark-button mt-2 btn-certificate">
                    Lấy chứng chỉ
                </a>
            </div>
        </div>

        <div class="info-row">
            <div class="info-label">
                Mô tả
            </div>

            <div class="info-content">
                <div class="text-lg">
                    ${enrollmentDetail.description}
                </div>
            </div>
        </div>
    </div>
</div>
