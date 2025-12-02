package iesmm.ad.t3_04.dao;

import iesmm.ad.t3_04.model.Libro;

import java.util.List;

public interface DAO {
    List<Libro> getAll();
    Libro get(int id);
    boolean insertar(Libro libro);
    boolean actualizar(Libro libro);
    boolean eliminar(int id);
}
