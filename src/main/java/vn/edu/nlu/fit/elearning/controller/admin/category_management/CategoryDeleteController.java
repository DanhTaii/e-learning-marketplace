package vn.edu.nlu.fit.elearning.controller.admin.category_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.services.CategoryService;

import java.io.IOException;

@WebServlet(name = "CategoryDeleteController", value = "/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int checkDelete = categoryService.deleteCategory(id);
        if (checkDelete > 0) {
            request.getSession().setAttribute("flashSuccess", "Xóa danh mục thành công !");
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        } else {
            request.getSession().setAttribute("flashError", "Không tìm thấy danh mục để xóa !");
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        }

    }
}