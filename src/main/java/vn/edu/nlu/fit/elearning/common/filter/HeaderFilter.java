package vn.edu.nlu.fit.elearning.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;
//        {
//                "/index",
//                "/course-detail/*",
//                "/result-search/*",
//                "/pagination-all-courses/*",
//                "/sign-up/*",
//                "/personal/my-course",
//                "/personal/my-wishlist",
//                "/personal/result-search/*",
//                "/personal/cart/*"
//                }
@WebFilter(filterName = "HeaderFilter", urlPatterns = "/*")
public class HeaderFilter implements Filter {
    private CategoryService categoryService;
    private TagService tagService;

    public void init(FilterConfig config) throws ServletException {
        this.categoryService = BeanContainer.getBean(CategoryService.class);
        this.tagService = BeanContainer.getBean(TagService.class);
    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tagService.getAllTags());

        chain.doFilter(request, response);
    }
}