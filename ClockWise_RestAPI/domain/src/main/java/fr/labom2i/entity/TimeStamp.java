package fr.labom2i.entity;

import java.time.Instant;

public class TimeStamp {

    private Long id;

    private Long startStamp;

    private Long endStamp;

    public TimeStamp() {
        startStamp = Instant.now().toEpochMilli()/1000;
    }

    public Long getId() {
        return id;
    }

    public Long getStartStamp() {
        return startStamp;
    }

    public Long getEndStamp() {
        return endStamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartStamp(Long startStamp) {
        this.startStamp = startStamp;
    }

    public void setEndStamp(Long endStamp) {
        this.endStamp = endStamp;
    }

    public void setEndStampNow() {
        this.endStamp = Instant.now().toEpochMilli()/1000;
    }

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
