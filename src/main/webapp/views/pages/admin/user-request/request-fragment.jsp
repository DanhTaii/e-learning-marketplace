<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="newTotalCount" value="${listRequests.size()}">

<div class="container-2__list-student">

    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>EMAIL</th>
            <th>TIÊU ĐỀ</th>
            <th>NỘI DUNG</th>
            <th>NGÀY TẠO</th>
            <th>TRẠNG THÁI</th>
            <th>THAO TÁC</th>
        </tr>
        </thead>

        <tbody>
        <jsp:include page="/views/pages/admin/user-request/request-table-body.jsp"/>
        </tbody>
    </table>

    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="danh mục"/>
        <jsp:param name="showDuplicate" value="true"/>
    </jsp:include>

</div>
