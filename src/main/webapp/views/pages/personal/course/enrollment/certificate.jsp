<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
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
                                <h2 class="brand-name">HỌC VIỆN E-LEARNING</h2>
                                <p class="brand-sub">CHỨNG NHẬN CHÍNH THỨC</p>
                            </div>

                            <!-- Badge -->
                            <div class="cert-badge">
                                <i class="fa-solid fa-award"></i>
                            </div>

                            <!-- Content -->
                            <div class="cert-content">
                                <p class="cert-intro">CHỨNG NHẬN RẰNG</p>
                                <!-- Tên học viên lấy từ DB -->
                                <h1 class="cert-student-name">${not empty certificateDetail ? certificateDetail.firstName.concat(' ').concat(certificateDetail.lastName)  : 'Nguyễn Văn A'}</h1>

                                <p class="cert-desc">đã hoàn thành xuất sắc chương trình học và bài kiểm tra của khóa học</p>
                                <!-- Tên khóa học -->
                                <h2 class="cert-course-name">${not empty certificateDetail.courseTitle ? certificateDetail.courseTitle : 'Kỹ năng Giao tiếp Chuyên nghiệp'}</h2>
                                <p class="cert-platform">Được chứng nhận thông qua nền tảng đào tạo E-Learning Workspace</p>
                            </div>

                            <!-- Footer của chứng chỉ (Chữ ký, ngày tháng, ID) -->
                            <div class="cert-footer">
                                <div class="footer-item">
                                    <span class="footer-label">NGÀY CẤP</span>
                                    <span class="footer-value">
                                        <c:if test="${not empty certificateDetail.issueDate}">
                                            <fmt:formatDate
                                                    value="${certificateDetail.issueDate}"
                                                    pattern="yyyy-MM-dd" />
                                        </c:if>
                                    </span>
                                </div>
                                <div class="footer-item signature-box">
                                    <img src="assets/image/signature.png" alt="Chữ ký" class="signature-img">
                                    <span class="footer-label">GIÁM ĐỐC ĐÀO TẠO</span>
                                </div>
                                <div class="footer-item align-right">
                                    <span class="footer-label">MÃ CHỨNG CHỈ</span>
                                    <span class="footer-value">${certificateDetail.certificateCode}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cert-ledger-note">
                        <i class="fa-solid fa-shield-check"></i> Chứng chỉ này đã được xác thực và lưu trữ vĩnh viễn trên hệ thống của Học viện E-Learning.
                    </div>
                </div>
            </div>

            <!-- CỘT PHẢI: BẢNG ĐIỀU KHIỂN & HÀNH ĐỘNG -->
            <div class="grid__column-4 column2">
                <!-- Box Quản lý chứng chỉ -->
                <div class="action-panel-card">
                    <h3 class="text-2xl bold mb-2">Quản lý Chứng chỉ</h3>
                    <p class="text-base text-gray mb-4 ">Xuất chứng chỉ ở độ phân giải cao để in ấn hoặc chia sẻ trực tuyến.</p>

                    <!-- Nút chức năng -->
                    <div class="cert-actions mb-4">
                        <a href="student/certificate/download?courseId=${certificateDetail.courseId}" class="btn btn-primary w-100 mb-3">
                            <i class="fa-solid fa-file-pdf"></i> Tải Bản PDF
                        </a>

                        <div class="action-row">
                            <button class="btn btn-secondary flex-1">
                                <i class="fa-regular fa-image"></i> Lưu Ảnh (PNG)
                            </button>
                            <button class="btn btn-linkedin flex-1">
                                <i class="fa-brands fa-linkedin"></i> Thêm vào LinkedIn
                            </button>
                        </div>
                    </div>

                    <hr class="card-divider">

                    <!-- Thông tin chi tiết -->
                    <ul class="cert-info-list">
                        <li>
                            <span class="info-label">Học viên</span>
                            <span class="info-value bold">${not empty certificateDetail ? certificateDetail.firstName.concat(' ').concat(certificateDetail.lastName) : 'Nguyễn Văn A'}</span>
                        </li>
                        <li>
                            <span class="info-label">Thời lượng học</span>
                            <span class="info-value">${certificateDetail.durationText}</span>
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