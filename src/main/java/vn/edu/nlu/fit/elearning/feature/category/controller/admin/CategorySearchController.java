package vn.edu.nlu.fit.elearning.feature.category.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategorySearchController", value = "/admin/categories/search")
public class CategorySearchController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("searchName");
        List<Category> listCategories = categoryService.getAllCategoriesByName(nameSearch);
        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("/views/pages/admin/category/category-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}