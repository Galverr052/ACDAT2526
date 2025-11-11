package iesmm.ad.t3_01;

import java.sql.*;
import java.util.Scanner;

public class SimpleProcedureFunction {
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
        int num_cliente;
        double limite = 0;
        String nivel;

        try {
            // Crear conexión a la base de datos
            generarConexion("localhost", "classicmodels", "root", "root");

            // Crear y preparar objeto para ejecutar la sentencias SQL
            System.out.println("Introducir nº cliente: ");
            num_cliente = new Scanner(System.in).nextInt();
            CallableStatement callableStatement = connection.prepareCall("{call obtener_limite_credito(?)}");
            callableStatement.setInt(1, num_cliente);
            boolean result = callableStatement.execute(); // Ejecutar sentencia de llamada a procedimiento almacenado

            // Obtener resultados a mostrar
            if (result) {
                ResultSet rs = callableStatement.getResultSet();
                while (rs.next()) {
                    limite = rs.getDouble(1);

                    // Calcular nivel asociado según llamada a función
                    CallableStatement nivelCredito = connection.prepareCall("{ ? = call calcular_nivel_credito(?) }");
                    nivelCredito.registerOutParameter(1, Types.VARCHAR); // Tipo de dato del valor de retorno de la función
                    nivelCredito.setDouble(2, limite); // Parámetro de entrada de la función
                    nivelCredito.execute(); // Ejecución de función
                    nivel = nivelCredito.getString(1); // Obtener valor del dato devuelto

                    // Resultados
                    System.out.println("CLIENTE " + num_cliente + "\nNIVEL CREDITO: " + nivel + "\nLIMITE MONETARIO: " + limite + " €");
                }
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