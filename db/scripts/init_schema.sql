CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    phone VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS films_cells (
    id SERIAL PRIMARY KEY,
    name TEXT,
    rowa SMALLINT,
    cell SMALLINT
);

CREATE TABLE IF NOT EXISTS tickets (
    id SERIAL PRIMARY KEY,
    film_cell_id INT NOT NULL REFERENCES films_cells(id),
    rowa INT NOT NULL,
    cell INT NOT NULL,
    user_id INT NOT NULL REFERENCES users(id)
    );

ALTER TABLE tickets ADD CONSTRAINT film_cell_id_unique UNIQUE (film_cell_id);

INSERT INTO films_cells (name, rowa, cell) VALUES ('Cold Heart', 1, 1);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Cold Heart', 1, 2);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Cold Heart', 1, 3);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Cold Heart', 2, 1);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Cold Heart', 2, 2);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Cold Heart', 2, 3);

INSERT INTO films_cells (name, rowa, cell) VALUES ('Scare face', 1, 1);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Scare face', 1, 2);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Scare face', 1, 3);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Scare face', 2, 1);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Scare face', 2, 2);
INSERT INTO films_cells (name, rowa, cell) VALUES ('Scare face', 2, 3);