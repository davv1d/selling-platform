package com.pik.dto;

public class UserDto {
    private String userId;
    private String email;
    private String status;

    public UserDto() {
    }

    public UserDto(String userId, String email, String status) {
        this.userId = userId;
        this.email = email;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
