<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <%-- Kiểm tra danh sách listVoucher được truyền từ Controller --%>
    <c:when test="${not empty listVoucher}">
        <c:forEach var="v" items="${listVoucher}">
            <tr>
                <td>
                    <input type="checkbox" name="item-checkbox" class="voucher-checkbox item-checkbox" value="${v.id}">
                </td>

                <td class="text-bold">
                    <span class="code-badge title"><c:out value="${v.code}"/></span>
                </td>

                <td>
                    <div class="content__title"><c:out value="${v.title}"/></div>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${v.discountType eq 'PERCENT'}">
                            <span class="badge type-percentage">Phần trăm (%)</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge type-fixed">Số tiền cố định</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td class="text-bold">
                    <c:choose>
                        <c:when test="${v.discountType eq 'PERCENT'}">
                            <fmt:formatNumber value="${v.discountValue}" type="number"/>%
                        </c:when>
                        <c:otherwise>
                            <c:out value="${v.formatDiscountValue}"></c:out>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td class="text-light">
                    <fmt:formatDate value="${v.startDate}" pattern="dd/MM/yyyy"/> -
                    <fmt:formatDate value="${v.endDate}" pattern="dd/MM/yyyy"/>
                </td>

                <td>
                    <span class="text-bold"><c:out value="${v.usedCount}"/></span> /
                    <span class="text-muted">
                        <c:choose>
                            <c:when test="${empty v.usageLimit || v.usageLimit == 0}">∞</c:when>
                            <c:otherwise><c:out value="${v.usageLimit}"/></c:otherwise>
                        </c:choose>
                    </span>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${v.status eq 'ACTIVE'}">
                            <span class="badge course-row__status-public">Hoạt động</span>
                        </c:when>
                        <c:when test="${v.status eq 'INACTIVE'}">
                            <span class="badge course-row-status-unactive">Tạm dừng</span>
                        </c:when>
                    </c:choose>
                </td>

                    <%-- THAO TÁC --%>
                <td class="action-btns">
                    <a href="admin/voucher/detail?id=${v.id}" class="js-edit-link">
                        <button type="button" class="icon-action-btn">
                            <i class="fa-solid fa-pen"></i>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </c:when>

    <%-- KHÔNG TÌM THẤY KẾT QUẢ --%>
    <c:otherwise>
        <tr>
            <td colspan="9">
                <div class="search-empty-state">
                    <i class="fa-solid fa-ticket search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy voucher nào phù hợp
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>