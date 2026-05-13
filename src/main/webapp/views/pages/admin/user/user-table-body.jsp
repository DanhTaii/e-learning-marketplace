<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${not empty listUsers}">
        <c:forEach var="user" items="${listUsers}">
            <tr>
                <td><input type="checkbox" class="category-checkbox item-checkbox" value="${user.id}"></td>
                <td>
                    <div class="title">
                            ${empty user.username ? "Chưa cập nhật" : user.username}
                    </div>
                </td>
                <td>
                    <div class="course-row__font-content">
                            ${user.email}
                    </div>
                </td>
                <td>

                    <c:choose>

                        <c:when test="${user.roleName == 'SUPER_ADMIN'}">
                            <div class="course-row__status course-row__font-content role-admin">
                                Super Admin
                            </div>
                        </c:when>

                        <c:when test="${user.roleName == 'ADMIN_USER'}">
                            <div class="course-row__status course-row__font-content role-admin">
                                Quản trị người dùng
                            </div>
                        </c:when>

                        <c:when test="${user.roleName == 'ADMIN_COURSE'}">
                            <div class="course-row__status course-row__font-content role-admin">
                                Quản trị khóa học
                            </div>
                        </c:when>

                        <c:when test="${user.roleName == 'ADMIN_ORDER'}">
                            <div class="course-row__status course-row__font-content role-admin">
                                Quản trị đơn hàng
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="course-row__status course-row__font-content course-row__status-private">
                                Người dùng
                            </div>
                        </c:otherwise>

                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.status == 'ACTIVE'}">
                            <div class="course-row__status course-row__font-content course-row__status-public">
                                Hoạt động
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="course-row__status course-row__font-content course-row-status-unactive">
                                Bị khóa
                            </div>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <div class="course-row__created course-row__font-content">
                        <fmt:setLocale value="en_US" scope="page"/>

                        <fmt:formatDate value="${user.createdAt}"
                                        pattern="dd-MM-YYYY"/>
                    </div>
                </td>
                <td class="action__button">
                    <div class="action-wrapper">
                        <a href="admin/user/detail?id=${user.id}">
                            <button type="button" class="icon-action-btn">
                                <i class="fa-solid fa-pen"></i>
                            </button>
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </c:when>

    <c:otherwise>
        <tr>
            <td colspan="7">
                <div class="search-empty-state">
                    <i class="fa-solid fa-book-open search-empty-icon"></i>
                    <div class="search-empty-title">
                        Không tìm thấy người dùng nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>