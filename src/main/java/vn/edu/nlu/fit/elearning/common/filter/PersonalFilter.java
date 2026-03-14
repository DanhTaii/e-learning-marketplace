package vn.edu.nlu.fit.elearning.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.io.IOException;

@WebFilter(filterName = "PersonalFilter", urlPatterns = {"/personal/*", "/add-cart", "/buy-now"})
public class PersonalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        User user = (session != null) ? (User) session.getAttribute("userSession") : null;
        if (user != null) {
            chain.doFilter(request, response);
        } else {
            boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

            if (isAjax) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Unauthenticated");
            } else {
                req.setAttribute("error", "Vui lòng đăng nhập!");
                req.getRequestDispatcher("/views/pages/auth/sign-in.jsp").forward(req, res);
            }

        }
    }


    @Override
    public void destroy() {

    }
}
