import javax.xml.xpath.XPathExpressionException;
import java.io.File;

public class Cochesapp {
    public static void main(String[] args) {
        File f = new File("res" + File.separator + "coches.xml");
        String txtExp = "//coche[fabricante='Nissan']";
        String txtExp2 = "//coche[fabricante='Renault' and unidades>15]";
        String txtExp3 = "count(//coche)";
        if (f.exists()) {
            try {
                System.out.println(ProcesadorXPath.consulta(f, txtExp));
                System.out.println(ProcesadorXPath.consulta(f, txtExp2));
                System.out.println(ProcesadorXPath.consulta(f, txtExp3));
            } catch (XPathExpressionException e) {
                System.err.println("Error en la expresion " + e.getLocalizedMessage());
            } catch (Exception e) {
                System.err.println("Error en la construccion de elemento " + e.getLocalizedMessage());
            }
        } else {
            System.err.println("Fichero no existe");
        }

    }
}