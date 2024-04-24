package com.clockwise.api.dto;

import com.clockwise.api.model.Employee;

public class EmployeeDto extends UserDto {


    private String firstname;

    private String lastname;

    private Float weekWorkingHour;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Float getWeekWorkingHour() {
        return weekWorkingHour;
    }

    public void setWeekWorkingHour(Float weekWorkingHour) {
        this.weekWorkingHour = weekWorkingHour;
    }


    public static EmployeeDto parse(Employee employee) {

        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setEmail(employee.getEmail());
        dto.setFirstname(employee.getFirstname());
        dto.setLastname(employee.getLastname());
        dto.setRole(employee.getRole());
        dto.setWeekWorkingHour(((float) employee.getWeekWorkingMin())/60);

        return dto;
    }

}
