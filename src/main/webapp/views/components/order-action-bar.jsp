<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="floating-action-bar" id="actionBar" style="display: none;">
    <div class="action-info">
        <span class="count-badge" id="selectedCount">0</span>
        <span>Đã chọn ${param.label != null ? param.label : 'đơn hàng'}</span>

        <div class="revenue-info">
            <i class="fa-solid fa-sack-dollar"></i>
            <span style="font-size: 12px">Tổng thu:</span>
            <span id="selected-revenue-total" class="revenue-highlight revenue-amount">0</span>
            <span class="revenue-highlight">đ</span>
        </div>
    </div>


    <button class="btn-close-bar" type="button" onclick="deselectAll()">
        <i class="fa-solid fa-xmark"></i>
    </button>
</div>