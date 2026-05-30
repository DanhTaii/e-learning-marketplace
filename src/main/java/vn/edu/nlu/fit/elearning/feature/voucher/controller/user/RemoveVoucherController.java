package vn.edu.nlu.fit.elearning.feature.voucher.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartService;
import vn.edu.nlu.fit.elearning.feature.cart.service.CartServiceImpl;

import java.io.IOException;

@WebServlet(name = "RemoveVoucherController", value = "/remove-voucher")
public class RemoveVoucherController extends BaseController {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        session.removeAttribute("appliedVoucher");
        session.removeAttribute("discountAmount");

        CartService c = (CartService) session.getAttribute("cart");
        if (c == null) c = new CartServiceImpl();
        double rawTotal = (c!= null) ? c.getFinalPriceTotal() : 0;

        String formattedTotal = DataFormatting.formatAndConvert(rawTotal);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("status", "success");
        jsonResponse.addProperty("originalTotalFormatted", formattedTotal);

        response.getWriter().write(new Gson().toJson(jsonResponse));
    }

}