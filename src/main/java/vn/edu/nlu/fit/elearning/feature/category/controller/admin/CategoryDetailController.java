package vn.edu.nlu.fit.elearning.feature.category.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.validator.category.CategoryValidator;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CategoryDetailController", value = "/admin/category/detail")
public class CategoryDetailController extends BaseController {
    private transient CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> listCategory = categoryService.getAllCategories();
            request.setAttribute("listCategory", listCategory);
            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = RequestUtils.getParameterAsInt(request, "id", -1);
                Category category = categoryService.getCategoryById(id);
                if (category != null) {
                    request.setAttribute("category", category);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy danh mục!");
                    return;
                }
            }
            this.forward(request, response, "/views/pages/admin/category/category-create.jsp");
        } catch (Exception e) {
            log("Unexpected error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Category category = new Category();

        int id = RequestUtils.getParameterAsInt(request, "id", -1);
        category.setId(id);

        category.setName(request.getParameter("nameCategory"));
        category.setSlug(request.getParameter("slug"));
        category.setParentId(RequestUtils.getParameterAsInt(request, "parentId", 0));

        BaseStatus status = RequestUtils.getParameterAsStatus(request, "status");
        if (status == null) {
            status = BaseStatus.INACTIVE;
        }
        category.setStatus(status);

        try {
            Map<String, String> errors = CategoryValidator.validate(category);

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("category", category);
                this.forward(request, response, "/views/pages/admin/category/category-create.jsp");
                return;
            }

            if (category.getId() > 0) {

                if (categoryService.existsBySlug(category.getSlug(), category.getId())) {
                    handleError(request, response, "Slug đã tồn tại!");
                    return;
                }

                int result = categoryService.updateCategory(category);

                if (result > 0) {
                    request.getSession().setAttribute("flashSuccess", "Cập nhật danh mục thành công!");
                }

                response.sendRedirect(request.getContextPath() + "/admin/categories");
                return;
            }

            if (categoryService.existsByName(category.getName())) {
                handleError(request, response, "Tên danh mục đã tồn tại!");
                return;
            }

            if (categoryService.existsBySlug(category.getSlug())) {
                handleError(request, response, "Slug đã tồn tại!");
                return;
            }

            int result = categoryService.createCategory(category);

            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/categories");
            } else {
                handleError(request, response, "Lỗi hệ thống khi tạo danh mục!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            this.forward(request, response, "/views/pages/admin/category/category-create.jsp");
        }
        System.out.println("STATUS PARAM: " + request.getParameter("status"));
    }
}