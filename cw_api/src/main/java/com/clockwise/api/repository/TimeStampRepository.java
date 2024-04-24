package com.clockwise.api.repository;

import com.clockwise.api.model.Employee;
import com.clockwise.api.model.TimeStamp;
import com.clockwise.api.model.User;
import com.clockwise.api.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeStampRepository {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public TimeStampRepository() {
        con = ConnectionDB.getConnection();
    }

    public TimeStamp getEmployeeLastTimeStamp(User user) {
        try {
            ps = con.prepareStatement("SELECT id_ts, start_stamp, end_stamp, empl_id, id_user, email, role, is_enable, user_id, firstname, lastname, week_working_min FROM time_stamp JOIN users ON time_stamp.empl_id = users.id_user JOIN details_employee ON users.id_user = details_employee.user_id WHERE user_id = ? ORDER BY start_stamp DESC LIMIT 1");
            ps.setLong(1, user.getId());

            rs = ps.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee.EmployeeBuilder()
                        .id(rs.getLong("empl_id"))
                        .email(rs.getString("email"))
                        .role(rs.getString("role"))
                        .isEnabled(rs.getBoolean("is_enable"))
                        .firstname(rs.getString("firstname"))
                        .lastname(rs.getString("lastname"))
                        .weekWorkingMin(rs.getInt("week_working_min"))
                        .build();

                TimeStamp ret = new TimeStamp();
                ret.setId(rs.getLong("id_ts"));
                ret.setStartStamp(rs.getLong("start_stamp"));
                ret.setEndStamp(rs.getLong("end_stamp"));
                ret.setEmployee(employee);

                return ret;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TimeStamp> getEmployeeAllTimeStamp(Long employeeId) {
        List<TimeStamp> ret = new ArrayList<TimeStamp>();

        String tsQuery = "SELECT id_ts, start_stamp, end_stamp, empl_id FROM time_stamp WHERE empl_id = ?";

        try {
            ps = con.prepareStatement(tsQuery);
            ps.setLong(1, employeeId);

            rs = ps.executeQuery();
            while (rs.next()) {
                TimeStamp timeStamp = new TimeStamp();
                timeStamp.setId(rs.getLong("id_ts"));
                timeStamp.setStartStamp(rs.getLong("start_stamp"));
                timeStamp.setEndStamp(rs.getLong("end_stamp"));

                ret.add(timeStamp);
            }

            return ret;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<TimeStamp> getEmployeeSelectTimeStamp(Long employeeId, Instant startDate, Instant endDate) {

        List<TimeStamp> ret = new ArrayList<TimeStamp>();

        String tsQuery = "SELECT id_ts, start_stamp, end_stamp FROM time_stamp WHERE empl_id = ? AND start_stamp >= ? AND start_stamp <= ? ";

        try {
            ps = con.prepareStatement(tsQuery);
            ps.setLong(1, employeeId);

            rs = ps.executeQuery();
            while (rs.next()) {
                TimeStamp timeStamp = new TimeStamp();
                timeStamp.setId(rs.getLong("id_ts"));
                timeStamp.setStartStamp(rs.getLong("start_stamp"));
                timeStamp.setEndStamp(rs.getLong("end_stamp"));

                ret.add(timeStamp);
            }

            return ret;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            ps = con.prepareStatement("UPDATE time_stamp SET start_stamp = ?, end_stamp = ? WHERE id_ts = ?");
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
