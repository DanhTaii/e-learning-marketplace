package vn.edu.nlu.fit.elearning.controller.admin.tag_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TagDeleteController", value = "/admin/tags/delete")
public class TagDeleteController extends HttpServlet {
    private TagService tagService;

    public TagDeleteController() {
        this.tagService = new TagService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tagId = request.getParameter("id");
        if(tagId != null){
            int idTag= Integer.parseInt(tagId);
            boolean success = tagService.deleteTags(idTag);
            response.sendRedirect(request.getContextPath() + "/admin/tags");

        }
    }
}