<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chứng nhận hoàn thành khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/course/course-content.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

    <link rel="stylesheet" href="assets/css/course/certificate.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>

<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>

    <div class="web__container">

        <div class="grid layout mt-4 mb-4">
            <!-- CỘT TRÁI: BẢN PREVIEW CHỨNG CHỈ -->
            <div class="grid__column-8 column1">
                <div class="certificate-preview-container">
                    <div class="cert-paper">
                        <div class="cert-border">
                            <!-- Logo / Brand -->
                            <div class="cert-brand">
                                <h2 class="brand-name">E-LEARNING ACADEMY</h2>
                                <p class="brand-sub">OFFICIAL CERTIFICATION</p>
                            </div>

                            <!-- Badge -->
                            <div class="cert-badge">
                                <i class="fa-solid fa-award"></i>
                            </div>

                            <!-- Content -->
                            <div class="cert-content">
                                <p class="cert-intro">THIS IS TO CERTIFY THAT</p>
                                <!-- Tên học viên lấy từ DB -->
                                <h1 class="cert-student-name">${not empty sessionScope.user.lastName ? sessionScope.user.lastName += ' ' += sessionScope.user.firstName : 'Nguyễn Văn A'}</h1>

                                <p class="cert-desc">has successfully mastered the curriculum and examinations for</p>
                                <!-- Tên khóa học -->
                                <h2 class="cert-course-name">${not empty enrollmentDetail.title ? enrollmentDetail.title : 'Mastering Executive Communication'}</h2>
                                <p class="cert-platform">Achieved with distinction through the E-Learning Workspace platform</p>
                            </div>

                            <!-- Footer của chứng chỉ (Chữ ký, ngày tháng, ID) -->
                            <div class="cert-footer">
                                <div class="footer-item">
                                    <span class="footer-label">DATE OF ISSUE</span>
                                    <span class="footer-value">
                                        <fmt:formatDate value="${enrollmentDetail.completedAt}" pattern="MMMM dd, yyyy" />
                                    </span>
                                </div>
                                <div class="footer-item signature-box">
                                    <img src="assets/image/signature.png" alt="Signature" class="signature-img">
                                    <span class="footer-label">REGISTRAR</span>
                                </div>
                                <div class="footer-item align-right">
                                    <span class="footer-label">CREDENTIAL ID</span>
                                    <span class="footer-value">AW-${enrollmentDetail.courseId}X-${enrollmentDetail.orderId}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cert-ledger-note">
                        <i class="fa-solid fa-shield-check"></i> This certificate is verified and permanently recorded on the E-Learning Academy ledger.
                    </div>
                </div>
            </div>

            <!-- CỘT PHẢI: BẢNG ĐIỀU KHIỂN & HÀNH ĐỘNG -->
            <div class="grid__column-4 column2">
                <!-- Box Quản lý chứng chỉ -->
                <div class="action-panel-card">
                    <h3 class="text-2xl bold mb-2">Manage Credential</h3>
                    <p class="text-base text-gray mb-4 ">Export your certificate in high resolution for print or digital display.</p>

                    <!-- Nút chức năng -->
                    <div class="cert-actions mb-4">
                        <a href="student/certificate/download?courseId=${enrollmentDetail.courseId}" class="btn btn-primary w-100 mb-3">
                            <i class="fa-solid fa-file-pdf"></i> Download Certificate (PDF)
                        </a>

                        <div class="action-row">
                            <button class="btn btn-secondary flex-1">
                                <i class="fa-regular fa-image"></i> Image (PNG)
                            </button>
                            <button class="btn btn-linkedin flex-1">
                                <i class="fa-brands fa-linkedin"></i> LinkedIn
                            </button>
                        </div>
                    </div>

                    <hr class="card-divider">

                    <!-- Thông tin chi tiết -->
                    <ul class="cert-info-list">
                        <li>
                            <span class="info-label">Học viên</span>
                            <span class="info-value bold">${not empty sessionScope.user.lastName ? sessionScope.user.lastName += ' ' += sessionScope.user.firstName : 'Nguyễn Văn A'}</span>
                        </li>
                        <li>
                            <span class="info-label">Course Credit</span>
                            <span class="info-value">${enrollmentDetail.durationText}</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/views/layouts/footer.jsp"/>
</div>
</body>
</html>