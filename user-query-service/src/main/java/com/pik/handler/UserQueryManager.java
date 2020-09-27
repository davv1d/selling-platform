package com.pik.handler;

import com.pik.dto.UserDto;
import com.pik.dto.UsersListDto;
import com.pik.entity.User;
import com.pik.mapper.UserMapper;
import com.pik.query.FindAllUsers;
import com.pik.query.FindUserByEmail;
import com.pik.query.FindActivatedUserByEmail;
import com.pik.query.FindUserById;
import com.pik.repository.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueryManager {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryManager(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @QueryHandler
    public UsersListDto handle(FindAllUsers findAllUsers) {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userEntityDtoList = this.userMapper.mapToUsersDtoList(users);
        return new UsersListDto(userEntityDtoList);
    }

    @QueryHandler
    public UserDto handle(FindUserById findUserById) {
        User user = this.userRepository.findById(findUserById.userId).orElse(new User(null, null, null, null));
        return this.userMapper.mapToUserDto(user);
    }

    @QueryHandler
    public UserDto handle(FindUserByEmail query) {
        User user = this.userRepository.findByEmail(query.email).orElse(new User());
        return this.userMapper.mapToUserDto(user);
    }

    @QueryHandler
    public UserDto handle(FindActivatedUserByEmail query) {
        User user = this.userRepository.findByEmailAndStatus(query.email, query.status).orElse(new User());
        return this.userMapper.mapToUserDto(user);
    }
}
