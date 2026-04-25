package vn.edu.nlu.fit.elearning.feature.order.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.order.OrderFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderManagementController", value = "/admin/orders")
public class OrderManagementController extends BaseController {

    private OrderService orderService;
private CourseService courseService;
    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.courseService = BeanContainer.getBean(CourseService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderFilter filter = new OrderFilter();
        filter.setName(RequestUtils.getParameterAsString(request, "searchName", ""));
        filter.setCourseId(RequestUtils.getParameterAsInt(request, "courseId", 0));
        filter.setCode(RequestUtils.getParameterAsString(request, "code", ""));
        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
        filter.setStatus(RequestUtils.getParameterAsOrderStatus(request, "status"));


        List<Order> listOrders = orderService.searchOrders(filter);
        List<Course> listCourses = courseService.getAllCourses();
        request.setAttribute("listOrders", listOrders);
        request.setAttribute("filter", filter);
        request.setAttribute("listCourse", listCourses);
        request.setAttribute("currentPage", "orders");

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