<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <title>Lỗi hệ thống - SoftSkill</title>
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
                <div class="error-code">500</div>
                <h1 class="error-msg">Ối! Lỗi hệ thống.</h1>
            </div>
        </div>
    </div>

    <jsp:include page="../../layouts/footer.jsp"/>
</div>


<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
</body>
</html>