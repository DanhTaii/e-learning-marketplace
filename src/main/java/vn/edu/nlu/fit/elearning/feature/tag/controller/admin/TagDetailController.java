    package vn.edu.nlu.fit.elearning.feature.tag.controller.admin;
    
    import com.google.gson.Gson;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import vn.edu.nlu.fit.elearning.common.base.BaseController;
    import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
    import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
    import vn.edu.nlu.fit.elearning.common.helper.validator.tag.TagValidator;
    import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
    import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
    import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
    import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
    
    import java.io.IOException;
    import java.util.Map;

    @WebServlet(name = "TagDetailController", value = "/admin/tag/detail")
    public class TagDetailController extends BaseController {
        private TagService tagService;

        @Override
        public void init() {
            this.tagService = BeanContainer.getBean(TagService.class);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String idStr = request.getParameter("id");

                if (idStr != null && !idStr.trim().isEmpty()) {
                    int id = RequestUtils.getParameterAsInt(request, "id", -1);
                    Tag tag = tagService.getTagById(id);
                    if (tag != null) {
                        request.setAttribute("tag", tag);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy tag!");
                        return;
                    }
                }
                this.forward(request, response, "/views/pages/admin/tag/tag-create.jsp");
            } catch (Exception e) {
                log("Unexpected error", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Tag tag = new Tag();

            int id = RequestUtils.getParameterAsInt(request, "id", -1);
            tag.setId(id);

            tag.setName(request.getParameter("nameTag"));
            tag.setSlug(request.getParameter("slug"));

            BaseStatus status = RequestUtils.getParameterAsStatus(request, "status");
            if (status == null) {
                status = BaseStatus.INACTIVE;
            }
            tag.setStatus(status);

            try {
                Map<String, String> errors = TagValidator.validate(tag);

                if (!errors.isEmpty()) {
                    request.setAttribute("errors", errors);
                    request.setAttribute("tag", tag);
                    request.getRequestDispatcher("/views/pages/admin/tag/tag-create.jsp").forward(request, response);
                    return;
                }

                if (tag.getId() > 0) {

                    if (tagService.existsBySlug(tag.getSlug(), tag.getId())) {
                        handleError(request, response, "Slug đã tồn tại!");
                        return;
                    }

                    int result = tagService.updateTag(tag);

                    if (result > 0) {
                        request.getSession().setAttribute("flashSuccess", "Cập nhật tag thành công!");
                    }

                    response.sendRedirect(request.getContextPath() + "/admin/tags");
                    return;
                }

                if (tagService.existsByName(tag.getName())) {
                    handleError(request, response, "Tên tag đã tồn tại!");
                    return;
                }

                if (tagService.existsBySlug(tag.getSlug())) {
                    handleError(request, response, "Slug đã tồn tại!");
                    return;
                }

                int result = tagService.createTag(tag);

                if (result > 0) {
                    request.getSession().setAttribute("flashSuccess", "Tạo tag thành công!");
                    response.sendRedirect(request.getContextPath() + "/admin/tags");
                } else {
                    handleError(request, response, "Lỗi hệ thống khi tạo tag!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/views/pages/admin/tag/tag-create.jsp").forward(request, response);
            }

            System.out.println("STATUS PARAM: " + request.getParameter("status"));
        }
    }