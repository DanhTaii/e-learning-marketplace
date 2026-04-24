<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listRoles}">

        <c:forEach var="role" items="${listRoles}">
            <tr>
                <td><input type="checkbox" name="item-checkbox" class="lesson-checkbox item-checkbox"
                           value="${role.id}">
                </td>
                <td>
                    <div class="lesson-info">
                        <div class="lesson-text">
                            <div class="content__title">${role.name}</div>
                        </div>
                    </div>
                </td>

                <td class="text-light">
                    ${role.description}
                </td>
                <td class="text-light">
                    <fmt:formatDate value="${role.createdAt}" pattern="dd/MM/yyyy"/>
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
                        Không tìm thấy vai trò nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>