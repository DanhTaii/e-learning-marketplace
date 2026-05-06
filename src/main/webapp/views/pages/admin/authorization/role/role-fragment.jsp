<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="newTotalCount">

<div class="container-2__list-student">
    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>TÊN VAI TRÒ</th>
            <th>MÔ TẢ</th>
            <th style=".modern-table th:nth-child(4) {
    width: 15%;
}">TRẠNG THÁI
            </th>
            <th>NGÀY TẠO</th>
            <th>THAO TÁC</th>
        </tr>
        </thead>
        <tbody>
        <jsp:include page="/views/pages/admin/authorization/role/role-table-body.jsp"/>
        </tbody>
    </table>

    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="vai trò"/>
        <jsp:param name="showDuplicate" value="true"/>
    </jsp:include>
</div>

<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/super/roles"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>