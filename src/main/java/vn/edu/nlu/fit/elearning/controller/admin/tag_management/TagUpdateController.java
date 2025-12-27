package vn.edu.nlu.fit.elearning.controller.admin.tag_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;

@WebServlet(name = "TagUpdateController", value = "/admin/tag/update")
public class TagUpdateController extends HttpServlet {

    private TagService tagService;

    public TagUpdateController() {
        this.tagService = new TagService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nameTag = request.getParameter("nameTag");
        String slugTag = request.getParameter("slugTag");


        if (nameTag.isEmpty() || slugTag.isEmpty()) {
            request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin!");
            response.sendRedirect(request.getContextPath() + "/admin/tags");
            return;
        }
        try {
            Tag tag = new Tag();
            tag.setId(id);
            tag.setName(nameTag);
            tag.setSlug(slugTag);
            int result = tagService.updateTag(tag);


            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật thông tin thẻ thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Cập nhật thông tin thẻ thất bại. Vui lòng thử lại!");
            }
        }   catch(Exception e){
                request.getSession().setAttribute("flashError", "Tên thẻ hoặc Slug đã tồn tại trong hệ thống!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/tags");


        }
    }




