package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SessionStore {

    private static final Logger LOG = LogManager.getLogger(SessionStore.class.getName());
    private final BasicDataSource pool;

    public SessionStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM session")
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sessions.add(new Session(
                            rs.getInt("id"),
                            rs.getString("name"),
                            getFreeCells(rs)
                    ));
                }
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return sessions;
    }

    public Session findById(int id) {
        try (Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM sessions WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Session(
                            rs.getInt("id"),
                            rs.getString("name"),
                            getFreeCells(rs)
                    );
                }
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return null;
    }

    private Map<Integer, List<Integer>> getFreeCells(ResultSet rs) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        try {
            Array data = rs.getArray("cells");
            int[][] cells = (int[][]) data.getArray();
            for (int i = 0; i < cells.length; i++) {
                result.put(i, new ArrayList<>());
                for (int j = 0; j < cells[i].length; j++) {
                    result.get(i).add(cells[i][j]);
                }
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return result;
    }
}
