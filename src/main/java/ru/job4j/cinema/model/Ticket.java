package ru.job4j.cinema.model;

import java.util.Objects;

public class Ticket {
    private int id;
    private Session session;
    private int posRow;
    private int cell;
    private User user;

    public Ticket() {
    }

    public Ticket(int id, Session session, int posRow, int cell, User user) {
        this.id = id;
        this.session = session;
        this.posRow = posRow;
        this.cell = cell;
        this.user = user;
    }

    public Ticket(Session session, int posRow, int cell, User user) {
        this.session = session;
        this.posRow = posRow;
        this.cell = cell;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return posRow == ticket.posRow && cell == ticket.cell;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posRow, cell);
    }
}
