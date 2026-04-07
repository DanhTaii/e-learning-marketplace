<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tag Management</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/tag-management.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>
<div class="web">
    <div class="web__container">
        <div class="grid">
            <div class="grid__row-2">
                <jsp:include page="/views/layouts/admin/sidebar-admin.jsp"/>
                <div class="grid__column-10 container-2">
                    <div class="container-2__header"></div>
                    <div class="grid__row-2 container-2__grid">
                        <div class="container-2__header">
                            <div class="header__title">Thẻ</div>
                        </div>
                        <div class="container-2__body">
                            <div class="title__admin">Tạo thẻ mới</div>
                            <form action="admin/tags" class="form" method="post">

                                <div class="container-2__create">
                                    <div class="create__selection">
                                        <div class="create__selection-input">
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Tên của thẻ:
                                                </div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="nameTag" value="${oldName}">
                                            </div>
                                            <div class="create__selection-items">
                                                <div class="filter__selection-title filter__item-name">Slug:</div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="slugTag" value="${oldSlug}">
                                            </div>

                                        </div>
                                        <div class="create__btn-create">
                                            <button type="submit" class="create-btn dark-button">Tạo mới</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="title__admin">Tất cả thẻ (${listTags.size()})</div>
                            <form action="admin/tags/search" class="form" method="get">
                                <div class="container-2__filter">
                                    <div class="filter__selection">
                                        <div class="filter__selection-input">
                                            <div class="filter__selection-items filter__selection-name">
                                                <div class="filter__selection-title filter__item-name">Tên thẻ:</div>
                                                <input placeholder="" type="text" class="admin-input__long"
                                                       name="searchName" value="${param.searchName}">
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
                                        <th>Tên thẻ</th>
                                        <th>Số lượng dùng</th>
                                        <th>Ngày tạo</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="t" items="${listTags}">
                                        <tr>
                                            <td>
                                                <div class="course-row__title title course-row__style-text">
                                                        ${t.name}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__font-content">
                                                        ${t.courseCount}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="course-row__created course-row__font-content">
                                                    <fmt:setLocale value="en_US" scope="page"/>

                                                    <fmt:formatDate
                                                            value="${t.createdAt}"
                                                            pattern="dd-MM-YYYY"/>
                                                </div>
                                            </td>
                                            <td class="action__button">
                                                <div class="action-wrapper">
                                                    <button type="button" onclick="showTagDetail(${t.id})"
                                                            class="icon-action-btn">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </button>
                                                    <form id="delete-form-${t.id}" action="admin/tags/delete"
                                                          method="POST"
                                                          class="form">

                                                        <input type="hidden" name="id" value="${t.id}">
                                                        <button type="button" class="icon-action-btn"
                                                                onclick="openConfirmModal(${t.id})">
                                                            <i
                                                                    class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listTags}">
                                        <tr>
                                            <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                <div class="search-empty-state">
                                                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                    <div class="search-empty-title">
                                                        Không tìm thấy thẻ nào
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
                <div id="tag-detail" class="modal modal__course-detail">
                    <div class="modal__course-content">
                        <form action="admin/tag/update" method="post">

                            <div class="course__header">
                                <div class="course__title">
                                    <i class="fa-solid fa-address-card"></i>
                                    <span id="modal-title"></span>
                                </div>
                                <div class="x__icon" onclick="closeModal('tag-detail')">
                                    <i class="fa-solid fa-xmark"></i>
                                </div>
                            </div>
                            <div class="course-body">
                                <div class="user-info-grid">
                                    <%--                                    Tạm lưu id của user để update--%>
                                    <input id="detail-id" type="text" class="input__create" name="id">
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-user"></i> Tên thẻ</label>
                                        <input id="detail-nameTag" type="text" class="input__create" name="nameTag">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-envelope"></i>Tên slug</label>
                                        <input id="detail-slugTag" type="text" class="input__create" name="slugTag">
                                    </div>


                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày tạo thẻ</label>
                                        <input id="detail-created" type="text" class="input__create" name="">
                                    </div>
                                    <div class="info-group">
                                        <label><i class="fa-solid fa-calendar-check"></i> Ngày cập nhật</label>
                                        <input id="detail-updated" type="text" class="input__create" name="">
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="button btn-cancel" onclick="closeModal('tag-detail')">
                                        Hủy
                                    </button>
                                    <button type="submit" class="button dark-button">
                                        Lưu thay đổi
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="confirm-delete-modal" class="modal">
    <div class="modal-content">
        <h3><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p>Bạn có chắc chắn muốn xóa thẻ này không?</p>
        <div>
            <button onclick="closeModal('confirm-delete-modal')" class="button btn-cancel">
                Hủy
            </button>
            <button id="btn-confirm-delete" class="button dark-button">
                Xóa ngay
            </button>
        </div>
    </div>
</div>
<jsp:include page="/views/components/toast.jsp"/>
</body>

<script src="assets/javascript/admin/tag/admin-tag-detail.js?v=<%=System.currentTimeMillis()%>"></script>

</html>