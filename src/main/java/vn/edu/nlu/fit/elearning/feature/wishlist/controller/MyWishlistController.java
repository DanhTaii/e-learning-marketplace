package vn.edu.nlu.fit.elearning.feature.wishlist.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistService;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyWishlistController", value = "/personal/my-wishlist")
public class MyWishlistController extends HttpServlet {

    private WishlistService ws;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ws = BeanContainer.getBean(WishlistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        int userId = (int) session.getAttribute("userId");

//        UserService userService =BeanContainer.getBean(UserService.class);
//        User user = userService.getUserById(userId);
//        request.setAttribute("user", user);

        // Nếu không có courseId thì hiển thị danh sách wishlist
        List<CourseCardDto> wishlistCourses = ws.getWishlistCourses(userId);
        request.setAttribute("wishlistCourses", wishlistCourses);
        request.getRequestDispatcher("/views/pages/personal/course/my-wishlist.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(401); // Unauthorized
            return;
        }
        int userId = (int) session.getAttribute("userId");
        System.out.println(userId);
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        boolean added = ws.toggleWishlist(userId, courseId);

        response.getWriter().write(added ? "added" : "removed");
    }
}