<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${archivedLessons != null}">
        <c:forEach var="item" items="${archivedLessons}">
            <tr>
                <td><input type="checkbox" class="item-checkbox" value="${item.id}"></td>
                <td>
                    <div class="lesson-info">
                        <img src="${item.thumbnailUrl}" class="lesson-thumb-mini">
                        <span>${item.title}</span>
                    </div>
                </td>
                <td>${item.courseName}</td>
                <td><fmt:formatDate value="${item.archivedAt}"
                                    pattern="dd/MM/yyyy"/></td>
                <td>
                    <span class="badge badge-reason">${item.reason}</span>
                </td>
                <td class="action-btns"></td>
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