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

    @Override
    public Collection<Contacto> obtenerContactos(Categoria cat) {
        Collection<Contacto> contactos = null;
        String sql = "SELECT * FROM CONTACTOS ";

        try {
            // 1. Preparación de consulta SQL
            Statement stmt = this.conexion.createStatement();
            contactos = new ArrayList<>();

            switch (cat) {
                case TODOS:
                    sql += "";
                    break;
                case OTROS:
                    sql += "WHERE categoria is NULL";
                    break;
                default:
                    sql += "WHERE categoria = '" + cat.name() + "'";
            }

            // 2. Ejecución de consulta y generación de lista de contactos
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                contactos.add(generarContacto(rs));
            }
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            throw new RuntimeException("No se pudieron obtener los contactos", e);
        }

        return contactos;
    }

    @Override
    public Contacto obtenerContacto(int id) {
        Contacto contacto = null;
        String sql = "SELECT * FROM contactos WHERE id = " + id;

        try {
            Statement stmt = this.conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            contacto = generarContacto(rs);
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener contacto con ID " + id, e);
        }

        return contacto;
    }

    @Override
    public void agregarContacto(Contacto contacto) throws SQLException {
        String sql = "INSERT INTO contactos VALUES (null,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            cargarDatosDeContactoEnSentencia(contacto, ps); // Cargar datos del contacto en consulta preparada
            ps.executeUpdate();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar contacto\n" + contacto, e);
        }
    }

    @Override
    public void actualizarContacto(Contacto contacto) throws SQLException {
        String campos = "nombre = ?, apellido = ?, mail = ?, telefono = ?";
        campos += ", direccion = ?, fecha_nacimiento = ?, categoria = ?";
        String sql = "UPDATE contactos SET " + campos + " WHERE id = " + contacto.getId();

        try {
            PreparedStatement ps = this.conexion.prepareStatement(sql);
            cargarDatosDeContactoEnSentencia(contacto, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo actualizar contacto\n" + contacto, ex);
        }
    }

    @Override
    public void borrarContacto(int id) throws SQLException {
        try (Statement s = conexion.createStatement();) {
            s.executeUpdate("DELETE FROM contactos WHERE contactos.id = " + id);
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo borrar contacto con id " + id, ex);
        }
    }

    @Override
    public void vaciarAgenda() throws SQLException {
        try {
            Statement s = conexion.createStatement();
            s.executeUpdate("DELETE FROM contactos");
            s.executeUpdate("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='contactos'"); // Reiniciar contador de IDs
        } catch (SQLException e) {
            showSQLErrors(e);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo vaciar la agenda", ex);
        }
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
