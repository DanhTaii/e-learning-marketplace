
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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
R