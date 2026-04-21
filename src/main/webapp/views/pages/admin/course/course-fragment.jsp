<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>

<input type="hidden" id="newTotalCount" value="${result.totalElement}">

<div class="container-2__list-student">
    <table class="modern-table">
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll"></th>
            <th>Khóa học</th>
            <%--                                            <th>Thời lượng</th>--%>
            <th>Học viên</th>
            <%--                                            <th>Cấp độ</th>--%>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
            <jsp:include page="/views/pages/admin/course/course-table-body.jsp"/>
        </tbody>

    </table>
    <jsp:include page="/views/components/bulk-action-bar.jsp">
        <jsp:param name="label" value="khóa học"/>
        <jsp:param name="showDuplicate" value="true"/>
    </jsp:include>
</div>

<jsp:include page="/views/components/pagination-base.jsp">
    <jsp:param name="baseUrl" value="admin/courses"/>
    <jsp:param name="currentPageNumber" value="${currentPageNumber}"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
</jsp:include>