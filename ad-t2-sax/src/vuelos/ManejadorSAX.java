package vuelos;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManejadorSAX extends DefaultHandler {
    private String xml;
    private int numVuelos=0;

    private File f;
    private FileWriter fout;

    public String getXMLResult(){
        return xml;
    }

    @Override
    public void startDocument() throws SAXException {
        //xml = "INICIO DOCUMENTO XML VUELOS";
        f = new File("res" + File.separator + "vuelos.html");
        try {
            fout = new FileWriter(f);

            // Cabecera HTML
            fout.write("<!DOCTYPE HTML> <html><head><meta charset=\"utf-8\"></head><body>");
            fout.write("<table border=\"1\"><tr><td>ORIGEN</td><td>DESTINO</td></tr>");

        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void endDocument() throws SAXException {
        //xml += "FIN DOCUMENTO XML VUELOS";
        try {
            fout.write("</table></body></html>");
            fout.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /*if (qName.equals("Vuelo"))
            xml += "\nVUELO " + (++numVuelos);
        else if (qName.equals("origen"))
            xml += "\n\tORIGEN: ";
        else if (qName.equals("destino"))
            xml += "\n\tDESTINO: ";
        */
        /*try {
            if (qName.equals("Vuelo")) {
                fout.write("<br>VUELO " + (++numVuelos));
                xml += "\nVUELO " + (++numVuelos);
            } else if (qName.equals("origen")) {
                fout.write("<br>ORIGEN: ");
                xml += "\n\tORIGEN: ";
            } else if (qName.equals("destino")) {
                fout.write("<br>DESTINO: ");
                xml += "\n\tDESTINO: ";
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

         */
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        xml += new String(ch, start, length);
    }
}
