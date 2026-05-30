<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- Sửa lại điều kiện kiểm tra danh sách voucher hợp lệ --%>
    <c:when test="${not empty archivedVouchers}">
        <c:forEach var="item" items="${archivedVouchers}">
            <tr>
                <td><input type="checkbox" name="item-checkbox" class="item-checkbox" value="${item.id}"></td>
                <td>
                    <span class="badge badge-code" style="font-weight: 600; text-transform: uppercase;">
                        <c:out value="${item.code}"/>
                    </span>
                </td>
                <td>
                    <div class="content__title"><c:out value="${item.title}"/></div>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${item.discountType eq 'PERCENT'}">
                            <fmt:formatNumber value="${item.discountValue}" type="number"/>%
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${item.discountValue}" type="number" maxFractionDigits="0"/>đ
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <fmt:formatDate value="${item.deletedAt}" pattern="dd/MM/yyyy"/>
                </td>
                <td>
                    <span class="badge badge-reason"><c:out value="${item.deleteReason}"/></span>
                </td>
                <td class="action-btns">
                        <%-- Đổi endpoint URL xử lý modal confirm về admin/vouchers/archive --%>
                    <button onclick="setupConfirmModal({action: 'restore', ids: ${item.id}, url: 'admin/vouchers/archive', isBulk: false})"
                            type="button"
                            class="icon-action-btn">
                        <i class="fa-solid fa-rotate-left"></i>
                    </button>
                    <button onclick="setupConfirmModal({action: 'delete', ids: ${item.id}, url: 'admin/vouchers/archive', isBulk: false})"
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
                    <i class="fa-solid fa-ticket search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy mã giảm giá nào phù hợp trong thùng rác
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>