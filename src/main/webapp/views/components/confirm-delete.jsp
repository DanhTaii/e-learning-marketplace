<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--COMPONENT CONFIRM FOR DELETE--%>
<div id="confirm-delete-modal" class="modal">
    <div class="modal-content">
        <h3 class="modal-title">
            <i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa
        </h3>
        <p id="confirm-modal-message" class="modal-message"></p>

        <div class="modal-actions">
            <button type="button" onclick="closeModal('confirm-delete-modal')" class="button btn-cancel">
                Hủy
            </button>
            <button type="button" id="btn-confirm-delete" class="button dark-button btn-modal-delete">
                Xóa ngay
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
</form>