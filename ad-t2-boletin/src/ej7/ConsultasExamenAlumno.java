package ej7;

import org.dom4j.Element;
import org.dom4j.DocumentException;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class ConsultasExamenAlumno {

    public static void main(String[] args) {
        File f = new File("res/ej7"+ File.separator +"examen.xml"); // Ruta del XML

        if (!f.exists()) {
            System.err.println("Fichero no existe");
            return;
        }

        try {
            //Número de respuestas contestadas
            String xpath1 = "count(/examen/cuestion[normalize-space(respuesta) != ''])";
            Object res1 = ProcesadorXPath.consultaJaxen(f, xpath1);
            System.out.println("1) Número de respuestas contestadas: " + res1);

            // 2) Categorías existentes de la 3ª pregunta
            String xpath2 = "/examen/cuestion[3]/categoria";
            ArrayList<Element> res2 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath2);
            System.out.println("2) Categorías de la 3ª pregunta:");
            for (Element e : res2) {
                System.out.println("  - " + e.getText());
            }

            //Categorías de la penúltima pregunta
            String xpath3 = "/examen/cuestion[last()-1]/categoria";
            ArrayList<Element> res3 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath3);
            System.out.println("3) Categorías de la penúltima pregunta:");
            for (Element e : res3) {
                System.out.println("  - " + e.getText());
            }

            //Respuestas vacía
            String xpath4 = "count(/examen/cuestion[not(respuesta) or normalize-space(respuesta) = ''])";
            Object res4 = ProcesadorXPath.consultaJaxen(f, xpath4);
            System.out.println("4) Número de cuestiones no respondidas: " + res4);

            //Respuestas ofrecidas
            String xpath5 = "/examen/cuestion[puntuacion >= 9]/respuesta";
            ArrayList<Element> res5 = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath5);
            System.out.println("5) Respuestas con puntuación mayor a 9:");
            for (Element e : res5) {
                System.out.println("  - " + e.getText());
            }

            //Número de respuestas aprobadas
            String xpath6 = "count(/examen/cuestion[puntuacion >= 5])";
            Object res6 = ProcesadorXPath.consultaJaxen(f, xpath6);
            System.out.println("6) Número de respuestas aprobadas: " + res6);

        } catch (DocumentException | JaxenException e) {
            e.printStackTrace();
        }
    }
}
