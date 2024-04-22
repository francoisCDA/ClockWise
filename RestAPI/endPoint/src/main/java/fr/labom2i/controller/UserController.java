package fr.labom2i.controller;

import fr.labom2i.domainEntity.User;
import fr.labom2i.dto.BaseResponseDto;
import fr.labom2i.dto.LoginDto;
import fr.labom2i.dto.UserDto;
import fr.labom2i.repository.UserBddRepository;
import fr.labom2i.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRestService userService;

    UserController() {
    }

    @PostMapping("/login")
    public BaseResponseDto login(@RequestBody LoginDto loginDto) {
        if (userService.isEmailExist(loginDto.getEmail())) {
            if (userService.verifyUser(loginDto.getEmail(),loginDto.getPassword())){

       //         User user = (User) userService.loadUserByUsername(loginDto.getEmail());

                Map<String,Object> data = new HashMap<>();

                data.put("jwt", userService.generateToken(loginDto.getEmail(),loginDto.getPassword()) );

                return new BaseResponseDto("Success",data);

            }
        }

        return new BaseResponseDto("Invalid email or password");

    }

    @GetMapping("/login")
    public ResponseEntity<String> init() {

        UserDto userDto = new UserDto();
        userDto.setEmail("root");
        userDto.setPassword("root");
        userDto.setRole("ROLE_ADMIN");

        boolean ret = userService.createUser(userDto);
        return ResponseEntity.ok("Success = " + ret);

    }


    @GetMapping
    public ResponseEntity<String> getEmployee() {
        return ResponseEntity.ok("Employee");
    }

    @PostMapping
    public ResponseEntity<String> addEmployee() {
        userService.saveUser("Jean","Bon");
        return ResponseEntity.ok("Success");
    }

}
