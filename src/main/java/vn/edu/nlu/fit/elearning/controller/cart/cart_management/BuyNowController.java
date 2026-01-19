package vn.edu.nlu.fit.elearning.controller.cart.cart_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.model.Cart;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;

@WebServlet(name = "BuyNowController", value = "/buy-now")

public class BuyNowController extends HttpServlet {

    private CourseService courseService;

    public BuyNowController() {
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getCourse(id);
        if(course == null){
            return;
        }
        HttpSession session = request.getSession();
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
