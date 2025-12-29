package vn.edu.nlu.fit.elearning.controller.personal;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Wishlist;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.OrderService;
import vn.edu.nlu.fit.elearning.services.UserService;
import vn.edu.nlu.fit.elearning.services.WishlistService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyWishlistController", value = "/my-wishlist")
public class MyWishlistController extends HttpServlet {

    private WishlistService ws;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ws = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ws =new WishlistService();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/sign-in");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String courseIdParam = request.getParameter("courseId");

        // Nếu có courseId thì toggle (thêm hoặc xóa)
        if (courseIdParam != null) {
            int courseId = Integer.parseInt(courseIdParam);
            if (ws.exists(userId, courseId)) {
                // dùng idParam để xóa theo wishlist.id
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    ws.removeCourseFromWishlist(id);
                }
            } else {
                ws.addCourseToWishlist(userId, courseId);
            }
            response.sendRedirect(request.getContextPath() + "/my-wishlist");
            return;
        }

        // Nếu không có courseId thì hiển thị danh sách wishlist
        List<Course> wishlistCourses = ws.getWishlistCourses(userId);
        request.setAttribute("wishlistCourses", wishlistCourses);
        request.getRequestDispatcher("/html-personal/my-wishlist.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}