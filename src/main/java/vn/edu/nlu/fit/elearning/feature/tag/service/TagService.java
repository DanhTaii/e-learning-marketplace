package vn.edu.nlu.fit.elearning.feature.tag.service;

import vn.edu.nlu.fit.elearning.common.utils.search.TagFilter;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.List;

public interface TagService {
    int createTag(Tag tag);

    List<Tag> getAllTags();

    Tag getTagById(int id);

    int updateTag(Tag tag);

    void deleteTag(int tagId);

    List<Tag> getAllTagsByName(String name);

    boolean deleteTags(int tagId);

    List<TagDto> getTagsByCourseId(int courseId);

    List<Tag> searchTags(TagFilter filter);

    int countTags();

}
