package ej2;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GestionarHTML {
    public static void main(String[] args) {
        try {
            // Crear el lector XML
            XMLReader reader = XMLReaderFactory.createXMLReader();

            // Desactivar validación y carga de DTDs
            reader.setFeature("http://xml.org/sax/features/validation", false);
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            // Crear el manejador de eventos
            ManejadorSAXHTML manejador = new ManejadorSAXHTML();

            // Asignar el manejador al lector
            reader.setContentHandler(manejador);

            // Cargar y parsear el archivo XML (ajusta la ruta)
            FileInputStream archivoXML = new FileInputStream("res/misficheros"+ File.separator +"miHTML.xml");
            reader.parse(new InputSource(archivoXML));

            // Mostrar el resultado procesado
            System.out.println("Documento XML parseado correctamente");
            System.out.println(manejador.getXMLResult());

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
