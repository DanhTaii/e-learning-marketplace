<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listTags}">
        <c:forEach var="t" items="${listTags}">
            <tr>
                <td><input type="checkbox" class="tag-checkbox item-checkbox" value="${t.id}"></td>
                <td><c:out value="${t.name}"/></td>
                <td><c:out value="${t.slug}"/></td>
                <td><c:out value="${t.courseCount}"/></td>
                <td>
                    <fmt:formatDate value="${t.createdAt}" pattern="dd/MM/yyyy"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${t.status.name() == 'ACTIVE'}">
                            <span class="badge course-row__status-public">Hoạt động</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge course-row-status-unactive">Không hoạt động</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="action-btns">
                    <a href="admin/tag/detail?id=${t.id}">
                        <button type="button" class="icon-action-btn">
                            <i class="fa-solid fa-pen"></i>
                        </button>
                    </a>
                </td>

            </tr>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <tr>
            <td colspan="6">
                <div class="search-empty-state">
                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy thẻ nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>