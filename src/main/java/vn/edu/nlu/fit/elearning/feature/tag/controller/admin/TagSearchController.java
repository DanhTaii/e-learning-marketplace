package vn.edu.nlu.fit.elearning.feature.tag.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TagSearchController", value = "/admin/tags/search")
public class TagSearchController extends HttpServlet {
    private TagService tagService;

    @Override
    public void init() {
        this.tagService = new TagService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("searchName");
        List<Tag> listTags = tagService.getAllTagsByName(nameSearch);
        request.setAttribute("listTags", listTags);
        request.getRequestDispatcher("/views/pages/admin/tag/tag-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}