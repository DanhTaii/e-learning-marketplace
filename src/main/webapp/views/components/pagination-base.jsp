<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <title>Pagination</title>
</head>
<body>
<ul class="pagination home-product__pagination">
    <!-- Nút Previous -->
    <c:if test="${param.currentPageNumber > 1}">
        <li class="pagination-item">
            <a href="${pageContext.request.contextPath}/${param.baseUrl}?page=${param.currentPageNumber - 1}"
               class="pagination-item__link">
                <i class="pagination-item__icon fa-solid fa-angle-left"></i>
            </a>
        </li>
    </c:if>

    <!-- Trang đầu -->
    <li class="pagination-item ${param.currentPageNumber == 1 ? 'pagination-item--active' : ''}">
        <a href="${pageContext.request.contextPath}/${param.baseUrl}?page=1" class="pagination-item__link">1</a>
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
                <a href="${pageContext.request.contextPath}/${param.baseUrl}?page=${i}"
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
            <a href="${pageContext.request.contextPath}/${param.baseUrl}?page=${param.totalPages}"
               class="pagination-item__link">${param.totalPages}</a>
        </li>
    </c:if>

    <!-- Nút Next -->
    <c:if test="${param.currentPageNumber < param.totalPages}">
        <li class="pagination-item">
            <a href="${pageContext.request.contextPath}/${param.baseUrl}?page=${param.currentPageNumber + 1}"
               class="pagination-item__link">
                <i class="pagination-item__icon fa-solid fa-angle-right"></i>
            </a>
        </li>
    </c:if>
</ul>
</body>
</html>
