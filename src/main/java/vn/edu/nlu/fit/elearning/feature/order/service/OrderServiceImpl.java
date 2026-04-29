package vn.edu.nlu.fit.elearning.feature.order.service;

import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.external.mail.SendGridService;
import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.PaymentStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.order.OrderFilter;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.model.UserLessonProgress;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;
import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDao;
import vn.edu.nlu.fit.elearning.feature.order.dto.OrderDTO;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentService;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderItemService orderItemService;
    private EnrollmentService enrollmentService;
    private LessonService lessonService;
    private UserLessonProgressService userLessonProgressService;
    private PaymentService paymentService;
    private UserService userService;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    private void ensureServices() {
        if (this.orderItemService == null) this.orderItemService = BeanContainer.getBean(OrderItemService.class);
        if (this.enrollmentService == null) this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
        if (this.lessonService == null) this.lessonService = BeanContainer.getBean(LessonService.class);
        if (this.userLessonProgressService == null) this.userLessonProgressService = BeanContainer.getBean(UserLessonProgressService.class);
        if (this.paymentService == null) this.paymentService = BeanContainer.getBean(PaymentService.class);
        if (this.userService == null) this.userService = BeanContainer.getBean(UserService.class);
    }
    @Override
    public int createOrder(Order order) {
       return  orderDao.create(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public Order getOrderByCode(String orderCode) {
        return orderDao.findByCode(orderCode);
    }

    @Override
    public Order findOrderPending(Integer userId) {
        if (userId == null) {
            return null;
        }
        return orderDao.findOrderPending(userId);
    }

    @Override
    public List<Order> searchOrders(OrderFilter filter) {
        return orderDao.getOrderBySearch(filter);
    }
    @Override
    public int getCountOrdersByFilter(OrderFilter filter) {
        return orderDao.countOrdersByFilter(filter);
    }


    @Override
    public int updateOrder(Order order) {
        if (order != null) {
            return orderDao.update(order);
        }
        return 0;
    }


    @Override
    public boolean deleteOrder(int orderId) {
        int status = orderDao.delete(orderId);
        return status > 0;
    }

    @Override
    public List<Map<String, Object>> getAllOrdersWithUserName() {
        return orderDao.findAllWithUserName();
    }


    @Override
    public double getRevenueTotal() {
        return orderDao.calculateRevenueTotal();
    }
    @Override
    public List<OrderDTO> getOrderHistoryByUserId(int userId){
        List<OrderDTO> list = orderDao.getOrderHistoryByUserId(userId);
        return list;
    }

    @Override
    public int getTotalOrders() {
        return orderDao.countAllOrder();
    }

    @Override
    public Order createOrderPending(Integer userId, CartService cart, int paymentMethodId) {
        ensureServices();
        Order order = new Order();
        order.setOrderCode("ORD" + System.currentTimeMillis());
        order.setUserId(userId);
        order.setPaymentMethodId(paymentMethodId);
        order.setTotalAmount((int) cart.getTotal());
        order.setDiscountAmount((int) cart.getDiscountPriceTotal());
        order.setFinalAmount((int) cart.getFinalPriceTotal());
        order.setStatus(OrderStatus.PENDING);
        String currentUsername = userService.getUserById(userId).getUsername();
        order.setUsernameSnapshot(currentUsername);
        int orderId = this.createOrder(order);
        order.setId(orderId);

        for (CartItem item : cart.getSelectedItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrderId(orderId);
            oi.setCourseId(item.getCourse().getId());
            oi.setPriceAtPurchase(item.getPrice());
            orderItemService.createOrderItem(oi);
        }
        return order;
    }

    @Override
    public void processPaymentResponse(String orderCode, String transactionNo, boolean isSuccess) {
        ensureServices();
        Order order = orderDao.findByCode(orderCode);

        if (order == null || order.getStatus() == OrderStatus.PAID) {
            return;
        }

        if (isSuccess) {
            handleSuccess(order, transactionNo);
        } else {
            handleFailure(order, transactionNo);
        }
    }
    private void handleSuccess(Order order, String transactionNo) {
        // 1. Cập nhật Order thành PAID
        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));
        orderDao.update(order);

        // 2. Tạo quyền truy cập (Enrollment & Progress)
        List<OrderItem> items = orderItemService.getCartItems(order.getId());
        for (OrderItem item : items) {

            Enrollment enrollment = new Enrollment();
            enrollment.setUserId(order.getUserId());
            enrollment.setCourseId(item.getCourseId());
            enrollment.setOrderId(order.getId());
            enrollmentService.createEnrollment(enrollment);


            List<Lesson> lessons = lessonService.getLessonsByCourseId(item.getCourseId());
            List<UserLessonProgress> progressList = new ArrayList<>();
            for (Lesson l : lessons) {
                UserLessonProgress lp = new UserLessonProgress();
                lp.setUserId(order.getUserId());
                lp.setLessonId(l.getId());
                progressList.add(lp);
            }
            if (!progressList.isEmpty()) {
                userLessonProgressService.createUserLessonProgress(progressList);
            }
        }


        savePaymentRecord(order, transactionNo, PaymentStatus.SUCCESS);
        try {
            UserDetailResponse user = userService.getUserById(order.getUserId());
            if (user != null && user.getEmail() != null) {

                String customerName = user.getUsername();
                new Thread(() -> {
                    SendGridService.sendPaymentSuccessEmail(user.getEmail(), customerName, order.getOrderCode(), order.getFinalAmount());
                }).start();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tiến hành gửi email hóa đơn: " + e.getMessage());
        }

    }
    private void handleFailure(Order order, String transactionNo) {
        order.setStatus(OrderStatus.FAILED);
        orderDao.update(order);

        savePaymentRecord(order, transactionNo, PaymentStatus.FAIL);
    }


    private void savePaymentRecord(Order order, String transactionNo, PaymentStatus status) {
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setPaymentMethodId(order.getPaymentMethodId());
        payment.setGateway_transaction_id(transactionNo);
        payment.setAmount(order.getFinalAmount());
        payment.setStatus(status);
        paymentService.createPayment(payment);
    }
    @Override
    public int countOrdersByTimeRange(String timeRange) {
        return orderDao.countOrdersByTimeRange(timeRange);
    }

    @Override
    public double getRevenueTotalByTimeRange(String timeRange) {
        return orderDao.getRevenueTotalByTimeRange(timeRange);
    }
}