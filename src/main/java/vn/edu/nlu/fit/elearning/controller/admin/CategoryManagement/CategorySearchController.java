package vn.edu.nlu.fit.elearning.controller.admin.CategoryManagement;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.services.CategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategorySearchController", value = "/admin/categories/search")
public class CategorySearchController extends HttpServlet {
    private CategoryService categoryService;

    public CategorySearchController() {
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("searchName");
        List<Category> listCategories = categoryService.getAllCategoriesByName(nameSearch);
        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("/html-admin/category-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}