<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="newTotalCount" value="${listOrders.size()}">

<div class="container-2__list-student">

    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>Mã đơn hàng</th>
            <th>Tên người dùng</th>
            <th>Thành tiền</th>
            <th>Kiểu thanh toán</th>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Hành động</th>
        </tr>
        </thead>

        <tbody>
        <jsp:include page="/views/pages/admin/order/order-table-body.jsp"/>
        </tbody>
    </table>

    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="đơn hàng"/>
        <jsp:param name="showDuplicate" value="true"/>
    </jsp:include>

</div>
<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/orders"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>