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
import vn.edu.nlu.fit.elearning.feature.payment_method.vnpay.VnpayConstants;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao pd;


    private OrderItemService orderItemService;


    public PaymentServiceImpl(PaymentDao pd) {
        this.pd = new PaymentDaoImpl();
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
    public String generateVNPAYUrl(Order order, HttpServletRequest request) {
        long vnpAmount = order.getFinalAmount() * 100L;

        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", VnpayConstants.vnp_TmnCode);
        vnpParams.put("vnp_Amount", String.valueOf(vnpAmount));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", order.getOrderCode());
        vnpParams.put("vnp_OrderInfo", "Thanh toan don hang " + order.getOrderCode());
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_ReturnUrl", VnpayConstants.vnp_ReturnUrl);
        vnpParams.put("vnp_IpAddr", VnpayConstants.getIpAddress(request));

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnpParams.put("vnp_CreateDate", formatter.format(cld.getTime()));

        List fieldNames = new ArrayList(vnpParams.keySet());
        Collections.sort(fieldNames);


        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnpParams.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {

                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII)).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));

                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnpSecureHash = VnpayConstants.hmacSHA512(VnpayConstants.secretKey, hashData.toString());
        return VnpayConstants.vnp_PayUrl + "?" + queryUrl + "&vnp_SecureHash=" + vnpSecureHash;
    }
    }
