package vn.edu.nlu.fit.elearning.feature.voucher.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.cart.model.Cart;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.voucher.dto.VoucherResultDTO;
import vn.edu.nlu.fit.elearning.feature.voucher.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ApplyVoucherController", value = "/apply-voucher")
public class ApplyVoucherController extends BaseController {

    private transient VoucherService voucherService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.voucherService = BeanContainer.getBean(VoucherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String code = request.getParameter("code");
        JsonObject jsonResponse = new JsonObject();

        try {
            HttpSession session = request.getSession();
            CartService c = (CartService) session.getAttribute("cart");
            if (c == null) c = new CartServiceImpl();

            if (c == null ) {
                throw new Exception("Giỏ hàng của bạn đang trống!");
            }

            VoucherResultDTO result = voucherService.applyVoucher(code, c.getFinalPriceTotal());

            session.setAttribute("appliedVoucher", result.getVoucher());
            session.setAttribute("discountAmount", result.getDiscountAmount());

            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "Áp dụng mã thành công!");
            jsonResponse.addProperty("discountAmount", result.getDiscountAmount());
            jsonResponse.addProperty("finalTotal", result.getFinalTotal());
            jsonResponse.addProperty("code", result.getVoucher().getCode());

        } catch (Exception e) {
            jsonResponse.addProperty("status", "error");
            jsonResponse.addProperty("message", e.getMessage());
        }

        response.getWriter().write(new Gson().toJson(jsonResponse));
    }

}