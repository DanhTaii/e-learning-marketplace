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

        Category newCategory = new Category();
        newCategory.setName(categoryName);
        newCategory.setParentId(categoryParentId);
        newCategory.setSlug(categorySlug);

        int checkCreate = categoryService.createCategory(newCategory);

        if (checkCreate == 1) {
//            request.setAttribute("success", "Tạo thành công !");
//            request.getRequestDispatcher("admin/categories").forward(request, response);
            request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công!");
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        } else {
            request.setAttribute("error", "Vui lòng điền thông tin ! ");
            request.getRequestDispatcher("admin/categories").forward(request, response);
//            doGet(request, response);
        }
    }
}