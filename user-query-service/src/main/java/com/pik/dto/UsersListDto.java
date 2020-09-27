package com.pik.dto;

import java.util.List;

public class UsersListDto {
    private List<UserDto> users;

    public UsersListDto() {
    }

    public UsersListDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
