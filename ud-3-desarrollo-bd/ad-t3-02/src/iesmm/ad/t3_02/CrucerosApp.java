package iesmm.ad.t3_02;

import java.sql.*;

public class CrucerosApp {

    // Crear objeto conexión
    private static Connection connection = null;

    /**
     * Genera la conexión a la BD
     *
     * @param HOST     IP o localhost
     * @param SID      Nombre de base de datos
     * @param usuario  Usuario con permisos en la BD
     * @param password Password del usuario
     */
    private static void generarConexion(String HOST, String SID, String usuario, String password) throws SQLException {
        // Crear conexión a la base de datos
        connection = DriverManager.getConnection("jdbc:ucanaccess://" + HOST + "/" + SID, usuario, password);

        // setAutoCommit: Cada sentencia SQL individual es tratada como una transación
        // y será automáticamente confirmada al SGBD justo después de ser ejecutada.
        connection.setAutoCommit(true);
    }

    public static void main(String[] args) {

        try {
            /* INSERCIÓN DE REGISTRO */

            // Crear conexión a la base de datos
            generarConexion("c:", "proyectos/ad-t2-02/res/cruceros.accdb", "", "");

            // Crear objeto para ejecutar la sentencia SQL de operación de inserción
            String sql = "INSERT INTO puerto (ciudad,pais) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "FINISTERRE");
            preparedStatement.setString(2, "ESPAÑA");

            // Ejecuta operación de inserción a partir de consulta preparada
            preparedStatement.execute();

            /* CONSULTA DE REGISTROS */
            sql = "SELECT * FROM cliente c JOIN crucerocliente cl ON c.NIF = cl.NIF";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String idcrucero = result.getString("idcrucero");
                String NIF = result.getString("NIF");
                String nombre_completo = result.getString("nombre") + " " + result.getString("apellidos");

                System.out.println(idcrucero + ", " + NIF + ", " + nombre_completo);
            }

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
