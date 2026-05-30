<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-2__list-student">
    <table class="modern-table">
        <thead>
        <tr>
            <th>Tên phương thức</th>
            <th>Code</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody id="paymentMethodTableBody">
        <jsp:include page="/views/pages/admin/payment/payment-method-table-body.jsp"/>
        </tbody>
    </table>
    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="showDuplicate" value="false"/>
        <jsp:param name="showUpdateStatus" value="true"/>
        <jsp:param name="showArchive" value="false"/>
        <jsp:param name="showRestore" value="false"/>
        <jsp:param name="showDelete" value="false"/>
    </jsp:include>
</div>

<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/payment-methods"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>