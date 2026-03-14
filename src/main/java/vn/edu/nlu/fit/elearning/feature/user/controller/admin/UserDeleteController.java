package vn.edu.nlu.fit.elearning.feature.user.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "UserDeleteController", value = "/admin/user/delete")
public class UserDeleteController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int checkDelete = userService.deleteUser(id);
            if (checkDelete > 0) {
                request.getSession().setAttribute("flashSuccess", "Xóa người dùng thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/users");
            } else {
                request.getSession().setAttribute("flashError", "Không tìm thấy người dùng để xóa.");
                response.sendRedirect(request.getContextPath() + "/admin/users");
            }
        } catch (Exception e) {
            request.getSession().setAttribute("flashError", "Lỗi: Người dùng này đã có dữ liệu liên quan (đơn hàng/khóa học), không thể xóa!");
            e.printStackTrace();
        }
    }
}