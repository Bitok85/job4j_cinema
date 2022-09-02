package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.User;

import java.sql.*;
import java.util.Optional;

@Repository
public class UserStore {

    private static final Logger LOG = Logger.getLogger(UserStore.class.getName());
    private BasicDataSource pool;

    public UserStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> result = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO users (username, email, phone, password) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return result;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> result = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("password")
                    );
                    return Optional.of(user);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return result;
    }


}
