<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <link rel="stylesheet" href="../assets/css-admin/admin.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="../assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="../assets/css/base.css">
    <link rel="stylesheet" href="../assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="../assets/css-admin/order-management.css">

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
                                    <div class="menu-item__student student-list">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="./payment-method-management.jsp">
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
                            <div class="header__title">Đơn hàng</div>
                        </div>
                        <div class="container-2__body">
                            <div class="container-2__filter">
                                <div class="filter__selection">
                                    <div class="filter__selection-input">
                                        <div class="filter__selection-items filter__selection-name">
                                            <div class="filter__selection-title filter__item-name">Mã:</div>
                                            <input placeholder="" type="text" class="admin-input__long" >
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Tên người dùng:</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Từ ngày:</div>
                                            <input placeholder="" type="datetime-local" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Trạng thái đơn hàng:</div>
                                            <select class="admin-input__short">
                                                <option class="text-medium">--Vui lòng chọn trạng thái--</option>
                                                <option class="text-medium">Paid</option>
                                                <option class="text-medium">Failed</option>
                                                <option class="text-medium">Pending</option>
                                            </select>
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
                                        <th>Mã đơn hàng</th>
                                        <th>Tên người dùng</th>
                                        <th>Thành tiền</th>
                                        <th>Kiểu thanh toán</th>
                                        <th>Trạng thái</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                ORD101
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                Danh Tai
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                400.000đ
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                Momo
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content course-row__status-public course-row__status">
                                                Paid
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__created course-row__font-content">April 13, 2022 – 4:24
                                                PM
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="#course-detail" class="button-de-mo">
                                                <span class="icon-action"><i class="fa-solid fa-eye"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                ORD102
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                Danh Tai
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                1.400.000đ
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                Momo
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status-public course-row__font-content course-row__status">
                                                Paid
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__created course-row__font-content">April 13, 2022 – 4:24
                                                PM
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="#course-detail" class="button-de-mo">
                                                <span class="icon-action"><i class="fa-solid fa-eye"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                ORD103
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                NgocMinh
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                600.000đ
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status-pending course-row__font-content course-row__status">
                                                Pending
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__created course-row__font-content">April 13, 2022 – 4:24
                                                PM
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="#course-detail" class="button-de-mo">
                                                <span class="icon-action"><i class="fa-solid fa-eye"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="course-row__title title course-row__style-text">
                                                ORD104
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                MinhLoc
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                400.000đ
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__font-content">
                                                <!--                                            Momo-->
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__status-failed course-row__font-content course-row__status">
                                                Failed
                                            </div>
                                        </td>
                                        <td>
                                            <div class="course-row__created course-row__font-content">April 13, 2022 – 4:24
                                                PM
                                            </div>
                                        </td>
                                        <td class="action__button">
                                            <a href="#course-detail" class="button-de-mo">
                                                <span class="icon-action"><i class="fa-solid fa-eye"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                            </a>
                                            <a href="">
                                                <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                            </a>
                                    </tr>
                                    </tbody>

                                </table>
                                <div id="course-detail" class="modal__course-detail">
                                    <div class="modal__course-content">
                                        <div class="course__header">
                                            <div class="course__title">Chi tiết đơn hàng</div>
                                            <div class="x__icon">
                                                <a href="#" class=""><i class="fa-solid fa-x"></i></a>
                                            </div>
                                        </div>

                                        <div class="course-body">
                                            <table>
                                                <thead>
                                                <tr>
                                                    <th>Mã đơn hàng</th>
                                                    <th>Tên khóa học</th>
                                                    <th>Giá</th>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <tr>
                                                    <td>ORD101</td>
                                                    <td>Khóa học A</td>
                                                    <td>200.00</td>
                                                </tr>
                                                <tr>
                                                    <td>ORD101</td>
                                                    <td>Khóa học B</td>
                                                    <td>50.00</td>
                                                </tr>
                                                <tr>
                                                    <td>ORD101</td>
                                                    <td>Khóa học C</td>
                                                    <td>0</td>
                                                </tr>
                                                <tr>
                                                    <td>ORD101</td>
                                                    <td>Khóa học D</td>
                                                    <td>150.00</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
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