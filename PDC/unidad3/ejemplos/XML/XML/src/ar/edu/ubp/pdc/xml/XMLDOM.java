package ar.edu.ubp.pdc.xml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLDOM implements IXML {

    private PrintWriter out;
    private Document doc;
    
    public XMLDOM(PrintWriter out) {
        this.out = out;
    }

    public void load(String xmlFile, String xsdFile) throws ParserConfigurationException, SAXException, IOException {
        /*
         *  Si queremos validar nuestro XML utilizando un SCHEMA Externo especificamos:
         *  factory.setValidating(false);
         *  SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
         *  factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource(xsdFile)}));
         * 
         *  Si queremos validar nuestro XML utilizando un SCHEMA Interno especificamos:
         *  factory.setValidating(true);
         *  factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
         *  NOTA: En nuestro caso no sirve validarlo utilizando este SCHEMA ya que el XML tiene una estructura específica
         */
                
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                               factory.setValidating(false);
                               factory.setNamespaceAware(true);
                               factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource(xsdFile)}));

        DocumentBuilder builder = factory.newDocumentBuilder();        
				        builder.setErrorHandler(
				            new ErrorHandler() {
				                @Override
				                public void warning(SAXParseException e) throws SAXException {
				                    throw e;
				                }
				
				                @Override
				                public void error(SAXParseException e) throws SAXException {
				                    throw e;
				                }
				
				                @Override
				                public void fatalError(SAXParseException e) throws SAXException {
				                    throw e;
				                }
				            });        

        this.out.println("<!DOCTYPE html>");
        this.out.println("<html>");
        this.out.println("<head>");
        this.out.println("<title>Ejemplos con XML - DOM</title>");            
        this.out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/style.css\" />");
        this.out.println("</head>");
        this.out.println("<body>");
        this.out.println("<table width=\"40%\">");
        this.out.println("<tr>");
        this.out.println("<th>XML</th>");
        this.out.println("</tr>");
        this.doc = builder.parse(new InputSource(xmlFile));      
        this.getNode(this.doc.getDocumentElement());
        this.out.println("</table>");
        this.out.println("</br>");
        this.out.println("<a href=\"index.html\" target=\"_self\">Volver al index principal</a>");
        this.out.println("</body>");
        this.out.println("</html>");        
    }
    
    private void getNode(Node node) {
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                this.out.println("<tr>");
                this.out.println("<td>"); 
                this.out.println("<b>" + node.getNodeName() + "</b>");

                if(node.hasAttributes()) {
                    this.out.println("<p><i>Atributos:</i></p>");
                    this.out.println("<ul>");
                    NamedNodeMap atts = node.getAttributes();
                    for (int i=0; i<atts.getLength(); i++) {
                        this.getNode(atts.item(i));
                    }
                    this.out.println("</ul>"); 
                }                    

                if (node.hasChildNodes()) {
                    NodeList children = node.getChildNodes();                
                    for (int i=0; i < children.getLength(); i++) {
                        this.getNode(children.item(i));
                    }
                }
                
                this.out.println("</td>");
                this.out.println("</tr>");                
                break;

            case Node.ATTRIBUTE_NODE:
                this.out.print("<li><u>" + node.getNodeName() + "</u>: " + node.getNodeValue() + "</li>");
                break;

            case Node.TEXT_NODE:
                this.out.println(node.getNodeValue());
                break;
        }
        
    }
	
}
