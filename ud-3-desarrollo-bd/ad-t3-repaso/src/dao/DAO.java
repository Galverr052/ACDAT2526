package dao;


import model.Acceso;
import model.Jugador;
import model.Partida;

import java.sql.Date;
import java.util.List;

public interface DAO {
    List<Acceso> accesosJugador(Jugador j);
    List<Jugador> cuotaMasAlta();
    int contabilizarNumPartidas(Date finicio, Date ffin);
    List<Acceso> insertarAcceso(Jugador j, Partida p);
    boolean eliminarJugador(Jugador jugador);
}
