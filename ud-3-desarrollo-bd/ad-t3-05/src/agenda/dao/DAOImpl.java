package agenda.dao;

import agenda.model.Categoria;
import agenda.model.Contacto;
import utils.Conexion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOImpl implements DAO {

    private static Connection conexion;

    public DAOImpl() throws SQLException, IOException {
        this.conexion = Conexion.getConnection(); // Conexión persistente en operaciones CRUD
    }

	private void cargarDatosDeContactoEnSentencia(Contacto contacto, PreparedStatement ps) throws SQLException {
        ps.setString(1, contacto.getNombre());
        ps.setString(2, contacto.getApellido());
        ps.setString(3, contacto.getMail());
        ps.setString(4, contacto.getTelefono());
        ps.setString(5, contacto.getDireccion());
        ps.setString(6, contacto.getFechaDeNacimiento().toString());
        String cat = null;

        if (contacto.getCategoria() != Categoria.OTROS) {
            cat = contacto.getCategoria().name();
        }

        ps.setString(7, cat);
    }

    private Contacto generarContacto(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String nom = rs.getString(2);
        String ape = rs.getString(3);
        String mail = rs.getString(4);
        String tel = rs.getString(5);
        String dir = rs.getString(6);
        Date fecha = Date.valueOf(rs.getString(7));
        Categoria c = Categoria.OTROS;

        if (rs.getString(8) != null) {
            c = Categoria.valueOf(rs.getString(8));
        }

        return new Contacto(id, nom, ape, mail, tel, dir, fecha, c);
    }

    @Override
    public Collection<Contacto> obtenerContactos(Categoria cat) {
		return null;
    }

    @Override
    public Contacto obtenerContacto(int id) {
        return null;
    }

    @Override
    public void agregarContacto(Contacto contacto) throws SQLException {
        
    }

    @Override
    public void actualizarContacto(Contacto contacto) throws SQLException {

    }

    @Override
    public void borrarContacto(int id) throws SQLException {
        
    }

    @Override
    public void vaciarAgenda() throws SQLException {
        
    }

    public void close() throws SQLException {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo cerrar conexión");
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
