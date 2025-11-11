package iesmm.ad.t3_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SimpleUpdate {
    // Crear objeto conexión
    private static Connection connection = null;

    /**
     * Genera la conexión a la BD
     *
     * @param HOST     IP o localhost
     * @param SID      En MySQL la base de datos usada (en el resto vacio) y Oracle ORADAM2
     * @param usuario  Usuario con permisos en la BD
     * @param password Password del usuario
     */
    private static void generarConexion(String HOST, String SID, String usuario, String password) throws SQLException {
        // Crear conexión a la base de datos
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + SID, usuario, password);

        // setAutoCommit: Cada sentencia SQL individual es tratada como una transación
        // y será automáticamente confirmada al SGBD justo después de ser ejecutada.
        connection.setAutoCommit(true);
    }

    public static void main(String[] args) {

        try {
            // Crear conexión a la base de datos
            generarConexion("localhost", "classicmodels", "root", "root");

            // Crear y preparar objeto para ejecutar la sentencias SQL
            String sql = "update productlines set productline=? where productline=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Modern Electric Cars");
            preparedStatement.setString(2, "Electric Cars");

            preparedStatement.execute(); // Ejecutar sentencia preparada

            // Comprobación de número de registros actualizados
            if (preparedStatement.getUpdateCount() > 0)
                System.out.println("Al menos, un registro ha sido actualizado");

        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            System.err.println("Message: " + e.getMessage());
        } finally {
            // Cerrar conexión
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                showSQLErrors(e);
            }
        }
    }

    /**
     * Muestra los errores y excepciones producidas en la operación en la base de datos.
     *
     * @param e Excepción
     */
    private static void showSQLErrors(SQLException e) {
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
    }
}
