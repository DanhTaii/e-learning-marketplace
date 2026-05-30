<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-2__list-student">
    <table class="modern-table archive-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAllArchive"></th>
            <th>MÃ VOUCHER</th>
            <th>TIÊU ĐỀ VOUCHER</th>
            <th>LOẠI GIẢM GIÁ</th>
            <th>NGÀY LƯU TRỮ</th>
            <th>LÝ DO</th>
        </tr>
        </thead>
        <tbody>
        <jsp:include page="/views/pages/admin/voucher/archive/voucher-archive-table-body.jsp"/>
        </tbody>
    </table>

    <%-- Thanh hành động hàng loạt (Bulk Actions) --%>
    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="voucher"/>
        <jsp:param name="showDuplicate" value="false"/>
        <jsp:param name="showUpdateStatus" value="false"/>
        <jsp:param name="showArchive" value="false"/>
        <jsp:param name="showRestore" value="true"/>
        <jsp:param name="showDelete" value="true"/>
    </jsp:include>
</div>

<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/vouchers/archive"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>
