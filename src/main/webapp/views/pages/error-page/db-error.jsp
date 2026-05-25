<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

</head>
<body>
<div class="web">
    <jsp:include page="../../layouts/header.jsp"/>

    <div class="web__container">
        <div class="grid">
            <div class="error-container">
                <div class="error-code">Database Exception</div>
                <h1 class="error-msg">Ối! Hệ thống không kết nối được cơ sở dữ liệu.</h1>
                <c:if test="${sessionScope.userSession.role == 'ADMIN'}">
                    <div style="background: #f8d7da; padding: 15px; border: 1px solid red;">
                        <h3>Thông tin kỹ thuật (Chỉ Admin thấy):</h3>
                        <p><strong>Loại lỗi:</strong> <c:out value="${pageContext.errorData.throwable}"/></p>
                        <p><strong>Thông điệp:</strong> <c:out value="${pageContext.errorData.throwable.message}"/></p>
                        <p><strong>Vị trí lỗi:</strong></p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <jsp:include page="../../layouts/footer.jsp"/>
</div>


<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>