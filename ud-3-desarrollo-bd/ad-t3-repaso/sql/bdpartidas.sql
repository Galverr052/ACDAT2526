DROP USER c##partidas CASCADE;
CREATE USER c##partidas IDENTIFIED BY partidas DEFAULT TABLESPACE USERS;
GRANT ALL PRIVILEGES TO c##partidas;

CONNECT c##partidas/partidas;

DROP TABLE acceso CASCADE CONSTRAINT;
DROP TABLE partida;
DROP TABLE jugador;

CREATE TABLE jugador (
	idjugador INTEGER PRIMARY KEY,
	dni NUMBER(8) UNIQUE NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	iban VARCHAR(24) NOT NULL,
	cuota NUMBER(4,2) DEFAULT 0 NOT NULL,
	falta DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE partida (
	idpartida INTEGER PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL
);

CREATE TABLE acceso (
	idjugador INTEGER NOT NULL,
	idpartida INTEGER NOT NULL,
	fhentrada DATE DEFAULT SYSDATE NOT NULL,
	fhsalida DATE,
	PRIMARY KEY (idjugador, idpartida, fhentrada),
	FOREIGN KEY (idjugador) REFERENCES jugador(idjugador),
	FOREIGN KEY (idpartida) REFERENCES partida(idpartida)
);

-- Procedimientos almacenados y funciones
CREATE OR REPLACE PROCEDURE entrar_jugador_en_partida (p_idjugador INTEGER, p_idpartida INTEGER)
AS
BEGIN
	INSERT INTO acceso (idjugador, idpartida, fhentrada)
	VALUES (p_idjugador, p_idpartida, SYSDATE);

	DBMS_OUTPUT.PUT_LINE('Acceso insertado correctamente para jugador ' || p_idjugador || ' en partida ' || p_idpartida);
EXCEPTION
    WHEN NO_DATA_FOUND THEN    
		DBMS_OUTPUT.PUT_LINE('Error: la partida o jugador con ID no existe.');

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al insertar el acceso: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE eliminar_partida (p_idpartida INTEGER)
AS
	v_idpartida INTEGER;
BEGIN
	-- Comprobar si partida existe
    SELECT idpartida INTO v_idpartida
    FROM partida
    WHERE idpartida = p_idpartida;
	
    DELETE FROM acceso
    WHERE idpartida = p_idpartida;

    DELETE FROM partida
    WHERE idpartida = p_idpartida;

    DBMS_OUTPUT.PUT_LINE('Partida ' || p_idpartida || ' eliminada correctamente.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No existe ninguna partida con ID ' || p_idpartida);

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al intentar borrar la partida: ' || SQLERRM);
END;
/

CREATE OR REPLACE FUNCTION num_partidas (p_fecha_inicio DATE, p_fecha_fin DATE) RETURN INTEGER
AS
    numpartidas INTEGER := 0;
BEGIN
    IF p_fecha_inicio < p_fecha_fin THEN
        SELECT COUNT(DISTINCT idpartida)
        INTO numpartidas
        FROM acceso
        WHERE fhentrada >= p_fecha_inicio AND fhsalida <= p_fecha_fin AND fhentrada < fhsalida;
    END IF;

    RETURN numpartidas;
EXCEPTION
    WHEN OTHERS THEN
        RETURN numpartidas;
END;
/

CREATE OR REPLACE PROCEDURE mostrar_accesos_por_jugador
IS
    v_idjugador INTEGER;
    v_num_accesos NUMBER;
BEGIN
    FOR rec IN (SELECT idjugador, nombre
        FROM jugador
        ORDER BY idjugador) LOOP

        SELECT COUNT(*) INTO v_num_accesos
        FROM acceso
        WHERE idjugador = rec.idjugador;

        DBMS_OUTPUT.PUT_LINE('Jugador ' || rec.idjugador || ' - ' || UPPER(rec.nombre) || ': ' ||  v_num_accesos || ' accesos');
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en el procedimiento: ' || SQLERRM);
END;
/

-- Jugadores
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(1, 23456957, 'Laura', 'Gómez Pérez', 'ES9121000418450200051332', 25.50, TO_DATE('2027-02-01', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(2, 12345678, 'Carlos', 'Martínez Ruiz', 'ES2300491500192123456789', 30.00, TO_DATE('2027-01-15', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(3, 34567890, 'Ana', 'López Díaz', 'ES7601825778346200001234', 22.75, TO_DATE('2027-02-10', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(4, 45678901, 'Luis', 'Hernández Torres', 'ES3514650100911234567890', 28.00, TO_DATE('2027-02-20', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(5, 56789012, 'Elena', 'Sánchez Gómez', 'ES1201822370960200012345', 19.99, TO_DATE('2027-02-26', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(6, 67890123, 'Javier', 'Moreno Sáez', 'ES9800750001680654321000', 35.00, TO_DATE('2027-03-01', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(7, 78901234, 'María', 'Fernández López', 'ES8721034567890123456789', 27.45, TO_DATE('2027-02-28', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(8, 89012345, 'Pablo', 'Jiménez Romero', 'ES4500491234560012345678', 24.00, TO_DATE('2027-01-10', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(9, 90123456, 'Lucía', 'Navarro Vidal', 'ES6614650100940012340000', 29.90, TO_DATE('2027-03-15', 'YYYY-MM-DD'));
INSERT INTO jugador (idjugador, dni, nombre, apellidos, iban, cuota, falta) VALUES
(10, 11223344, 'Sergio', 'Castro Molina', 'ES3000492352310012345678', 35.00, TO_DATE('2027-04-20', 'YYYY-MM-DD'));

-- Partidas
INSERT INTO partida (idpartida, nombre) VALUES (1, 'Gonzalos');
INSERT INTO partida (idpartida, nombre) VALUES (2, 'Examples 2030');
INSERT INTO partida (idpartida, nombre) VALUES (3, 'Club partida');
INSERT INTO partida (idpartida, nombre) VALUES (4, 'Los Charrajeros');
INSERT INTO partida (idpartida, nombre) VALUES (5, 'Sunset Beach');
INSERT INTO partida (idpartida, nombre) VALUES (6, '3D Animators');

-- Accesos
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(1, 1, TO_DATE('2027-03-02 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(1, 2, TO_DATE('2027-03-05 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-05 19:20:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(1, 3, TO_DATE('2027-03-10 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(1, 1, TO_DATE('2027-03-15 12:10:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-15 13:25:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(1, 2, TO_DATE('2027-03-20 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-20 18:10:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(2, 2, TO_DATE('2027-03-03 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(2, 3, TO_DATE('2027-03-10 08:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-10 13:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(2, 4, TO_DATE('2027-03-14 09:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-14 12:45:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(2, 3, TO_DATE('2027-03-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-20 17:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(2, 2, TO_DATE('2027-03-25 07:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-25 11:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(3, 4, TO_DATE('2027-04-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-05 13:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(3, 4, TO_DATE('2027-04-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-15 14:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(3, 4, TO_DATE('2027-04-20 11:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-20 15:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(3, 4, TO_DATE('2027-05-01 08:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-01 12:45:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(3, 4, TO_DATE('2027-05-10 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-10 13:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(4, 3, TO_DATE('2027-04-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-10 11:15:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(4, 3, TO_DATE('2027-04-26 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-26 13:45:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(4, 3, TO_DATE('2027-05-05 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-05 12:20:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(4, 3, TO_DATE('2027-05-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-20 16:10:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(5, 5, TO_DATE('2027-05-06 08:50:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-06 11:10:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(5, 5, TO_DATE('2027-05-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-15 12:45:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES 
(5, 5, TO_DATE('2027-05-22 13:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-22 17:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(6, 1, TO_DATE('2027-04-02 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-02 12:15:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(6, 2, TO_DATE('2027-04-12 17:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-12 20:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(6, 3, TO_DATE('2027-04-25 08:45:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-25 11:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(6, 4, TO_DATE('2027-05-08 09:10:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-08 12:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(7, 5, TO_DATE('2027-04-03 14:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-03 17:10:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(7, 3, TO_DATE('2027-04-14 18:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-14 21:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(7, 1, TO_DATE('2027-05-02 11:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-02 13:00:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(7, 2, TO_DATE('2027-05-18 16:45:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-18 19:10:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(8, 4, TO_DATE('2027-03-22 09:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-03-22 12:20:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(8, 5, TO_DATE('2027-04-07 10:30:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-07 12:40:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(8, 2, TO_DATE('2027-04-19 17:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-19 19:30:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(8, 3, TO_DATE('2027-05-09 09:15:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-09 11:45:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(9, 1, TO_DATE('2027-04-01 13:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-01 15:15:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(9, 4, TO_DATE('2027-04-20 08:50:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-04-20 12:10:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(9, 5, TO_DATE('2027-05-03 10:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-03 13:15:00','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO acceso (idjugador, idpartida, fhentrada, fhsalida) VALUES
(9, 2, TO_DATE('2027-05-21 17:45:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2027-05-21 20:05:00','YYYY-MM-DD HH24:MI:SS'));

-- Pruebas
SET SERVEROUTPUT ON;
SELECT num_partidas(DATE '2027-01-01', DATE '2027-04-15') AS NUM_PARTIDAS FROM DUAL;
CALL entrar_jugador_en_partida(1, 6);
CALL entrar_jugador_en_partida(3, 6);
-- CALL eliminar_partida(6);
CALL mostrar_accesos_por_jugador();

COMMIT;
