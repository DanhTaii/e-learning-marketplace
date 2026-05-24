package vn.edu.nlu.fit.elearning.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AdminAuthFilter", urlPatterns = "/admin/*")
public class AdminAuthFilter implements Filter {

    private AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(AdminAuthFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.authService = BeanContainer.getBean(AuthService.class);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {

        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            HttpSession session = req.getSession(false);

            UserShortResponse user =
                    (session != null)
                            ? (UserShortResponse) session.getAttribute("userSession")
                            : null;

            // chưa đăng nhập
            if (user == null) {
                res.sendRedirect(req.getContextPath() + "/sign-in");
                return;
            }

            Set<String> userPermissions =
                    authService.getUserPermissions(user.getId());

            // có quyền admin mới được vào /admin/*
            if (userPermissions.contains("ADMIN_ACCESS")) {

                chain.doFilter(request, response);

            } else {

                res.sendError(HttpServletResponse.SC_FORBIDDEN);

            }

        } catch (Exception e) {

            logger.error("Error AdminAuthFilter", e);

            ((HttpServletResponse) response)
                    .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }
}