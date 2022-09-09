package ru.job4j.cinema.model;

import java.util.Objects;

public class Ticket {
    private int id;

    private String name;
    private int posRow;
    private int cell;
    private int filmCellId;

    private String username;
    private FilmCell filmCell;
    public Ticket() {
    }


    public Ticket(int id, String name, int posRow, int cell, int filmCellId) {
        this.id = id;
        this.name = name;
        this.posRow = posRow;
        this.cell = cell;
        this.filmCellId = filmCellId;
    }

    public Ticket(String name, int posRow, int cell, int filmCellId) {
        this.name = name;
        this.posRow = posRow;
        this.cell = cell;
        this.filmCellId = filmCellId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosRow() {
        return posRow;
    }

    public void setPosRow(int posRow) {
        this.posRow = posRow;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FilmCell getFilmCell() {
        return filmCell;
    }

    public void setFilmCell(FilmCell filmCell) {
        this.filmCell = filmCell;
    }

    public int getFilmCellId() {
        return filmCellId;
    }

    public void setFilmCellId(int filmCellId) {
        this.filmCellId = filmCellId;
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
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
