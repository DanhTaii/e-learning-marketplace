package vn.edu.nlu.fit.elearning.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.services.CategoryService;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "IndexController", value = "/index")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // này làm cho phần catrgory
        CategoryService cs = new CategoryService();
        ArrayList<Category> categories = (ArrayList<Category>) cs.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

        // làm cho phần banner

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}