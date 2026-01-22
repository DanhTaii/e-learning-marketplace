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

import java.io.IOException;

@WebServlet(name = "AddCartController", value = "/add-cart")

public class AddCartController extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        int id = Integer.parseInt(request.getParameter("id"));
        CourseCardDto course = courseService.getCourseCardById(id, userId);
        if (course == null) {
            return;
        }

        Cart c = (Cart) session.getAttribute("cart");
        if (c == null) c = new Cart();
        c.addCourse(course);
        session.setAttribute("cart", c);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        response.getWriter().write(String.valueOf(c.getTotalQuantity()));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
