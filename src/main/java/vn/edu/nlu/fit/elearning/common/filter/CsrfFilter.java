package vn.edu.nlu.fit.elearning.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.utils.security.HashUtils;
import java.io.IOException;

@WebFilter(filterName = "CsrfFilter", urlPatterns = "/*")
public class CsrfFilter implements Filter {
    private static final String CSRF_TOKEN_SESSION_ATTR = "csrfToken";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        String method = httpRequest.getMethod();

        if ("GET".equalsIgnoreCase(method) || "HEAD".equalsIgnoreCase(method) || "OPTIONS".equalsIgnoreCase(method)) {

            // Kiểm tra xem user này đã có token trong session chưa
            if (session.getAttribute("csrfToken") == null) {
                // Nếu chưa, sinh ra một chuỗi ngẫu nhiên (UUID) và lưu vào Session
                String token = HashUtils.generateTokenForVerify();
                session.setAttribute(CSRF_TOKEN_SESSION_ATTR, token);
            }

            // Cho phép request đi qua filter, tới Servlet xử lý bình thường
            chain.doFilter(request, response);
            return;
        }

        //Kiểm tra request nếu không phải là GET thì phải có token hợp lệ
        String sessionToken = (String) session.getAttribute(CSRF_TOKEN_SESSION_ATTR);
        String requestToken = httpRequest.getHeader("X-CSRF-Token");

        // kiểm tra nếu token không có trong header thì có thể là gửi từ form, nên kiểm tra thêm ở parameter
        if (requestToken == null || requestToken.isEmpty()) {
            requestToken = httpRequest.getParameter("csrfToken");
        }

        if (sessionToken != null && sessionToken.equals(requestToken)) {
            // Trùng khớp -> Đây là request hợp lệ do chính user thao tác trên web của mình
            chain.doFilter(request, response);
        } else {
            // Không trùng khớp -> Có thể là tấn công CSRF hoặc request bị giả mạo
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Lỗi bảo mật: CSRF Token không tồn tại hoặc không hợp lệ!");
        }
    }
}