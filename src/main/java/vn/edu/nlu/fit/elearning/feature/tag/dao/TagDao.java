package vn.edu.nlu.fit.elearning.feature.tag.dao;

import vn.edu.nlu.fit.elearning.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.List;

public interface TagDao extends BaseCrudDao<Tag, Integer> {
    @Override
    int create(Tag entity);

    @Override
    Tag findById(Integer integer);

    List<Tag> findByName(String name);

    @Override
    List<Tag> findAll();

    @Override
    int update(Tag entity);

    @Override
    int delete(Integer tagId);

    List<TagDto> findTagsByCourseId(int courseId);
}
