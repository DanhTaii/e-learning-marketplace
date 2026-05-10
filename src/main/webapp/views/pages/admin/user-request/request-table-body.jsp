<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listRequests}">
        <c:forEach var="request" items="${listRequests}">
            <tr>
                <td><input type="checkbox" class="request-checkbox item-checkbox" value="${request.id}"></td>

                <td>${request.email}</td>

                <td>${request.subject}</td>

                <td>${request.message}</td>

                <td>
                    <fmt:formatDate value="${request.createdAt}" pattern="dd/MM/yyyy"/>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${request.status.name() == 'ACTIVE'}">
                            <span class="badge course-row__status-public">Đã xử lý</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge course-row-status-unactive">Chưa xử lý</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td class="action-btns">
                    <a href="admin/request/detail?id=${request.id}">
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
            <td colspan="7">
                <div class="search-empty-state">
                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy yêu cầu nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>