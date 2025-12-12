<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh mục</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css-admin/admin.css">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css-admin/course-edit.css">
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
                                    <div class="menu-item__student ">
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
                                    <div class="menu-item__student ">
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
                                    <div class="menu-item__student student-list">
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
                            <div class="header__title">Danh mục</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo danh mục</div>
                            <form action="admin/categories" class="form" method="post">
                                <div class="container-2__create">
                                    <div class="create__selection">
                                        <c:if test="${not empty error}">
                                            <span style="color: red; padding: 10px; background: #f8d7da;">
                                                Lỗi: ${error}
                                            </span>
                                        </c:if>
                                        <%--                                        Nếu reload laij trang thif nó sẽ mất thông báo--%>
                                        <c:if test="${not empty sessionScope.flashSuccess}">
                                            <span style="color: green; padding: 2rem; background: #d4edda;">
                                                Thành công: ${sessionScope.flashSuccess}
                                            </span>
                                            <c:remove var="flashSuccess" scope="session"/>
                                        </c:if>
                                        <div class="create__selection-input">
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Tên danh mục:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="categoryName" value="${param.categoryName}">
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">ID danh mục cha:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="categoryParentId">
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Slug:</div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="categorySlug">
                                            </div>
                                        </div>
                                        <div class="create__btn-create">
                                            <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="title__admin">Tất cả danh mục</div>
                            <form action="admin/categories/search" class="form" method="get">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên danh mục:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="searchName" value="${param.searchName}">
                                            </div>

                                        </div>

                                        <div class="filter__button-search">
                                            <button class="button dark-button" type="submit">
                                                <i class="fa-solid fa-magnifying-glass"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="container-2__list-student">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th>ID danh mục cha</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="cate" items="${listCategories}">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${cate.id}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${cate.name}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${cate.parentId}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
<%--                                                    <fmt:setLocale value="en_US" scope="page"/>--%>
<%--                                                    <fmt:formatDate value="${cate.createdAt}"--%>
<%--                                                                    pattern="MMMM d, yyyy - h:mm a"/>--%>
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