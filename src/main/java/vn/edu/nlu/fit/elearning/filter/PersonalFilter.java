package vn.edu.nlu.fit.elearning.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.model.User;

import java.io.IOException;

@WebFilter(filterName = "PersonalFilter", urlPatterns = "/personal/*")
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
        if (user != null ) {
            chain.doFilter(request, response);
        }else {
            req.setAttribute("error", "Vui lòng đăng nhập!");
            req.getRequestDispatcher("/html-authentication/sign-in.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {

    }
}
