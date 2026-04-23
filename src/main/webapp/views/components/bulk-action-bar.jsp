<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="floating-action-bar" id="actionBar" style="display: none;">
    <div class="action-info">
        <span class="count-badge" id="selectedCount">0</span>
        <span>Đã chọn ${param.label != null ? param.label : 'mục'}</span>
    </div>

    <div class="action-buttons">
        <c:if test="${param.showDuplicate == 'true'}">
            <button class="btn-bar" type="button" data-action="duplicate">
                <i class="fa-regular fa-copy"></i> Nhân bản
            </button>
        </c:if>

        <button class="btn-bar" type="button" data-action="update_status">
            <i class="fa-solid fa-arrows-rotate"></i> Đổi trạng thái
        </button>

        <button class="btn-bar btn-bar-danger" type="button" data-action="archive">
            <i class="fa-solid fa-trash"></i> Xóa
        </button>
    </div>

    <button class="btn-close-bar" type="button" onclick="deselectAll()">
        <i class="fa-solid fa-xmark"></i>
    </button>
</div>