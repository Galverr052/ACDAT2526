package libros;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAX extends DefaultHandler {

	private String xmlResult;

	public ManejadorSAX() {
		xmlResult = "";
	}

	public String getXMLResult() {
		return xmlResult;
	}

	public void setXMLResult(String xmlResult) {
		this.xmlResult = xmlResult;
	}

	// SOBRECARGA DE EVENTOS DE LA CLASE HANDLER
	/**
	 * INICIO DEL DOCUMENTO XML
	 */
	@Override
	public void startDocument() throws SAXException {
		xmlResult += "COMIENZO DEL DOCUMENTO XML\n";
	}

	/**
	 * FIN DEL DOCUMENTO XML
	 */
	@Override
	public void endDocument() throws SAXException {
		xmlResult += "FIN DEL DOCUMENTO XML\n";
	}

	/**
	 * COMIENZO DEL ELEMENTO
	 */
	@Override
	public void startElement(String uri, String nombre, String elemento,
			Attributes atts) throws SAXException {
		xmlResult += "INICIO NODO: " + elemento + "\n";
	}

	/**
	 * FIN DEL ELEMENTO
	 */
	@Override
	public void endElement(String uri, String nombre, String elemento)
			throws SAXException {
		xmlResult += "FIN NODO: " + elemento + "\n";
	}

	/**
	 * CADENA DE CARACTERES (VALOR DEL CONTENIDO)
	 *
	 * @param cadena
	 *            Cadena de caracteres extraída del elemento como valor
	 *            específico
	 * @param posinicio
	 *            Posición del caracter dentro del documento XML
	 * @param longitud
	 *            Número de carácteres
	 *
	 * @throws SAXException
	 *             Cualquier excepción producida en la lectura del valor
	 */
	@Override
	public void characters(char[] cadena, int posinicio, int longitud)
			throws SAXException {
		// OPCIÓN 1
		xmlResult += "VALOR: " + new String(cadena, posinicio, longitud) + "\n";

		// OPCIÓN 2
		// for (int i = start; i < length + start; i++)
		// result += Character.toString(cadena[i]);
	}

	/**
	 * CADENA DE CARACTERES EN BLANCO (solo cuando la DTD esté incluida como
	 * recurso o dentro del mismo documento)
	 *
	 * @param cadena
	 *            Cadena de caracteres extraída del elemento como valor
	 *            específico
	 * @param posinicio
	 *            Posición del caracter dentro del documento XML
	 * @param longitud
	 *            Número de carácteres
	 *
	 * @throws SAXException
	 *             Cualquier excepción producida en la lectura del valor
	 */
	public void ignorableWhitespace(char[] cadena, int posinicio, int longitud)
			throws SAXException {
		// System.out.println("Es un blanco");
	}
}