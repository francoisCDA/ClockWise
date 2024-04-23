package com.clockwise.api.repository;

import com.clockwise.api.model.User;
import com.clockwise.api.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserRepository() {
        con = ConnectionDB.getConnection();
    }

    public Optional<User> findByEmail(String email) {

        Optional<User> ret = Optional.empty();

        try {
            ps = con.prepareStatement("SELECT id_user, email, password, is_enable, role FROM users WHERE email = ?");
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User.Builder()
                        .id(rs.getLong("id_user"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .isEnabled(rs.getBoolean("is_enable"))
                        .role(rs.getString("role"))
                        .build();

                ret = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public boolean save(User user) {

        try {
            ps = con.prepareStatement("INSERT INTO users (role, email, password, is_enable) VALUES (?, ?, ?,?) ");
            ps.setString(1, user.getRole());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isEnabled());

            int ret = ps.executeUpdate();

            return (ret > 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findUsersByRoles(String role) {
        List<User> ret = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT id_user, email, password, is_enable FROM users WHERE role = ?");
            ps.setString(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id_user"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setEnabled(rs.getBoolean("is_enable"));
                user.setRole(role);
                ret.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }
}
