package vn.edu.nlu.fit.elearning.feature.user.mapper;

import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserProfileResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserTableResponse;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserProfileResponse toUserProfileDto(User entity) {
        if (entity == null) return null;

        UserProfileResponse dto = new UserProfileResponse();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());

        return dto;
    }

    public static UserDetailResponse toUserDetailDto(User entity) {
        if (entity == null) return null;

        UserDetailResponse dto = new UserDetailResponse();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public static UserShortResponse toUserShortDto(User entity) {
        if (entity == null) return null;

        UserShortResponse dto = new UserShortResponse();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());

        return dto;

    }

    public static List<UserTableResponse> toUserTableDto(List<User> entity) {
        List<UserTableResponse> result = new ArrayList<>();
        if (entity == null) return result;

        for (User u : entity) {
            if (u == null) continue;
            result.add(new UserTableResponse(u.getId(), u.getUsername(), u.getEmail(), u.getPhone(), u.getRole(), u.getStatus(), u.getCreatedAt()));
        }

        return result;
    }

}
