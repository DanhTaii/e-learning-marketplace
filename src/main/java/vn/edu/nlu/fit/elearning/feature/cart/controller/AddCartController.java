package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartSyncService;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseService;

import java.io.IOException;

@WebServlet(name = "AddCartController", value = "/add-cart")

public class AddCartController extends HttpServlet {

    private CourseService courseService;
    private CartSyncService cartSyncService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseService = BeanContainer.getBean(CourseService.class);
        this.cartSyncService = BeanContainer.getBean(CartSyncService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userIdObj = (Integer) session.getAttribute("userId");
        int userId = (userIdObj != null) ? userIdObj : 0;

        int id = Integer.parseInt(request.getParameter("id"));
        CourseCardDto course = courseService.getCourseCardById(id, userId);
        if (course == null) {
            return;
        }

        CartService c = (CartService) session.getAttribute("cart");
        if (c == null) c = new CartServiceImpl();
        c.addCourse(course);
        session.setAttribute("cart", c);

        if (userIdObj != null) {
            cartSyncService.saveSessionToDatabase(userIdObj, (CartServiceImpl) c);
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        response.getWriter().write(String.valueOf(c.getTotalQuantity()));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
