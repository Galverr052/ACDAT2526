import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jaxen.JaxenException;

import java.io.File;
import java.util.ArrayList;

public class CochesJaxenApp {
    public static void main(String[] args) {
        File f = new File("res" + File.separator + "coches.xml");
        String txtExp = "//coche";

        if (f.exists()) {
            try {
                // 1. Consulta y devuelve una lista de elementos a partir de una expresión
                ArrayList<Element> list = (ArrayList<Element>) ProcesadorXPath.consultaJaxen(f, txtExp);

                // 2. Recorremos la colección
                for (Element e : list)
                    System.out.println(e.asXML());

                System.out.println(ProcesadorXPath.consultaJaxen(f,"count(//coche)"));

            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (JaxenException e) {
                throw new RuntimeException(e);
            }
        }
        else
            System.err.println("Fichero no existe");
    }
}