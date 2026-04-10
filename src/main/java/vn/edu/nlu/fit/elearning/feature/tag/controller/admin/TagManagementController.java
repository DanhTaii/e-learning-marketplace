package vn.edu.nlu.fit.elearning.feature.tag.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.search.TagFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminTagController", value = "/admin/tags")
public class TagManagementController extends HttpServlet {

    private TagService tagService;

    @Override
    public void init() {
        this.tagService = BeanContainer.getBean(TagService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TagFilter filter = new TagFilter();

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<Tag> listTags = tagService.searchTags(filter);

        int totalRecords = tagService.countTags();
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("listTags", listTags);
        request.setAttribute("filter", filter);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "tags");
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/views/pages/admin/tag/tag-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameTag = request.getParameter("nameTag");
        String slugTag = request.getParameter("slugTag");

        if (nameTag.isEmpty() || slugTag.isEmpty()) {
            request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin!");
            request.setAttribute("listTags", tagService.getAllTags());
            request.getRequestDispatcher("/views/pages/admin/tag/tag-management.jsp").forward(request, response);
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
            request.getRequestDispatcher("/views/pages/admin/tag/tag-management.jsp").forward(request, response);
        }

    }

}
