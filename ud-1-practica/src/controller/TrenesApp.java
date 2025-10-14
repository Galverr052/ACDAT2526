package controller;

import java.io.File;
import java.time.LocalTime;

public class TrenesApp {
    public static void main(String[] args) {
        ControlTrenes ct = new ControlTrenes();

        File estaciones = new File("res/estaciones.csv");
        File horarios = new File("res/horarios.csv");

        if (ct.generarFicheros(estaciones, horarios)) {
            System.out.println("Fichero .dat generado correctamente.");

            ct.ocupacion(LocalTime.of(7, 0), LocalTime.of(10, 0));
        }
    }
}
