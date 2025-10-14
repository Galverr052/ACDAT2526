package controller;

import java.io.File;

public class TrenesApp {
    public static void main(String[] args) {
        ControlTrenes ct = new ControlTrenes();
        File estaciones = new File("res/estaciones.csv");
        File horarios = new File("res/horarios.csv");

        File resultFile = ct.ocupacion();

        boolean ok = ct.generarFicheros(estaciones, horarios);
        System.out.println("Resultado: " + ok);

    }
}
