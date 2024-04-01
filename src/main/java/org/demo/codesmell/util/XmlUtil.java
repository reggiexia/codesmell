package org.demo.codesmell.util;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class XmlUtil {

    public static Document readXML(InputSource source) throws ParserConfigurationException, IOException, SAXException {
        final DocumentBuilder builder = documentBuilder();
        return builder.parse(source);
    }

    public static DocumentBuilder documentBuilder() throws ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder();
    }
}
