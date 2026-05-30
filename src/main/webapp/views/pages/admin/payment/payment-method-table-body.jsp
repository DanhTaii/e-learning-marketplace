<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty listPaymentMethods}">
        <c:forEach items="${listPaymentMethods}" var="pm">
            <tr>
                <td><input type="checkbox" class="category-checkbox item-checkbox" value="${pm.id}"
                           data-status="${pm.status}"></td>
                <td>
                    <div class="course-row__title title course-row__style-text">
                        <c:out value="${pm.name}"/>
                    </div>
                </td>
                <td>
                    <div class="course-row__title title course-row__style-text">
                        <c:out value="${pm.code}"/>
                    </div>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${pm.status == 'ACTIVE'}">
                            <div class="course-row__status course-row__font-content course-row__status-public">
                                Hoạt động
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="course-row__status course-row__font-content course-row-status-unactive">
                                Tạm dừng
                            </div>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td class="action__button">
                    <a href="admin/payment-method/detail?id=${pm.id}" class="turn-page">
                        <button type="button"
                                class="icon-action-btn">
                            <i class="fa-solid fa-eye"></i>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <tr>
            <td colspan="4">
                <div class="search-empty-state">
                    <i class="fa-solid fa-credit-card search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy kiểu thanh toán nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>