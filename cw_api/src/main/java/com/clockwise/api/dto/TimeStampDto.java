package com.clockwise.api.dto;

import com.clockwise.api.model.TimeStamp;

import java.time.Instant;
import java.util.Date;

public class TimeStampDto {

    private Long startStampMillis;

    private Long endStampMillis;

    private Instant startStampInstant;

    private Instant endStampInstant;

    private String startStampStr;

    private String endStampStr;

    private Date startStampDate ;

    private Date endStampDate ;



    public Long getStartStampMillis() {
        return startStampMillis;
    }

    public void setStartStampMillis(Long startStampMillis) {
        this.startStampMillis = startStampMillis;
    }

    public Long getEndStampMillis() {
        return endStampMillis;
    }

    public void setEndStampMillis(Long endStampMillis) {
        this.endStampMillis = endStampMillis;
    }

    public Instant getStartStampInstant() {
        return startStampInstant;
    }

    public void setStartStampInstant(Instant startStampInstant) {
        this.startStampInstant = startStampInstant;
    }

    public Instant getEndStampInstant() {
        return endStampInstant;
    }

    public void setEndStampInstant(Instant endStampInstant) {
        this.endStampInstant = endStampInstant;
    }

    public String getStartStampStr() {
        return startStampStr;
    }

    public void setStartStampStr(String startStampStr) {
        this.startStampStr = startStampStr;
    }

    public String getEndStampStr() {
        return endStampStr;
    }

    public void setEndStampStr(String endStampStr) {
        this.endStampStr = endStampStr;
    }

    public Date getStartStampDate() {
        return startStampDate;
    }

    public void setStartStampDate(Date startStampDate) {
        this.startStampDate = startStampDate;
    }

    public Date getEndStampDate() {
        return endStampDate;
    }

    public void setEndStampDate(Date endStampDate) {
        this.endStampDate = endStampDate;
    }

    public static TimeStampDto parse(TimeStamp timeStamp) {

        TimeStampDto timeStampDto = new TimeStampDto();

        timeStampDto.setStartStampMillis(timeStamp.getStartStamp());
        timeStampDto.setEndStampMillis(timeStamp.getEndStamp());

        timeStampDto.setStartStampInstant(Instant.ofEpochMilli(timeStamp.getStartStamp()));
        timeStampDto.setEndStampInstant(Instant.ofEpochMilli(timeStamp.getEndStamp()));

        timeStampDto.setStartStampDate(Date.from(timeStampDto.getStartStampInstant()));
        timeStampDto.setEndStampDate(Date.from(timeStampDto.getEndStampInstant()));

        timeStampDto.setStartStampStr(timeStampDto.getStartStampDate().toString());
        timeStampDto.setEndStampStr(timeStampDto.getEndStampDate().toString());

        return timeStampDto;

    }

}
