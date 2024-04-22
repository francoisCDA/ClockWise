package com.clockwise.api.service;

import com.clockwise.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

            UserDto root = new UserDto();
            root.setEmail(rootMail);
            root.setPassword(rootPassword);
            root.setRole("ROLE_ADMIN");

            return root;
        }

        return null;
    }

}
