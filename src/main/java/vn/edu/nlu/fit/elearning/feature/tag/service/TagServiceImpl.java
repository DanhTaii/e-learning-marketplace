package vn.edu.nlu.fit.elearning.feature.tag.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.List;

public class TagServiceImpl implements TagService {

    private TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public int createTag(Tag tag) {
        if (tag != null) {
            return tagDao.create(tag);
        }
        return 0;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.findById(id);
    }

    @Override
    public int updateTag(Tag tag) {
     return tagDao.update(tag);

    }

    @Override
    public int deleteTag(int tagId) {
        return tagDao.delete(tagId);
    }

    @Override
    public List<Tag> getAllTagsByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public boolean deleteTags(int tagId) {
        int status = tagDao.delete(tagId);
        return status > 0;
    }

    @Override
    public List<TagDto> getTagsByCourseId(int courseId) {
        return tagDao.findTagsByCourseId(courseId);
    }

    @Override
    public List<Tag> searchTags(TagFilter filter) {
        return tagDao.findTags(filter);
    }

    @Override
    public int countTags() {
        return tagDao.countTags();
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
