package com.clockwise.api.service;

import com.clockwise.api.dto.EmployeeDto;
import com.clockwise.api.model.Employee;
import com.clockwise.api.model.User;
import com.clockwise.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean save(EmployeeDto employeeDto) {

        if (employeeDto.getId() != null) {
            return false;
        }
        //TODO : autres tests de validation

        int weekWorkingMin = (int) (employeeDto.getWeekWorkingHour() * 60);

        Employee employee = new Employee.EmployeeBuilder()
                .email(employeeDto.getEmail())
                .password(passwordEncoder.encode(employeeDto.getPassword()))
                .role("ROLE_EMPLOYEE")
                .isEnabled(true)
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .weekWorkingMin(weekWorkingMin)
                .build();

        return employeeRepository.save(employee);

    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> ret = new ArrayList<>();

        List<Employee> employeeList = employeeRepository.getAllEmployees();

        for (Employee employee : employeeList) {
            ret.add(EmployeeDto.parse(employee));
        }

        return ret;

    }

    public EmployeeDto findEmployee(User user) {

        Employee employee = employeeRepository.getDetailsUser(user);

        return EmployeeDto.parse(employee);

    }

    public EmployeeDto getEmployeeById(long idEmployee) {

        Employee employee = employeeRepository.getEmployeeById(idEmployee);

        return EmployeeDto.parse(employee);

    }
}
