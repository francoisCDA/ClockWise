package fr.labom2i.spi.port;

import fr.labom2i.domainEntity.TimeStamp;

import java.util.List;

public interface TimeStampRepository extends BaseCrudRepo<TimeStamp> {

    List<TimeStamp> findByEmployeeId(Long employeeId);
}
