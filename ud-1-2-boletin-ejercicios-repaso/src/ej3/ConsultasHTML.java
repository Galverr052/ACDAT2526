package ej3;

import org.dom4j.Element;
import org.dom4j.DocumentException;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class ConsultasHTML {

    public static void main(String[] args) {
        File f = new File("res/misficheros" + File.separator + "mihtml.xml"); // Ruta del XML

        if (!f.exists()) {
            System.err.println("Fichero no existe");
            return;
        }

        try {
            // 1) Número de tablas existentes
            String xpath1 = "count(//table)";
            Object res1 = ProcesadorXPath.consultaJaxen(f, xpath1);
            System.out.println("1) Número de tablas existentes: " + res1);

            // 2) Celdas existentes en cada una de las tablas
            String xpath2 = "//table";
            ArrayList<Element> tablas = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath2);
            System.out.println("2) Número de celdas en cada tabla:");
            int i = 1;
            for (Element tabla : tablas) {
                // Consulta de celdas dentro de cada tabla usando XPath relativo
                String xpathCeldas = "count(.//td)";
                org.jaxen.dom4j.Dom4jXPath xp = new org.jaxen.dom4j.Dom4jXPath(xpathCeldas);
                Object numCeldas = xp.evaluate(tabla);
                System.out.println("   Tabla " + i + ": " + numCeldas + " celdas");
                i++;
            }

            // 3) Primera fila de cada una de las tablas (filas cabecera)
            String xpath3 = "//table/tr[1]";
            ArrayList<Element> filasCabecera = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, xpath3);
            System.out.println("3) Primera fila de cada tabla:");
            int j = 1;
            for (Element fila : filasCabecera) {
                System.out.println("   Tabla " + j + ": " + fila.asXML());
                j++;
            }

            // 4) Número de tablas que contienen el valor de celda "Celda B1"
            String xpath4 = "count(//table[.//td = 'Celda B1'])";
            Object res4 = ProcesadorXPath.consultaJaxen(f, xpath4);
            System.out.println("4) Número de tablas que contienen 'Celda B1': " + res4);

            // 5) Número de filas que contienen, al menos, 2 celdas
            String xpath5 = "count(//tr[count(td) >= 2])";
            Object res5 = ProcesadorXPath.consultaJaxen(f, xpath5);
            System.out.println("5) Número de filas con al menos 2 celdas: " + res5);

        } catch (DocumentException | JaxenException e) {
            e.printStackTrace();
        }
    }
}
