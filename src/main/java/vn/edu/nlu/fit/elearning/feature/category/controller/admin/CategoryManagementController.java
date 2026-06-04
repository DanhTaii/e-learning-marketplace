package vn.edu.nlu.fit.elearning.feature.category.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryController", value = "/admin/categories")
public class CategoryManagementController extends BaseController {

    private transient CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryFilter filter = new CategoryFilter();

        filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));
        filter.setSlug(RequestUtils.getParameterAsString(request, "slug", ""));
        filter.setParentId(RequestUtils.getParameterAsInt(request, "parentId", -1) == -1
                ? null
                : RequestUtils.getParameterAsInt(request, "parentId", -1));

        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
        filter.setStatus(RequestUtils.getParameterAsStatus(request, "status"));

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<Category> listCategories = categoryService.getCategoriesByFilter(filter);

        int totalRecords = categoryService.getCountCategoriesByFilter(filter);
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("listCategories", listCategories);
        request.setAttribute("totalCategories", totalRecords);
        request.setAttribute("filter", filter);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "categories");
        request.setAttribute("totalPages", totalPages);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            this.forward(request, response, "/views/pages/admin/category/category-fragment.jsp");
        } else {
            this.forward(request, response, "/views/pages/admin/category/category-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        int categoryParentId = Integer.parseInt(request.getParameter("categoryParentId"));
        String categorySlug = request.getParameter("categorySlug");

        if (categoryName.isEmpty() || categoryParentId == 0 || categorySlug.isEmpty()) {
            request.setAttribute("flashError", "Vui lòng điền thông tin ! ");
            request.getRequestDispatcher("admin/categories").forward(request, response);
        }
        try {
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            newCategory.setParentId(categoryParentId);
            newCategory.setSlug(categorySlug);
            int checkCreate = categoryService.createCategory(newCategory);
            if (checkCreate == 1) {
                request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/categories");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Tên hoặc Slug đã tồn tại trong hệ thống!");
            request.setAttribute("listTags", categoryService.getAllCategories());
            request.getRequestDispatcher("/views/pages/admin/tag/tag-management.jsp").forward(request, response);
        }
    }
}