<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <title>Lỗi cơ sở dữ liệu - SoftSkill</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/css/base/home.css">
    <link rel="stylesheet" href="assets/css/base/default.css">
    <link rel="stylesheet" href="assets/css/base/error-page.css">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>
<div class="web">
    <jsp:include page="../../layouts/header.jsp"/>

    <div class="web__container">
        <div class="grid">
            <div class="error-container">
                <div class="error-code">System Error</div>
                <h1 class="error-msg">Ối! Hệ thống đang bị lỗi</h1>
            </div>

            <%-- Xóa tạm thời điều kiện Admin để kiểm tra xem nó có hiện không --%>
            <c:if test="${sessionScope.userSession.role == 'ADMIN'}">
            <div style="background: #f8d7da; padding: 15px; border: 1px solid red;" class="error-container">
                <h3 class="error-msg">Thông tin kỹ thuật:</h3>
                <%-- Sửa lại dòng bị lỗi ở đây --%>
                <p class="error-msg"><strong>Loại lỗi:</strong> <%= exception != null ? exception.getClass().getName() : "N/A" %>
                </p>
                <p class="error-msg"><strong>Thông điệp:</strong> <c:out value="${pageContext.errorData.throwable.message}"/></p>

            </div>
            </c:if>
        </div>
    </div>

    <jsp:include page="../../layouts/footer.jsp"/>
</div>


<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>