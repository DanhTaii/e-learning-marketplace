<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Chi tiết Chứng chỉ - Admin</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/course/course-content.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-course-admin.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

    <link rel="stylesheet" href="assets/css/course/certificate.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">

    <link rel="stylesheet" href="assets/css/admin/pages/certificate/certificate-detail.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/confirm-modal.css?v=<%=System.currentTimeMillis()%>">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <jsp:include page="/views/layouts/admin/header-cert-admin.jsp"/>
            <div class="layout mt-4 mb-4">

                <!-- CỘT TRÁI: BẢN PREVIEW CHỨNG CHỈ -->
                <div class="grid__column-8 column1">
                    <div class="certificate-preview-container">
                        <div class="cert-paper" id="certificate-preview">
                            <div class="cert-border">
                                <div class="cert-brand">
                                    <h2 class="brand-name">HỌC VIỆN E-LEARNING</h2>
                                    <p class="brand-sub">CHỨNG NHẬN CHÍNH THỨC</p>
                                </div>
                                <div class="cert-badge">
                                    <i class="fa-solid fa-award"></i>
                                </div>
                                <div class="cert-content">
                                    <p class="cert-intro">CHỨNG NHẬN RẰNG</p>
                                    <h1 class="cert-student-name"><c:out value="${not empty certificateDetail ? certificateDetail.firstName.concat(' ').concat(certificateDetail.lastName)  : 'Nguyễn Văn A'}"/></h1>
                                    <p class="cert-desc">đã hoàn thành xuất sắc chương trình học và bài kiểm tra của
                                        khóa học</p>
                                    <h2 class="cert-course-name"><c:out value="${not empty certificateDetail.courseTitle ? certificateDetail.courseTitle : 'Kỹ năng Giao tiếp Chuyên nghiệp'}"/></h2>
                                    <p class="cert-platform">Được chứng nhận thông qua nền tảng đào tạo E-Learning
                                        Workspace</p>
                                </div>
                                <div class="cert-footer">
                                    <div class="footer-item">
                                        <span class="footer-label">NGÀY CẤP</span>
                                        <span class="footer-value">
                                        <c:if test="${not empty certificateDetail.issueDate}">
                                            <fmt:formatDate value="${certificateDetail.issueDate}"
                                                            pattern="yyyy-MM-dd"/>
                                        </c:if>
                                    </span>
                                    </div>
                                    <div class="footer-item signature-box">
                                        <img src="assets/image/signature.png" alt="Chữ ký" class="signature-img">
                                        <span class="footer-label">GIÁM ĐỐC ĐÀO TẠO</span>
                                    </div>
                                    <div class="footer-item align-right">
                                        <span class="footer-label">MÃ CHỨNG CHỈ</span>
                                        <span class="footer-value"><c:out value="${certificateDetail.certificateCode}"/></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="cert-ledger-note">
                            <i class="fa-solid fa-shield-check"></i> Chứng chỉ này đã được xác thực và lưu trữ vĩnh viễn
                            trên hệ thống của Học viện E-Learning.
                        </div>
                    </div>
                </div>

                <!-- CỘT PHẢI: BẢNG ĐIỀU KHIỂN CỦA ADMIN -->
                <div class="grid__column-4 column2">
                    <div class="action-panel-card">
                        <h3 class="text-2xl bold mb-3">Bảng điều khiển</h3>

                        <!-- Trạng thái hiện tại -->
                        <div class="mb-4">
                            <span class="info-label">Trạng thái</span>
                            <c:choose>
                                <c:when test="${certificateDetail.status eq 'ACTIVE'}">
                                    <span class="status-badge valid">Hợp lệ</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status-badge revoked">Đã thu hồi</span>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- Nút chức năng của Admin -->
                        <div class="cert-actions mb-4">
                            <c:choose>
                                <c:when test="${certificateDetail.status eq 'ACTIVE'}">
                                    <button type="button" class="btn btn-danger w-100" onclick="setupConfirmModal({action: 'revoke_cert', ids: ${certificateDetail.id}, url: 'admin/certificate/action', isBulk: false})">
                                        <i class="fa-solid fa-lock"></i> Thu hồi Chứng chỉ
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-success w-100" onclick="setupConfirmModal({action: 'reinstate_cert', ids: ${certificateDetail.id}, url: 'admin/certificate/action', isBulk: false})">
                                        <i class="fa-solid fa-lock-open"></i> Cấp lại Chứng chỉ
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <hr class="card-divider">

                        <!-- Link xác thực -->
                        <div class="mb-4">
                            <label class="info-label mb-2" style="display: block;">Link xác thực</label>
                            <div class="verification-link-box">
                                <input type="text" id="verificationLink" class="verification-link-input"
                                       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/verify/${certificateDetail.certificateCode}"
                                       readonly>
                                <button class="btn-copy-link" onclick="copyLink()">
                                    <i class="fa-regular fa-copy"></i>
                                </button>
                            </div>
                        </div>

                        <hr class="card-divider">

                        <!-- Thông tin chi tiết -->
                        <ul class="cert-info-list">
                            <li>
                                <span class="info-label">Học viên</span>
                                <span class="info-value bold"><c:out value="${not empty certificateDetail ? certificateDetail.firstName.concat(' ').concat(certificateDetail.lastName) : 'Nguyễn Văn A'}"/></span>
                            </li>
                            <li>
                                <span class="info-label">Ngày bắt đầu</span>
                                <span class="info-value">
                                <fmt:formatDate value="${certificateDetail.enrollmentDate}" pattern="dd/MM/yyyy"/>
                            </span>
                            </li>
                            <li>
                                <span class="info-label">Ngày hoàn thành</span>
                                <span class="info-value">
                                <fmt:formatDate value="${certificateDetail.completionDate}" pattern="dd/MM/yyyy"/>
                            </span>
                            </li>
                            <li>
                                <span class="info-label">Thời lượng học</span>
                                <span class="info-value"><c:out value="${certificateDetail.durationText}"/></span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>


        </div>

    </div>

</div>
<jsp:include page="/views/components/toast.jsp"/>
<jsp:include page="/views/components/modal-confirm.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>

</body>
</html>