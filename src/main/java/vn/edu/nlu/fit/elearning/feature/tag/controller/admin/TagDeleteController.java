package vn.edu.nlu.fit.elearning.feature.tag.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;

@WebServlet(name = "TagDeleteController", value = "/admin/tag/delete")
public class TagDeleteController extends HttpServlet {
    private TagService tagService;

    @Override
    public void init() {
        this.tagService = BeanContainer.getBean(TagService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                "Phương thức GET không được hỗ trợ cho endpoint này");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String idStr = request.getParameter("id");

            if (idStr == null || idStr.trim().isEmpty()) {
                request.getSession().setAttribute("flashError", "ID không hợp lệ!");
                response.sendRedirect(request.getContextPath() + "/admin/tags");
                return;
            }

            int id = Integer.parseInt(idStr);
            boolean success = tagService.deleteTags(id);

            if (success) {
                request.getSession().setAttribute("flashSuccess", "Xóa thẻ thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Không tìm thấy thẻ để xóa!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/tags");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Lỗi hệ thống khi xóa thẻ!");
            response.sendRedirect(request.getContextPath() + "/admin/tags");
        }
    }
}