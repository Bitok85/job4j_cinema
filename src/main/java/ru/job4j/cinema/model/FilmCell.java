package ru.job4j.cinema.model;

import java.util.Objects;

public class FilmCell {

    private int id;
    private String name;
    private int row;
    private int cell;

    public FilmCell() {
    }

    public FilmCell(int id, String name, int row, int cell) {
        this.id = id;
        this.name = name;
        this.row = row;
        this.cell = cell;
    }

    public FilmCell(String name, int row, int cell) {
        this.name = name;
        this.row = row;
        this.cell = cell;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmCell filmCell = (FilmCell) o;
        return id == filmCell.id && Objects.equals(name, filmCell.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}



