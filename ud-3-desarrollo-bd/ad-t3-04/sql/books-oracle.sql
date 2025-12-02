-- CREACION DE ESQUEMA
DROP USER c##books CASCADE;
CREATE USER c##books IDENTIFIED BY books DEFAULT TABLESPACE USERS TEMPORARY TABLESPACE TEMP;
GRANT ALL PRIVILEGES TO c##books;
CONNECT c##books/books;

-- BORRADO DE TABLAS Y OBJETOS
DROP TABLE libro CASCADE CONSTRAINTS;
DROP VIEW libros_anio;
DROP SEQUENCE id_libro_seq;

-- TABLAS
CREATE SEQUENCE id_libro_seq START WITH 1;

CREATE TABLE libro (
    id INTEGER DEFAULT id_libro_seq.nextval NOT NULL,
    titulo VARCHAR(50) NOT NULL,
    autor VARCHAR(50) NOT NULL,
    anio INTEGER NOT NULL,
    paginas INTEGER DEFAULT 0,
    PRIMARY KEY (id)
);


-- Inserción de datos
INSERT INTO libro (titulo, autor, anio, paginas) VALUES ('EL CAPOTE', 'NIKOLAI GOGOL', 1940, 200);
INSERT INTO libro (titulo, autor, anio, paginas) VALUES ('EL SANADOR DE CABALLOS', 'GONZALO GINER', 2018, 100);
INSERT INTO libro (titulo, autor, anio, paginas) VALUES ('EL NOMBRE DE LA ROSA', 'UMBERTO ECO', 1981, 150);

CREATE VIEW libros_anio
AS (SELECT anio, count(*) AS numero
   FROM libro
   GROUP BY anio);

COMMIT;