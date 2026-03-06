package vn.edu.nlu.fit.elearning.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.io.IOException;

@WebFilter(filterName = "AdminAuthFilter", urlPatterns = "/admin/*")
public class AdminAuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User user = (session != null) ? (User) session.getAttribute("userSession") : null;
        if (user != null && "admin".equalsIgnoreCase(user.getRole())) {
            chain.doFilter(request, response);
        }else {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}