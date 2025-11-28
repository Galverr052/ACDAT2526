package dao;

import model.Articulo;
import utils.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOImpl implements DAO {
    private static final Logger logTag = Logger.getLogger("AD");

    @Override
    public boolean insertar(Articulo articulo) {
        Connection c;
        boolean valueReturn = false;
		
		// implementar código
		
        return valueReturn;
    }

    @Override
    public List<Articulo> listarArticulos() {
        Connection c;
        List<Articulo> listaArticulos = new ArrayList<Articulo>();
        
		// implementar código

        return listaArticulos;
    }

    @Override
    public List<Articulo> listarArticulosPorCategoria(String categoria) {
        Connection c;
        List<Articulo> listaArticulos = new ArrayList<Articulo>();

		// implementar código

        return listaArticulos;
    }

    @Override
    public Articulo obtenerPorId(int id) {
        Connection c;
        boolean encontrado = false;

		// implementar código

        return articulo;
    }

    @Override
    public boolean actualizar(Articulo articulo) {
        Connection c;
        boolean valueReturn = false;
        
		// implementar código
		
        return valueReturn;
    }

    @Override
    public boolean eliminar(int id) {
        Connection c;
        boolean valueReturn = false;
        
		// implementar código

        return valueReturn;
    }

    /**
     * Muestra los errores y excepciones producidas en la operación en la base de datos.
     * @param e Excepción
     */
    private static void showSQLErrors (SQLException e) {
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
    }
}