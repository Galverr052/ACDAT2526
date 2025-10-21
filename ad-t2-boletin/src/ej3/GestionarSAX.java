package ej3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

public class GestionarSAX extends DefaultHandler {
    private String xml;
    private int numEmpleados=0;

    public String getXMLResult(){
        return xml;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Empleado"))
            System.out.println(xml += "\nEmpleados :" + (++numEmpleados));
        try {
            if (qName.equals("nombre")){
                System.out.printf("\nNombre :");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        xml += new String(ch, start, length);
    }
}
