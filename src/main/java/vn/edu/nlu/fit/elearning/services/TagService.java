package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.TagDao;
import vn.edu.nlu.fit.elearning.model.Tag;

import java.util.List;

public class TagService {

    private TagDao tagDao;

    public TagService() {
        this.tagDao = new TagDao();
    }

    public int createTag(Tag tag) {
        if(tag != null){
            tagDao.create(tag);
        return 1;
        }
            return 0;
    }

    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

    public Tag getTagById(int id) {
        return null;
    }

    public void updateTag(Tag tag) {

    }

    public void deleteTag(int id) {
    }


}
