package vn.edu.nlu.fit.elearning.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(filterName = "LoggingFilter", urlPatterns = "/*")
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        long startTime = System.currentTimeMillis();

        try {
            // Cho phép request đi tiếp vào hệ thống
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // Bắt và ghi log lại nếu có lỗi xảy ra (Exception)
            logger.error("Lỗi hệ thống tại URI: " + req.getRequestURI(), t);
            if (t instanceof IOException) throw (IOException) t;
            if (t instanceof ServletException) throw (ServletException) t;
            throw new ServletException(t);
        } finally {
            // Ghi log thông tin truy cập sau khi hoàn thành
            long duration = System.currentTimeMillis() - startTime;
            logger.info("IP: {} | Method: {} | URI: {} | Time: {}ms",
                    request.getRemoteAddr(), req.getMethod(), req.getRequestURI(), duration);
        }
    }
}