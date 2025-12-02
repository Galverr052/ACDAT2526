DROP DATABASE IF EXISTS books;
CREATE DATABASE books CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE books;

-- TABLAS
CREATE TABLE libro (
	id INTEGER AUTO_INCREMENT NOT NULL,
	titulo varchar(50) NOT NULL,
	autor varchar(50) NOT NULL,
	anio INTEGER NOT NULL,
	paginas INTEGER DEFAULT 0,
	PRIMARY KEY (id)
);


-- DATOS
INSERT INTO libro (titulo, autor, anio, paginas) VALUES ('EL CAPOTE', 'NIKOLAI GOGOL', 1940, 200);
INSERT INTO libro (titulo, autor, anio, paginas) VALUES ('EL SANADOR DE CABALLOS', 'GONZALO GINER', 2018, 100);
INSERT INTO libro (titulo, autor, anio, paginas) VALUES ('EL NOMBRE DE LA ROSA', 'UMBERTO ECO', 1981, 150);

CREATE VIEW libros_anio
AS (SELECT anio, count(*) AS numero
   FROM libro
   GROUP BY anio);

COMMIT;