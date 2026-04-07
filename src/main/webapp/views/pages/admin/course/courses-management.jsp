<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="vi_VN"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quản lý khóa học</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/admin/layouts/admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/sidebar-admin.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/css/admin/layouts/header-admin.css?v=<%=System.currentTimeMillis()%>">

    <link rel="stylesheet" href="assets/css/admin/notification.css?v=<%=System.currentTimeMillis()%>">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="assets/fonts/normalize.css-master/normalize.css">
    <link rel="stylesheet" href="assets/css/base/base.css?v=<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-7.1.0-web/css/all.min.css">

    <link rel="stylesheet" href="assets/css/admin/layouts/management-default.css?v=<%=System.currentTimeMillis()%>">

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
                                <div class="header__title">Các khóa học</div>
                                <div class="admin-create__buttons">
                                    <button type="button" class="dark-button">
                                        <a href="admin/course/detail">
                                            <i class="fa-solid fa-plus"></i>Tạo mới
                                        </a>
                                    </button>
                                </div>
                            </div>

                            <div class="container-2__body">
                                <form action="admin/course/search" method="GET">
                                    <div class="container-2__filter">
                                        <div class="filter__selection">
                                            <div class="filter__selection-input">
                                                <div class="filter__selection-items filter__selection-name">
                                                    <div class="filter__selection-title  filter__item-label">Tên khóa học:
                                                    </div>
                                                    <input placeholder="" type="text" class="admin-input__long"
                                                           name="courseTitle" value="${param.courseTitle}">
                                                </div>
                                                <div class="filter__selection-items">
                                                    <div class="filter__selection-title">Từ ngày</div>
                                                    <input placeholder="" type="date" class="admin-input__long"
                                                           name="dateFrom" value="${param.dateFrom}">
                                                </div>
                                                <div class="filter__selection-items-select">
                                                    <div class="filter__selection-title">Trạng thái:</div>
                                                    <select name="isPublic" class="combobox admin-input__short">
                                                        <option value="" ${empty param.isPublic ? 'selected' : ''}>Tất cả
                                                        </option>
                                                        <option value="public" ${param.isPublic == 'public' ? 'selected' : ''}>
                                                            Công khai
                                                        </option>
                                                        <option value="private" ${param.isPublic == 'private' ? 'selected' : ''}>
                                                            Riêng tư
                                                        </option>
                                                    </select>
                                                </div>
                                                <div class="filter__selection-items-select">
                                                    <div class="filter__selection-title">Cấp độ:</div>
                                                    <select name="level" class="combobox admin-input__short">
                                                        <option value="" ${empty param.level ? 'selected' : ''}>Tất cả
                                                        </option>
                                                        <option value="beginner" ${param.level == 'beginner' ? 'selected' : ''}>
                                                            Sơ cấp
                                                        </option>
                                                        <option value="intermediate" ${param.level == 'intermediate' ? 'selected' : ''}>
                                                            Trung cấp
                                                        </option>
                                                        <option value="advanced" ${param.level == 'advanced' ? 'selected' : ''}>
                                                            Cao cấp
                                                        </option>
                                                    </select>
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

                                    <div class="admin-table-responsive">
                                        <table id="admin-course-table">
                                            <thead>
                                            <tr>
                                                <th>Khóa học</th>
                                                <th>Thời lượng</th>
                                                <th>Học viên</th>
                                                <th>Cấp độ</th>
                                                <th>Trạng thái</th>
                                                <th>Ngày tạo</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody id="admin-course-table-body">
                                            </tbody>
                                        </table>

                                        <template id="tpl-empty-state">
                                            <tr>
                                                <td colspan="7"> <%-- Số 7 này tương ứng với 7 cột của bảng --%>
                                                    <div class="search-empty-state">
                                                        <i class="fa-solid fa-book-open search-empty-icon"></i>
                                                        <div class="search-empty-title">
                                                            Không tìm thấy khóa học nào
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </template>

                                        <template id="course-row-template">
                                            <tr class="course-row">
                                                <td><div class="course-row__title title course-row__style-text js-title"></div></td>
                                                <td><div class="course-row__duration js-duration"></div></td>
                                                <td><div class="course-row__total__enrollment js-enrollment"></div></td>
                                                <td><div class="course-row__level js-level"></div></td>
                                                <td><div class="course-row__status js-status"></div></td>
                                                <td><div class="course-row__created js-created"></div></td>
                                                <td class="action__button">
                                                    <div class="action-wrapper">
                                                        <a href="" class="js-edit-link">
                                                            <button type="button" class="icon-action-btn"><i class="fa-solid fa-pen"></i></button>
                                                        </a>
                                                        <button type="button" class="icon-action-btn js-delete-btn">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </template>

                                        <jsp:include page="/views/components/pagination.jsp"/>
                                    </div>

                                    <div class="admin-pagination-container">
                                        <div class="admin-pagination-wrapper">
                                            <ul id="admin-pagination-list" class="pagination home-product__pagination">
                                            </ul>
                                            <div id="pagination-info-text" class="pagination-info">
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
</div>

<%--COMPONENT CONFIRM FOR DELETE--%>
<div id="confirm-delete-modal" class="modal">
    <div class="modal-content">
        <h3><i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa</h3>
        <p>Bạn có chắc chắn muốn xóa khóa học này không?</p>
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
<%--DELETE ACTION--%>
<form id="delete-form-id" action="admin/course/delete" method="post" class="form">
    <input id="input-delete-id" type="hidden" name="id">
</form>
<jsp:include page="/views/components/toast.jsp"/>
</body>

<script src="assets/javascript/utils/pagination/course/course-pagination.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/pagination/base-pagination.js?v=<%=System.currentTimeMillis()%>"></script>
<script src="assets/javascript/utils/formatter/base.js?v=<%=System.currentTimeMillis()%>"></script>
</html>