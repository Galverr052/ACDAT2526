package ej2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAXHTML extends DefaultHandler {
    private String xmlResult;
    private int ntablas;       // Número de tablas encontradas
    private boolean esTabla;   // Indica si estamos dentro de una <table>

    public ManejadorSAXHTML() {
        xmlResult = "";
        ntablas = 0;
        esTabla = false;
    }

    public String getXMLResult() {
        return xmlResult;
    }

    public void setXMLResult(String xmlResult) {
        this.xmlResult = xmlResult;
    }

    // --- Eventos SAX ---

    @Override
    public void startElement(String uri, String nombre, String elemento, Attributes atts)
            throws SAXException {
        if (elemento.equalsIgnoreCase("table")) {
            esTabla = true;
            ntablas++;
        }
    }

    @Override
    public void endElement(String uri, String nombre, String elemento)
            throws SAXException {
        if (elemento.equalsIgnoreCase("table")) {
            esTabla = false;
            xmlResult += "\n"; // salto entre tablas
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (esTabla) {
            String texto = new String(ch, start, length).trim();
            if (!texto.isEmpty()) {
                xmlResult += texto + " ";
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        xmlResult += "Número de tablas: " + ntablas;
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        // No hace nada con los espacios en blanco
    }
}
