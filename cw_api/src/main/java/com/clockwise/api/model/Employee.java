package com.clockwise.api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "details_employee")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {

    private String firstname;

    private String lastname;

    @Column(name = "week_working_min")
    private int weekWorkingMin;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeStamp> timeStamps = new ArrayList<>();


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

    public int getWeekWorkingMin() {
        return weekWorkingMin;
    }

    public void setWeekWorkingMin(int weekWorkingMin) {
        this.weekWorkingMin = weekWorkingMin;
    }

    public List<TimeStamp> getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(List<TimeStamp> timeStamps) {
        this.timeStamps = timeStamps;
    }

    public static class EmployeeBuilder {

        private Long id;
        private String email;
        private String password;
        private String role;
        private boolean isEnabled;
        private String firstname;
        private String lastname;
        private int weekWorkingMin;
        private List<TimeStamp> timeStamps = new ArrayList<>();

        public EmployeeBuilder () {}

        public EmployeeBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public EmployeeBuilder email(String email) {
            this.email = email;
            return this;
        }
        public EmployeeBuilder password(String password) {
            this.password = password;
            return this;
        }
        public EmployeeBuilder role(String role) {
            this.role = role;
            return this;
        }
        public EmployeeBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }
        public EmployeeBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }
        public EmployeeBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }
        public EmployeeBuilder weekWorkingMin(int weekWorkingMin) {
            this.weekWorkingMin = weekWorkingMin;
            return this;
        }
        public EmployeeBuilder timeStamps(List<TimeStamp> timeStamps) {
            this.timeStamps = timeStamps;
            return this;
        }
        public Employee build() {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setRole(role);
            employee.setEnabled(isEnabled);
            employee.setFirstname(firstname);
            employee.setLastname(lastname);
            employee.setWeekWorkingMin(weekWorkingMin);
            employee.setTimeStamps(timeStamps);
            return employee;
        }



    }


}
