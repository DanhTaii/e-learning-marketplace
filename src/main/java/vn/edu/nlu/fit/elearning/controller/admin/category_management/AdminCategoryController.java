package vn.edu.nlu.fit.elearning.controller.admin.category_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.services.CategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryController", value = "/admin/categories")
public class AdminCategoryController extends HttpServlet {

    private CategoryService categoryService;

    public AdminCategoryController() {
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listCategories = categoryService.getAllCategories();
        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("/html-admin/category-management.jsp").forward(request, response);
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
            request.getRequestDispatcher("/html-admin/tag-management.jsp").forward(request, response);
        }
    }
}