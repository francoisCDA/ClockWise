package com.clockwise.api.repository;

import com.clockwise.api.dto.EmployeeDto;
import com.clockwise.api.model.Employee;
import com.clockwise.api.model.User;
import com.clockwise.api.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Employee getEmployeeByEmail(String email) {

        String userQuery = "select id_user, email, role, is_enable, user_id, firstname, lastname, week_working_min from users JOIN details_employee ON users.id_user = details_employee.user_id where email = ?";

        try {
            PreparedStatement psUser = con.prepareStatement(userQuery);
            psUser.setString(1, email);

            rs = psUser.executeQuery();

            if (rs.next()) {
                return mkEmployeeFromRs();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<Employee> getAllEmployees() {
        List<Employee> ret = new ArrayList<Employee>();
        String employeeQuery = "select id_user, email, role, is_enable, user_id, firstname, lastname, week_working_min from users JOIN details_employee ON users.id_user = details_employee.user_id where role = 'ROLE_EMPLOYEE' ";

        try {
            rs = con.createStatement().executeQuery(employeeQuery);

            while (rs.next()) {
                Employee employee = mkEmployeeFromRs();

                ret.add(employee);
            }
            return ret;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Employee getDetailsUser(User user) {
        Employee ret = new Employee();

        String detailsQuery = "SELECT user_id, firstname, lastname, week_working_min FROM details_employee WHERE user_id = ? ";

        try {
            ps = con.prepareStatement(detailsQuery);
            ps.setLong(1, user.getId());

            rs = ps.executeQuery();

            if (rs.next()) {
                ret.setFirstname(rs.getString("firstname"));
                ret.setLastname(rs.getString("lastname"));
                ret.setWeekWorkingMin(rs.getInt("week_working_min"));
            }

            ret.setId(user.getId());
            ret.setRole(user.getRole());
            ret.setEmail(user.getEmail());
            ret.setEnabled(user.isEnabled());

            return ret;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Employee getEmployeeById(long idEmployee) {

        String userQuery = "select id_user, email, role, is_enable, user_id, firstname, lastname, week_working_min from users JOIN details_employee ON users.id_user = details_employee.user_id where id_user = ?";

        try {
            PreparedStatement psUser = con.prepareStatement(userQuery);
            psUser.setLong(1, idEmployee);

            rs = psUser.executeQuery();

            if (rs.next()) {
                return mkEmployeeFromRs();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }


    private Employee mkEmployeeFromRs() throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id_user"));
        employee.setRole(rs.getString("role"));
        employee.setEmail(rs.getString("email"));
        employee.setEnabled(rs.getBoolean("is_enable"));
        employee.setFirstname(rs.getString("firstname"));
        employee.setLastname(rs.getString("lastname"));
        employee.setWeekWorkingMin(rs.getInt("week_working_min"));
        return employee;
    }



}
