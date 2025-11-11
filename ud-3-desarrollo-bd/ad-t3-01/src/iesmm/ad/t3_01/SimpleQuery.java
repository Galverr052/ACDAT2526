package iesmm.ad.t3_01;

import java.sql.*;

public class SimpleQuery {
    public static void main(String[] args) {
        // Crear objeto conexión
        Connection connection = null;

        try {
            // Crear conexión a la base de datos
            // La paso el nombre de la base de datos classimodels
            // El usuario de la base de datos user=root
            // La contraseña password=root
            connection = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels?user=root&password=root");

            // Crear objeto para ejecutar la sentencia SQL
            Statement statement = connection.createStatement();

            // Establezco un tiempo máximo de respuesta (OPCIONAL)
            statement.setQueryTimeout(30);

            // Ejecuta consulta de sencilla de búsqueda
            // El resultado se almacena en el ResultSet (conjunto de resultados)
            ResultSet rs = statement.executeQuery("select * from customers");

            // Recorrer conjunto de resultados
            while (rs.next()) {
                // Obtener un campo de tipo entero
                System.out.println("ID cliente: " + rs.getInt("customerNumber"));
                // Obtener un campo cadena
                System.out.println("Nombre: " + rs.getString("customerName"));
                // Obtener un campo de tipo real
                System.out.println("Limite monetario: " + rs.getDouble("creditLimit"));
                // Obtener un campo por posición
                System.out.println("Tercer campo: " + rs.getString(3));
                System.out.println("-----------------");
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
