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

@WebServlet(name = "ShowCartController", value = "/cart")

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
        Integer userId = (Integer) session.getAttribute("userId");

        List<CourseCardDto> coursesLastest = indexService.getSixCoursesLast(userId);
        request.setAttribute("coursesLastest", coursesLastest);

        List<Voucher> vouchers = voucherService.findValidVouchers();
        if (userId != null) {
            for (Voucher v : vouchers) {
                boolean hasUsed = voucherService.hasUserUsedVoucher(userId, v.getId());
                v.setUsedByCurrentUser(hasUsed);
            }
        } else {
            request.setAttribute("listVoucher", null);
        }

        request.setAttribute("listVoucher", vouchers);

        request.getRequestDispatcher("/views/pages/cart/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
