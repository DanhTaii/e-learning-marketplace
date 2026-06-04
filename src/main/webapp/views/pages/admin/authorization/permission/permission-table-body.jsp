<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listPermissions}">

        <c:forEach var="permission" items="${listPermissions}">
            <tr>
                <td><input type="checkbox" name="item-checkbox" class="lesson-checkbox item-checkbox"
                           value="${permission.id}">
                </td>
                <td>
                    <div class="lesson-info">
                        <div class="lesson-text">
                            <div class="content__title"><c:out value="${permission.name}"/></div>
<%--                            <div class="content__sub-title">Chương <c:out value="${lesson.orderIndex}"/> • Bài <c:out value="${lesson.orderIndex}"/></div>--%>
                        </div>
                    </div>
                </td>
                    <%--                                                <td class="course-name">Soft Skills Masterclass</td>--%>

                <td class="text-light">
                    <c:out value="${permission.description}"/>
                </td>
                <td class="text-light">
                    <c:out value="${permission.groupName}"/>
                </td>
                <td class="text-light">
                    <fmt:formatDate value="${permission.createdAt}" pattern="yyyy-MM-dd"/>
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
                        Không tìm thấy quyền nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>