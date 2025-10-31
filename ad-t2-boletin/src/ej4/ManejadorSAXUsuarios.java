package ej4;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorSAXUsuarios extends DefaultHandler {
    private StringBuilder contenido = new StringBuilder();
    private String username;
    private String password;
    private StringBuilder resultado = new StringBuilder();

    public String getXMLResult(){
        return resultado.toString();
    }

    @Override
    public void startElement(String uri, String locaName, String qName, Attributes attributes){
        contenido.setLength(0);
    }
    @Override
    public void characters(char[] ch, int start, int length){
        contenido.append(ch, start, length);
    }
    @Override
    public  void endElement(String uri, String localName, String qName){
        switch (qName){
            case "username":
                username = contenido.toString().trim();
                break;
            case "password":
                password = contenido.toString().trim();
                break;
            case "user":
                resultado.append(username).append(":").append(password).append("\n");
                break;
        }
    }
}
