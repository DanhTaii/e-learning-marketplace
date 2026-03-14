package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.category.service.ICategoryService;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course_tag.service.CourseTagService;
import vn.edu.nlu.fit.elearning.feature.course_tag.service.CourseTagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.helper.enums.Level;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseDetailController", value = "/admin/course/detail")
public class CourseDetailController extends HttpServlet {

    private CourseServiceImpl cs;
    private TagService tagService;
    private ICategoryService ICategoryService;
    private CourseTagService courseTagService;
    private CourseServiceImpl courseServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = new CourseServiceImpl();
        this.ICategoryService = new CategoryService();
        this.tagService = new TagServiceImpl();
        this.courseTagService = new CourseTagServiceImpl();
        this.courseServiceImpl = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        // Luôn lấy Categories và Tags để hiển thị danh sách lựa chọn (Dù tạo hay sửa)
        List<Category> categoryList = ICategoryService.getAllCategories();
        List<Tag> tagList = tagService.getAllTags();

        request.setAttribute("categories", categoryList);
        request.setAttribute("tags", tagList);

        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Course c = cs.getCourseById(id);
                // Lấy thêm danh sách ID các tag mà khóa học này ĐÃ CÓ (để check vào checkbox)
                List<Integer> tagIdList = courseTagService.getAllTagIdByCourseId(id);

                request.setAttribute("course", c);
                request.setAttribute("courseTagIdList", tagIdList);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        request.getRequestDispatcher("/views/pages/admin/course/course-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String title = request.getParameter("title");
        String subtitle = request.getParameter("subtitle");
        String levelStr = request.getParameter("level");
        String goals = request.getParameter("goals");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String discountStr = request.getParameter("discount_price");
        String categoryIdStr = request.getParameter("category_id");
        String[] tagIdsStr = request.getParameterValues("tags");

        String error;
        if ((error = checkLength(title, "Tên khóa học", 10, 150)) != null) {
            handleError(request, response, error);
            return;
        }
        if ((error = checkLength(subtitle, "Phụ đề", 10, 250)) != null) {
            handleError(request, response, error);
            return;
        }
        if (priceStr == null || priceStr.isBlank()) {
            handleError(request, response, "Bạn chưa nhập giá gốc");
            return;
        }
        if ((error = checkLength(goals, "Mục tiêu", 20, 1000)) != null) {
            handleError(request, response, error);
            return;
        }
        if ((error = checkLength(description, "Mô tả", 50, 5000)) != null) {
            handleError(request, response, error);
            return;
        }
        if (categoryIdStr == null || categoryIdStr.isBlank()) {
            handleError(request, response, "Bạn chưa chọn danh mục");
            return;
        }

        // 3. Ép kiểu dữ liệu (Dùng hàm helper đã viết ở trên)
        int price = parseIntOrDefault(priceStr, -1);
        int discountPrice = parseIntOrDefault(discountStr, 0);
        int categoryId = parseIntOrDefault(categoryIdStr, -1);

        if (price < 0) {
            handleError(request, response, "Giá gốc phải là số dương");
            return;
        }
        if (discountPrice < 0) {
            handleError(request, response, "Giá giảm phải là số dương");
            return;
        }
        if (discountPrice > price) {
            handleError(request, response, "Giá giảm không được lớn hơn giá gốc");
            return;
        }

        //Tự xét cấp độ nếu không có
        Level level = null;
        if (levelStr != null && !levelStr.isBlank()) {
            level = Level.valueOf(levelStr);
        } else {
            level = Level.BEGINNER;
        }

        Course course = new Course();
        course.setTitle(title);
        course.setSubtitle(subtitle);
        course.setGoals(goals);
        course.setDescription(description);
        course.setPrice(price);
        course.setDiscountPrice(discountPrice);
        course.setCategoryId(categoryId);
        course.setIsPublic(Boolean.parseBoolean(request.getParameter("status")));
        course.setThumbnailUrl(request.getParameter("thumbnail"));
        course.setAuthorName("Quản trị viên");
        course.setLevel(level);

        int checkCourseCreate = 0;
        boolean isUpdate = (courseId != null && !courseId.isEmpty());

        if (isUpdate) {
            int courseIdInt = Integer.parseInt(courseId);
            course.setId(courseIdInt);
//          Cập nhật tag mới
            if (tagIdsStr != null) {
                courseTagService.deleteCourseTag(courseIdInt);
                courseTagService.createCourseTag(courseIdInt, tagIdsStr);
            }
//          Cập nhật khóa học
            checkCourseCreate = courseServiceImpl.updateCourse(course);

            if (checkCourseCreate > 0) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật khóa học thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/course/detail?id=" + courseIdInt);
            }

        } else if (!isUpdate) {
//            Trả về course id
            checkCourseCreate = courseServiceImpl.createCourse(course);
            if (checkCourseCreate > 0) {
                if (tagIdsStr != null) {
                    courseTagService.createCourseTag(checkCourseCreate, tagIdsStr);
                }
                request.getSession().setAttribute("flashSuccess", "Tạo khóa học thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/courses");
            }
        }
    }

    // Hàm 1: Gửi lỗi và dừng cuộc chơi nhanh gọn
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.getSession().setAttribute("flashError", msg);
        doGet(req, resp);
    }

    //Ép kiểu số an toàn, nếu lỗi hoặc rỗng thì trả về giá trị mặc định
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return (value == null || value.isBlank()) ? defaultValue : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String checkLength(String value, String label, int min, int max) {
        if (value == null || value.trim().isEmpty()) {
            return "Vui lòng nhập " + label;
        }
        String trimmedValue = value.trim();
        if (trimmedValue.length() < min) {
            return label + " phải có ít nhất " + min + " ký tự";
        }
        if (trimmedValue.length() > max) {
            return label + " không được vượt quá " + max + " ký tự";
        }
        return null;
    }
}