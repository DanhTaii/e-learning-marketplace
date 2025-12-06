<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kiểu thanh toán </title>
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
                                <a href="./dashboard.jsp">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./users-management.jsp">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./courses-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./lesson-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./tag-management.jsp">
                                    <div class="menu-item__student ">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./category-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./order-management.jsp">
                                    <div class="menu-item__student">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                                <li>
                                    <a href="./payment-method-management.jsp">
                                        <div class="menu-item__student student-list">
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
                            <div class="header__title">Kiểu thanh toán</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo phương thức thanh toán</div>
                            <div class="container-2__create">
                                <div class="create__selection">
                                    <div class="create__selection-input">
                                        <div class="create__selection-items">
                                            <div class="filter__selection-title filter__item-name">Tên phương
                                                thức:
                                            </div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="create__selection-items">
                                            <div class="filter__selection-title filter__item-name">Icon URL:</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                    </div>
                                    <div class="create__btn-create">
                                        <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                    </div>
                                </div>
                            </div>
                            <div class="title__admin">Tất cả phương thức thanh toán</div>
                            <div class="container-2__filter">
                                <div class="filter__selection">
                                    <div class="filter__selection-input">
                                        <div class="filter__selection-items filter__selection-name">
                                            <div class="filter__selection-title filter__item-name">Tên phương thức:
                                            </div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>

                                    </div>

                                    <div class="filter__button-search">
                                        <button class="button dark-button" type="submit">
                                            <i class="fa-solid fa-magnifying-glass"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="container-2__list-student">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Tên phương thức</th>
                                        <th>Icon URL</th>
                                        <th>Hoạt động</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                Momo
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                https://
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status course-row__font-content course-row__status-public">
                                                Yes
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                VNPay
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                https://
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status course-row__font-content course-row__status-public">
                                                Yes
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                GooglePay
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                https://
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status course-row__font-content course-row__status-public">
                                                Yes
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                ZaloPay
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                https://
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status course-row__font-content course-row__status-failed">
                                                No
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                        </td>
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