<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>

<c:forEach var="course" items="${listCourses}">
    <tr class="course-row">
        <td>
            <input type="checkbox" name="item-checkbox" class="course-checkbox item-checkbox" value="${course.id}">
        </td>
        <td>
            <div class="content__title">
                    ${course.title}
            </div>
            <div class="content__sub-title">Cấp độ: ${course.level.vietnameseName} • ${course.durationText}</div>
        </td>
            <%--                                                <td>--%>
            <%--                                                    <div class="course-row__duration course-row__font-content">--%>
            <%--                                                            ${course.durationText}--%>
            <%--                                                    </div>--%>
            <%--                                                </td>--%>
        <td>
            <div class="course-row__total__enrollment course-row__font-content">${course.studentCount}</div>
        </td>
            <%--                                                <td>--%>
            <%--                                                    <div class="course-row__level course-row__font-content">--%>
            <%--                                                        <div class="level-dot"></div>--%>
            <%--                                                            ${course.level.vietnameseName}--%>
            <%--                                                    </div>--%>
            <%--                                                </td>--%>
        <td>
            <c:choose>
                <c:when test="${course.isPublic}">
                    <div class="course-row__status course-row__font-content course-row__status-public">
                        Công khai
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="course-row__status course-row__font-content course-row__status-private">
                        Riêng tư
                    </div>
                </c:otherwise>
            </c:choose>
        </td>
        <td>
            <div class="course-row__created course-row__font-content">
                <fmt:setLocale value="en_US" scope="page"/>

                <fmt:formatDate
                        value="${course.createdAt}"
                        pattern="dd-MM-YYYY"/>
            </div>
        </td>
        <td class="action__button">
            <div class="action-wrapper">
                <a href="admin/course/detail?id=${course.id}">
                    <button type="button"
                            class="icon-action-btn">
                        <i class="fa-solid fa-pen"></i>
                    </button>
                </a>
                <button type="button" class="icon-action-btn"
                        onclick="setupConfirmModal({action: 'archive', ids: ${course.id}, url: 'admin/course/action', isBulk: false})">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </div>
        </td>
    </tr>
</c:forEach>
<c:if test="${empty listCourses}">
    <tr>
        <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
            <div class="search-empty-state"
                 style="text-align: center; padding: 40px 0;">
                <i class="fa-solid fa-book-open search-empty-icon"
                   style="font-size: 3rem; color: #ccc;"></i>
                <div class="search-empty-title"
                     style="font-size: 1.8rem; font-weight: bold; margin-top: 15px;">
                    Không tìm thấy khóa học nào
                </div>
            </div>
        </td>
    </tr>
</c:if>
</tbody>