package vn.edu.nlu.fit.elearning.feature.tag.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminTagController", value = "/admin/tags")
public class TagManagementController extends BaseController {

    private transient TagService tagService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.tagService = BeanContainer.getBean(TagService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TagFilter filter = new TagFilter();

        filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));
        filter.setSlug(RequestUtils.getParameterAsString(request, "slug", ""));
        filter.setStatus(RequestUtils.getParameterAsStatus(request, "status"));

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<Tag> listTags = tagService.searchTags(filter);

        int totalRecords = tagService.countTags(); // (có thể cải tiến theo filter sau)
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("listTags", listTags);
        request.setAttribute("totalTags", totalRecords);
        request.setAttribute("filter", filter);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "tags");
        request.setAttribute("totalPages", totalPages);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            this.forward(request, response, "/views/pages/admin/tag/tag-fragment.jsp");
        } else {
            this.forward(request, response, "/views/pages/admin/tag/tag-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức POST không được hỗ trợ cho endpoint này");
    }
}