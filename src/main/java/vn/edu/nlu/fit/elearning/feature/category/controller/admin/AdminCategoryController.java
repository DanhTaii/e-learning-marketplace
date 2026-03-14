package vn.edu.nlu.fit.elearning.feature.category.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.category.service.ICategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryController", value = "/admin/categories")
public class AdminCategoryController extends HttpServlet {

    private ICategoryService ICategoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ICategoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listCategories = ICategoryService.getAllCategories();
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("currentPage", "categories");
        request.getRequestDispatcher("/views/pages/admin/category/category-management.jsp").forward(request, response);
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
            int checkCreate = ICategoryService.createCategory(newCategory);
            if (checkCreate == 1) {
                request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/categories");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Tên hoặc Slug đã tồn tại trong hệ thống!");
            request.setAttribute("listTags", ICategoryService.getAllCategories());
            request.getRequestDispatcher("/views/pages/admin/tag/tag-management.jsp").forward(request, response);
        }
    }
}