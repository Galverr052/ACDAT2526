package ej4;

import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class GestionarCuentas {
    private ManejadorSAXUsuarios handler;
    public void imprimirNodos(){
        System.out.println(handler.getXMLResult());
    }

    public int abrir_XML_SAX(File fichero){
        try {
            //se crea un objeto SAXparserFactory.
            SAXParserFactory factory = SAXParserFactory.newInstance();

            //se crea un objeto SAXParser para que interprete el xml.
            SAXParser parser = factory.newSAXParser();

            //se crea una instancia del manejador que sera el que recorra el xml.
            handler = new ManejadorSAXUsuarios();

            parser.parse(fichero, handler);
            return (0);
        } catch (SAXException e){
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        GestionarCuentas miParser = new GestionarCuentas();

        File xmlFIle = new File("res/ej4" + File.separator + "usuarios.xml");

        if(miParser.abrir_XML_SAX(xmlFIle)==0){
            miParser.imprimirNodos();
        }

    }
}
