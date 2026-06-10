<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Kiểm định chứng chỉ</title>
    <base href="${pageContext.request.contextPath}/">
    <!-- Base CSS -->
    <link rel="stylesheet" href="assets/css/base/base.css?v=${applicationScope.assetVersion}">

    <!-- Page CSS -->
    <link rel="stylesheet" href="assets/css/verify-certificate.css?v=${applicationScope.assetVersion}">

    <!-- Normalize -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=${applicationScope.assetVersion}">

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>

    <div class="web__container">
        <div class="grid">
            <div class="verify-page mt-4 mb-5">
                <!-- HEADER -->
                <div class="verify-header">
                    <h1 class="verify-title">
                        Kiểm định chứng chỉ
                    </h1>
                    <p class="verify-subtitle">
                        Xác minh tính hợp lệ của chứng chỉ được cấp bởi hệ thống đào tạo.
                    </p>
                </div>

                <!-- SEARCH -->
                <div class="verify-search-card">
                    <div class="verify-search-form">
                        <input type="text" id="certificateCode" class="verify-search-input"
                               placeholder="Nhập mã chứng chỉ..." name="certificateCode"
                               value="${param.certificateCode != null ? '' : param.certificateCode}">
                        <button type="button" class="verify-search-button">
                            <i class="fa-solid fa-magnifying-glass"></i>
                            <span>Xác minh</span>
                        </button>
                    </div>
                </div>

                <!-- RESULT -->
                <div class="verify-result">
                    <!-- EMPTY -->
                    <div id="emptyState">
                        <div class="verify-card">
                            <i class="fa-solid fa-certificate verify-icon"></i>
                            <h3>
                                Chưa có dữ liệu
                            </h3>
                            <p>
                                Vui lòng nhập mã chứng chỉ để bắt đầu xác minh.
                            </p>
                        </div>
                    </div>
                    <!-- SUCCESS -->
                    <div id="successState" style="display:none">
                        <div class="verify-card verify-success">
                            <div class="verify-status">
                                <i class="fa-solid fa-circle-check"></i>
                                <span>
                                    Chứng chỉ hợp lệ
                                </span>
                            </div>

                            <div class="verify-info">

                                <div class="info-row">
                                    <div class="info-label">Mã chứng chỉ</div>
                                    <div class="info-content" id="cert-code"></div>
                                </div>

                                <div class="info-row">
                                    <div class="info-label">Học viên</div>
                                    <div class="info-content" id="cert-student"></div>
                                </div>

                                <div class="info-row">
                                    <div class="info-label">Khóa học</div>
                                    <div class="info-content" id="cert-course"></div>
                                </div>

<%--                                <div class="info-row">--%>
<%--                                    <div class="info-label">Giảng viên</div>--%>
<%--                                    <div class="info-content" id="cert-lecturer"></div>--%>
<%--                                </div>--%>

<%--                                <div class="info-row">--%>
<%--                                    <div class="info-label">Ngày hoàn thành</div>--%>
<%--                                    <div class="info-content" id="cert-completion-date"></div>--%>
<%--                                </div>--%>

                                <div class="info-row">
                                    <div class="info-label">Ngày cấp</div>
                                    <div class="info-content" id="cert-issue-date"></div>
                                </div>

<%--                                <div class="info-row">--%>
<%--                                    <div class="info-label">Điểm hoàn thành</div>--%>
<%--                                    <div class="info-content" id="cert-score"></div>--%>
<%--                                </div>--%>
                            </div>
<%--                            <div class="verify-actions">--%>
<%--                                <a href="#" class="btn-certificate">Xem chứng chỉ</a>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <!-- ERROR -->
                    <div id="errorState" style="display:none">
                        <div class="verify-card verify-error">
                            <i class="fa-solid fa-circle-xmark"></i>
                            <h3>
                                Không tìm thấy chứng chỉ
                            </h3>
                            <p id="error-message">
                                Mã chứng chỉ không tồn tại hoặc đã bị thu hồi.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/views/layouts/footer.jsp"/>
</div>

<script src="assets/javascript/security/security.js?v=${applicationScope.assetVersion}"></script>
<script src="assets/javascript/features/certificate/verify-certificate.js?v=${applicationScope.assetVersion}"></script>

</body>
</html>