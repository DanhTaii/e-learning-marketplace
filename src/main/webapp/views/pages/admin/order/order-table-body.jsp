<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listOrders}">

        <c:forEach var="order" items="${listOrders}">

            <tr>
                <td><input type="checkbox" class="category-checkbox item-checkbox" value="${order.id}" data-amount="${order.finalAmount}"
                           data-status="${order.status}"></td>
                <td>
                    <div class="course-row__title title course-row__style-text">
                            ${order.orderCode}
                    </div>
                </td>
                <td>
                    <div class="course-row__font-content">
                            ${order.usernameSnapshot}
                    </div>
                </td>
                <td>
                    <div class="course-row__font-content">
                            ${order.finalAmount}
                    </div>
                </td>
                <td>
                    <div class="course-row__font-content">
                            ${order.paymentMethodId == 1 ? 'Momo' :
                                    (order.paymentMethodId == 2 ? 'VNPAY' :
                                            (order.paymentMethodId == 3 ? 'ZaloPay' : 'Chưa chọn'))}
                    </div>
                </td>
                <td>
                    <div class="course-row__font-content course-row__status ">
                            ${order.status}
                    </div>
                </td>
                <td>
                    <div class="course-row__created course-row__font-content">
                        <fmt:setLocale value="en_US" scope="page"/>

                        <fmt:formatDate value="${order.createdAt}"
                                        pattern="yyyy-MM-dd"/>
                    </div>
                </td>
                <td class="action__button">
                    <a href="admin/order/detail?id=${order.id}" class="turn-page">
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
            <td colspan="7">
                <div class="search-empty-state">
                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy đơn hàng nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>