package vn.edu.nlu.fit.elearning.feature.tag.service;

import vn.edu.nlu.fit.elearning.common.utils.search.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDaoImpl;
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
            tagDao.create(tag);
            return 1;
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
    public void deleteTag(int tagId) {

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

}
