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



}
