<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Lấy giá trị từ param truyền vào, nếu không có thì để mặc định
    String label = request.getParameter("label") != null ? request.getParameter("label") : "mục";
    String showDuplicate = request.getParameter("showDuplicate") != null ? request.getParameter("showDuplicate") : "true";
%>

<div class="floating-action-bar" id="actionBar">
    <div class="action-info">
        <span class="count-badge" id="selectedCount">0</span>
        <span>Đã chọn <%= label %></span>
    </div>

    <div class="action-buttons">
        <% if ("true".equals(showDuplicate)) { %>
        <button class="btn-bar" type="button" onclick="handleBulkDuplicate()">
            <i class="fa-regular fa-copy"></i> Nhân bản
        </button>
        <% } %>

        <button class="btn-bar" type="button" onclick="handleBulkStatus()">
            <i class="fa-solid fa-arrows-rotate"></i> Đổi trạng thái
        </button>

        <button class="btn-bar btn-bar-danger" type="button" onclick="handleBulkDelete()">
            <i class="fa-solid fa-trash"></i> Xóa
        </button>
    </div>

    <button class="btn-close-bar" type="button" onclick="deselectAll()">
        <i class="fa-solid fa-xmark"></i>
    </button>
</div>