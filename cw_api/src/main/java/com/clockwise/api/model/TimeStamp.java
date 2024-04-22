package com.clockwise.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "time_stamp")
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ts")
    private Long id;

    @Column(name = "start_stamp")
    private Long startStamp;

    @Column(name = "end_stamp")
    private Long endStamp;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;



    public long getMinuteDuration() {
        long totalSeconds = 0L;
        if (startStamp != null) {
            if (endStamp != null) {
                totalSeconds = endStamp - startStamp;
            } else {
                totalSeconds = Instant.now().toEpochMilli()/1000 - startStamp;
            }
        }
        return  totalSeconds / 60;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartStamp() {
        return startStamp;
    }

    public void setStartStamp(Long startStamp) {
        this.startStamp = startStamp;
    }

    public Long getEndStamp() {
        return endStamp;
    }

    public void setEndStamp(Long endStamp) {
        this.endStamp = endStamp;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
