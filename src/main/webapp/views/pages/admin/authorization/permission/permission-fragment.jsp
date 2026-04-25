<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="newTotalCount" >

<div class="container-2__list-student">
    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>TÊN QUYỀN</th>
            <th>MÔ TẢ</th>
            <th>NHÓM</th>
            <th>NGÀY TẠO</th>
        <%--            <th>NHÓM</th>--%>

        </tr>
        </thead>
        <tbody>
        <jsp:include page="/views/pages/admin/authorization/permission/permission-table-body.jsp"/>
        </tbody>
    </table>

    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="bài học"/>
        <jsp:param name="showDuplicate" value="true"/>
    </jsp:include>
</div>

<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/super/permissions"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>