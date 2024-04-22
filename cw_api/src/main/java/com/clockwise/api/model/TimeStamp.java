package com.clockwise.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time_stamp")
@Builder
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ts")
    private Long id;

    @Column(name = "start_stamp")
    private Long startStamp;

    @Column(name = "end_stamp")
    private Long endStamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

}
