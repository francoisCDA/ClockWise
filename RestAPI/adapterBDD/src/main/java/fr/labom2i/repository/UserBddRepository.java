package fr.labom2i.repository;

import fr.labom2i.domainEntity.RoleUser;
import fr.labom2i.domainEntity.User;
import fr.labom2i.spi.port.UserRepository;
import fr.labom2i.util.ConnectionDB;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserBddRepository implements UserRepository  {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserBddRepository() {
        con = ConnectionDB.getConnection();
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public boolean save(User user) {

        try {
            ps = con.prepareStatement("INSERT INTO users (role_id, email, password) VALUES (?, ?, ?) ");
            ps.setLong(1, 1L);
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int ret = ps.executeUpdate();

            return (ret > 0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public Optional<User> findByEmail(String mail) {

        return findByPseudo(mail);
    }

    @Override
    public Optional<User> findByPseudo(String email) {
        Optional<User> ret = Optional.empty();

        try {
            ps = con.prepareStatement("SELECT id_user, email, password, role FROM users JOIN role ON users.role_id = role.id_role WHERE email = ?");
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User.Builder()
                        .id(rs.getLong("id_user"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .role(RoleUser.valueOf(rs.getString("role")))
                        .build();

                ret = Optional.of(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ret;
    }
}
