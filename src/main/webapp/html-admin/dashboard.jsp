<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/dashboard.css">

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
                                    <div class="menu-item__student student-list">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/user-management">
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
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/lesson">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/tag">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/category">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/order">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/payment-method">
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
                            <a href="../html-authentication/sign-in.jsp">
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
                            <div class="header__title">Dashboard</div>
                        </div>

                        <div class="list-card">
                            <ul>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div>Doanh thu</div>
                                            <div class="card-icon"><i class="fa-solid fa-money-check-dollar"></i></i>
                                            </div>
                                        </div>
                                        <div class="card-content__number">250+</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div class="card-title">Đơn hàng</div>
                                            <div class="card-icon"><i class="fa-solid fa-cart-shopping"></i></i></div>
                                        </div>
                                        <div class="card-content__number">250+</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div>Người dùng</div>
                                            <div class="card-icon"><i class="fa-solid fa-user"></i></div>
                                        </div>
                                        <div class="card-content__number">250+</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div>Khóa học</div>
                                            <div class="card-icon"><i class="fa-solid fa-tags"></i></div>
                                        </div>
                                        <div class="card-content__number">250+</div>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="visualization">
                            <div class="chart">
                                <div class="chart__title">DOANH THU 7 NGÀY QUA</div>
                                <div class="bar-chart">
                                    <div class="bar-chart__grid">
                                        <div class="vertical-axis"></div>
                                        <div class="horizontal-line"></div>
                                        <div class="horizontal-line"></div>
                                        <div class="horizontal-line"></div>
                                        <div class="horizontal-line"></div>
                                        <div class="horizontal-line"></div>
                                    </div>

                                    <div class="bar-chart__bar">
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 70%"><span>1.8tr</span></div>
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 90%"><span>1.8tr</span></div>
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 60%"><span>1.8tr</span></div>
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 10%"><span>1.8tr</span></div>
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 30%"><span>1.8tr</span></div>
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 40%"><span>1.8tr</span></div>
                                        <div class="column-chart column-chart__1" data-label="6/11" style="height: 80%"><span>3.6tr</span></div>
                                    </div>
                                </div>
                            </div>

                            <div class="top-course">
                                <div class="top-course__title">
                                    <center>TOP 10 KHÓA HỌC BÁN CHẠY NHẤT</center>
                                </div>
                                <table>
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên khóa học</th>
                                        <th>Số lượng học viên</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1.</td>
                                        <td>Kỹ năng giao tiếp</td>
                                        <td>2100</td>
                                    </tr>
                                    <tr>
                                        <td>2.</td>
                                        <td>Lãnh đạo bản thân</td>
                                        <td>1800</td>
                                    </tr>
                                    <tr>
                                        <td>3.</td>
                                        <td>Quản lý thời gian</td>
                                        <td>3600</td>
                                    </tr>
                                    <tr>
                                        <td>4.</td>
                                        <td>Khả năng thuyết trình</td>
                                        <td>3600</td>
                                    </tr>
                                    <tr>
                                        <td>5.</td>
                                        <td>Làm việc nhóm</td>
                                        <td>3600</td>
                                    </tr>
                                    <tr>
                                        <td>6.</td>
                                        <td>Vượt lên chính mình</td>
                                        <td>3600</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>