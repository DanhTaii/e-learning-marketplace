package vn.edu.nlu.fit.elearning.common.container;

import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDaoImpl;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenServiceImpl;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthServiceImpl;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDaoImpl;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryServiceImpl;
import vn.edu.nlu.fit.elearning.feature.certificate.dao.CertificateDao;
import vn.edu.nlu.fit.elearning.feature.certificate.dao.CertificateDaoImp;
import vn.edu.nlu.fit.elearning.feature.certificate.service.CertificateService;
import vn.edu.nlu.fit.elearning.feature.certificate.service.CertificateServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course.admin.dao.CourseAdminDao;
import vn.edu.nlu.fit.elearning.feature.course.admin.dao.CourseAdminDaoImpl;
import vn.edu.nlu.fit.elearning.feature.course.student.dao.CourseDao;
import vn.edu.nlu.fit.elearning.feature.course.student.dao.CourseDaoImpl;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseService;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminServiceImpl;
import vn.edu.nlu.fit.elearning.feature.contact.dao.ContactDao;
import vn.edu.nlu.fit.elearning.feature.contact.dao.ContactDaoImpl;
import vn.edu.nlu.fit.elearning.feature.contact.service.ContactService;
import vn.edu.nlu.fit.elearning.feature.contact.service.ContactServiceImpl;
//import vn.edu.nlu.fit.elearning.feature.course.dao.CourseDao;
//import vn.edu.nlu.fit.elearning.feature.course.dao.CourseDaoImpl;
//import vn.edu.nlu.fit.elearning.feature.course_user.dao.CourseSearchDao;
//import vn.edu.nlu.fit.elearning.feature.course_user.dao.CourseSearchDaoImpl;
//import vn.edu.nlu.fit.elearning.feature.course_user.service.CourseSearchService;
//import vn.edu.nlu.fit.elearning.feature.course_user.service.CourseSearchServiceImpl;
//import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
//import vn.edu.nlu.fit.elearning.feature.course.service.CourseServiceImpl;
import vn.edu.nlu.fit.elearning.feature.course_tag.dao.CourseTagDao;
import vn.edu.nlu.fit.elearning.feature.course_tag.dao.CourseTagDaoImpl;
import vn.edu.nlu.fit.elearning.feature.course_tag.service.CourseTagService;
import vn.edu.nlu.fit.elearning.feature.course_tag.service.CourseTagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDaoImpl;
import vn.edu.nlu.fit.elearning.feature.dashboard.service.DashboardService;
import vn.edu.nlu.fit.elearning.feature.dashboard.service.DashboardServiceImpl;
import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDao;
import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentServiceImpl;
import vn.edu.nlu.fit.elearning.feature.index.dao.IndexDao;
import vn.edu.nlu.fit.elearning.feature.index.dao.IndexDaoImpl;
import vn.edu.nlu.fit.elearning.feature.index.service.IndexService;
import vn.edu.nlu.fit.elearning.feature.index.service.IndexServiceImpl;
import vn.edu.nlu.fit.elearning.feature.lesson.dao.LessonDao;
import vn.edu.nlu.fit.elearning.feature.lesson.dao.LessonDaoImpl;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonServiceImpl;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dao.UserLessonProgressDao;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.dao.UserLessonProgressDaoImpl;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressServiceImpl;
import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDao;
import vn.edu.nlu.fit.elearning.feature.order.dao.OrderDaoImpl;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderServiceImpl;
import vn.edu.nlu.fit.elearning.feature.order_item.dao.OrderItemDao;
import vn.edu.nlu.fit.elearning.feature.order_item.dao.OrderItemDaoImpl;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemService;
import vn.edu.nlu.fit.elearning.feature.order_item.service.OrderItemServiceImpl;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDao;
import vn.edu.nlu.fit.elearning.feature.payment.dao.PaymentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentService;
import vn.edu.nlu.fit.elearning.feature.payment.service.PaymentServiceImpl;
import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDao;
import vn.edu.nlu.fit.elearning.feature.payment_method.dao.PaymentMethodDaoImpl;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodService;
import vn.edu.nlu.fit.elearning.feature.payment_method.service.PaymentMethodServiceImpl;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.dao.PermissionDao;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.dao.PermissionDaoImpl;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.service.PermissionService;
import vn.edu.nlu.fit.elearning.feature.authorization.permission.service.PermissionServiceImpl;
import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDao;
import vn.edu.nlu.fit.elearning.feature.review.dao.ReviewDaoImpl;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewService;
import vn.edu.nlu.fit.elearning.feature.review.service.ReviewServiceImpl;
import vn.edu.nlu.fit.elearning.feature.authorization.role.dao.RoleDao;
import vn.edu.nlu.fit.elearning.feature.authorization.role.dao.RoleDaoImpl;
import vn.edu.nlu.fit.elearning.feature.authorization.role.service.RoleService;
import vn.edu.nlu.fit.elearning.feature.authorization.role.service.RoleServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDao;
import vn.edu.nlu.fit.elearning.feature.tag.dao.TagDaoImpl;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.admin.dao.UserAdminDao;
import vn.edu.nlu.fit.elearning.feature.user.admin.dao.UserAdminDaoImpl;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.student.dao.UserDao;
import vn.edu.nlu.fit.elearning.feature.user.student.dao.UserDaoImpl;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserServiceImpl;
import vn.edu.nlu.fit.elearning.feature.wishlist.dao.WishlistDao;
import vn.edu.nlu.fit.elearning.feature.wishlist.dao.WishlistDaoImpl;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistService;
import vn.edu.nlu.fit.elearning.feature.wishlist.service.WishlistServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class BeanContainer {

    private static final Map<Class<?>, Object> beans = new HashMap<>();

    static {
        AccessTokenDao accessTokenDao = new AccessTokenDaoImpl();
        beans.put(AccessTokenService.class, new AccessTokenServiceImpl(accessTokenDao));

        UserDao userDao = new UserDaoImpl();
        UserAdminDao userAdminDao = new UserAdminDaoImpl();

        UserAdminService userAdminService = new UserAdminServiceImpl(userAdminDao);
        beans.put(UserAdminService.class, userAdminService);

        beans.put(UserService.class, new UserServiceImpl(userDao, userAdminDao));

        UserServiceImpl userService = (UserServiceImpl) beans.get(UserService.class);
        beans.put(AuthService.class, new AuthServiceImpl(userService, userAdminService));

        CategoryDao categoryDao = new CategoryDaoImpl();
        beans.put(CategoryService.class, new CategoryServiceImpl(categoryDao));

        CourseAdminDao courseAdminDao = new CourseAdminDaoImpl();
        beans.put(CourseAdminService.class, new CourseAdminServiceImpl(courseAdminDao));

        CourseDao courseDao = new CourseDaoImpl();
        beans.put(CourseService.class, new CourseServiceImpl(courseDao));

//        CourseSearchDao courseSearchDao = new CourseSearchDaoImpl();
//        beans.put(CourseSearchService.class, new CourseSearchServiceImpl(courseSearchDao));

        CourseTagDao courseTagDao = new CourseTagDaoImpl();
        beans.put(CourseTagService.class, new CourseTagServiceImpl(courseTagDao));

        DashboardDao dashboardDao = new DashboardDaoImpl();
        beans.put(DashboardService.class, new DashboardServiceImpl(dashboardDao));

        EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();
        beans.put(EnrollmentService.class, new EnrollmentServiceImpl(enrollmentDao));

        LessonDao lessonDao = new LessonDaoImpl();
        beans.put(LessonService.class, new LessonServiceImpl(lessonDao));

        UserLessonProgressDao userLessonProgressDao = new UserLessonProgressDaoImpl();
        beans.put(UserLessonProgressService.class, new UserLessonProgressServiceImpl(userLessonProgressDao));

        OrderDao orderDao = new OrderDaoImpl();
        beans.put(OrderService.class, new OrderServiceImpl(orderDao));

        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        beans.put(OrderItemService.class, new OrderItemServiceImpl(orderItemDao));

        PaymentMethodDao paymentMethodDao = new PaymentMethodDaoImpl();
        beans.put(PaymentMethodService.class, new PaymentMethodServiceImpl(paymentMethodDao));

        PaymentDao paymentDao = new PaymentDaoImpl();
        beans.put(PaymentService.class, new PaymentServiceImpl(paymentDao));

        ReviewDao reviewDao = new ReviewDaoImpl();
        beans.put(ReviewService.class, new ReviewServiceImpl(reviewDao));

        TagDao tagDao = new TagDaoImpl();
        beans.put(TagService.class, new TagServiceImpl(tagDao));

        IndexDao indexDao = new IndexDaoImpl();
        beans.put(IndexService.class, new IndexServiceImpl(indexDao));

        WishlistDao wishlistDao = new WishlistDaoImpl();
        beans.put(WishlistService.class, new WishlistServiceImpl(wishlistDao));

        PermissionDao permissionDao = new PermissionDaoImpl();
        beans.put(PermissionService.class, new PermissionServiceImpl(permissionDao));

        RoleDao roleDao = new RoleDaoImpl();
        beans.put(RoleService.class, new RoleServiceImpl(roleDao));

        ContactDao contactDao = new ContactDaoImpl();
        beans.put(ContactService.class, new ContactServiceImpl(contactDao));

        beans.put(UserAdminService.class, new UserAdminServiceImpl(userAdminDao));

        CertificateDao certificateDao = new CertificateDaoImp();
        beans.put(CertificateService.class, new CertificateServiceImpl(certificateDao));

    }

    public static <T> T getBean(Class<T> clazz){
        return clazz.cast(beans.get(clazz));
    }

}