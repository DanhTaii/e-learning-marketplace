package vn.edu.nlu.fit.elearning.feature.order.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.order.OrderFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentService;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentServiceImpl;
import vn.edu.nlu.fit.elearning.feature.payment_method.model.PaymentMethod;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.voucher.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderManagementController", value = "/admin/orders")
public class OrderManagementController extends BaseController {

    private OrderService orderService;
    private CourseAdminService courseAdminService;
    private PaymentMethodService paymentMethodService;
    private VoucherService voucherService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
        this.paymentMethodService = BeanContainer.getBean(PaymentMethodService.class);
        this.voucherService = BeanContainer.getBean(VoucherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderFilter filter = new OrderFilter();
        filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));
        filter.setCourseId(RequestUtils.getParameterAsInt(request, "courseId", 0));
        filter.setPaymentMethodId(RequestUtils.getParameterAsInt(request, "paymentMethodId", 0));
        filter.setCode(RequestUtils.getParameterAsString(request, "code", ""));
        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
        filter.setStatus(RequestUtils.getParameterAsOrderStatus(request, "status"));
        filter.setVoucherCode(RequestUtils.getParameterAsString(request, "voucherCode", ""));

        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<Order> listOrders = orderService.searchOrders(filter);
        List<Course> listCourses = courseAdminService.getAllCourses();
        List<PaymentMethod> listPaymentMethods = paymentMethodService.getAllPaymentMethods();

        request.setAttribute("listVouchers", voucherService.findAll());
        request.setAttribute("listPaymentMethods", listPaymentMethods);
        request.setAttribute("listOrders", listOrders);
        request.setAttribute("totalOrders", orderService.getTotalOrders());
        request.setAttribute("filter", filter);
        request.setAttribute("listCourse", listCourses);


        int totalRecords = orderService.getCountOrdersByFilter(filter);
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "orders");
        request.setAttribute("totalPages", totalPages);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {

            this.forward(request, response, "/views/pages/admin/order/order-fragment.jsp");
        } else {
            this.forward(request, response, "/views/pages/admin/order/order-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}