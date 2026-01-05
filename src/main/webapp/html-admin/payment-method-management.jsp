<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kiểu thanh toán </title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css?v=1.0.4">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/course-edit.css">
    <link rel="stylesheet" href="assets/css-admin/notification.css">
    <style>
        /* CSS modal Payment Method - giống hệt modal Tag */
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            justify-content: center;
            align-items: center;
            padding: 20px;
            box-sizing: border-box;
        }

        .modal.show {
            display: flex !important;
        }

        .modal__course-content {
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            width: 90%;
            max-width: 700px;
            overflow: hidden;
            animation: modalFadeIn 0.3s ease-out;
        }

        @keyframes modalFadeIn {
            from { opacity: 0; transform: translateY(-50px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .course__header {
            background: #1a1f36;
            color: white;
            padding: 20px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 19px;
            font-weight: 600;
        }

        .course__header .course__title i {
            margin-right: 12px;
            font-size: 22px;
        }

        .x__icon {
            cursor: pointer;
            font-size: 26px;
            opacity: 0.8;
            transition: opacity 0.2s;
        }

        .x__icon:hover {
            color: red;
        }

        .course-body {
            padding: 30px;
        }

        .user-info-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 24px;
        }

        .info-group {
            margin-bottom: 20px;
        }

        .info-group label {
            display: block;
            margin-bottom: 10px;
            font-weight: 600;
            color: #333;
            font-size: 15px;
        }

        .info-group label i {
            color: #5e72e4;
            margin-right: 10px;
            font-size: 18px;
        }

        .input__create {
            width: 100%;
            padding: 14px 18px;
            border: 1px solid #e0e0e0;
            border-radius: 12px;
            font-size: 15px;
            background-color: #fafafa;
            transition: all 0.3s;
        }

        .input__create:focus {
            outline: none;
            border-color: #5e72e4;
            background-color: white;
            box-shadow: 0 0 0 4px rgba(94, 114, 228, 0.15);
        }

        .input__create[disabled] {
            background-color: #f5f5f5;
            color: #888;
            cursor: not-allowed;
        }

        .modal-footer {
            display: flex;
            justify-content: flex-end;
            gap: 16px;
            margin-top: 35px;
            padding-top: 20px;
            border-top: 1px solid #eee;
        }

        .btn-cancel {
            background: #f8f9fa !important;
            color: #6c757d !important;
            padding: 12px 28px;
            border-radius: 12px;
            font-weight: 500;
            font-size: 1.6rem;
        }

        .btn-cancel:hover {
            background: #e9ecef !important;
        }


    </style>

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
                            <div class="header__title">Kiểu thanh toán</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo phương thức thanh toán</div>
                            <div class="container-2__create">
                                <form action="${pageContext.request.contextPath}/admin/payment-methods" method="post"
                                      style="width: 100%;">
                                    <div class="create__selection">
                                        <div class="create__selection-input">
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Tên phương
                                                    thức:
                                                </div>
                                                <input type="text" name="name" class="admin-input__long" required>
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Icon URL:</div>
                                                <input type="text" name="iconUrl" class="admin-input__long">
                                            </div>
                                        </div>
                                        <div class="create__btn-create">
                                            <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="title__admin">Tất cả phương thức thanh toán (${listPaymentMethods.size()})</div>
                            <div class="container-2__filter">
                                <form action="${pageContext.request.contextPath}/admin/payment-methods/search"
                                      method="get" style="width: 100%;">
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
                                                        value="${param.searchName != null ? param.searchName : ''}">
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
                                        <th>Ngày tạo</th>
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
                                                <div class="course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate value="${pm.createdAt}"
                                                                    pattern="MMMM d, yyyy"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                    <%--                                                <a href="">--%>
                                                    <%--                                                    <span class="icon-action"><i class="fa-solid fa-pen"></i></span>--%>
                                                    <%--                                                </a>--%>
                                                <button type="button" onclick="showPaymentMethodDetail(${pm.id})"
                                                        class="icon-action-btn">
                                                    <i class="fa-solid fa-pen"></i>
                                                </button>
                                                <form action="${pageContext.request.contextPath}/admin/payment-methods/delete"
                                                      method="post" style="display: inline;">
                                                    <input type="hidden" name="id" value="${pm.id}">
                                                    <button type="submit"
                                                            style="background: none; border: none; cursor: pointer; padding: 0;">
                                                        <span class="icon-action"><i
                                                                class="fa-solid fa-trash"></i></span>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
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
                                        <input id="detail-name" type="text" class="input__create" name="name">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-code"></i> Mã phương thức (Code)</label>
                                        <input id="detail-code" type="text" class="input__create" name="code">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-image"></i> Icon URL</label>
                                        <input id="detail-iconUrl" type="text" class="input__create" name="iconUrl">
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
<div id="toast"></div>
</body>
<script>
    window.flashError = '${sessionScope.flashError}';
    window.flashSuccess = '${sessionScope.flashSuccess}';

    <%
        session.removeAttribute("flashError");
        session.removeAttribute("flashSuccess");
    %>

</script>
<script src="assets/javascript/js-admin/admin-payment-method-detail.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/notification.js?v=<%=System.currentTimeMillis()%>"></script>
</html>