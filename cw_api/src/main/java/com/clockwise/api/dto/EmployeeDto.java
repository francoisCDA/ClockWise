package com.clockwise.api.dto;

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
}
