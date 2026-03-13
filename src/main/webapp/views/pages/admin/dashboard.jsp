<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <base href="${pageContext.request.contextPath}/">
    <%--    Mỗi laanf cập nhật cái CSS đều phải thêm đuôi version đằng sau cho Tomcat nhận diện--%>
    <%--    ?v=1.0.1--%>
    <link rel="stylesheet" href="assets/css/admin/admin.css?v=1.0.1">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/dashboard.css?v=<%=System.currentTimeMillis()%>">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <div class="grid__column-2 container-1">
                    <a href="index">
                    <div class="container-1__logo">
                        <i class="fa-solid fa-graduation-cap"></i>
                        <span>Softskill</span>
                    </div>
                    </a>
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
                                        <div class="card-content__number"><fmt:formatNumber value="${revenueTotal}" type="number" pattern="###,###"> </fmt:formatNumber> đ</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div class="card-title">Đơn hàng</div>
                                            <div class="card-icon"><i class="fa-solid fa-cart-shopping"></i></div>
                                        </div>
                                        <div class="card-content__number">${orderCount}</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div>Người dùng</div>
                                            <div class="card-icon"><i class="fa-solid fa-user"></i></div>
                                        </div>
                                        <div class="card-content__number">${userCount}</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="card-information">
                                        <div class="card-content">
                                            <div>Khóa học</div>
                                            <div class="card-icon"><i class="fa-solid fa-tags"></i></div>
                                        </div>
                                        <div class="card-content__number">${courseCount}</div>
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
                                        <c:forEach items="${chartData}" var="item">
                                            <div class="bar-item">
                                                <div class="column-chart"
                                                     data-label="${item.orderDate}"
                                                     style="height: ${item.heightPercent}%">
                                                    <span>
                                                        <fmt:formatNumber value="${item.revenueMillion}" maxFractionDigits="1"/>tr
                                                    </span>
                                                </div>
                                                <span class="bar-label">${item.orderDate}</span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>

                            <div class="top-course">
                                <table>
                                    <thead>
                                    <tr>
                                        <!-- Thay vì div riêng, đưa vào th với colspan -->
                                        <th colspan="3" class="top-course__title">
                                            TOP 6 KHÓA HỌC BÁN CHẠY NHẤT
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên khóa học</th>
                                        <th>Học viên</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="c" items="${popularCourses}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${c.title}</td>
                                            <td>${c.studentCount}</td>
                                        </tr>
                                    </c:forEach>
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