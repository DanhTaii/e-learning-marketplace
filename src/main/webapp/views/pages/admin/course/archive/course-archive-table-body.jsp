<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${archivedCourses != null}">
        <c:forEach var="item" items="${archivedCourses}">
            <tr>
                <td><input type="checkbox" name="item-checkbox" class="item-checkbox" value="${item.id}"></td>
                <td>
                    <div class="content__title"><c:out value="${item.title}"/></div>
                </td>
                <td><c:out value="${item.categoryName}"/></td>
                <td><fmt:formatDate value="${item.deletedAt}"
                                    pattern="dd/MM/yyyy"/></td>
                <td>
                    <span class="badge badge-reason"><c:out value="${item.deleteReason}"/></span>
                </td>
                <td class="action-btns">
                    <button onclick="setupConfirmModal({action: 'restore', ids: ${item.id}, url: 'admin/course/action', isBulk: false})"
                            type="button"
                            class="icon-action-btn">
                        <i class="fa-solid fa-rotate-left"></i>
                    </button>
                    <button onclick="setupConfirmModal({action: 'delete', ids: ${item.id}, url: 'admin/course/action', isBulk: false})"
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
                        Không tìm thấy khóa học nào phù hợp
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>