package iesmm.ad.t3_04.dao;

import iesmm.ad.t3_04.model.Libro;
import iesmm.ad.t3_04.utils.Conexion;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOImpl implements DAO {

    @Override
    public List<Libro> getAll() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro";

        try {
            Connection c = Conexion.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Libro l = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio"),
                        rs.getInt("paginas")
                );
                lista.add(l);
            }

            Conexion.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    @Override
    public Libro get(int id) {
        Libro libro = null;
        String sql = "SELECT * FROM libro WHERE id = ?";

        try {
            Connection c = Conexion.getConnection();
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                libro = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio"),
                        rs.getInt("paginas")
                );
            }

            Conexion.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return libro;
    }

    @Override
    public boolean insertar(Libro libro) {
        boolean ok = false;
        String sql = "INSERT INTO libro (titulo, autor, anio, paginas) VALUES (?, ?, ?, ?)";

        try {
            Connection c = Conexion.getConnection();
            PreparedStatement st = c.prepareStatement(sql);

            st.setString(1, libro.getTitulo());
            st.setString(2, libro.getAutor());
            st.setInt(3, libro.getAnio());
            st.setInt(4, libro.getPaginas());

            ok = st.executeUpdate() > 0;

            Conexion.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return ok;
    }

    @Override
    public boolean actualizar(Libro libro) {
        boolean ok = false;
        String sql = "UPDATE libro SET titulo = ?, autor = ?, anio = ?, paginas = ? WHERE id = ?";

        try {
            Connection c = Conexion.getConnection();
            PreparedStatement st = c.prepareStatement(sql);

            st.setString(1, libro.getTitulo());
            st.setString(2, libro.getAutor());
            st.setInt(3, libro.getAnio());
            st.setInt(4, libro.getPaginas());
            st.setInt(5, libro.getId());

            ok = st.executeUpdate() > 0;

            Conexion.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return ok;
    }

    @Override
    public boolean eliminar(int id) {
        boolean ok = false;
        String sql = "DELETE FROM libro WHERE id = ?";

        try {
            Connection c = Conexion.getConnection();
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, id);

            ok = st.executeUpdate() > 0;

            Conexion.close();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return ok;
    }
}
