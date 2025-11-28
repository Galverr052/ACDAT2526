DROP DATABASE IF EXISTS tienda;
CREATE DATABASE tienda CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE tienda;

-- TABLAS
CREATE TABLE categoria (
    id INTEGER PRIMARY KEY NOT NULL,
    descripcion varchar(50) NOT NULL
);

CREATE TABLE articulo (
    id INTEGER NOT NULL,
    codigo varchar(30) NOT NULL,
    nombre varchar(50) NOT NULL,
    descripcion varchar(50) NOT NULL,
    existencia double NOT NULL,
    precio double NOT NULL,
    idcategoria INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (idcategoria) REFERENCES CATEGORIA(id) ON UPDATE CASCADE
);


-- DATOS
INSERT INTO CATEGORIA VALUES
(1, 'PAPEL'),
(2, 'SOBRES'),
(3, 'CARPETAS'),
(4, 'DECORACION');

INSERT INTO articulo (id, codigo, nombre, descripcion, existencia, precio, idcategoria) VALUES
(5, 'EC001', 'ESFERO ROJO', 'ESFERO BORRABLE', 40, 0.65, 4),
(4, 'EC002', 'ESFERO NEGRO', 'ESFERO BORRABLE', 30, 0.65, 4),
(6, 'FA001', 'FOLDER ARCHIVADOR', 'FOLDER CARTON', 10, 2.79, 3),
(7, 'SM001', 'SOBRE MANILA ', 'SOBRE MANILA OFICIO', 15, 0.1, 1),
(8, 'PL001', 'PAPEL COLOR', 'PAPEL 12 COLORES', 10, 0.20, 1);


-- PROCEDIMIENTOS

DELIMITER $$

CREATE PROCEDURE articulos_categoria (categoria VARCHAR(50))
    COMMENT 'Mostrar artículos según categoría'
BEGIN
    DECLARE idcategoria_articulos int;

    -- 1. Obtener el número id de la categoría
    SELECT id
    INTO idcategoria_articulos
    FROM categoria
    WHERE descripcion = categoria;

    -- 2. Mostrar artículos según su categoría
    IF (idcategoria_articulos IS NOT NULL) THEN
        SELECT *
        FROM articulo
        WHERE idcategoria = idcategoria_articulos;
    ELSE
        SIGNAL SQLSTATE '45000' SET message_text='CATEGORIA NO EXISTENTE';
    END IF;

END$$

DELIMITER ;

COMMIT;