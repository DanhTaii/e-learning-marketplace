package vn.edu.nlu.fit.elearning.feature.index.service;

import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.index.dao.IndexDao;

import java.util.List;

public class IndexServiceImpl implements IndexService {
    private IndexDao indexDao;

    public IndexServiceImpl(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    @Override
    public List<CourseCardDto> getThreeCoursesWereLiked(Integer userId) {
        return indexDao.findThreeCoursesWereLiked(userId);
    }

    @Override
    public List<CourseCardDto> getSixCoursesMostPopular(Integer userId) {
        return indexDao.findSixCoursesMostPopular(userId);
    }

    @Override
    public CourseCardDto getCoursesMostPopular(Integer userId) {
        return indexDao.findCourseMostPopular(userId);
    }

    @Override
    public List<CourseCardDto> getSixCoursesLast(Integer userId) {
        return indexDao.findSixCoursesLast(userId);
    }

}
