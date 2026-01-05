package vn.edu.nlu.fit.elearning.controller.cart.payment_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.model.*;
import vn.edu.nlu.fit.elearning.services.*;

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
        this.orderService = new OrderService();
        this.orderItemService = new OrderItemService();
        this.enrollmentService = new EnrollmentService();
        this.lessonService = new LessonService();
        this.userLessonProgressService = new UserLessonProgressService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Cart cart = (Cart) session.getAttribute("cart");
        int paymentMethodId = Integer.parseInt(request.getParameter("payment-method-id"));

        //khỏi tạo order
        Order order = new Order();
        order.setOrderCode("ORD-" + System.currentTimeMillis());
        order.setUserId(userId);
        order.setPaymentMethodId(paymentMethodId);
        order.setTotalAmount((int) cart.getTotal());
        order.setDiscountAmount((int) cart.getDiscountPriceTotal());
        order.setFinalAmount((int) cart.getFinalPriceTotal());
        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));


        int orderId = orderService.createOrder(order);

        for (CartItem item : cart.getSelectedItems()) {
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
        cart.removeSelected();


        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/show-receipt?orderId=" + orderId);
    }
}