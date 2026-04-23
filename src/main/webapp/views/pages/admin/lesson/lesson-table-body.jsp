<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listLessons}">

        <c:forEach var="lesson" items="${listLessons}">
            <tr>
                <td><input type="checkbox" name="item-checkbox" class="lesson-checkbox item-checkbox"
                           value="${lesson.id}">
                </td>
                <td>
                    <div class="lesson-info">
                        <div class="lesson-icon"><i class="fa-solid fa-play"></i></div>
                        <div class="lesson-text">
                            <div class="content__title">${lesson.title}</div>
                            <div class="content__sub-title">Chương ${lesson.orderIndex} • Bài ${lesson.orderIndex}</div>
                        </div>
                    </div>
                </td>
                    <%--                                                <td class="course-name">Soft Skills Masterclass</td>--%>
                <td class="text-bold">${lesson.durationMinutes}:00</td>
                <td class="text-light">
                    <fmt:formatDate value="${lesson.createdAt}" pattern="dd/MM/yyyy"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${lesson.videoUrl != null && lesson.videoUrl != ''}">
                            <i class="fa-solid fa-circle-check icon-success"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="fa-solid fa-circle-exclamation icon-danger"></i>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${lesson.status eq 'ACTIVE'}">
                            <span class="badge course-row__status-public">Hoạt động</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge course-row-status-unactive">Bản nháp</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="action-btns">
                    <a href="admin/lesson/detail?id=${lesson.id}" class="js-edit-link">
                        <button type="button" class="icon-action-btn"><i
                                class="fa-solid fa-pen"></i></button>
                    </a>
                    <button onclick="setupConfirmModal({action: 'archive', ids: ${lesson.id}, url: 'admin/lesson/delete', isBulk: false})"
                            type="button"
                            class="icon-action-btn">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <tr>
            <td colspan="7">
                <div class="search-empty-state">
                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy bài học nào phù hợp
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>