package ej7;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorXPath {

    /**
     * Evalúa una expresión XPath sobre un documento XML y devuelve el resultado.
     * Si la consulta devuelve nodos, devuelve un ArrayList<Element>.
     * Si la consulta devuelve un valor escalar (count, string, number), devuelve ese valor (String, Double, etc).
     *
     * @param f archivo XML
     * @param txtexpresion expresión XPath
     * @return ArrayList<Element> o valor escalar (String, Double, etc)
     * @throws DocumentException si el archivo no puede ser parseado
     * @throws JaxenException si la expresión XPath es incorrecta
     */
    public static Object consultaJaxen(File f, String txtexpresion) throws DocumentException, JaxenException {
        SAXReader reader = new SAXReader();
        reader.setValidation(false);
        Document document = reader.read(f);

        XPath xpath = new Dom4jXPath(txtexpresion);
        Object result = xpath.evaluate(document);

        if (result instanceof List) {
            List<?> lista = (List<?>) result;
            if (lista.isEmpty() || lista.get(0) instanceof Element) {
                ArrayList<Element> elementos = new ArrayList<>();
                for (Object obj : lista) {
                    elementos.add((Element) obj);
                }
                return elementos;
            } else {
                return lista;
            }
        }
        return result;
    }
}
