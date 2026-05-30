<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="newTotalCount" value="${totalVouchers}">

<div class="container-2__list-student">
    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>MÃ VOUCHER</th>
            <th>TÊN VOUCHER</th>
            <th>LOẠI GIẢM GIÁ</th>
            <th>GIÁ TRỊ GIẢM</th>
            <th>HẠN SỬ DỤNG</th>
            <th>SỐ LƯỢT DÙNG</th>
            <th>TRẠNG THÁI</th>
            <th>THAO TÁC</th>
        </tr>
        </thead>
        <tbody>
        <%-- Đường dẫn include bảng dữ liệu voucher --%>
        <jsp:include page="/views/pages/admin/voucher/voucher-table-body.jsp"/>
        </tbody>
    </table>

    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="showDuplicate" value="true"/>
        <jsp:param name="showUpdateStatus" value="true"/>
        <jsp:param name="showArchive" value="true"/>
        <jsp:param name="showRestore" value="false"/>
        <jsp:param name="showDelete" value="false"/>
    </jsp:include>
</div>

<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/vouchers"/>
    <jsp:param name="currentPageNumber" value="${currentPageNumber}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>