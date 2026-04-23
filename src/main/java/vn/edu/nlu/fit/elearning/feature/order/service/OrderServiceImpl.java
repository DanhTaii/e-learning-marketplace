package vn.edu.nlu.fit.elearning.feature.order.service;

import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.PaymentStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private transient OrderItemService orderItemService;
    private transient EnrollmentService enrollmentService;
    private transient LessonService lessonService;
    private transient UserLessonProgressService userLessonProgressService;
private  transient  PaymentService paymentService;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
        this.orderItemService = BeanContainer.getBean(OrderItemService.class);
        this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
        this.lessonService = BeanContainer.getBean(LessonService.class);
        this.userLessonProgressService = BeanContainer.getBean(UserLessonProgressService.class);
        this.paymentService = BeanContainer.getBean(PaymentService.class);
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
    public List<Map<String, Object>> searchOrders(String orderCode, String userName, Timestamp fromDate, String status) {
        return orderDao.searchWithUserAndPayment(orderCode, userName, fromDate, status);
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
    public void completeOrder(String orderCode,String transactionNo) {

        this.orderItemService = BeanContainer.getBean(OrderItemService.class);
        this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
        this.lessonService = BeanContainer.getBean(LessonService.class);
        this.userLessonProgressService = BeanContainer.getBean(UserLessonProgressService.class);
        this.paymentService = BeanContainer.getBean(PaymentService.class);
        Order order = orderDao.findByCode(orderCode);


        if (order == null || order.getStatus() == OrderStatus.PAID) {
            return;
        }

        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));
        orderDao.update(order);


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
            Payment payment = new Payment();
            payment.setOrderId(order.getId());
            payment.setPaymentMethodId(order.getPaymentMethodId());
            payment.setGateway_transaction_id(transactionNo);
            payment.setAmount(order.getFinalAmount());
            payment.setStatus(PaymentStatus.valueOf("SUCCESS"));
            paymentService.createPayment(payment);
        }
    }
}