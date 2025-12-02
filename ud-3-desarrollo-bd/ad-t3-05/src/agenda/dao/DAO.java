package agenda.dao;

import agenda.model.Categoria;
import agenda.model.Contacto;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO {
    Collection<Contacto> obtenerContactos(Categoria cat);
    Contacto obtenerContacto(int id);
    void agregarContacto(Contacto co) throws SQLException;
    void actualizarContacto(Contacto co) throws SQLException;
    void borrarContacto(int id) throws SQLException;
    void vaciarAgenda() throws SQLException;
}
