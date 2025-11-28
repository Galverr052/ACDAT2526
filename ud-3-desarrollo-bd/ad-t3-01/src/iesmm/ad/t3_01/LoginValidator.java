package iesmm.ad.t3_01;

import java.sql.*;
import java.util.Scanner;

/**
 * Login validator para un empleado a partir de su número y email.
 * Al tercer intento fallido sale del programa.
 * <p>
 * NOTA: En SGBD posee la siguiente función implementada que deberá invocarse desde el programa:
 * es_empleado_valido (id INTEGER, femail VARCHAR(100))
 */
public class LoginValidator {
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
        int idempleado;
        String email;
        boolean es_login_valido = false;
        int nintentos = 0;
        final int MAX_INTENTOS = 3; // Nº de intentos máximo permitido
        Scanner input = new Scanner(System.in);

        try {
            // Crear conexión a la base de datos
            generarConexion("localhost", "classicmodels", "root", "root");

            do {
                // Introducir número de empleado y email
                System.out.print("EMAIL EMPLEADO: ");
                email = input.nextLine();
                System.out.print("NUMERO EMPLEADO: ");
                idempleado = Integer.parseInt(input.nextLine()); // input.nextInt() no captura retorno de carro

                // Crear y preparar objeto para ejecutar la sentencias SQL
                CallableStatement loginStatement = connection.prepareCall("{ ? = call es empleado_valido(?, ?) }");
                loginStatement.registerOutParameter(1, Types.BOOLEAN);
                loginStatement.setInt(2, idempleado);
                loginStatement.setString(3, email);
                loginStatement.execute();

                // Obtener resultados a mostrar
                es_login_valido = loginStatement.getBoolean(1);

                if(es_login_valido)
                    System.out.println("Entrada al sistema de: "+ email);
                else {
                    System.out.println("Error de autentificación");
                    nintentos++;
                }

            } while (!es_login_valido && nintentos < MAX_INTENTOS);

            if (nintentos >= MAX_INTENTOS)
                System.err.println("HA SUPERADO NUMERO DE INTENTOS MAXIMO PERMITIDO");

        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            System.err.println("Message: " + e.getMessage());
        } finally {
            // Cerrar conexión


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