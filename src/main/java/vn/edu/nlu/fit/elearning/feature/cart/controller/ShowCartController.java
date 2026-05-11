package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.index.service.IndexService;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;
import vn.edu.nlu.fit.elearning.feature.voucher.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowCartController", value = "/personal/cart")

public class ShowCartController extends HttpServlet {
    private IndexService indexService;
    private VoucherService voucherService;

    @Override
    public void init() {
        this.indexService = BeanContainer.getBean(IndexService.class);
        this.voucherService = BeanContainer.getBean(VoucherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        List<CourseCardDto> coursesLastest = indexService.getSixCoursesLast(userId);
        List<Voucher> vouchers = voucherService.findValidVouchers();
        request.setAttribute("coursesLastest", coursesLastest);
        request.setAttribute("listVoucher", vouchers);

        request.getRequestDispatcher("/views/pages/cart/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
