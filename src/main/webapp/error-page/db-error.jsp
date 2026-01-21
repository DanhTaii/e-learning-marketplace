<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <title>Lỗi cơ sở dữ liệu - SoftSkill</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/home.css">
    <link rel="stylesheet" href="assets/css/default.css">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <style>
        .error-container {
            text-align: center;
            padding: 100px 20px;
            min-height: 60vh;
        }

        .error-code {
            margin-top: 4rem;
            font-size: 80px;
            font-weight: bold;
            color: #ccc;
        }

        .error-msg {
            font-size: 24px;
            margin-bottom: 30px;
            margin-top: 5rem;
            color: #333;
        }
    </style>
</head>
<body>
<div class="web">
    <jsp:include page="../header-footer/header.jsp"/>

    <div class="web__container">
        <div class="grid">
            <div class="error-container">
                <div class="error-code">Database Exception</div>
                <h1 class="error-msg">Ối! Hệ thống không kết nối được cơ sở dữ liệu.</h1>
                <c:if test="${sessionScope.userSession.role == 'ADMIN'}">
                    <div style="background: #f8d7da; padding: 15px; border: 1px solid red;">
                        <h3>Thông tin kỹ thuật (Chỉ Admin thấy):</h3>
                        <p><strong>Loại lỗi:</strong> ${pageContext.errorData.throwable}</p>
                        <p><strong>Thông điệp:</strong> ${pageContext.errorData.throwable.message}</p>
                        <p><strong>Vị trí lỗi:</strong></p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <jsp:include page="../header-footer/footer.jsp"/>
</div>


</body>
</html>