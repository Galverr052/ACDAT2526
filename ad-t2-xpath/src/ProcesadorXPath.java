
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

import org.xml.sax.InputSource;

public class ProcesadorXPath {
	/**
	 * Devuelve el resultado de una expresi�n XPath sobre un documento XML.
	 * 
	 * @param f
	 *            Documento XML de entrada
	 * @param txtexpresion
	 *            Expresi�n XPath
	 * @return Un objeto de tipo Node, Nodeset, String, Number o Boolean
	 * @throws XPathExpressionException
	 *             Si se produce un error en la expresi�n
	 * @throws FileNotFoundException
	 *             Si no se encuentra el fichero de entrada
	 */
	public static Object consulta(File f, String txtexpresion)
			throws XPathExpressionException, FileNotFoundException {
		InputSource entrada = new InputSource(new FileInputStream(f));

		// 1. Factor�a de creaci�n de objetos XPath
		XPathFactory factory = XPathFactory.newInstance();

		// 2. Usa la XPathFactory para crear un nuevo objeto XPath
		javax.xml.xpath.XPath xpath = factory.newXPath();

		// 3. Compila la expresi�n XPath para su correcci�n
		XPathExpression expresionXPath = xpath.compile(txtexpresion);

		// 4. Evalua la expresi�n XPath en el documento XML de entrada
		// 5. Devuelve el resultado de tipo XPathConstants
		return expresionXPath.evaluate(entrada);
	}

	/**
	 * Devuelve el resultado de una expresi�n XPath sobre un documento XML que
	 * se va a validar usando el parser DOM4J y la librer�a XPath Jaxen
	 * 
	 * @param f
	 *            Documento XML de entrada
	 * @param txtexpresion
	 *            Expresi�n XPath
	 * @return Un objeto de tipo ArrayList o SingletonList
	 * @throws DocumentException
	 *             Si se produce un error en el parseo
	 * @throws JaxenException
	 *             Si se produce un error en la expresi�n
	 */

	public static Object consultaJaxen(File f, String txtexpresion)
			throws DocumentException, JaxenException {
		// 1. Definici�n de un parser con DOM4J y con VALIDACI�N
		SAXReader reader = new SAXReader();
		reader.setValidation(true);

		// 2. Parseo y generaci�n de un �rbol con DOM4J
		Document document = reader.read(f);

		// 3. Compila la expresi�n XPath para su correcci�n
		XPath path = new Dom4jXPath(txtexpresion);

		// 4. Evalua la expresi�n XPath en el documento XML de entrada
		// 5. Devuelve el resultado de tipo XPathConstants
		return path.selectNodes(document);
	}
}
