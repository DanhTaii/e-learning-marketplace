<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/order-management.css">

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
                                    <div class="menu-item__student  ${currentPage == 'dashboard' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items ">
                                        <i class="fa-solid fa-table-columns"></i>
                                        <span>Dashboard</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/users">
                                    <div class="menu-item__student ${currentPage == 'users' ? 'student-list' : ''} ">
                                    <span class="container-1__menu-items">

                                        <i class="fa-solid fa-user"></i>
                                        <span>Người dùng</span>

                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/courses">
                                    <div class="menu-item__student ${currentPage == 'courses' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-users-between-lines"></i>
                                        <span>Khóa học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/lessons">
                                    <div class="menu-item__student ${currentPage == 'lessons' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">
                                        <i class="fa-solid fa-book"></i>
                                        <span>Bài học</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/tags">
                                    <div class="menu-item__student ${currentPage == 'tags' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">

                                        <i class="fa-solid fa-tags"></i>
                                        <span>Thẻ</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/categories">
                                    <div class="menu-item__student ${currentPage == 'categories' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__course">

                                       <i class="fa-solid fa-list"></i>
                                        <span>Danh mục</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/orders">
                                    <div class="menu-item__student ${currentPage == 'orders' ? 'student-list' : ''}">
                                    <span class="container-1__menu-items menu-item__order">

                                        <i class="fa-solid fa-receipt"></i>
                                        <span>Đơn hàng</span>
                                    </span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="admin/payment-methods">
                                    <div class="menu-item__student ${currentPage == 'payment-methods' ? 'student-list' : ''}">
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
                            <div class="header__title">Đơn hàng</div>
                        </div>
                        <div class="container-2__body">
                            <div class="container-2__filter">
                                <div class="filter__selection">
                                    <div class="filter__selection-input">
                                        <div class="filter__selection-items filter__selection-name">
                                            <div class="filter__selection-title filter__item-name">Mã:</div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Tên người dùng:
                                            </div>
                                            <input placeholder="" type="text" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Từ ngày:</div>
                                            <input placeholder="" type="datetime-local" class="admin-input__long">
                                        </div>
                                        <div class="filter__selection-items">
                                            <div class="filter__selection-title filter__item-phone">Trạng thái đơn
                                                hàng:
                                            </div>
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
                                    <c:forEach var="order" items="${listOrders}">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${order.orderCode}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                    User ID: ${order.userId} <!-- Tạm thời hiển thị ID, sau này JOIN tên -->
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${order.finalAmount}đ <!-- Dùng trực tiếp finalAmount, tạm chưa format -->
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                    <c:choose>
                                                        <c:when test="${order.paymentMethodId == 1}">Momo</c:when>
                                                        <c:when test="${order.paymentMethodId == 2}">VNPAY</c:when>
                                                        <c:when test="${order.paymentMethodId == 3}">ZaloPay</c:when>
                                                        <c:otherwise>Chưa chọn</c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content course-row__status
                    <c:choose>
                        <c:when test="${order.status == 'PAID'}">course-row__status-public</c:when>
                        <c:when test="${order.status == 'PENDING'}">course-row__status-pending</c:when>
                        <c:when test="${order.status == 'FAILED'}">course-row__status-failed</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                    ">
                                                        ${order.status}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <a href="#course-detail" class="button-de-mo" onclick="showOrderDetail(${order.id})">
                                                    <span class="icon-action"><i class="fa-solid fa-eye"></i></span>
                                                </a>
                                                <a href="#">
                                                    <span class="icon-action"><i class="fa-solid fa-pen"></i></span>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/admin/orders?action=delete&id=${order.id}"
                                                   onclick="return confirm('Bạn có chắc muốn xóa đơn hàng này?')">
                                                    <span class="icon-action"><i class="fa-solid fa-trash"></i></span>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <!-- Nếu không có đơn hàng nào -->
                                    <c:if test="${empty listOrders}">
                                        <tr>
                                            <td colspan="7" style="text-align: center; padding: 20px;">
                                                Chưa có đơn hàng nào
                                            </td>
                                        </tr>
                                    </c:if>
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