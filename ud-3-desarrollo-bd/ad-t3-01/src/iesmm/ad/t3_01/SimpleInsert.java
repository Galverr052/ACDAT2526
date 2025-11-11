package iesmm.ad.t3_01;

import java.sql.*;

public class SimpleInsert {
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

            // OPCIÓN 1: Crear objeto para ejecutar la sentencia SQL de operación de inserción
            Statement statement = connection.createStatement();
            statement.execute("insert into productlines(productline,textdescription) values ('Modern Electric Cars','Coches eléctricos')");

            // OPCIÓN 2: Crear y preparar objeto para ejecutar la sentencias SQL
            PreparedStatement preparedStatement = connection.prepareStatement("insert into productlines(productline,textdescription) values (?,?)");
            preparedStatement.setString(1, "Modern Hybrid Cars");
            preparedStatement.setString(2, "Coches híbridos enchufables");

            // Ejecuta operación de inserción a partir de consulta preparada
            preparedStatement.execute();

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
