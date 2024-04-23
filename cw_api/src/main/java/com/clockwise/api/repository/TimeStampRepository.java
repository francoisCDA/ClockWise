package com.clockwise.api.repository;

import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
public class TimeStampRepository {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public TimeStampRepository() {
        con = ConnectionDB.getConnection();
    }

    public TimeStamp getEmployeeLastTimeStamp(Long employeeId) {
        try {
            rs = con.createStatement().executeQuery("SELECT id_ts, start_stamp, end_stamp, empl_id FROM time_stamp JOIN users ON users.role_id = role.id_role  WHERE email = ?");

            // double jointure

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TimeStamp> getEmployeeAllTimeStamp(Long employeeId) {
        try {
            rs = con.createStatement().executeQuery("SELECT id_ts, start_stamp, end_stamp, empl_id FROM time_stamp ");
            ps = con.prepareStatement(
                    ps.setLong(1, timeStamp.getStartStamp());
            ps.setLong(2, timeStamp.getEmployee().getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TimeStamp> getEmployeeSelectTimeStamp(Long employeeId, Instant startDate, Instant endDate) {
        return null;
    }

    public boolean insertTimeStamp(TimeStamp timeStamp) {
        try {
            ps = con.prepareStatement("INSERT INTO time_stamp (start_stamp,empl_id) VALUES (?,?)");
            ps.setLong(1, timeStamp.getStartStamp());
            ps.setLong(2, timeStamp.getEmployee().getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTimeStamp(TimeStamp timeStamp) {
        try {
            ps = con.prepareStatement("UPDATE time_stamp SET start_stamp = ?, end_stamp = ? ) WHERE id_ts = ?");
            ps.setLong(1, timeStamp.getStartStamp());
            ps.setLong(2, timeStamp.getEndStamp());
            ps.setLong(3, timeStamp.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteTimeStamp(Long timeStampId) {
        try {
            ps = con.prepareStatement("DELETE from time_stamp WHERE id_ts = ?");
            ps.setLong(1, timeStampId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
