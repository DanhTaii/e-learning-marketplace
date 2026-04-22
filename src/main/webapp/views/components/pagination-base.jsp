<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Lấy ra đường dẫn bên trang fragment --%>
<c:set var="targetUrl" value="${param.baseUrl}" />

<%-- Tạo ra đường dẫn URL với baseUrl kèm với PARAMS bằng thẻ c:url --%>
<c:url var="basePaginationUrl" value="/${targetUrl}">
    <%-- paramValues là đối tượng lấy ra toàn bộ dữ liệu từ URL --%>
    <%-- Thay vì là param thì chỉ lâấy được 1 cái --%>
    <c:forEach var="entry" items="${paramValues}">
        <%-- LOẠI BỎ tất cả các tham số kỹ thuật của JSP Include --%>
        <c:if test="${entry.key ne 'page'
                    and entry.key ne 'baseUrl'
                    and entry.key ne 'renderType'
                    and entry.key ne 'currentPageNumber'
                    and entry.key ne 'totalPages'}">
            <%-- Bắt đầu duyệt và kiểm tra xem có giá trị không --%>
            <c:forEach var="val" items="${entry.value}">
                <c:if test="${not empty val}">
                    <%-- Nếu nó không rỗng thì thêm PARAM vô URL --%>
                    <c:param name="${entry.key}" value="${val}"/>
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>
</c:url>

<c:set var="hasQuery" value="${fn:contains(basePaginationUrl, '?')}" />

<ul class="pagination home-product__pagination">
    <!-- Nút Previous -->
    <c:if test="${param.currentPageNumber > 1}">
        <li class="pagination-item">
            <a href="${basePaginationUrl}${hasQuery ? '&' : '?'}page=${param.currentPageNumber - 1}"
               class="pagination-item__link">
                <i class="pagination-item__icon fa-solid fa-angle-left"></i>
            </a>
        </li>
    </c:if>

    <!-- Trang đầu -->
    <li class="pagination-item ${param.currentPageNumber == 1 ? 'pagination-item--active' : ''}">
        <a href="${basePaginationUrl}${hasQuery ? '&' : '?'}page=1" class="pagination-item__link">1</a>
    </li>

    <!-- Dấu ... nếu currentPageNumber > 4 -->
    <c:if test="${param.currentPageNumber > 4}">
        <li class="pagination-item"><span class="pagination-item__link">...</span></li>
    </c:if>

    <!-- Các trang gần currentPageNumber -->
    <c:forEach var="i" begin="${param.currentPageNumber - 2 < 1 ? 1 : param.currentPageNumber - 2}"
               end="${param.currentPageNumber + 2}">
        <c:if test="${i > 1 && i < param.totalPages}">
            <li class="pagination-item ${i == param.currentPageNumber ? 'pagination-item--active' : ''}">
                <a href="${basePaginationUrl}${hasQuery ? '&' : '?'}page=${i}"
                   class="pagination-item__link">${i}</a>
            </li>
        </c:if>
    </c:forEach>

    <!-- Dấu ... nếu currentPageNumber < totalPages - 3 -->
    <c:if test="${param.currentPageNumber < param.totalPages - 3}">
        <li class="pagination-item"><span class="pagination-item__link">...</span></li>
    </c:if>

    <!-- Trang cuối -->
    <c:if test="${param.totalPages > 1}">
        <li class="pagination-item ${param.currentPageNumber == param.totalPages ? 'pagination-item--active' : ''}">
            <a href="${basePaginationUrl}${hasQuery ? '&' : '?'}page=${param.totalPages}"
               class="pagination-item__link">${param.totalPages}</a>
        </li>
    </c:if>

    <!-- Nút Next -->
    <c:if test="${param.currentPageNumber < param.totalPages}">
        <li class="pagination-item">
            <a href="${basePaginationUrl}${hasQuery ? '&' : '?'}page=${param.currentPageNumber + 1}"
               class="pagination-item__link">
                <i class="pagination-item__icon fa-solid fa-angle-right"></i>
            </a>
        </li>
    </c:if>
