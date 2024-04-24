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
import org.springframework.http.HttpStatus;
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

    @GetMapping("/statut")
    public ResponseBaseDto statut(@AuthenticationPrincipal User user) {

        EmployeeDto employeeDto = employeeService.findEmployee(user);

        TimeStamp timeStamp = timeStampService.getEmployeeLastStamp(user);

        TimeStampDto lastTimeStampDto;

        HashMap<String, Object> data = new HashMap<>();

        if (timeStamp != null) {
            lastTimeStampDto = TimeStampDto.parse(timeStamp);
            data.put("lastTimeStamp", lastTimeStampDto);

            if (lastTimeStampDto.getEndStampMillis() == 0 ) {
                data.put("isWorking", true);
            } else {
                data.put("isWorking", false);
            }

        } else {
            data.put("lastTimeStamp", "aucun pointage trouvé");
            data.put("isWorking", false);
        }

        data.put("firstname", employeeDto.getFirstname());
        data.put("lastname", employeeDto.getLastname());
        data.put("weekWorkingHour",employeeDto.getWeekWorkingHour());

     //   data.put("lastTimeStamp", lastTimeStampDto);

        return new ResponseBaseDto("Success",data);


    }

    @GetMapping("/stamp")  // GET @ localhost:8000/cwise/api/v2/employee/stamp   @RequestHeader (name="Authorization") String token
    public ResponseEntity<TimeStampDto> stamp(@AuthenticationPrincipal User user) {

        TimeStampDto lastStampDto = timeStampService.stampEmployee(user);

        if (lastStampDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(lastStampDto);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @GetMapping("/timestamps")
    public ResponseEntity<List<TimeStampDto>> timestamps(@RequestBody(required = false) TimelaspDto timelaspDto,@AuthenticationPrincipal User user) {
        List<TimeStampDto> timeStamps = timeStampService.getEmployeeAllStamp(user);
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
