<%--
  Created by IntelliJ IDEA.
  User: DanhTai
  Date: 5/16/2026
  Time: 2:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="section-7__review section__space">

    <div class="review-box__header style__sub-title">
        <span>Ghi chú cá nhân</span>
    </div>

    <div class="note-editor-container">

        <div class="note-editor-header">
            <div class="note-time-indicator">
                <i class="fa-solid fa-at"></i> <span id="current-video-time-display">00:00</span>
            </div>
        </div>

        <div class="note-editor-body">
            <textarea id="note-content-input" class="note-textarea" placeholder="Viết ghi chú mới tại đây..."></textarea>
        </div>

        <div class="note-editor-footer">
            <button class="btn-save-note" type="button" id="btn-save-note">
                <i class="fa-regular fa-floppy-disk"></i> Lưu ghi chú
            </button>
        </div>
    </div>

    <div id="notes-list-container" style="display: flex; flex-direction: column; gap: 15px;">
        <div class="empty-state" id="empty-note-state">
            <i class="fa-solid fa-pen-to-square empty-icon"></i>
            <div class="empty-title">Chưa có ghi chú nào</div>
            <div class="empty-description">
                Ghi chú giúp bạn lưu lại những kiến thức quan trọng. Hãy bấm lưu để tạo ghi chú đầu tiên!
            </div>
        </div>
    </div>

</div>