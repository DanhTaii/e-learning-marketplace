package vn.edu.nlu.fit.elearning.feature.payment.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
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
import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;

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
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.orderItemService =BeanContainer.getBean(OrderItemService.class);
        this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
        this.lessonService = BeanContainer.getBean(LessonService.class);
        this.userLessonProgressService = BeanContainer.getBean(UserLessonProgressService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        CartService ICartService = (CartService) session.getAttribute("cart");
        int paymentMethodId = Integer.parseInt(request.getParameter("payment-method-id"));

        //khỏi tạo order
        Order order = new Order();
        order.setOrderCode("ORD-" + System.currentTimeMillis());
        order.setUserId(userId);
        order.setPaymentMethodId(paymentMethodId);
        order.setTotalAmount((int) ICartService.getTotal());
        order.setDiscountAmount((int) ICartService.getDiscountPriceTotal());
        order.setFinalAmount((int) ICartService.getFinalPriceTotal());
        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));


        int orderId = orderService.createOrder(order);

        for (CartItem item : ICartService.getSelectedItems()) {
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
        ICartService.removeSelected();


        session.setAttribute("cart", ICartService);
        session.setAttribute("flashSuccess", "Thanh toán thành công! Chúc bạn học tốt.");
        response.sendRedirect(request.getContextPath() + "/receipt?orderId=" + orderId);
    }
}