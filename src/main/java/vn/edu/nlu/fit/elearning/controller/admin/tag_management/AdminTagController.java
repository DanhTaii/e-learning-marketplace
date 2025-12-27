package vn.edu.nlu.fit.elearning.controller.admin.tag_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminTagController", value = "/admin/tags")
public class AdminTagController extends HttpServlet {

    private TagService tagService;


    public AdminTagController() {
        this.tagService = new TagService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tag> listTags = tagService.getAllTags();
        request.setAttribute("listTags", listTags);
        request.setAttribute("currentPage", "tags");
        request.getRequestDispatcher("/html-admin/tag-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameTag = request.getParameter("nameTag");
        String slugTag = request.getParameter("slugTag");

        if (nameTag.isEmpty() || slugTag.isEmpty()) {
            request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin!");
            request.setAttribute("listTags", tagService.getAllTags());
            request.getRequestDispatcher("/html-admin/tag-management.jsp").forward(request, response);
            return;
        }
        try {
            Tag newTag = new Tag();
            newTag.setName(nameTag);
            newTag.setSlug(slugTag);
            int checkCreate = tagService.createTag(newTag);
            if (checkCreate == 1) {
                request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/tags");

            }
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Tên hoặc Slug đã tồn tại trong hệ thống!");
            request.setAttribute("oldName", nameTag);
            request.setAttribute("oldSlug", slugTag);
            request.setAttribute("listTags", tagService.getAllTags());
            request.getRequestDispatcher("/html-admin/tag-management.jsp").forward(request, response);
        }

    }

}
