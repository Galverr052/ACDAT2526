package ej6;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import java.io.*;

public class CatalogoVideojuegos extends DefaultHandler {

    private static final String RUTA_SALIDA = "res/ej6/catalogo.html";

    private StringBuilder contenido = new StringBuilder();
    private FileWriter fout;

    private String titulo;
    private String plataforma;
    private String caratula;
    private int stock;

    public static void main(String[] args) {
        CatalogoVideojuegos app = new CatalogoVideojuegos();
        app.procesarXML("res/ej6/catalogo.xml");
    }

    public void procesarXML(String rutaXML) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(rutaXML), this);
            System.out.println("Archivo HTML generado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------- INICIO DOCUMENTO -------------------------

    @Override
    public void startDocument() throws SAXException {
        try {
            fout = new FileWriter(RUTA_SALIDA);

            fout.write("""
                    <!DOCTYPE html>
                    <html lang="es">
                    <head>
                    <meta charset="UTF-8">
                    <title>CATÁLOGO DE VIDEOJUEGOS CLÁSICOS</title>
                    <style>
                        body {
                            background-color: #e6e6e6;
                            font-family: Arial, sans-serif;
                            text-align: center;
                        }
                        h1 {
                            background-color: #333;
                            color: white;
                            padding: 15px 0;
                            margin: 0;
                            font-size: 28px;
                        }
                                    
                        table {
                                 margin: 20px auto;
                                 border-collapse: collapse;   /* sin separación */
                                 border: 3px solid #000;      /* <<<<< borde global de la tabla */
                             }
                             
                             td {
                                 width: 200px;
                                 padding: 10px;
                                 background-color: white;
                                 vertical-align: top;
                                 border: 1px solid #000;      /* <<<<< borde de cada celda */
                             }
                                    
                        img {
                            width: 160px;
                            height: 200px;
                            border: 1px solid black;
                        }
                                    
                        .titulo {
                            font-weight: bold;
                            margin-top: 10px;
                        }
                    </style>
                    </head>
                    <body>
                    <h1>CATÁLOGO DE VIDEOJUEGOS CLÁSICOS</h1>
                    <table>
                    <tr>
                    """);

        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    // ----------------------- FIN DOCUMENTO -----------------------------

    @Override
    public void endDocument() throws SAXException {
        try {
            fout.write("""
                </tr>
                </table>
                </body>
                </html>
                """);
            fout.close();
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    // ----------------------- ELEMENTOS XML -----------------------------

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        contenido.setLength(0);

        if (qName.equalsIgnoreCase("videojuego")) {
            // Reiniciar atributos
            titulo = "";
            plataforma = "";
            caratula = "";
            stock = 0;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String texto = contenido.toString().trim();

        switch (qName.toLowerCase()) {
            case "titulo" -> titulo = texto;
            case "plataforma" -> plataforma = texto;
            case "caratula" -> caratula = texto;
            case "stock" -> {
                if (!texto.isEmpty())
                    stock = Integer.parseInt(texto);
            }
            case "videojuego" -> escribirJuego();
        }
    }

    // ----------------------- ESCRITURA HTML ----------------------------

    private void escribirJuego() throws SAXException {
        try {
            fout.write("<td>");
            fout.write("<img src='" + caratula + "' alt='" + titulo + "'/><br>");
            fout.write("<div class='titulo'>" + titulo + "</div>");
            fout.write("<div>Consola: " + plataforma + "</div>");
            fout.write("<div>Stock: " + stock + "</div>");
            fout.write("</td>");
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }
}
