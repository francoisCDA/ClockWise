package com.clockwise.api.controller;


import com.clockwise.api.dto.EmployeeDto;
import com.clockwise.api.dto.ResponseBaseDto;

import com.clockwise.api.dto.TimeStampDto;
import com.clockwise.api.dto.TimelaspDto;
import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.model.User;
import com.clockwise.api.service.EmployeeService;
import com.clockwise.api.service.TimeStampService;
import com.clockwise.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/cwise/api/v2/employee")
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimeStampService timeStampService;


    @GetMapping
    public ResponseBaseDto getEmployee() {

        EmployeeDto employeeDto = new EmployeeDto();
        List<TimeStamp> timeStampsEmployee = new ArrayList<>();

        HashMap<String, Object> data = new HashMap<>();

        data.put("firstname", employeeDto.getFirstname());
        data.put("lastname", employeeDto.getLastname());
        data.put("email", employeeDto.getEmail());
        data.put("weekwrokinghour",employeeDto.getWeekWorkingHour());

        data.put("timeStamps", timeStampsEmployee);

        return new ResponseBaseDto("Success",data);
    }

    @GetMapping("/stamp")  // GET @ localhost:8000/cwise/api/v2/employee/stamp   @RequestHeader (name="Authorization") String token
    public ResponseBaseDto stamp(@AuthenticationPrincipal User user) {

        TimeStampDto lastStampDto = timeStampService.stampEmployee(user.getId());

        if (timeStamp == null || timeStamp.getEndStamp() != null) {
            if (timeStamp.getEndStamp() == null) {

            }
        }

        if (timeStampService.stampEmployee(user.getId())) {
            try {

                return new ResponseBaseDto("Success",timeStampDto);
            } catch (Exception ex) {
                return new ResponseBaseDto("Success, but :",ex);
            }
        }
        return new ResponseBaseDto("Error, nothing change",null);
    }

    @GetMapping("/timestamps")
    public ResponseEntity<List<TimeStampDto>> timestamps(@RequestBody(required = false) TimelaspDto timelaspDto) {
        List<TimeStampDto> timeStamps = new ArrayList<>();
        return ResponseEntity.ok(timeStamps);
    }

    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestBody String message) {
        return ResponseEntity.ok("message send : " + message);
    }

    @PostMapping("/newpwd")
    public ResponseEntity<String> newpwd(@RequestBody String newpwd) {
        return ResponseEntity.ok("password update : " + newpwd);
    }





}
