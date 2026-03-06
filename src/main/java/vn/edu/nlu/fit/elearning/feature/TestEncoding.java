package vn.edu.nlu.fit.elearning.feature;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TestEncoding", value = "/TestEncoding")
public class TestEncoding extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("ISO-8859-1");
//        response.setCharacterEncoding("ISO-8859-1");

//       request.setCharacterEncoding("UTF");
//       Các tham số trong URL (Query String, dành cho GET) được máy chủ Tomcat giải mã
//       TRƯỚC KHI yêu cầu được chuyển đến phương thức doGet() của Servlet của bạn.
//
//       Quyết định về bảng mã (UTF-8 hay ISO-8859-1) đã được đưa ra dựa trên cấu hình <Connector> trong server.xml
//      (hoặc cấu hình mặc định ẩn của Tomcat/IntelliJ).
//
//      Khi bạn gọi String name = request.getParameter("name");, chuỗi name đã được tạo ra.

//      Phương thức request.setCharacterEncoding() chỉ được thiết kế để áp dụng cho phần Body của Request (thường dùng cho POST request).
//      Nó không ảnh hưởng đến cách Tomcat giải mã các tham số trong URL (Query String).

//      GET/URL (Request Giải mã): Phải đặt URIEncoding="UTF-8" trong server.xml.
//
//      POST Body (Request Giải mã): Phải gọi request.setCharacterEncoding("UTF-8") trong doPost (hoặc dùng Filter).
//
//      Response (Hiển thị): Phải đặt response.setContentType("...charset=UTF-8") (hoặc JSP Directive) để trình duyệt hiển thị.

        response.getWriter().println("<h1>Name nhận được trên Server:</h1>");
        response.getWriter().println("<p>" + name + "</p>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}