package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.FilmCell;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.store.TicketStore;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketStore store;

    public TicketService(TicketStore store) {
        this.store = store;
    }

    public Optional<Ticket> createTicket(String name, int row, int cell, int filmCell, User user) {
        return store.createTicket(name, row, cell, filmCell, user);
    }

    public List<FilmCell> findFreeCells() {
        return store.findFreeCells();
    }

}
