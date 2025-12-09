package dao;


import model.Acceso;
import model.Jugador;
import model.Partida;
import utils.Conexion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {

    public List<Acceso> accesosJugador(Jugador jugador) {
        Connection c;
        List<Acceso> listaAccesos = null;
        String sql = "SELECT * FROM acceso WHERE idjugador = ? ORDER BY fhentrada DESC";

        try {
            c = Conexion.getConnection();
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, jugador.getIdjugador());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                listaAccesos = new ArrayList<Acceso>();

                do {
                    // Consultar campos
                    int idjugador = resultSet.getInt("idjugador");
                    int idpartida = resultSet.getInt("idpartida");
                    Date fhentrada = resultSet.getDate("fhentrada");
                    Date fhsalida = resultSet.getDate("fhsalida");

                    // Crear objeto Acceso
                    Acceso acceso = new Acceso(idjugador, idpartida, fhentrada, fhsalida);

                    // Insertar objeto en colección
                    listaAccesos.add(acceso);
                } while (resultSet.next());
            }

            Conexion.close();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return listaAccesos;
    }

    public List<Jugador> cuotaMasAlta() {
        Connection c;
        List<Jugador> listaJugadores = null;
        String sql = "SELECT * FROM jugador WHERE cuota = (SELECT MAX(cuota) FROM jugador)";

        try {
            c = Conexion.getConnection();
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                listaJugadores = new ArrayList<Jugador>();

                do {
                    // Consultar campos
                    int idjugador = resultSet.getInt("idjugador");
                    int dni = resultSet.getInt("dni");
                    String nombre = resultSet.getString("nombre");
                    String iban = resultSet.getString("iban");
                    float cuota = resultSet.getFloat("cuota");
                    Date falta = resultSet.getDate("falta");

                    // Crear objeto Jugador
                    Jugador jugador = new Jugador(idjugador, dni, nombre, iban, cuota, falta);

                    // Insertar objeto en colección
                    listaJugadores.add(jugador);
                } while (resultSet.next());
            }

            Conexion.close();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return listaJugadores;
    }
    public int contabilizarNumPartidas(Date finicio, Date ffin) {
        Connection c;
        int npartidas = -1;
        String sql = "{ ? = call num_partidas(?, ?) }";
        // Prueba en cliente DB: SELECT num_partidas(DATE '2027-03-15', DATE '2027-03-30') FROM DUAL;

        try {
            c = Conexion.getConnection();
            CallableStatement callableStatement = c.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setDate(2, finicio);
            callableStatement.setDate(3, ffin);
            callableStatement.execute(); // Ejecutar sentencia de llamada a función
            npartidas = callableStatement.getInt(1); // Obtener número de partidas
            Conexion.close();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return npartidas;
    }

    public List<Acceso> insertarAcceso(Jugador jugador, Partida partida) {
        Connection c = null;
        List<Acceso> listaAccesos = null;
        String sql1 = "{ call entrar_jugador_en_partida(?, ?) }";
        String sql2 = "SELECT * FROM acceso ORDER BY fhentrada DESC";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false); // Inicio transacción: control manual usando commit() o rollback()

            // 1. Insertar jugador en partida
            CallableStatement callableStatement = c.prepareCall(sql1);
            callableStatement.setInt(1, jugador.getIdjugador());
            callableStatement.setInt(2, partida.getIdpartida());
            callableStatement.execute(); // Ejecutar sentencia de llamada a procedimiento almacenado

            // 2. Obtener lista de accesos recientes
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(sql2);

            if (resultSet.next()) {
                listaAccesos = new ArrayList<Acceso>();

                do {
                    // Consultar campos
                    int idjugador = resultSet.getInt("idjugador");
                    int idpartida = resultSet.getInt("idpartida");
                    Date fhentrada = resultSet.getDate("fhentrada");
                    Date fhsalida = resultSet.getDate("fhsalida");

                    // Crear objeto Acceso
                    Acceso acceso = new Acceso(idjugador, idpartida, fhentrada, fhsalida);

                    // Insertar objeto en colección
                    listaAccesos.add(acceso);
                } while (resultSet.next());

                c.commit(); // Validar transacción: NO restablece autocommit a true
            }
        } catch (SQLException e) {
            if (c != null)
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    showSQLErrors(ex);
                }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (c != null)
                try {
                    c.setAutoCommit(true); // Restablecimiento del control transaccional
                    Conexion.close();
                } catch (SQLException e) {
                    showSQLErrors(e);
                }
        }

        return listaAccesos;
    }

    public boolean eliminarJugador(Jugador jugador) {
        Connection c = null;
        boolean vreturn = false;
        String sql1 = "DELETE FROM acceso WHERE idjugador = ?";
        String sql2 = "DELETE FROM jugador WHERE idjugador = ?";

        try {
            c = Conexion.getConnection();
            c.setAutoCommit(false);

            // 1. Eliminar accesos del jugador
            PreparedStatement statement1 = c.prepareStatement(sql1);
            statement1.setInt(1, jugador.getIdjugador());
            vreturn = statement1.executeUpdate() > 0;

            if (vreturn) {
                // 2. Eliminar jugador
                PreparedStatement statement2 = c.prepareStatement(sql2);
                statement2.setInt(1, jugador.getIdjugador());
                vreturn = statement2.executeUpdate() > 0;

                c.commit();
            }
        } catch (SQLException e) {
            if (c != null)
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    showSQLErrors(ex);
                }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (c != null)
                try {
                    c.setAutoCommit(true);
                    Conexion.close();
                } catch (SQLException e) {
                    showSQLErrors(e);
                }
        }

        return vreturn;
    }
    private static void showSQLErrors(SQLException e) {
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
    }

}
