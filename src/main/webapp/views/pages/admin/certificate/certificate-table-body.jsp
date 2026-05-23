<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listCertificates}">

        <c:forEach var="cert" items="${listCertificates}">
            <tr>
                <td><input type="checkbox" name="item-checkbox" class="certificate-checkbox item-checkbox"
                           value="${cert.id}">
                </td>
                <td>
                    <div class="cert-code content__title text-bold">${cert.certificateCode}</div>
                </td>
                <td>
                    <div class="user-name content__title">${cert.username}</div>
                </td>
                <td>
                    <div class="content__title">${cert.courseTitle}</div>
                </td>
                <td class="text-light">
                    <fmt:formatDate value="${cert.issueDate}" pattern="dd/MM/yyyy"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${cert.status eq 'ACTIVE'}">
                            <span class="badge course-row__status-public">Hợp lệ</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge course-row-status-unactive">Đã thu hồi</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="action-btns">
                    <a href="admin/certificate/detail?id=${cert.id}" class="icon-action-btn">
                        <i class="fa-solid fa-eye"></i>
                    </a>
<%--                    <button onclick="setupConfirmModal({action: 'revoke', ids: ${cert.id}, url: 'admin/certificate/action', isBulk: false})"--%>
<%--                            type="button"--%>
<%--                            class="icon-action-btn">--%>
<%--                        <i class="fa-solid fa-lock"></i>--%>
<%--                    </button>--%>
                </td>
            </tr>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <tr>
            <td colspan="7">
                <div class="search-empty-state">
                    <i class="fa-solid fa-file-certificate search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy chứng chỉ nào phù hợp
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>