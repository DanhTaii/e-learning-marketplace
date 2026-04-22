package vn.edu.nlu.fit.elearning.feature.payment.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order_item.model.OrderItem;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDao;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment.model.Payment;
import vn.edu.nlu.fit.elearning.feature.payment_method.vnpay.VnpayConstrants;

import java.text.SimpleDateFormat;
import java.util.*;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao pd;

    private OrderService orderService;
    private OrderItemService orderItemService;

    public PaymentServiceImpl() {
        this.pd = new PaymentDaoImpl();
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.orderItemService = BeanContainer.getBean(OrderItemService.class);
    }

    @Override
    public int createPayment(Payment payment) {
        // TODO: Implement creation logic
        return pd.create(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        // TODO: Implement getAll logic
        return pd.findAll();
    }

    @Override
    public Payment getPaymentById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    @Override
    public void updatePayment(Payment payment) {
        // TODO: Implement update logic
    }

    @Override
    public void deletePayment(int id) {
        // TODO: Implement delete logic
    }

    @Override
    public Order createOrderPending(Integer userId, CartService cart, int paymentMethodId) {
        Order order = new Order();
        order.setOrderCode("ORD" + System.currentTimeMillis());
        order.setUserId(userId);
        order.setPaymentMethodId(paymentMethodId);
        order.setTotalAmount((int) cart.getTotal());
        order.setDiscountAmount((int) cart.getDiscountPriceTotal());
        order.setFinalAmount((int) cart.getFinalPriceTotal());
        order.setStatus(OrderStatus.PENDING);

        int orderId = orderService.createOrder(order);
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
    public String generateVNPAYUrl(Order order, HttpServletRequest request) {
        long vnpAmount = order.getFinalAmount() * 100L;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", VnpayConstrants.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(vnpAmount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", order.getOrderCode());
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + order.getOrderCode());
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnpayConstrants.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", VnpayConstrants.getIpAddress(request));

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));

        // Chỗ này gọi cái hàm Utils bạn đã copy vào
        return VnpayConstrants.vnp_PayUrl + "?" + VnpayConstrants.hashAllFields(vnp_Params);
    }
    }
