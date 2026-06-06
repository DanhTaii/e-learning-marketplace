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

<link rel="icon" type="image/png" href="assets/image/logo.jpg">
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
                                                <div class="card-title">Doanh thu</div>
                                                <div class="card-icon"><i class="fa-solid fa-money-check-dollar"></i></div>
                                            </div>
                                            <div class="card-content__number">
                                                <fmt:formatNumber value="${revenueTotal}" type="number" pattern="###,###"/> đ
                                            </div>

                                            <div class="card-growth">
                                                <c:choose>
                                                    <%-- Trường hợp Tăng --%>
                                                    <c:when test="${revenueGrowth > 0}">
                            <span class="growth-badge badge-increase">
                                <i class="fa-solid fa-arrow-trend-up"></i>
                                +<fmt:formatNumber value="${revenueGrowth}" maxFractionDigits="1"/>%
                            </span>
                                                    </c:when>

                                                    <%-- Trường hợp Giảm --%>
                                                    <c:when test="${revenueGrowth < 0}">
                            <span class="growth-badge badge-decrease">
                                <i class="fa-solid fa-arrow-trend-down"></i>
                                <fmt:formatNumber value="${revenueGrowth}" maxFractionDigits="1"/>%
                            </span>
                                                    </c:when>

                                                    <%-- Trường hợp Không đổi --%>
                                                    <c:otherwise>
                            <span class="growth-badge badge-neutral">
                                <i class="fa-solid fa-minus"></i> 0%
                            </span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <span class="growth-text">so với kỳ trước</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="card-information">
                                            <div class="card-content">
                                                <div class="card-title">Đơn hàng</div>
                                                <div class="card-icon"><i class="fa-solid fa-cart-shopping"></i></div>
                                            </div>
                                            <div class="card-content__number"><c:out value="${orderCount}"/></div>

                                            <div class="card-growth">
                                                <c:choose>
                                                    <%-- Trường hợp Tăng --%>
                                                    <c:when test="${orderGrowth > 0}">
                    <span class="growth-badge badge-increase">
                        <i class="fa-solid fa-arrow-trend-up"></i>
                        +<fmt:formatNumber value="${orderGrowth}" maxFractionDigits="1"/>%
                    </span>
                                                    </c:when>

                                                    <%-- Trường hợp Giảm --%>
                                                    <c:when test="${orderGrowth < 0}">
                    <span class="growth-badge badge-decrease">
                        <i class="fa-solid fa-arrow-trend-down"></i>
                        <fmt:formatNumber value="${orderGrowth}" maxFractionDigits="1"/>%
                    </span>
                                                    </c:when>

                                                    <%-- Trường hợp Không đổi --%>
                                                    <c:otherwise>
                    <span class="growth-badge badge-neutral">
                        <i class="fa-solid fa-equals"></i> 0%
                    </span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <span class="growth-text">so với kỳ trước</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="card-information">
                                            <div class="card-content">
                                                <div class="card-title">Người dùng mới</div> <div class="card-icon"><i class="fa-solid fa-user"></i></div>
                                            </div>
                                            <div class="card-content__number"><c:out value="${userCount}"/></div>

                                            <div class="card-growth">
                                                <c:choose>
                                                    <c:when test="${userGrowth > 0}">
                    <span class="growth-badge badge-increase">
                        <i class="fa-solid fa-arrow-trend-up"></i>
                        +<fmt:formatNumber value="${userGrowth}" maxFractionDigits="1"/>%
                    </span>
                                                    </c:when>
                                                    <c:when test="${userGrowth < 0}">
                    <span class="growth-badge badge-decrease">
                        <i class="fa-solid fa-arrow-trend-down"></i>
                        <fmt:formatNumber value="${userGrowth}" maxFractionDigits="1"/>%
                    </span>
                                                    </c:when>
                                                    <c:otherwise>
                    <span class="growth-badge badge-neutral">
                        <i class="fa-solid fa-equals"></i> 0%
                    </span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <span class="growth-text">so với kỳ trước</span>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="card-information" style="height: 100%; display: flex; flex-direction: column; justify-content: center;">
                                            <div class="card-content">
                                                <div class="card-title">Tổng khóa học</div>
                                                <div class="card-icon"><i class="fa-solid fa-tags"></i></div>
                                            </div>
                                            <div class="card-content__number"><c:out value="${courseCount}"/></div>
                                            <div class="card-growth">
                                                <span class="growth-text">Hoạt động trên hệ thống</span>
                                            </div>
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
                                                    <span class="bar-label"><c:out value="${item.orderDate}"/></span>
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
                                                <td><c:out value="${loop.index + 1}"/></td>
                                                <td><c:out value="${c.title}"/></td>
                                                <td><c:out value="${c.studentCount}"/></td>
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
<div class="sidebar-overlay" id="sidebar-overlay"></div>
<script src="assets/javascript/security/security.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/admin-toggle-sidebar.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>