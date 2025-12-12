<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tạo mới khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="../assets/css-admin/admin.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="../assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="../assets/css/base.css">
    <link rel="stylesheet" href="../assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="../assets/css-admin/course-edit.css">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-2 container-1">
                    <div class="container-1__title">Softskill</div>
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
                    <div class="container-2__header"></div>
                    <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">Tạo mới khóa học</div>
                            <a href="./courses-management.jsp">
                                <div class="admin-create__buttons dark-button">
                                    <button type="button" class="dark-button">
                                        <i class="fa-solid fa-backward-step"></i>Trở về
                                    </button>
                                </div>
                            </a>
                        </div>

                        <div class="container-2__form">
                            <form action="" class="">
                                <!--                                Phần Tiêu đề và tiêu đề phụ kèm với việc gắn ảnh-->
                                <div class="course-create__section-1">
                                    <div class="course-create__title">
                                        <div class="course-create__block-input">
                                            <div class="course-create__title-style">Tên khóa học</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="course-create__block-input">
                                            <div class="course-create__title-style">Phụ đề</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                    </div>
                                    <div class="course-create__img">
                                        <div class="course-create__title-style">Ảnh khóa học</div>
                                        <input placeholder="" type="file" class="file__input ">
                                    </div>
                                </div>

                                <!--                                Phần Giá cả, mức độ-->
                                <div class="course-create__section-2">
                                    <div class="course-create__block-input-short">
                                        <div class="course-create__title-style">Giá</div>
                                        <input placeholder="" type="text" class="admin-input__short">
                                    </div>
                                    <div class="course-create__block-input-short">
                                        <div class="course-create__title-style">Giảm giá</div>
                                        <input placeholder="" type="text" class="admin-input__short">
                                    </div>
                                    <div class="course-create__block-input">
                                        <div class="course-create__title-style">Mức độ</div>
                                        <select name="Level" class="combobox admin-input__short">
                                            <option value="">Người mới</option>
                                            <option value="1">Trung cấp</option>
                                            <option value="2">Cao cấp</option>
                                        </select>
                                    </div>
                                </div>
                                <!--                                Phần lựa chọn-->
                                <div class="course-create__section-3">
                                    <label class="checkbox-label">
                                        <input type="checkbox" name="is_public" class="checkbox__item"/>
                                        Công khai khóa học
                                    </label>
                                    <label class="checkbox-label">
                                        <input type="checkbox" name="is_featured " class="checkbox__item"/>
                                        Hiển thị nổi bật.
                                    </label>
                                </div>

                                <!--                                Phần Mục tiêu của khóa học-->
                                <div class="course-create__section-4">
                                    <div>
                                        <div class="course-create__title-style">Mục tiêu khóa học</div>
                                        <textarea name="mota" class="course-create__textarea"></textarea>
                                    </div>
                                </div>

                                <!--                                Phần mô tả của khóa học-->
                                <div class="course-create__section-5 space__section">
                                    <div>
                                        <div class="course-create__title-style">Mô tả khóa học</div>
                                        <textarea name="mota" class="course-create__textarea"></textarea>
                                    </div>
                                </div>

                                <div class="course-create__section-8-box">
                                    <div class="course-create__title-style">Liên kết khóa học với thẻ</div>
                                    <div class="course-create__section-8">

                                        <div class="tag-course">

                                            <div class="course-create__block-input-short">
                                                <div class="course-create__title-style">ID thẻ</div>
                                                <input placeholder="" type="text" class="admin-input__short">
                                            </div>
                                            <div class="course-create__block-input-short">
                                                <div class="course-create__title-style">ID khóa học</div>
                                                <input placeholder="" type="text" class="admin-input__short">
                                            </div>
                                            <div class="admin-create__buttons1">
                                                <button type="button" class="dark-button">
                                                    Liên kết
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="course-create__section-8-box">
                                    <div class="course-create__title-style">Liên kết khóa học với danh mục</div>
                                    <div class="course-create__section-8">

                                        <div class="tag-course">

                                            <div class="course-create__block-input-short">
                                                <div class="course-create__title-style">ID khóa học</div>
                                                <input placeholder="" type="text" class="admin-input__short">
                                            </div>
                                            <div class="course-create__block-input-short">
                                                <div class="course-create__title-style">ID danh mục</div>
                                                <input placeholder="" type="text" class="admin-input__short">
                                            </div>
                                            <div class="admin-create__buttons1">
                                                <button type="button" class="dark-button">
                                                    Liên kết
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                                <div class="section6-box">
                                    <div class="course-create__section-6 space__section">
                                        <div class="course-create__title-style">Bài học:</div>

                                        <div class="course-create__section-6-content">
                                            <div class="course-create__content-style">Tổng số bài học:</div>
                                            <div class="course-create__content-style">12</div>
                                        </div>

                                        <a href="lesson-management.jsp" class="turn-page">
                                            <div class="admin-create__buttons">
                                                <button type="button" class="dark-button">
                                                    Quản lý bài học
                                                </button>
                                            </div>
                                        </a>
                                    </div>
                                </div>

                                <div class="course-create__section-7 space__section">
                                    <div class="admin-create__buttons">
                                        <button type="submit" class="dark-button">
                                            <i class="fa-solid fa-floppy-disk"></i>Lưu
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>