package ej6;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import java.io.*;

public class CatalogoVideojuegos extends DefaultHandler {

    private StringBuilder contenido = new StringBuilder();

    private FileWriter fout;
    private String titulo, plataforma, caratula;
    private int stock;
    private int contador = 0;
    private boolean card = false;

    public static void main(String[] args) {
        CatalogoVideojuegos app = new CatalogoVideojuegos();
        String ruta = "res/ej6" + File.separator + "catalogo.xml";
        app.procesarXML(ruta);
    }

    public void procesarXML(String rutaXML) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(rutaXML), this);
            System.out.println("Archivo se genero correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startDocument() throws SAXException {
        try {
            File f = new File("res/ej6" + File.separator + "catalogo.html");
            fout = new FileWriter(f);

            fout.write("""
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <title>CATÁLOGO DE VIDEOJUEGOS CLÁSICOS</title>
                    <style>
                        body {text-align: center; }
                        h1 {text-align: center; display: inline-block; padding-bottom: 5px; }
                        .juego {border: 2px solid black; display: inline-block; width: 200px; margin: 15px; vertical-align: top; }
                        img { width: 150px; height: 200px; border: 1px solid black;}
                        .titulo { font-weight: bold; margin-top: 8px; }
                        .plataforma, .stock { font-size: 14px; margin: 2px 0; }
                    </style>
                </head>
                <body>
                <h1>CATÁLOGO DE VIDEOJUEGOS CLÁSICOS</h1>
                <div id='catalogo'>
                """);

        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            fout.write("</div>");
            fout.write("</body></html>");
            fout.close();
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        contenido.setLength(0);
        if (qName.equalsIgnoreCase("videojuego")) {
            card = true;
            titulo = plataforma = caratula = "";
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

        if (!card) return;

        switch (qName.toLowerCase()) {
            case "titulo" -> titulo = texto;
            case "plataforma" -> plataforma = texto;
            case "caratula" -> caratula = texto;
            case "stock" -> stock = Integer.parseInt(texto);
            case "videojuego" -> escribirJuego();
        }
    }

    private void escribirJuego() throws SAXException {
        try {
            fout.write("<div class='juego'>");
            fout.write("<img src='" + caratula + "' alt='" + titulo + "' />");
            fout.write("<div class='titulo'>" + titulo + "</div>");
            fout.write("<div class='plataforma'>Consola: " + plataforma + "</div>");
            fout.write("<div class='stock'>Stock actual: " + stock + "</div>");
            fout.write("</div>");
            contador++;
        } catch (IOException e) {
            throw new SAXException(e);
        }
    }
}
