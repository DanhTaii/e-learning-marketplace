package vn.edu.nlu.fit.elearning.feature.payment.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.service.ICart;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentServiceImpl;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonServiceImpl;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.model.UserLessonProgress;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressServiceImpl;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderServiceImpl;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemServiceImpl;
import vn.edu.nlu.fit.elearning.helper.enums.OrderStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ConfirmController", value = "/confirm-payment")
public class ConfirmController extends HttpServlet {
    OrderService orderService;
    OrderItemService orderItemService;
    EnrollmentService enrollmentService;
    LessonService lessonService;
    UserLessonProgressService userLessonProgressService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = new OrderServiceImpl();
        this.orderItemService = new OrderItemServiceImpl();
        this.enrollmentService = new EnrollmentServiceImpl();
        this.lessonService = new LessonServiceImpl();
        this.userLessonProgressService = new UserLessonProgressServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        ICart ICart = (ICart) session.getAttribute("cart");
        int paymentMethodId = Integer.parseInt(request.getParameter("payment-method-id"));

        //khỏi tạo order
        Order order = new Order();
        order.setOrderCode("ORD-" + System.currentTimeMillis());
        order.setUserId(userId);
        order.setPaymentMethodId(paymentMethodId);
        order.setTotalAmount((int) ICart.getTotal());
        order.setDiscountAmount((int) ICart.getDiscountPriceTotal());
        order.setFinalAmount((int) ICart.getFinalPriceTotal());
        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));


        int orderId = orderService.createOrder(order);

        for (CartItem item : ICart.getSelectedItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrderId(orderId);
            oi.setCourseId(item.getCourse().getId());
            oi.setPriceAtPurchase(item.getPrice());

            orderItemService.createOrderItem(oi);

            Enrollment enrollment = new Enrollment();
            enrollment.setUserId(userId);
            enrollment.setCourseId(item.getCourse().getId());
            enrollment.setOrderId(orderId);
            enrollmentService.createEnrollment(enrollment);

            List<Lesson> lesson = lessonService.getLessonsByCourseId(item.getCourse().getId());
            List<UserLessonProgress> progressList = new ArrayList<>();
            for (Lesson l : lesson) {
                UserLessonProgress userLessonProgress = new UserLessonProgress();
                userLessonProgress.setUserId(userId);
                userLessonProgress.setLessonId(l.getId());
                progressList.add(userLessonProgress);

            }
            userLessonProgressService.createUserLessonProgress(progressList);
        }
        ICart.removeSelected();


        session.setAttribute("cart", ICart);
        response.sendRedirect(request.getContextPath() + "/receipt?orderId=" + orderId);
    }
}