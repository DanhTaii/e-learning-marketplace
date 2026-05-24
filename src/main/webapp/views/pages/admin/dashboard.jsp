<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="csrf-token" content="${sessionScope.csrfToken}">
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">

    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/pages/dashboard.css?v=<%=System.currentTimeMillis()%>">

</head>
<body>

<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <jsp:include page="/views/layouts/admin/sidebar-admin.jsp"/>

                <div class="grid__column-10 container-2">

                    <jsp:include page="/views/layouts/admin/header-admin.jsp"/>

                    <div class="container-2__content-body">
                        <div class="grid__row-2 container-2__grid">
                            <div class="dashboard-header">
                                <form action="" method="GET" class="filter-form">
                                    <select name="timeRange" class="time-filter" onchange="this.form.submit()">
                                        <option value="today" ${param.timeRange == 'today' ? 'selected' : ''}>Hôm nay</option>
                                        <option value="7days" ${empty param.timeRange || param.timeRange == '7days' ? 'selected' : ''}>7 ngày qua</option>
                                        <option value="month" ${param.timeRange == 'month' ? 'selected' : ''}>Tháng này</option>
                                        <option value="year" ${param.timeRange == 'year' ? 'selected' : ''}>Năm nay</option>
                                        <option value="all" ${param.timeRange == 'all' ? 'selected' : ''}>Toàn thời gian</option>
                                    </select>
                                </form>
                            </div>
                            <div class="list-card">
                                <ul>
                                    <li>
                                        <div class="card-information">
                                            <div class="card-content">
                                                <div>Doanh thu</div>
                                                <div class="card-icon"><i
                                                        class="fa-solid fa-money-check-dollar"></i></i>
                                                </div>
                                            </div>
                                            <div class="card-content__number"><fmt:formatNumber value="${revenueTotal}"
                                                                                                type="number"
                                                                                                pattern="###,###"> </fmt:formatNumber>
                                                đ
                                            </div>
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
                                    <div class="chart__title">
                                        <c:choose>
                                            <c:when test="${param.timeRange == 'today'}">DOANH THU HÔM NAY</c:when>
                                            <c:when test="${param.timeRange == 'month'}">DOANH THU THÁNG NÀY</c:when>
                                            <c:when test="${param.timeRange == 'year'}">DOANH THU NĂM NAY</c:when>
                                            <c:when test="${param.timeRange == 'all'}">DOANH THU TOÀN THỜI GIAN</c:when>
                                            <c:otherwise>DOANH THU 7 NGÀY QUA</c:otherwise>
                                        </c:choose>
                                    </div>
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
                                                        <fmt:formatNumber value="${item.revenueMillion}"
                                                                          maxFractionDigits="1"/>tr
                                                    </span>
                                                    </div>
                                                    <span class="bar-label">${item.orderDate}</span>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <div class="top-course">
                                    <table class="top-course-table">
                                        <thead>
                                        <tr>
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
</div>
<jsp:include page="/views/components/toast.jsp"/>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>