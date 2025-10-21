package ej2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionarSAX extends DefaultHandler {

    private String etiquetaActual = "";
    private String nombre = "";
    private String x = "", y = "", z = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        etiquetaActual = qName;
        // Reiniciamos variables al comenzar un nuevo <lugar>
        if (qName.equalsIgnoreCase("lugar")) {
            nombre = "";
            x = "";
            y = "";
            z = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String texto = new String(ch, start, length).trim();
        if (texto.length() == 0) return;

        switch (etiquetaActual) {
            case "nombre":
                nombre += texto;
                break;
            case "x":
                x += texto;
                break;
            case "y":
                y += texto;
                break;
            case "z":
                z += texto;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("lugar")) {
            System.out.println("NOMBRE: " + nombre);
            if (!x.isEmpty()) System.out.println("  |_X: " + x);
            if (!y.isEmpty()) System.out.println("  |_Y: " + y);
            if (!z.isEmpty()) System.out.println("  |_Z: " + z);
            System.out.println();
        }
        etiquetaActual = "";
    }
}
