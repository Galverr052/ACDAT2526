import dao.DAO;
import dao.DAOImpl;
import model.Articulo;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        Logger logTag = Logger.getLogger("AD");
        boolean valueReturn = false; // Valor de retorno en cada modificación del modelo
        Scanner sc = new Scanner(System.in);
        String categoria;

        // Obtiene un objeto DAO a partir de la sesión iniciada y los parámetros configurados
        DAO dao = new DAOImpl();

        // MENÚ DE CONSULTAS
        int op = 0;
        do {
            System.out.println("============================");
            System.out.println("1. Listado de artículos");
            System.out.println("2. Insertar artículo");
            System.out.println("3. Actualizar artículo");
            System.out.println("4. Eliminar artículo");
            System.out.println("5. Filtrar artículos por categoría");
            System.out.println("6. SALIR");
            System.out.println("Introducir opción (1-6): ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    logTag.log(Level.INFO, "Acción consultada: " + "CONSULTA");
                    List<Articulo> articulos = dao.listarArticulos();

                    for (Articulo a : articulos)
                        System.out.println(a);
                    break;

                case 2:
                    logTag.log(Level.INFO, "Acción consultada: " + "INSERTAR");
                    valueReturn = dao.insertar(new Articulo(2, "PL002", "PAPEL PARED", "PAPEL DECORATIVO HOGAR", 10, 0.20, 1));

                    if (valueReturn)
                        System.out.println("Producto insertado con ID: 2");

                    break;

                case 3:
                    logTag.log(Level.INFO, "Acción consultada: " + "ACTUALIZAR");
                    if (dao.actualizar(new Articulo(2, "PD002", "PAPEL ARTDECOR", "PAPEL ADHESIVO", 12, 0.20, 1)))
                        System.out.println("Artículo actualizado");
                    break;

                case 4:
                    logTag.log(Level.INFO, "Acción consultada: " + "ELIMINAR");

                    valueReturn = dao.eliminar(2);

                    if (valueReturn)
                        System.out.println("Producto eliminado con ID: 2");

                    break;

                case 5:
                    logTag.log(Level.INFO, "Acción consultada: " + "FILTRAR POR CATEGORÍA");
                    System.out.print("Introducir categoría: ");
                    categoria = sc.next();

                    if (!categoria.isEmpty()) {
                        List<Articulo> articulos_categoria = dao.listarArticulosPorCategoria(categoria);

                        for (Articulo a : articulos_categoria)
                            System.out.println(a);
                    }
                    break;

                case 6:
                    break;
                default:
                    System.out.println("Opción errónea");
            }
        } while (op != 6);
    }
}