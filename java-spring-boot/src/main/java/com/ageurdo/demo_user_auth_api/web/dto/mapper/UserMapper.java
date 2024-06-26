package com.ageurdo.demo_user_auth_api.web.dto.mapper;

import com.ageurdo.demo_user_auth_api.entity.User;
import com.ageurdo.demo_user_auth_api.web.dto.UserCreateDto;
import com.ageurdo.demo_user_auth_api.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class  UserMapper {

    public static User toUser(UserCreateDto createDto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(createDto, User.class);

//        if (createDto.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
//        }

//        if (createDto.getCreatedBy() == null) {
            user.setCreatedBy("System");
//        }

        if (createDto.getStatus() == null) {
            user.setStatus(User.RecordStatus.ACTIVE);
        } else if (createDto.getStatus().equals("REMOVED")) {
            user.setStatus(User.RecordStatus.REMOVED);
        } else {
            user.setStatus(User.RecordStatus.ACTIVE);
        }

        return user;
    }

    public static UserResponseDto toDto(User user) {
        ModelMapper mapper = new ModelMapper();
        UserResponseDto dto = mapper.map(user, UserResponseDto.class);

        String role = user.getRole().name().substring("ROLE_".length());
        String status = !user.getStatus().name().equals("REMOVED") ? "ACTIVE" : "REMOVED";
        dto.setRole(role);
        return dto;
    }

    public static List<UserResponseDto> toListDto(List<User> users) {
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }


}
