package ru.job4j.cinema.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Session {
    private int id;
    private String name;
    private Map<Integer, List<Integer>> freeCells;

    public Session() {
    }

    public Session(String name, Map<Integer, List<Integer>> freeCells) {
        this.name = name;
        this.freeCells = freeCells;
    }

    public Session(int id, String name, Map<Integer, List<Integer>> freeCells) {
        this.id = id;
        this.name = name;
        this.freeCells = freeCells;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return id == session.id && Objects.equals(name, session.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
