package vn.edu.nlu.fit.elearning.feature.tag.dao;

import vn.edu.nlu.fit.elearning.common.utils.search.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.List;

public interface TagDao {
    int create(Tag entity);

    Tag findById(Integer integer);

    List<Tag> findByName(String name);

    List<Tag> findAll();

    int update(Tag entity);

    int delete(Integer tagId);

    List<TagDto> findTagsByCourseId(int courseId);

    List<Tag> findTags(TagFilter filter);

    int countTags();

}
