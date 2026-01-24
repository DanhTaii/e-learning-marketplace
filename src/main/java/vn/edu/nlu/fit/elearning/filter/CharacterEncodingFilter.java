package vn.edu.nlu.fit.elearning.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Thiết lập Encoding cho Request (Dữ liệu từ Form gửi lên)
        request.setCharacterEncoding("UTF-8");

        // Thiết lập Encoding cho Response (Dữ liệu trả về trình duyệt)
        response.setCharacterEncoding("UTF-8");

        // Đôi khi cần set ContentType để trình duyệt hiểu là UTF-8 nếu chưa có JSP header
        // response.setContentType("text/html; charset=UTF-8");

        chain.doFilter(request, response);
    }
}