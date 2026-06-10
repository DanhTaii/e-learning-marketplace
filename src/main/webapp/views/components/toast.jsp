<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=${applicationScope.assetVersion}">
<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>

<div id="toast"></div>

<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
<script>
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>
</script>

<script src="assets/javascript/ui/notification.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/component/modal/modal.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/component/modal/modal-configs.js?v=${applicationScope.assetVersion}"></script>
</html>