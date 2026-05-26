<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chính sách quyền riêng tư - SoftSkill</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/privacy.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

</head>
<body>
<div class="web">
    <jsp:include page="/views/layouts/header.jsp"/>
    <div class="web__container">
        <div class="privacy__container">
            <div class="grid">
                <h1 class="privacy__title text-big-title">Chính sách quyền riêng tư – SoftSkill</h1>

                <div class="privacy__section">
                    <h2>1. Giới thiệu</h2>
                    <p>Ứng dụng SoftSkill được phát triển bởi nhóm quản lý tại miền <em>wabi.id.vn</em>.
                        Chúng tôi cam kết bảo vệ quyền riêng tư và dữ liệu cá nhân của người dùng.</p>
                </div>

                <div class="privacy__section">
                    <h2>2. Dữ liệu thu thập</h2>
                    <ul>
                        <li>
                            <strong style="font-size: var(--text-xl)">Thông tin tài khoản</strong>: email, tên hiển thị, ảnh đại diện.
                        </li>
                        <li>
                            <strong style="font-size: var(--text-xl)">Dữ liệu sử dụng</strong>: hành vi truy cập, thời gian sử dụng.
                        </li>
                        <li>
                            <strong style="font-size: var(--text-xl)">Dữ liệu thiết bị</strong>: loại thiết bị, hệ điều hành, địa chỉ IP.
                        </li>
                    </ul>
                </div>

                <div class="privacy__section">
                    <h2>3. Mục đích sử dụng</h2>
                    <p>Dữ liệu được dùng để cải thiện trải nghiệm người dùng, phân tích hiệu suất ứng dụng,
                        và gửi thông báo hoặc hỗ trợ kỹ thuật khi cần thiết.</p>
                </div>

                <div class="privacy__section">
                    <h2>4. Chia sẻ dữ liệu</h2>
                    <p>Chúng tôi không bán hoặc chia sẻ dữ liệu cá nhân cho bên thứ ba, trừ khi có sự đồng ý
                        của người dùng hoặc theo yêu cầu của pháp luật Việt Nam.</p>
                </div>

                <div class="privacy__section">
                    <h2>5. Bảo mật</h2>
                    <p>Chúng tôi áp dụng các biện pháp kỹ thuật để bảo vệ dữ liệu khỏi truy cập trái phép,
                        mất mát hoặc thay đổi.</p>
                </div>

                <div class="privacy__section">
                    <h2>6. Quyền của người dùng</h2>
                    <p>Người dùng có quyền truy cập, chỉnh sửa hoặc xóa dữ liệu cá nhân.
                        Liên hệ qua email <strong class="text-big">minh6112005@gmail.com</strong> để yêu cầu hỗ trợ.</p>
                </div>

                <div class="privacy__section">
                    <h2>7. Liên hệ</h2>
                    <p>Email: <strong class="text-big">minh6112005@gmail.com</strong></p>
                </div>

                <div class="privacy__section">
                    <h2>8. Cập nhật</h2>
                    <p>Chính sách này có thể được cập nhật định kỳ. Phiên bản mới nhất sẽ được đăng tại:
                        <a class="fix-privacy" href="https://wabi.id.vn/privacy">https://wabi.id.vn/privacy</a></p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/views/layouts/footer.jsp"/>
</div>
</body>
</html>
