package vn.edu.nlu.fit.elearning.common.container;

import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDaoImpl;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDaoImpl;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course.dao.CourseDao;
import vn.edu.nlu.fit.elearning.feature.course.dao.CourseDaoImpl;
import vn.edu.nlu.fit.elearning.feature.course_tag.dao.CourseTagDao;
import vn.edu.nlu.fit.elearning.feature.course_tag.dao.CourseTagDaoImpl;
import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDaoImpl;
import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDao;
import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.lesson.dao.LessonDao;
import vn.edu.nlu.fit.elearning.feature.lesson.dao.LessonDaoImpl;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dao.UserLessonProgressDao;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dao.UserLessonProgressDaoImpl;
import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDao;
import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDaoImpl;
import vn.edu.nlu.fit.elearning.feature.order_item.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.feature.order_item.dao.OrderItemDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDao;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDao;
import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDaoImpl;
import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDao;
import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDaoImpl;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDaoImpl;
import vn.edu.nlu.fit.elearning.feature.user.dao.UserDao;
import vn.edu.nlu.fit.elearning.feature.user.dao.UserDaoImpl;
import vn.edu.nlu.fit.elearning.feature.wishlist.dao.WishlistDao;
import vn.edu.nlu.fit.elearning.feature.wishlist.dao.WishlistDaoImpl;

import java.util.HashMap;
import java.util.Map;

public class BeanContainer {

    private static final Map<Class<?>, Object> beans = new HashMap<>();

    static {
        AccessTokenDao accessTokenDao = new AccessTokenDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        CourseDao courseDao = new CourseDaoImpl();
        CourseTagDao courseTagDao = new CourseTagDaoImpl();
        DashboardDao dashboardDao = new DashboardDaoImpl();
        EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();
        LessonDao lessonDao = new LessonDaoImpl();
        UserLessonProgressDao userLessonProgressDao = new UserLessonProgressDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        PaymentDao paymentDao = new PaymentDaoImpl();
        PaymentMethodDao paymentMethodDao = new PaymentMethodDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();
        TagDao tagDao = new TagDaoImpl();
        UserDao userDao = new UserDaoImpl();
        WishlistDao wishlistDao = new WishlistDaoImpl();

        beans.put(CategoryService.class, new CategoryServiceImpl(categoryDao));

    }

    public static <T> T getBean(Class<T> claszz){
        return claszz.cast(beans.get(claszz));
    }

}
