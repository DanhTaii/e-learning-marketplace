<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson Management</title>
    <base href="${pageContext.request.contextPath}/">

    <%-- Admin Layout Css--%>
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">

    <%-- Management Css --%>
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">

    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

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
                            <div class="container-2__header">
                                <div class="header__title">Bài học</div>
                                <div class="admin-create__buttons">
                                    <button type="button" class="dark-button">
                                        <a href="admin/lesson/detail" class="admin-create-link">
                                            <i class="fa-solid fa-plus"></i>Tạo mới
                                        </a>
                                    </button>
                                </div>
                            </div>
                            <div class="container-2__body">
                                <div class="title__admin">Tất cả bài học (${listLessons.size()})</div>
                                <form method="get" class="form" action="admin/lessons">
                                    <div class="container-2__filter">
                                        <div class="filter__selection">
                                            <div class="filter__selection-input">
                                                <div class="filter__selection-items">
                                                    <div class="filter__selection-title filter__item-phone">Tên bài học:
                                                    </div>
                                                    <input placeholder="" type="text"
                                                           class="input__font admin-input__long" name="searchName"
                                                           value="${param.searchName}">
                                                </div>
                                                <div class="filter__selection-items">
                                                    <div class="filter__selection-items-select">
                                                        <div class="filter__selection-title filter__item-phone">Tên khóa
                                                            học:
                                                        </div>
                                                        <select name="courseId" class="combobox admin-input__short ">
                                                            <option class="text-medium" value="">--- Vui lòng chọn khóa học
                                                                ---
                                                            </option>
                                                            <c:forEach var="c" items="${listCourse}">
                                                                <option class="text-medium"
                                                                        value="${c.id}" ${param.courseId == c.id ? 'selected' : ''}>
                                                                        ${c.title}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="filter__button-search">
                                                <button type="submit" class="admin-search-btn">
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
                                            <th>Tên bài học</th>
                                            <th>Số thứ tự</th>
                                            <th>Thời lượng</th>
                                            <th>Ngày tạo</th>
                                            <th>Hành động</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="lesson" items="${listLessons}">
                                            <tr>
                                                <td>
                                                    <div class="course-row__title title course-row__style-text">
                                                            ${lesson.title}
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="course-row__font-content">
                                                            ${lesson.orderIndex}
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="course-row__font-content">
                                                            ${lesson.durationMinutes} p
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="course-row__created course-row__font-content">
                                                        <fmt:setLocale value="en_US" scope="page"/>

                                                        <fmt:formatDate
                                                                value="${lesson.createdAt}"
                                                                pattern="dd-MM-YYYY"/>
                                                    </div>
                                                </td>
                                                <td class="action__button">
                                                    <div class="action-wrapper">
<%--                                                        <button type="button" onclick="showLessonDetail(${lesson.id})"--%>
<%--                                                                class="icon-action-btn">--%>
<%--                                                            <i class="fa-solid fa-pen"></i>--%>
<%--                                                        </button>--%>
                                                        <a href="admin/lesson/detail?id=${lesson.id}" class="">
                                                            <button type="button" class="icon-action-btn">
                                                                <i class="fa-solid fa-pen"></i>
                                                            </button>
                                                        </a>
                                                        <button type="button" class="icon-action-btn"
                                                                onclick="openConfirmModal(${lesson.id})">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty listLessons}">
                                            <tr>
                                                <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                    <div class="search-empty-state">
                                                        <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                        <div class="search-empty-title">
                                                            Không tìm thấy bài học nào
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                        </tbody>
                                    </table>
                                </div>

                                <jsp:include page="/views/components/pagination-base.jsp">
                                    <jsp:param name="baseUrl" value="admin/lessons"/>
                                    <jsp:param name="currentPageNumber" value="${filter.page}"/>
                                    <jsp:param name="totalPages" value="${totalPages}"/>
                                </jsp:include>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--COMPONENT CONFIRM FOR DELETE--%>
<div id="confirm-delete-modal" class="modal"
     style="display: none; position: fixed; z-index: 1001; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); align-items: center; justify-content: center;">
    <div class="modal-content"
         style="background: white; padding: 25px; border-radius: 8px; width: 350px; text-align: center;">
        <h3 style="color: #dc3545; font-size:1.8rem "><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p style="font-size: 1.6rem">Bạn có chắc chắn muốn xóa bài học này không?</p>
        <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
            <button onclick="closeModal('confirm-delete-modal')" class="button btn-cancel" style="padding: 8px 20px;">
                Hủy
            </button>
            <button id="btn-confirm-delete" class="button dark-button"
                    style="background-color: #dc3545; padding: 8px 20px;">Xóa ngay
            </button>
        </div>
    </div>
</div>
<%--DELETE ACTION--%>
<form id="delete-form-id" action="admin/lesson/delete"
      method="POST"
      class="form"
      style="display: none">
    <input id="input-delete-id" type="hidden" name="id">
</form>
<jsp:include page="/views/components/toast.jsp"/>
</body>
</html>