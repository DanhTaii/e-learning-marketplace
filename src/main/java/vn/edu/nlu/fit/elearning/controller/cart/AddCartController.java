package vn.edu.nlu.fit.elearning.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.model.Cart;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Order;
import vn.edu.nlu.fit.elearning.model.OrderItem;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.OrderItemService;
import vn.edu.nlu.fit.elearning.services.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCartController", value = "/add-cart")

public class AddCartController extends HttpServlet {

    private CourseService courseService;

    public AddCartController() {
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
        session.setAttribute("cart", c);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        response.getWriter().write(String.valueOf(c.getTotalQuantity()));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
