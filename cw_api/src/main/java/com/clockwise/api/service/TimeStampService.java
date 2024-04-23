package com.clockwise.api.service;

import com.clockwise.api.dto.TimeStampDto;
import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.repository.TimeStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeStampService {

    @Autowired
    private TimeStampRepository timeStampRepository;

    public TimeStampDto stampEmployee(Long employeeId) {
         timeStampRepository.stamp(employeeId);
    }


    public TimeStamp getEmployeeLastStamp(Long id) {
        return null;
    }
}
