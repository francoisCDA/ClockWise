package com.clockwise.api.dto;

import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String password;
    private String role;

}
