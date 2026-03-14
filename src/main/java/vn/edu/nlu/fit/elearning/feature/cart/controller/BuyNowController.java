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

@WebServlet(name = "BuyNowController", value = "/buy-now")

public class BuyNowController extends HttpServlet {

    private CourseService courseServiceImpl;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseServiceImpl = BeanContainer.getBean(CourseService.class);
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
        CourseCardDto course = courseServiceImpl.getCourseCardById(id, userId);
        if (course == null) {
            return;
        }
        CartService c = (CartService) session.getAttribute("cart");
        if (c == null) c = new CartServiceImpl();
        c.addCourse(course);
        c.selectOnly(course.getId());
        session.setAttribute("cart", c);

        response.sendRedirect(request.getContextPath() + "/payment");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
