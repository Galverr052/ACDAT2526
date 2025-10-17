package accesosax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionarSAX {
	// Objeto Handler que almacena el XML seleccionado durante el recorrido.
	private DefaultHandler handler;

	public GestionarSAX(DefaultHandler manejador) {
		this.handler = manejador;
	}

	public void imprimirNodos() {
		System.out.println(handler.toString());
	}

	public int abrir_XML_SAX(File fichero) {
		try {
			// Se crea un objeto SAXParserFactory
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// Se crea un objeto SAXParser para interpretar el documento XML.
			SAXParser parser = factory.newSAXParser();

			// Se da la salida al parser para que comience a manejar el
			// documento XML. Esto recorrerá secuencialmente el documento XML
			// y cuando detecte un comienzo o fin de elemento o un texto
			// entonces lo tratará (según la implementación realizada en el
			// event handler)
			parser.parse(fichero, handler);

			return 0;

		} catch (SAXException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}