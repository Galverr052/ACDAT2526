package ej2;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class GestionarLugares {
    public static void main(String[] args) {
        try {
            // Crear el lector XML
            XMLReader reader = XMLReaderFactory.createXMLReader();

            // Crear el manejador de eventos
            GestionarSAX manejador = new GestionarSAX();

            // Asignamos el manejador al lector
            reader.setContentHandler(manejador);

            // Cargar y parsear el archivo XML
            FileInputStream archivoXML = new FileInputStream("C:\\Users\\usuario\\Desktop\\repositorios GA\\ACDAT2526\\ad-t2-boletin\\res\\ej2\\mapa.xml");
            reader.parse(new InputSource(archivoXML));

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

