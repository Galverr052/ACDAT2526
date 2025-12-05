package agenda.dao;

import agenda.model.Categoria;
import agenda.model.Contacto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOImpl implements DAO {
    private Connection connection;

    // Constructor que recibe una conexión a la base de datos SQLite
    public DAOImpl(Connection connection) {
        this.connection = connection;
    }

    // Obtener todos los contactos de una categoría específica
    @Override
    public Collection<Contacto> obtenerContactos(Categoria categoria) {
        Collection<Contacto> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE categoria = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.toString());  // Usamos categoria.toString() para pasarla como String
            ResultSet rs = stmt.executeQuery();  // Ejecuta la consulta

            // Recorre los resultados y los agrega a la lista de contactos
            while (rs.next()) {
                // Convertir el String a Categoria (enum)
                Categoria categoriaRecuperada = Categoria.valueOf(rs.getString("categoria"));

                Contacto contacto = new Contacto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("mail"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento"),
                        categoriaRecuperada
                );
                contactos.add(contacto);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja posibles errores
        }

        return contactos;  // Devuelve la lista de contactos
    }

    // Obtener un contacto por su ID
    @Override
    public Contacto obtenerContacto(int id) {
        Contacto contacto = null;
        String sql = "SELECT * FROM contactos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);  // Establece el ID en el PreparedStatement
            ResultSet rs = stmt.executeQuery();  // Ejecuta la consulta

            // Si existe un resultado, crea el contacto
            if (rs.next()) {
                // Convertir el String a Categoria (enum)
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                contacto = new Contacto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("mail"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_nacimiento"),
                        categoria
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja posibles errores
        }

        return contacto;  // Devuelve el contacto encontrado (o null si no existe)
    }

    // Agregar un nuevo contacto a la base de datos
    @Override
    public void agregarContacto(Contacto co) throws SQLException {
        String sql = "INSERT INTO contactos (nombre, apellido, mail, telefono, direccion, fecha_nacimiento, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, co.getNombre());
            stmt.setString(2, co.getApellido());
            stmt.setString(3, co.getMail());
            stmt.setString(4, co.getTelefono());  // Cambié a setString para coincidir con el tipo de tu clase
            stmt.setString(5, co.getDireccion());
            stmt.setDate(6, new java.sql.Date(co.getFechaDeNacimiento().getTime()));  // Convertimos a java.sql.Date
            stmt.setString(7, co.getCategoria().toString());  // Usamos toString() para almacenar la categoría como String
            stmt.executeUpdate();  // Ejecuta la inserción
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja posibles errores
            throw e;  // Vuelve a lanzar la excepción para que el código que llama al método pueda manejarla
        }
    }

    // Actualizar un contacto en la base de datos
    @Override
    public void actualizarContacto(Contacto co) throws SQLException {
        String sql = "UPDATE contactos SET nombre = ?, apellido = ?, mail = ?, telefono = ?, direccion = ?, fecha_nacimiento = ?, categoria = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, co.getNombre());
            stmt.setString(2, co.getApellido());
            stmt.setString(3, co.getMail());
            stmt.setString(4, co.getTelefono());  // Cambié a setString para coincidir con el tipo de tu clase
            stmt.setString(5, co.getDireccion());
            stmt.setDate(6, new java.sql.Date(co.getFechaDeNacimiento().getTime()));  // Convertimos a java.sql.Date
            stmt.setString(7, co.getCategoria().toString());  // Usamos toString() para almacenar la categoría como String
            stmt.setInt(8, co.getId());  // Establece el ID para el filtro de actualización
            stmt.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja posibles errores
            throw e;  // Vuelve a lanzar la excepción
        }
    }

    // Borrar un contacto por su ID
    @Override
    public void borrarContacto(int id) throws SQLException {
        String sql = "DELETE FROM contactos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);  // Establece el ID en el PreparedStatement
            stmt.executeUpdate();  // Ejecuta la eliminación
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja posibles errores
            throw e;  // Vuelve a lanzar la excepción
        }
    }

    // Vaciar la agenda (eliminar todos los contactos)
    @Override
    public void vaciarAgenda() throws SQLException {
        String sql = "DELETE FROM contactos";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);  // Ejecuta la eliminación de todos los contactos
        } catch (SQLException e) {
            e.printStackTrace();  // Maneja posibles errores
            throw e;  // Vuelve a lanzar la excepción
        }
    }
}
