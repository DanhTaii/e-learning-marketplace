package vn.edu.nlu.fit.elearning.feature.category.service;

import vn.edu.nlu.fit.elearning.common.cache.CacheService;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryOptionDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final CacheService cacheService;
    private static final String CACHE_KEY_ALL_CATEGORIES = "all_categories";

    public CategoryServiceImpl(CategoryDao categoryDao, CacheService cacheService) {
        this.categoryDao = categoryDao;
        this.cacheService = cacheService;
    }

    @Override
    public int createCategory(Category category) {
        if (category != null) {
            int result = categoryDao.create(category);
            if (result > 0) {
                cacheService.invalidate(CACHE_KEY_ALL_CATEGORIES);
                return result;
            }
        }
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        // Bắt đầu đếm thời gian
        long startTime = System.currentTimeMillis();

        // trong CacheService chứa 1 cái gọi là
        List<Category> categories = (List<Category>) cacheService.get(CACHE_KEY_ALL_CATEGORIES);

        if (categories == null) {
            categories = categoryDao.findAll();
            cacheService.put(CACHE_KEY_ALL_CATEGORIES, categories);
            long endTime2 = System.currentTimeMillis();
            // IN RA MÀN HÌNH NẾU LẤY TỪ DATABASE
            System.out.println("CACHE MISS: Xuống DB lấy danh mục! Thời gian: " + (endTime2 - startTime) + "ms");
        } else {
            long endTime = System.currentTimeMillis();
            // IN RA MÀN HÌNH NẾU LẤY TỪ RAM
            System.out.println("CACHE HIT: Lấy danh mục từ RAM! Thời gian: " + (endTime - startTime) + "ms");
        }
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public int updateCategory(Category category) {
        int result = categoryDao.update(category);
        if (result > 0) {
            cacheService.invalidate(CACHE_KEY_ALL_CATEGORIES);
        }
        return result;
    }

    @Override
    public int deleteCategory(int id) {
        int result = categoryDao.delete(id);
        if (result > 0) {
            cacheService.invalidate(CACHE_KEY_ALL_CATEGORIES);
        }
        return result;
    }

    @Override
    public List<Category> getAllCategoriesByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public CategoryDto getCategoryByCourseId(int courseId) {
        return categoryDao.findCategoryByCourseId(courseId);
    }

    @Override
    public boolean existsByName(String name) {
        List<Category> list = categoryDao.findByName(name);
        return list != null && !list.isEmpty();
    }

    @Override
    public boolean existsBySlug(String slug) {
        Category c = categoryDao.findBySlug(slug);
        return c != null;
    }

    @Override
    public boolean existsBySlug(String slug, int excludeId) {
        Category c = categoryDao.findBySlugExcludeId(slug, excludeId);
        return c != null;
    }

    @Override
    public List<Category> getCategoriesByFilter(CategoryFilter filter) {
        return categoryDao.findCategoriesByFilter(filter);
    }

    @Override
    public int getCountCategoriesByFilter(CategoryFilter filter) {
        return categoryDao.countCategoriesByFilter(filter);
    }

    @Override
    public List<CategoryOptionDto> getCategoriesIdAndName() {
        return categoryDao.findIdAndName();
    }


}
