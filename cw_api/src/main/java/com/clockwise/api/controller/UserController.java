package com.clockwise.api.controller;

import com.clockwise.api.dto.LoginDto;
import com.clockwise.api.dto.ResponseBaseDto;
import com.clockwise.api.dto.UserDto;
import com.clockwise.api.model.User;
import com.clockwise.api.service.RootService;
import com.clockwise.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBaseDto login(@RequestBody LoginDto loginDto) {

        if (userService.checkUserEmailExists(loginDto.getEmail())) {
            if (userService.verifyUser(loginDto.getEmail(), loginDto.getPassword())) {

                Map<String, String> data = new HashMap<>();

                String token = userService.generateToken(loginDto.getEmail(), loginDto.getPassword());

                data.put("token", token);

                return new ResponseBaseDto("Success", data);
            }
        }

        return new ResponseBaseDto("Invalid connexion",null);

    }

    @PostMapping("/register")
    public ResponseBaseDto register(@RequestBody UserDto userDto) {

        if (userService.createUser(userDto)) {
            return new ResponseBaseDto("Success",null);
        }
        return new ResponseBaseDto("Invalid connexion",null);

    }

    @GetMapping("/createroot")
    public ResponseBaseDto createRoot() {

        UserDto userDto = new RootService(userService).getRootUser();

        if (userDto != null) {
            if (userService.createUser(userDto)) {
                return new ResponseBaseDto("Success", null);
            }
        }
        return new ResponseBaseDto("Error", null);

    }



}
