package vn.edu.nlu.fit.elearning.feature.user.mapper;

import vn.edu.nlu.fit.elearning.feature.user.dto.UserProfileDto;
import vn.edu.nlu.fit.elearning.feature.user.dto.UserShortDto;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

public class UserMapperImpl {

    public static UserProfileDto toDto(User entity){
        if(entity == null) return null;

        UserProfileDto dto = new UserProfileDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());

        return dto;
    }

    public static UserShortDto toUserShortDto(User entity){
        if(entity == null) return null;

        UserShortDto dto = new UserShortDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());

        return dto;

    }

}
