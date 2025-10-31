package ej8;
import org.dom4j.Element;
import org.dom4j.DocumentException;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class ConsultasInventario {

    public static void main(String[] args) {

        File f = new File("res/ej8" + File.separator + "catalogo.xml");

        if (!f.exists()) {
            System.err.println("Fichero no existe");
            return;
        }

        try {
            //Juegos de mesa existentes
            String xpath1 = "//juegomesa";
            ArrayList<Element> res1 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath1);
            System.out.println("Juegos de mesa existentes:");
            for (Element e : res1) {
                System.out.println("  - " + e.elementText("titulo"));
            }

            //Títulos de los videojuegos cuyo stock sea superior a 10 unidades
            String xpath2 = "//videojuego[stock > 10]/titulo";
            ArrayList<Element> res2 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath2);
            System.out.println("Títulos de los videojuegos con stock mayor a 10:");
            for (Element e : res2) {
                System.out.println("  - " + e.getText());
            }

            //Contabilizar el número de videojuegos que no tienen capturas de pantalla
            String xpath3 = "count(//videojuego[not(imagenes/captura)])";
            Object res3 = ProcesadorXPath.consultaJaxen(f, xpath3);
            System.out.println("Número de videojuegos sin capturas: " + res3);

            // Capturas del título de videojuego mario bros
            String xpath4 = "//videojuego[titulo='MARIO BROS']/imagenes/captura";
            ArrayList<Element> res4 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath4);
            System.out.println("Capturas del videojuego 'MARIO BROS':");
            for (Element e : res4) {
                System.out.println("  - " + e.getText());
            }

            //Títulos de los videojuegos NES con stock = 15
            String xpath5 = "//videojuego[plataforma='NES' and stock >= 15]/titulo";
            ArrayList<Element> res5 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath5);
            System.out.println("Videojuegos de la plataforma 'NES' con stock igual o mas de 15:");
            for (Element e : res5) {
                System.out.println("  - " + e.getText());
            }

        } catch (DocumentException | JaxenException e) {
            e.printStackTrace();
        }
    }
}
