package com.clockwise.api.controller;


import com.clockwise.api.dto.EmployeeDto;
import com.clockwise.api.dto.ResponseBaseDto;
import com.clockwise.api.dto.TimelaspDto;
import com.clockwise.api.dto.UserDto;

import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.service.EmployeeService;
import com.clockwise.api.service.TimeStampService;
import com.clockwise.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cwise/api/v2/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimeStampService timeStampService;

// ADMINISTRATION DES ADMIN

    @PostMapping("/new")  // Post localhost:8000/cwise/api/v2/admin/new
    public ResponseBaseDto register(@RequestBody UserDto userDto) {

        if (userService.createUser(userDto)) {
            return new ResponseBaseDto("Success",null);
        }
        return new ResponseBaseDto("Invalid connexion",null);
    }

    @DeleteMapping("/{userEmail}")
    public ResponseEntity<String> removeEmployee(@PathVariable("userEmail") String userEmail) {

        if (userService.removeUserByEmail(userEmail)){
            return ResponseEntity.ok("User removed");
        }
        return new ResponseEntity<String>("Not implemented yet",null,HttpStatus.BAD_REQUEST);
    }

    // ADMINISTRATION DES EMPLOYEES

    @PostMapping("/employee")  // POST @ localhost:8000/cwise/api/v2/admin/employee
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {

        if (employeeService.save(employeeDto)) {
            return ResponseEntity.ok("employee created");
        }
        return new ResponseEntity<String>("employee not created",null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> list = employeeService.getAllEmployee();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/employee/{idEmployee}")
    public ResponseBaseDto getEmployeeById(@PathVariable long idEmployee) {

        EmployeeDto employeeDto = employeeService.getEmployeeById(idEmployee);

        List<TimeStamp> timeStamps = timeStampService.getEmployeeAllTimeStamp(idEmployee);

        HashMap<String,Object> data = new HashMap<>();

        List<String> messages = new ArrayList<>();

        data.put("employee",employeeDto);
        data.put("timeStamps",timeStamps);
        data.put("messages","aucun message");

        return new ResponseBaseDto("success",data);
    }

    @PutMapping("/employee")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok("employee updated");
    }

    @DeleteMapping("/employee/{idEmployee}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long idEmployee) {
        return ResponseEntity.ok("employee deleted");
    }

    // GESTION DES TIMESTAMPS

    @GetMapping("/timestamps")
    public ResponseEntity<List<TimeStamp>> getAllTimeStamps(@RequestBody(required = false) TimelaspDto timelaspDto) {

        List<TimeStamp> data = new ArrayList<>();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/timestamp")
    public ResponseEntity<String> updateTimeStamp(@RequestBody TimeStamp timeStamp) {
        return ResponseEntity.ok("timestamp updated");
    }

    @DeleteMapping("/timestamp/{idTs}")
    public ResponseEntity<String> deleteTimeStamp(@PathVariable long idTs) {
        return ResponseEntity.ok("timestamp deleted");
    }

}
