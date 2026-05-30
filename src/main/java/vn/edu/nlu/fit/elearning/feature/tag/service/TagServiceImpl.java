package vn.edu.nlu.fit.elearning.feature.tag.service;

import vn.edu.nlu.fit.elearning.common.cache.CacheService;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.List;

public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final CacheService cacheService;

    // Define cache keys as constants for consistency and to avoid magic strings
    private static final String TAG_CACHE_REGION = "TAGS";
    private static final String ALL_TAGS_KEY = TAG_CACHE_REGION + "::ALL";
    private static final String TAGS_BY_COURSE_ID_PREFIX = TAG_CACHE_REGION + "::COURSE_ID::";

    public TagServiceImpl(TagDao tagDao, CacheService cacheService) {
        this.tagDao = tagDao;
        this.cacheService = cacheService;
    }

    @Override
    public int createTag(Tag tag) {
        if (tag != null) {
            int result = tagDao.create(tag);
            if (result > 0) {
                // Tạo thành công thì xóa bộ nhớ RAM
                invalidateGeneralTagCaches();
            }
            return result;
        }
        return 0;
    }

    @Override
    public int updateTag(Tag tag) {
        int result = tagDao.update(tag);
        if (result > 0) {
            // Cập nhật thành công thì xóa bộ nhớ RAM
            invalidateGeneralTagCaches();
        }
        return result;
    }

//    @Override
//    public int deleteTag(int tagId) {
//        int result = tagDao.delete(tagId);
//        if (result > 0) {
//            invalidateGeneralTagCaches();
//        }
//        return result;
//    }

    @Override
    public boolean deleteTags(int tagId) {
        int status = tagDao.delete(tagId);
        if (status > 0) {
            // Xóa thành công thì xóa bộ nhớ RAM
            invalidateGeneralTagCaches();
        }
        return status > 0;
    }

    @Override
    public List<Tag> getAllTags() {
        long startTime = System.currentTimeMillis();

        // Tìm thử danh sách tag trong cache trước
        List<Tag> tags = cacheService.get(ALL_TAGS_KEY, List.class);

        if (tags != null) {
            long endTime = System.currentTimeMillis();
            System.out.println("CACHE HIT: Lấy " + tags.size() + " tags từ RAM! Thời gian: " + (endTime - startTime) + "ms");
            return tags;
        }

        // KHÔNG CÓ TRONG RAM
        tags = tagDao.findAll();
        if (tags != null) {
            cacheService.put(ALL_TAGS_KEY, tags);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("CACHE MISS: Chọc xuống MySQL lấy Tags! Thời gian: " + (endTime - startTime) + "ms");

        return tags;
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.findById(id);
    }

    @Override
    public List<TagDto> getTagsByCourseId(int courseId) {
        long startTime = System.currentTimeMillis();

        final String cacheKey = TAGS_BY_COURSE_ID_PREFIX + courseId;
        List<TagDto> tags = cacheService.get(cacheKey, List.class);

        if (tags == null) {
            tags = tagDao.findTagsByCourseId(courseId);
            cacheService.put(cacheKey, tags);
            long endTime2 = System.currentTimeMillis();
            // IN RA MÀN HÌNH NẾU LẤY TỪ DATABASE
            System.out.println("CACHE MISS: Chọc xuống MySQL lấy Tags theo khóa học Thời gian: " + (endTime2 - startTime) + "ms");
        } else {
            long endTime = System.currentTimeMillis();
            System.out.println("CACHE HIT: Lấy Tag theo khóa học từ RAM! Thời gian: " + (endTime - startTime) + "ms");
        }
        return tags;
    }

//    @Override
//    public int countTags() {
//        Integer count = cacheService.get(TAG_COUNT_KEY, Integer.class);
//        if (count == null) {
//            count = tagDao.countTags();
//            cacheService.put(TAG_COUNT_KEY, count);
//        }
//        return count;
//    }

    private void invalidateGeneralTagCaches() {
        cacheService.invalidate(ALL_TAGS_KEY);
    }

    @Override
    public List<Tag> getAllTagsByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public List<Tag> searchTags(TagFilter filter) {
        return tagDao.findTags(filter);
    }

    @Override
    public boolean existsByName(String name) {
        List<Tag> list = tagDao.findByName(name);
        return list != null && !list.isEmpty();
    }

    @Override
    public boolean existsBySlug(String slug) {
        Tag tag = tagDao.findBySlug(slug);
        return tag != null;
    }

    @Override
    public boolean existsBySlug(String slug, int excludeId) {
        Tag tag = tagDao.findBySlugExcludeId(slug, excludeId);
        return tag != null;
    }

    @Override
    public int getCountTagsByFilter(TagFilter filter) {
        return tagDao.countTagsByFilter(filter);
    }
}
