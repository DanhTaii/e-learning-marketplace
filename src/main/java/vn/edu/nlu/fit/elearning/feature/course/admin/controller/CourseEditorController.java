package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.Level;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.course_tag.service.CourseTagService;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "CourseEditorController", value = "/admin/course/editor")
public class CourseEditorController extends BaseController {
    private CourseAdminService cs;
    private TagService tagService;
    private CategoryService ICategoryService;
    private CourseTagService courseTagService;
    private CourseAdminService courseAdminServiceImpl;
    private transient LessonService lessonService;
    private static final Logger logger = LoggerFactory.getLogger(CourseEditorController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = BeanContainer.getBean(CourseAdminService.class);
        this.ICategoryService = BeanContainer.getBean(CategoryService.class);
        this.tagService = BeanContainer.getBean(TagService.class);
        this.courseTagService = BeanContainer.getBean(CourseTagService.class);
        this.courseAdminServiceImpl = BeanContainer.getBean(CourseAdminService.class);
        this.lessonService = BeanContainer.getBean(LessonService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Luôn lấy Categories và Tags để hiển thị danh sách lựa chọn (Dù tạo hay sửa)
            List<Category> categoryList = ICategoryService.getAllCategories();
            List<Tag> tagList = tagService.getAllTags();

            request.setAttribute("categories", categoryList);
            request.setAttribute("tags", tagList);

            int id = RequestUtils.getParameterAsInt(request, "id", 0);
            Course c = cs.getCourseById(id);
            // Lấy thêm danh sách ID các tag mà khóa học này ĐÃ CÓ (để check vào checkbox)
            List<Integer> tagIdList = courseTagService.getAllTagIdByCourseId(id);
            List<Lesson> lessons = lessonService.getLessonsByCourseId(id);

            String lessonIdParam = request.getParameter("lessonId");
            Lesson currentLesson = null;
            if (lessonIdParam != null && !lessonIdParam.trim().isEmpty()) {
                int lessonId = Integer.parseInt(lessonIdParam);
                if (lessonId > 0) {
                    currentLesson = lessonService.getLessonById(lessonId);
                }
            } else {
                if (lessons != null && !lessons.isEmpty()) {
                    currentLesson = lessons.getFirst();
                }
            }

            request.setAttribute("lessons", lessons);
            request.setAttribute("lesson", currentLesson);
            request.setAttribute("course", c);
            request.setAttribute("courseTagIdList", tagIdList);

            request.getRequestDispatcher("/views/pages/admin/course/editor/course-editor.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error in CourseAdminDetailController: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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
            if ((error = ValidationUtils.checkLength(title, "Tên khóa học", 10, 150)) != null) {
                handleError(request, response, error);
                return;
            }
            if ((error = ValidationUtils.checkLength(subtitle, "Phụ đề", 10, 250)) != null) {
                handleError(request, response, error);
                return;
            }
            if (priceStr == null || priceStr.isBlank()) {
                handleError(request, response, "Bạn chưa nhập giá gốc");
                return;
            }
            if ((error = ValidationUtils.checkLength(goals, "Mục tiêu của khóa học", 20, 1000)) != null) {
                handleError(request, response, error);
                return;
            }
            if ((error = ValidationUtils.checkLength(description, "Mô tả của khóa học", 50, 5000)) != null) {
                handleError(request, response, error);
                return;
            }
            if (categoryIdStr == null || categoryIdStr.isBlank()) {
                handleError(request, response, "Bạn chưa chọn danh mục");
                return;
            }

            // 3. Ép kiểu dữ liệu (Dùng hàm helper đã viết ở trên)
            int price = RequestUtils.getParameterAsIntOrDefault(priceStr, -1);
            int discountPrice = RequestUtils.getParameterAsIntOrDefault(discountStr, 0);
            int categoryId = RequestUtils.getParameterAsIntOrDefault(categoryIdStr, -1);

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

            request.setAttribute("activeTab", "overview");

            if (isUpdate) {
                int courseIdInt = Integer.parseInt(courseId);
                course.setId(courseIdInt);
//          Cập nhật tag mới
                if (tagIdsStr != null) {
                    courseTagService.deleteCourseTag(courseIdInt);
                    courseTagService.createCourseTag(courseIdInt, tagIdsStr);
                }
//          Cập nhật khóa học
                checkCourseCreate = courseAdminServiceImpl.updateCourse(course);

                if (checkCourseCreate > 0) {
                    request.getSession().setAttribute("flashSuccess", "Cập nhật khóa học thành công !");
                    response.sendRedirect(request.getContextPath() + "/admin/course/editor?id=" + courseIdInt);
                }

            } else if (!isUpdate) {
//            Trả về course id
                checkCourseCreate = courseAdminServiceImpl.createCourse(course);
                if (checkCourseCreate > 0) {
                    if (tagIdsStr != null) {
                        courseTagService.createCourseTag(checkCourseCreate, tagIdsStr);
                    }
                    request.getSession().setAttribute("flashSuccess", "Tạo khóa học thành công !");
                    response.sendRedirect(request.getContextPath() + "/admin/course/editor?id=" + checkCourseCreate);
                }
            }
        } catch (Exception e) {
            logger.error("Error in CourseEditorController: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            return;
        }

    }
}