package com.clockwise.api.controller;

import com.clockwise.api.dto.LoginDto;
import com.clockwise.api.dto.ResponseBaseDto;
import com.clockwise.api.dto.UserDto;
import com.clockwise.api.service.RootService;
import com.clockwise.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cwise/api/v2/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login") // localhost:8000/cwise/api/v2/user/login
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


    // génère l'utilisateur root faute de mieux
    @GetMapping("/init")
    public ResponseEntity<String> createRoot() { // GET @ localhost:8000/cwise/api/v2/user/init

        UserDto userDto = new RootService(userService).getRootUser();

        if (userDto != null) {
            userService.createUser(userDto);
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.status(401).body("");
    }

}
