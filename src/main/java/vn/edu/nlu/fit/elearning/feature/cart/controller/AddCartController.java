package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import java.io.IOException;

@WebServlet(name = "AddCartController", value = "/add-cart")

public class AddCartController extends HttpServlet {

    private CourseService courseServiceImpl;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseServiceImpl = BeanContainer.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        int id = Integer.parseInt(request.getParameter("id"));
        CourseCardDto course = courseServiceImpl.getCourseCardById(id, userId);
        if (course == null) {
            return;
        }

        CartService c = (CartService) session.getAttribute("cart");
        if (c == null) c = new CartServiceImpl();
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
