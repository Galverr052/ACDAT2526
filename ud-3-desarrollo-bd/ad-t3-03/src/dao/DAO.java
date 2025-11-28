package dao;

import model.Articulo;

import java.util.List;

public interface DAO {
    boolean insertar(Articulo articulo);
    List<Articulo> listarArticulos();
    List<Articulo> listarArticulosPorCategoria(String categoria);
    Articulo obtenerPorId(int id);
    boolean actualizar(Articulo articulo);
    boolean eliminar(int id);
}
