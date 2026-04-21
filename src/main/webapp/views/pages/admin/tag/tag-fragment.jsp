<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="newTotalCount" value="${listTags.size()}">

<div class="container-2__list-student">

    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>TÊN THẺ</th>
            <th>SLUG</th>
            <th>SỐ LƯỢNG</th>
            <th>NGÀY TẠO</th>
            <th>TRẠNG THÁI</th>
            <th>THAO TÁC</th>
        </tr>
        </thead>

        <tbody>
        <jsp:include page="/views/pages/admin/tag/tag-table-body.jsp"/>
        </tbody>
    </table>

    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="thẻ"/>
        <jsp:param name="showDuplicate" value="true"/>
    </jsp:include>

</div>
<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/tags"/>
    <jsp:param name="currentPageNumber" value="${filter.page}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>