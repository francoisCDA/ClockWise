package com.clockwise.api.repository;

import com.clockwise.api.model.Employee;
import com.clockwise.api.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class EmployeeRepository {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public EmployeeRepository() {
        con = ConnectionDB.getConnection();
    }


    public boolean save(Employee employee) {

        String userQuery = "insert into users (role, email, password, is_enable) VALUES (?,?,?,?) ";

        String detailsQuery = "insert into details_employee (user_id, firstname,lastname,week_working_min ) values(?,?,?,?)";

        Connection conLocal = ConnectionDB.getConnection();

        try {
//            conLocal.setAutoCommit(false);

            PreparedStatement psUser = conLocal.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, employee.getRole());
            psUser.setString(2, employee.getEmail());
            psUser.setString(3, employee.getPassword());
            psUser.setBoolean(4,employee.isEnabled());

            psUser.executeUpdate();

            rs = psUser.getGeneratedKeys();

            Long idUser;

            if (rs.next()) {
                idUser = rs.getLong(1);
            } else {
                throw new SQLException("Database error");
            }

            PreparedStatement psDetails = con.prepareStatement(detailsQuery);
            psDetails.setLong(1, idUser);
            psDetails.setString(2, employee.getFirstname());
            psDetails.setString(3, employee.getLastname());
            psDetails.setLong(4, employee.getWeekWorkingMin());

            if (psDetails.executeUpdate() > 0) {
  //              conLocal.commit();
                return true;
            } else {
   //             conLocal.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
