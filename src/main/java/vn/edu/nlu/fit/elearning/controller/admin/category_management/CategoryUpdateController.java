package vn.edu.nlu.fit.elearning.controller.admin.category_management;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.services.CategoryService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "CategoryUpdateController", value = "/admin/category/update")
public class CategoryUpdateController extends HttpServlet {

    private CategoryService categoryService;

    public CategoryUpdateController() {
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        //Trong Servlet API (Jakarta), hàm setCharacterEncoding nhận vào một String (ví dụ: "UTF-8"), chứ không nhận vào một đối tượng Charset.
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        Category cate = categoryService.getCategoryById(id);
        if (cate != null) {
            String cateJson = new Gson().toJson(cate);
            response.getWriter().write(cateJson);
            System.out.println(cateJson);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"message\": \"Không tìm thấy category\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}