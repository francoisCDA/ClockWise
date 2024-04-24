package com.clockwise.api.service;

import com.clockwise.api.dto.TimeStampDto;
import com.clockwise.api.model.Employee;
import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.model.User;
import com.clockwise.api.repository.TimeStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeStampService {

    @Autowired
    private TimeStampRepository timeStampRepository;

    public TimeStampDto stampEmployee(User user) {

        TimeStamp timeStamp = timeStampRepository.getEmployeeLastTimeStamp(user);

        if (timeStamp != null ) {
            if (timeStamp.getEndStamp() == 0 || timeStamp.getEndStamp() == null) {
                timeStamp.setEndStamp(Instant.now().toEpochMilli());
                if (timeStampRepository.updateTimeStamp(timeStamp)) {
                    return TimeStampDto.parse(timeStamp);
                } else {
                    return null;
                }
            }
        }

        Employee employeeFromUser = new Employee.EmployeeBuilder().id(user.getId()).email(user.getEmail()).role(user.getRole()).build();

        TimeStamp newTimeStamp = new TimeStamp();
        newTimeStamp.setStartStamp(Instant.now().toEpochMilli());
        newTimeStamp.setEmployee(employeeFromUser);

        if (timeStampRepository.insertTimeStamp(newTimeStamp)) {
            return TimeStampDto.parse(newTimeStamp);
        } else {
            return null;
        }

    }

    public TimeStamp getEmployeeLastStamp(User user) {
        return timeStampRepository.getEmployeeLastTimeStamp(user);
    }

    public List<TimeStampDto> getEmployeeAllStamp(User user) {

        List<TimeStampDto> ret = new ArrayList<>();

        List<TimeStamp> timeStamps = timeStampRepository.getEmployeeAllTimeStamp(user.getId());

        for (TimeStamp timeStamp : timeStamps) {
            ret.add(TimeStampDto.parse(timeStamp));
        }
        return ret;

    }

    public  List<TimeStamp> getEmployeeAllTimeStamp(Long id) {
        return timeStampRepository.getEmployeeAllTimeStamp(id);
    }

}
