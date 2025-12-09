    package vn.edu.nlu.fit.elearning.controller.auth;

    import jakarta.servlet.*;
    import jakarta.servlet.http.*;
    import jakarta.servlet.annotation.*;
    import vn.edu.nlu.fit.elearning.dao.UserDao;
    import vn.edu.nlu.fit.elearning.model.User;
    import vn.edu.nlu.fit.elearning.services.UserService;

    import java.io.IOException;

    @WebServlet(name = "LoginController", value = "/sign-in")
    public class LoginController extends HttpServlet {

        private UserService userService;

        public LoginController() {
            this.userService = new UserService();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            User canLogin = userService.login(email, pass);
            if (canLogin != null) {
                HttpSession session = request.getSession();

                session.setAttribute("userId", canLogin.getId());
//                System.out.println("DEBUG (Login): User ID " + canLogin.getId() + " Đã được lưu vào Session.");

                if (canLogin.getRole().equalsIgnoreCase("admin")) {
                    response.sendRedirect("admin/dashboard");
                } else {
                    canLogin.setPassword("");
                    response.sendRedirect("index");
                }
            } else {
                request.setAttribute("error", "Bạn nhập sai email hoặc mật khẩu!");
                request.getRequestDispatcher("/html-authentication/sign-in.jsp").forward(request, response);
            }
        }
    }