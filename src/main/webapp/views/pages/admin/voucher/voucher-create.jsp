<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Voucher Create</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Layout Admin --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/form-detail-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/pages/voucher/voucher-create.css?v=<%=System.currentTimeMillis()%>">

    <%-- Base & Notification--%>
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">

                <jsp:include page="/views/layouts/admin/sidebar-admin.jsp"/>

                <div class="grid__column-10 container-2">
                    <div class="container-2__header-modern">
                        <h2 class="header__title-modern">
                            <c:out value="${(not empty voucher and voucher.id > 0) ? 'Cập nhật mã giảm giá' : 'Tạo mới mã giảm giá'}"/>
                        </h2>
                        <a href="admin/vouchers" class="btn-back">
                            <i class="fa-solid fa-backward-step"></i> Trở về
                        </a>
                    </div>

                    <div class="form-container">
                        <%-- Xóa enctype="multipart/form-data" vì không tải file --%>
                        <form id="voucherForm" action="admin/voucher/detail" method="post" class="form-modern">
                            <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
                            <c:if test="${voucher != null}">
                                <input type="hidden" name="id" value="${voucher.id}"/>
                            </c:if>

                            <div class="lesson-create-card">

                                <%-- Hàng 1: Mã Voucher & Tiêu đề --%>
                                <div class="form-row">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Mã Voucher (Code)</label>
                                        <input type="text" name="code" class="input-modern" id="voucherCode"
                                               value="${voucher != null ? voucher.code : param.code}"
                                               placeholder="VD: SALE2024" style="text-transform: uppercase;">
                                        <span class="error-client" id="error_code"><c:out value="${errors.code}"/></span>
                                    </div>
                                    <div class="form-group flex-2">
                                        <label class="label-style">Tên chương trình / Tiêu đề</label>
                                        <input type="text" name="title" class="input-modern" id="voucherTitle"
                                               value="${voucher != null ? voucher.title : param.title}"
                                               placeholder="Nhập tên chương trình...">
                                        <span class="error-client" id="error_title"><c:out value="${errors.title}"/></span>
                                    </div>
                                </div>

                                <%-- Hàng 2: Mô tả chi tiết --%>
                                <div class="form-group mt-3">
                                    <label class="label-style">Mô tả chi tiết</label>
                                    <textarea name="description" class="input-modern" rows="3" id="voucherDescription"
                                              placeholder="Mô tả điều kiện áp dụng...">${voucher != null ? voucher.description : param.description}</textarea>
                                    <span class="error-client" id="error_description"><c:out value="${errors.description}"/></span>
                                </div>

                                <%-- Hàng 3: Loại giảm giá & Mức giảm --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Loại giảm giá</label>
                                        <select class="input-modern" name="discountType" id="discountTypeSelect">
                                            <option value="PERCENT" ${(voucher.discountType == 'PERCENT' || param.discountType == 'PERCENT') ? 'selected' : ''}>Theo phần trăm (%)</option>
                                            <option value="FIXED_AMOUNT" ${(voucher.discountType == 'FIXED_AMOUNT' || param.discountType == 'FIXED_AMOUNT') ? 'selected' : ''}>Số tiền cố định (VNĐ)</option>
                                        </select>
                                        <span class="error-client" id="error_discountType"><c:out value="${errors.discountType}"/></span>
                                    </div>
                                    <div class="form-group flex-1">
                                        <label class="label-style" id="labelDiscountValue">Mức giảm (%)</label>
                                        <input type="number" name="discountValue" class="input-modern" step="0.01"
                                               value="${voucher != null ? voucher.discountValue : param.discountValue}" placeholder="Ví dụ: 10">
                                        <span class="error-client" id="error_discountValue"><c:out value="${errors.discountValue}"/></span>
                                    </div>
                                </div>

                                <%-- Hàng 4: Điều kiện đơn hàng --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Đơn tối thiểu (VNĐ)</label>
                                        <input type="number" name="minOrderValue" class="input-modern"
                                               value="${voucher != null ? voucher.minOrderValue : param.minOrderValue}" placeholder="0 hoặc để trống">
                                        <span class="error-client" id="error_minOrderValue"><c:out value="${errors.minOrderValue}"/></span>
                                    </div>
                                    <div class="form-group flex-1" id="maxDiscountContainer">
                                        <label class="label-style">Mức giảm tối đa (VNĐ)</label>
                                        <input type="number" name="maxDiscountValue" class="input-modern"
                                               value="${voucher != null ? voucher.maxDiscountValue : param.maxDiscountValue}" placeholder="Để trống nếu không giới hạn">
                                        <span class="error-client" id="error_maxDiscountValue"><c:out value="${errors.maxDiscountValue}"/></span>
                                    </div>
                                </div>

                                <%-- Hàng 5: Thời gian áp dụng --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Ngày bắt đầu</label>
                                        <input type="datetime-local" name="startDate" class="input-modern"
                                               value="<fmt:formatDate value="${voucher.startDate}" pattern="yyyy-MM-dd'T'HH:mm" />">
                                        <span class="error-client" id="error_startDate"><c:out value="${errors.startDate}"/></span>
                                    </div>
                                    <div class="form-group flex-1">
                                        <label class="label-style">Ngày kết thúc</label>
                                        <input type="datetime-local" name="endDate" class="input-modern"
                                               value="<fmt:formatDate value="${voucher.endDate}" pattern="yyyy-MM-dd'T'HH:mm" />">
                                        <span class="error-client" id="error_endDate"><c:out value="${errors.endDate}"/></span>
                                    </div>
                                </div>

                                <%-- Hàng 6: Giới hạn lượt dùng & Trạng thái --%>
                                <div class="form-row mt-3">
                                    <div class="form-group flex-1">
                                        <label class="label-style">Tổng lượt sử dụng</label>
                                        <input type="number" name="usageLimit" class="input-modern"
                                               value="${voucher != null ? voucher.usageLimit : param.usageLimit}" placeholder="Để trống nếu không giới hạn">
                                        <span class="error-client" id="error_usageLimit"><c:out value="${errors.usageLimit}"/></span>
                                    </div>
                                    <div class="form-group flex-1">
                                        <label class="label-style">Trạng thái hiển thị</label>
                                        <select class="input-modern" name="status">
                                            <option value="INACTIVE" ${(voucher.status == 'INACTIVE' || param.status == 'INACTIVE' ) ? 'selected' : ''}>
                                                Vô hiệu hóa
                                            </option>
                                            <option value="ACTIVE" ${(voucher.status == 'ACTIVE' || param.status == 'ACTIVE' )? 'selected' : ''}>
                                                Hoạt động
                                            </option>
                                        </select>
                                        <span class="error-client" id="error_status"><c:out value="${errors.status}"/></span>
                                    </div>
                                </div>

                                <%-- Row Readonly: Chỉ hiện khi cập nhật --%>
                                <c:if test="${voucher != null and voucher.id > 0}">
                                    <div class="form-row mt-3">
                                        <div class="form-group flex-1">
                                            <label class="label-style">Đã sử dụng</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${voucher.usedCount} lượt" readonly>
                                        </div>
                                        <div class="form-group flex-1">
                                            <label class="label-style">Ngày tạo</label>
                                            <input type="text" class="input-modern readonly-field"
                                                   value="${voucher.createdAt}" readonly>
                                        </div>
                                    </div>
                                </c:if>

                                <%-- Nút chức năng --%>
                                <div class="form-actions mt-4">
                                    <div style="display: flex; gap: 10px; flex: 1;">
                                        <a href="admin/vouchers" class="btn-cancel-modern"
                                           style="text-decoration: none;">
                                            Hủy bỏ
                                        </a>

                                        <button type="submit" class="btn-submit-modern w-100">
                                            <i class="fa-solid fa-floppy-disk"></i>
                                            <c:out value="${(not empty voucher and voucher.id > 0) ? 'Cập nhật' : 'Thêm voucher'}"/>
                                        </button>
                                    </div>

                                    <c:if test="${voucher != null and voucher.id > 0}">
                                        <button type="button" class="btn-delete-modern"
                                                onclick="setupConfirmModal({action: 'archive', ids: ${voucher.id}, url: 'admin/voucher/action', isBulk: false})">
                                            <i class="fa-solid fa-trash-can"></i>
                                            Xóa voucher
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
<jsp:include page="/views/components/modal-confirm.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<%-- Javascript --%>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="assets/javascript/validation/base-validator.js?v=<%=System.currentTimeMillis()%>"></script>
<%-- Javascript Validation--%>
<script src="assets/javascript/validation/admin/voucher-form-validation.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>