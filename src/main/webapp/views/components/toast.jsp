<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
</head>

<div id="toast"></div>

<script>
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>
</script>

<script src="assets/javascript/ui/notification.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/ui/modal.js?v=<%=System.currentTimeMillis()%>"></script>

</html>