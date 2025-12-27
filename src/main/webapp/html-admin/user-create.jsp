<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo mới người dùng</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.4">
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/course-edit.css">
</head>
<body>
<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">

                <div class="grid__column-2 container-1">
                    <div class="container-1__logo">
                        <i class="fa-solid fa-graduation-cap"></i>
                        <span>Softskill</span>
                    </div>
                    <div class="container-1__menu">
                        <ul>
                            <li>
                                <a href="admin/dashboard">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/users">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/courses">
                                    <div class="menu-item__student student-list">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/lessons">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/tags">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/categories">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/orders">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/payment-methods">
                                    <div class="menu-item__student">
                                        <span class="container-1__menu-items menu-item__order">

                                            <i class="fa-solid fa-credit-card"></i>
                                            <span>Kiểu thanh toán</span>
                                        </span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <div class="log-out">
                            <a href="html-authentication/sign-in.jsp">
                                <div class="log-out__container">
                                    <div class="log-out__content">
                                        Thoát
                                    </div>
                                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="grid__column-10 container-2">
                    <div class="container-2__header-modern">
                        <h2 class="header__title-modern">Thêm người dùng mới</h2>
                        <a href="admin/users" class="btn-back">
                            <i class="fa-solid fa-arrow-left"></i> Danh sách
                        </a>
                    </div>

                    <div class="user-form-container">
                        <form action="admin/users/create" method="post" enctype="multipart/form-data">

                            <div class="form-row">
                                <div class="form-column-8">
                                    <div class="form-group">
                                        <label class="label-style">Họ và tên đệm</label>
                                        <input name="firstName" type="text" class="input-modern"
                                               placeholder="VD: Nguyễn Văn" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="label-style">Tên</label>
                                        <input name="lastName" type="text" class="input-modern" placeholder="VD: An"
                                               required>
                                    </div>
                                </div>
                                <div class="form-column-4">
                                    <div class="avatar-upload-box">
                                        <label class="label-style">Avatar</label>
                                        <div class="upload-wrapper">
                                            <i class="fa-solid fa-cloud-arrow-up"></i>
                                            <span>Tải ảnh lên</span>
                                            <input name="avatar" type="file" class="file-hidden">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group flex-1">
                                    <label class="label-style">Username</label>
                                    <input name="username" type="text" class="input-modern" placeholder="username123"
                                           required>
                                </div>
                                <div class="form-group flex-1">
                                    <label class="label-style">Email</label>
                                    <input name="email" type="email" class="input-modern"
                                           placeholder="example@gmail.com" required>
                                </div>
                                <div class="form-group flex-1">
                                    <label class="label-style">Mật khẩu</label>
                                    <input name="password" type="password" class="input-modern" placeholder="••••••••"
                                           required>
                                </div>
                            </div>

                            <div class="form-row mt-4">
                                <div class="form-group flex-1">
                                    <label class="label-style">Số điện thoại</label>
                                    <input name="phone" type="text" class="input-modern" placeholder="0987xxxxxx">
                                </div>
                                <div class="form-group flex-1">
                                    <label class="label-style">Vai trò</label>
                                    <select name="role" class="input-modern select-custom">
                                        <option value="user">Người dùng (User)</option>
                                        <option value="admin">Quản trị viên (Admin)</option>
                                    </select>
                                </div>
                                <div class="form-group flex-1">
                                    <label class="label-style">Trạng thái</label>
                                    <select name="status" class="input-modern select-custom">
                                        <option value="ACTIVE">Hoạt động</option>
                                        <option value="BANNED">Bị khóa</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-actions mt-5">
                                <button type="submit" class="btn-submit-modern">
                                    <i class="fa-solid fa-circle-check"></i> Lưu người dùng
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>