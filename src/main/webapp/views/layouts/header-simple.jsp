<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>header</title>
    <link rel="stylesheet" href="assets/css/base/header-simple.css?v=<%=System.currentTimeMillis()%>">
<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>
    <header class="web__header">
        <div class="grid">
            <div class="header__box">
                <a href="javascript:history.back()"
                   class="turn-page link">
                    <div class="header1">
                        <i class="fa-solid fa-arrow-left"></i>
                        <span class="back">Quay lại</span>
                    </div>
                </a>

            </div>
        </div>
    </header>
    <script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>