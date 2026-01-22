package vn.edu.nlu.fit.elearning.controller.cart.cart_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Cart;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.WishlistService;

import java.io.IOException;

@WebServlet(name = "BuyNowController", value = "/buy-now")

public class BuyNowController extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String userIdStr = request.getParameter("userId");
        int userId = 0;
        if (userIdStr != null) {
            userId = Integer.parseInt(userIdStr);
        }
        CourseCardDto course = courseService.getCourseCardById(id, userId);
        if (course == null) {
            return;
        }
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null) c = new Cart();
        c.addCourse(course);
        c.selectOnly(course.getId());
        session.setAttribute("cart", c);

        response.sendRedirect(request.getContextPath() + "/payment");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
