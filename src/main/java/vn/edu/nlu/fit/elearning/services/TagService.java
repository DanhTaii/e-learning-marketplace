package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.TagDao;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Tag;

import java.util.List;

public class TagService {

    private TagDao tagDao;

    public TagService() {
        this.tagDao = new TagDao();
    }

    public int createTag(Tag tag) {
        if (tag != null) {
            tagDao.create(tag);
            return 1;
        }
        return 0;
    }

    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

    public Tag getTagById(int id) {
        return tagDao.findById(id);
    }

    public int updateTag(Tag tag) {
     return tagDao.update(tag);

    }

    public void deleteTag(int tagId) {

    }

    public List<Tag> getAllTagsByName(String name) {
        return tagDao.findByName(name);
    }

    public boolean deleteTags(int tagId) {
        int status = tagDao.delete(tagId);
        return status > 0;
    }
}
