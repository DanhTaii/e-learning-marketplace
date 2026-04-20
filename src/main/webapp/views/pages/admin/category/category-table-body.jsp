<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty listCategories}">
        <c:forEach var="cate" items="${listCategories}">
            <tr>
                <td><input type="checkbox" class="category-checkbox" value="${cate.id}"></td>

                <td>${cate.name}</td>

                <td>${cate.slug}</td>

                <td>${cate.parentId}</td>

                <td>
                    <fmt:formatDate value="${cate.createdAt}" pattern="dd/MM/yyyy"/>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${cate.status.name() == 'ACTIVE'}">
                            <span class="badge course-row__status-public">Hoạt động</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge course-row-status-unactive">Không hoạt đông</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td class="action-btns">
                    <a href="admin/category/detail?id=${cate.id}">
                        <button type="button" class="icon-action-btn">
                            <i class="fa-solid fa-pen"></i>
                        </button>
                    </a>

                    <button type="button"
                            onclick="openConfirmModal(${cate.id}, 'admin/category/delete', 'Bạn có chắc chắn muốn xóa danh mục này?')"
                            class="icon-action-btn">
                        <i class="fa-solid fa-trash"></i>
                    </button>
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
                        Không tìm thấy danh mục nào
                    </div>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>