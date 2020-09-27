package com.pik.mapper;

import com.pik.dto.UserDto;
import com.pik.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getStatus());
    }

    public List<UserDto> mapToUsersDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}

