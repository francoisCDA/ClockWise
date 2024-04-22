package com.clockwise.api.service;

import com.clockwise.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class RootService {


    private String rootMail = System.getenv("API_ROOT_MAIL");

    private String rootPassword = System.getenv("API_ROOT_PASSWORD");

    private UserService userService;

    public RootService(UserService userService) {
        this.userService = userService;
    }

    public UserDto getRootUser() {
        int adminCount = userService.getAllAdminCount();

        if (adminCount == 0) {
            UserDto userDto = new UserDto();
            userDto.setEmail(rootMail);
            userDto.setPassword(rootPassword);
            userDto.setRole("ROLE_ADMIN");
            return userDto;
        }

        return null;
    }

}
