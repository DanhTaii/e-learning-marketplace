<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="newTotalCount" value="${totalCertificates}">

<div class="container-2__list-student">
    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>MÃ CHỨNG CHỈ</th>
            <th>TÊN HỌC VIÊN</th>
            <th>TÊN KHÓA HỌC</th>
            <th>NGÀY CẤP</th>
            <th>TRẠNG THÁI</th>
            <th>ACTION</th>
        </tr>
        </thead>
        <tbody>
            <jsp:include page="/views/pages/admin/certificate/certificate-table-body.jsp"/>
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
    <jsp:param name="baseUrl" value="admin/certificates"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>