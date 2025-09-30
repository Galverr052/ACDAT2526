package controller;

import java.io.File;

public class ClientesApp {
    public static void main(String[] args) {
        GestionaClientes gestiona = new GestionaClientes();
        File fmediciones = new File("res" + File.separator + "mediciones.csv");
        File fcontadores = new File("res" + File.separator + "contadores.csv");
        File fclientes = new File("res" + File.separator + "clientes.csv");

        File resultFile = gestiona.consumoSiministros(fmediciones);

        if (resultFile != null)
            System.out.println("Archivo de consumo generado en: " + resultFile.getAbsolutePath());

        if (gestiona.generarFicheroContadores(fcontadores, fmediciones)) {
            File fbincontadores = new File("res" + File.separator + "contadores.dat");
            gestiona.mostrarConsumoContadores(fbincontadores);
        }
    }
}
