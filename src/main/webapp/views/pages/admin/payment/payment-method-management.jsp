<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kiểu thanh toán </title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/course-edit.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/component/notification.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/payment-method-management.css?v=<%=System.currentTimeMillis()%>">

</head>
<body>
<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <jsp:include page="/views/layouts/admin/sidebar-admin.jsp"/>
                <div class="grid__column-10 container-2">
                    <jsp:include page="/views/layouts/admin/header-admin.jsp"/>
                    <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">Kiểu thanh toán</div>
                        </div>
                        <div class="container-2__body">


                            <div class="title__admin">Tất cả phương thức thanh toán (${listPaymentMethods.size()})</div>
                            <div class="container-2__filter">
                                <form action="admin/payment-methods/search"
                                      method="get" class="form">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên phương
                                                    thức:
                                                </div>
                                                <input
                                                        placeholder=""
                                                        type="text"
                                                        name="searchName"
                                                        class="admin-input__long"
                                                        value="${param.searchName}">
                                            </div>
                                        </div>

                                        <div class="filter__button-search">
                                            <button type="submit" class="admin-search-btn">
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="container-2__list-student">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Tên phương thức</th>
                                        <th>Code</th>
                                        <th>Trạng thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${listPaymentMethods}" var="pm">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${pm.name}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${pm.code}
                                                </div>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${pm.status == 'ACTIVE'}">
                                                        <div class="course-row__status course-row__font-content course-row__status-public">
                                                            Hoạt động
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="course-row__status course-row__font-content course-row-status-unactive">
                                                            Không hoạt động
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td class="action__button">
                                                <button type="button" onclick="showPaymentMethodDetail(${pm.id})"
                                                        class="icon-action-btn">
                                                    <i class="fa-solid fa-pen"></i>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listPaymentMethods}">
                                        <tr>
                                            <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                <div class="search-empty-state">
                                                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                    <div class="search-empty-title">
                                                        Không tìm thấy kiểu thanh toán nào
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="payment-method-detail" class="modal modal__course-detail">
                    <div class="modal__course-content">
                        <form action="${pageContext.request.contextPath}/admin/payment-methods/update" method="post">

                            <div class="course__header">
                                <div class="course__title">
                                    <i class="fa-solid fa-credit-card"></i>
                                    <span id="modal-title" class="text-header"></span>
                                </div>
                                <div class="x__icon" onclick="closePaymentMethodModal()">
                                    <i class="fa-solid fa-xmark"></i>
                                </div>
                            </div>
                            <div class="course-body">
                                <div class="user-info-grid">
                                    <input id="detail-id" type="hidden" name="id">

                                    <div class="info-group">
                                        <label><i class="fa-solid fa-tag"></i> Tên phương thức</label>
                                        <input id="detail-name" type="text" class="input__create" name="name"disabled>
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-code"></i> Mã phương thức (Code)</label>
                                        <input id="detail-code" type="text" class="input__create" name="code" disabled>
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-image"></i> Icon URL</label>
                                        <input id="detail-iconUrl" type="text" class="input__create" name="iconUrl" disabled>
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-power-off"></i> Trạng thái</label>
                                        <select id="detail-status" class="input__create" name="status">
                                            <option value="ACTIVE">Hoạt động</option>
                                            <option value="INACTIVE">Không hoạt động</option>
                                        </select>
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-plus"></i> Ngày tạo</label>
                                        <input id="detail-created" type="text" class="input__create" disabled>
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày cập nhật</label>
                                        <input id="detail-updated" type="text" class="input__create" disabled>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="button btn-cancel" onclick="closePaymentMethodModal()">
                                        Hủy
                                    </button>
                                    <button type="submit" class="button dark-button">Lưu thay đổi</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
</body>

<script src="assets/javascript/admin/payment/admin-payment-method-detail.js?v=<%=System.currentTimeMillis()%>"></script>
</html>