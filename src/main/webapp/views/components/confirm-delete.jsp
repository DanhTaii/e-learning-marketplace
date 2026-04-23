<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--COMPONENT CONFIRM FOR DELETE--%>
<div id="confirm-delete-modal" class="modal">
    <div class="modal-content">
        <h3 class="modal-title" id="confirm-modal-title">
            <i class="fa-solid fa-triangle-exclamation"></i> Xác nhận
        </h3>
        <%-- CHỖ HIỂN THỊ NỘI DUNG --%>
        <p id="confirm-modal-message" class="modal-message"></p>

        <div id="reason-container" class="hidden">
            <label for="archive-reason">LÝ DO LƯU TRỮ:</label>
            <select id="archive-reason" name="reason" class="modal-select">
                <option value="Lỗi thời">Lỗi thời</option>
                <option value="Trùng lặp">Trùng lặp</option>
                <option value="Cập nhật nội dung">Cập nhật nội dung</option>
                <option value="Khác">Khác...</option>
            </select>
        </div>

        <div class="modal-actions">
            <button type="button" onclick="closeModal('confirm-delete-modal')" class="button btn-cancel">
                Hủy
            </button>
            <button type="button" id="btn-confirm-delete" class="button">
                Xác nhận
            </button>
        </div>
    </div>
</div>
<%--DELETE ACTION--%>
<form id="delete-form-id" action=""
      method="POST"
      class="form"
      style="display: none">
    <input id="input-delete-id" type="hidden" name="id">
    <input id="input-delete-type" type="hidden" name="deleteType">
    <input id="input-delete-reason" type="hidden" name="deleteReason">
</form>