package vn.edu.nlu.fit.elearning.feature.category.controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.helper.enums.BasicStatus;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;

import java.io.IOException;

@WebServlet(name = "CategoryUpdateController", value = "/admin/category/update")
public class CategoryUpdateController extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
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
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"message\": \"Không tìm thấy category\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String slug = request.getParameter("slug");
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        String icon = request.getParameter("icon");
        BasicStatus statusEnum = BasicStatus.valueOf(request.getParameter("status"));

        Category cate = new Category();
        cate.setId(id);
        cate.setName(name);
        cate.setSlug(slug);
        cate.setParentId(parentId);
        cate.setIconUrl(icon);
        cate.setStatus(statusEnum);

        if (categoryService.updateCategory(cate) > 0) {
            request.getSession().setAttribute("flashSuccess", "Cập nhật thành công danh mục !");
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        }

    }
}