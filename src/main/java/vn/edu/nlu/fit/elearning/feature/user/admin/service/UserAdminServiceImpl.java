package vn.edu.nlu.fit.elearning.feature.user.admin.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.common.helper.excel.UserExcelParser;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user.UserFilter;
import vn.edu.nlu.fit.elearning.common.utils.excel.ExcelReaderUtils;
import vn.edu.nlu.fit.elearning.feature.user.admin.dao.UserAdminDao;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.mapper.UserMapper;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserTableResponse;

import java.io.InputStream;
import java.util.List;

public class UserAdminServiceImpl implements UserAdminService {

    private final UserAdminDao userAdminDao;

    public UserAdminServiceImpl(UserAdminDao userAdminDao) {
        this.userAdminDao = userAdminDao;
    }

    @Override
    public List<UserAdminDto> getAllUsers() {
        return userAdminDao.findAll();
//        return UserMapper.toUserTableDto(users);
    }

    @Override
    public List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role) {
        return userAdminDao.findUsersByFilter(username, phone, createdAt, role);
    }

    @Override
    public int deleteUser(int id) {
        return userAdminDao.delete(id);
    }

    @Override
    public int createUser(User user) {
        return userAdminDao.create(user);
    }

    @Override
    public int updateRole(int userId, UserRoleStatusRequest req) {
        Role role = req.getRole();
        BaseStatus status = req.getStatus();
        return userAdminDao.updateRole(userId, role, status);
    }

    @Override
    public List<UserAdminDto> getUsersByFilter(UserFilter filter) {
        return userAdminDao.findUsersByFilter(filter);
    }

    @Override
    public int countUsersByFilter(UserFilter filter) {
        return userAdminDao.countUsersByFilter(filter);
    }

    @Override
    public UserAdminDto getUserById(int id) {
        return userAdminDao.findById(id);
    }

    @Override
    public int updateUserRoleAndStatus(int userId, int roleId, BaseStatus status) {
        return userAdminDao.updateUserRoleAndStatus(userId, roleId, status);
    }

    @Override
    public int createUser(UserAdminDto user) {
        return userAdminDao.create(user);
    }

    @Override
    public int createListUsers(List<User> users) {
        return userAdminDao.createList(users);
    }

    @Override
    public List<User> importUsersFromExcel(InputStream inputStream, List<String> errorMessages) throws Exception {
        return ExcelReaderUtils.readExcel(inputStream, UserExcelParser::parseRowToUser, errorMessages);
    }

}
