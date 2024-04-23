package com.clockwise.api.service;

import com.clockwise.api.dto.TimeStampDto;
import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.repository.TimeStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TimeStampService {

    @Autowired
    private TimeStampRepository timeStampRepository;

    public TimeStampDto stampEmployee(Long employeeId) {

        TimeStamp timeStamp = timeStampRepository.getEmployeeLastTimeStamp(employeeId);

        if (timeStamp == null || timeStamp.getEndStamp() != null) {
             TimeStamp newTimeStamp = new TimeStamp();
             newTimeStamp.setStartStamp(Instant.now().toEpochMilli());

             if (timeStampRepository.insertTimeStamp(newTimeStamp)) {
                 timeStamp = timeStampRepository.getEmployeeLastTimeStamp(employeeId);
                 return TimeStampDto.parse(timeStamp);
             };
        }

        return null;
    }


    public TimeStamp getEmployeeLastStamp(Long id) {
        return null;
    }
}
