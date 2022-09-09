package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.FilmCell;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TicketStore {

    private static final Logger LOG = Logger.getLogger(TicketStore.class.getName());

    private final BasicDataSource pool;
    private final ConcurrentHashMap<Integer, Ticket> bookedTickets = new ConcurrentHashMap<>();
    private final List<Integer> bookedCellsIds = new ArrayList<>();

    public TicketStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<Ticket> createTicket(String name, int row, int cell, int filmCellId, User user) {
        Ticket ticket = new Ticket(name, row, cell, filmCellId);
        Optional<Ticket> result = Optional.empty();
        try (Connection cn = pool.getConnection();
            PreparedStatement ps
                    = cn.prepareStatement("INSERT INTO tickets (film_cell_id, rowa, cell, user_id) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            ps.setInt(1, ticket.getFilmCellId());
            ps.setInt(2, ticket.getPosRow());
            ps.setInt(3, ticket.getCell());
            ps.setInt(4, user.getId());
            ps.execute();
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        ticket.setId(id.getInt(1));
                        return Optional.of(ticket);
                    }
                }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return result;
    }

    public List<FilmCell> findFreeCells() {
        List<FilmCell> freeCells = new ArrayList<>();
        if (bookedTickets.size() == 0) {
            try (Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement("SELECT * FROM films_cells")
            ) {
                ps.execute();
                freeCellsAdd(freeCells, ps);
            } catch (SQLException e) {
                LOG.error("SQLException", e);
            }
        } else {
            String inSql = getInSql();
            int increment = 1;
            try (Connection cn = pool.getConnection();
                 PreparedStatement ps = cn.prepareStatement(
                         String.format("SELECT * FROM films_cells WHERE id NOT IN (%s)", inSql)
                 )
            ) {
                for (Integer id : bookedCellsIds) {
                    ps.setInt(increment, id);
                    increment++;
                }
                ps.execute();
                freeCellsAdd(freeCells, ps);
            } catch (SQLException e) {
                LOG.error("SQLException", e);
            }
        }
        return freeCells;
    }

    private String getInSql() {
        for (Integer key : bookedTickets.keySet()) {
            bookedCellsIds.add(bookedTickets.get(key).getFilmCell().getId());
        }
        return String.join(",", Collections.nCopies(bookedCellsIds.size(), "?"));
    }

    private void freeCellsAdd(List<FilmCell> freeCells, PreparedStatement ps) {
        try (ResultSet rs = ps.getResultSet()) {
            while (rs.next()) {
                freeCells.add(new FilmCell(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("rowa"),
                        rs.getInt("cell")
                ));
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
    }
}
