package ar.edu.ubp.pdc.xml;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XMLSAX extends DefaultHandler implements IXML {

    private PrintWriter out;
 
    public XMLSAX(PrintWriter out) {
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

        SAXParserFactory factory = SAXParserFactory.newInstance();        
                         factory.setValidating(false); 
                         factory.setNamespaceAware(true);
                         factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource(xsdFile)}));
                
        SAXParser parser = factory.newSAXParser();        
        XMLReader xr = parser.getXMLReader(); 
		          xr.setErrorHandler(
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
                  xr.setContentHandler(this);
                  xr.parse(new InputSource(new FileReader(xmlFile)));    
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.out.println("<!DOCTYPE html>");
        this.out.println("<html>");
        this.out.println("<head>");
        this.out.println("<title>Ejemplos con XML - SAX</title>");            
        this.out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/style.css\" />");
        this.out.println("</head>");
        this.out.println("<body>");
        this.out.println("<table width=\"40%\">");
        this.out.println("<tr>");
        this.out.println("<th>XML</th>");
        this.out.println("</tr>");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        this.out.println("</table>");
        this.out.println("</br>");
        this.out.println("<a href=\"index.html\" target=\"_self\">Volver al index principal</a>");
        this.out.println("</body>");
        this.out.println("</html>");        
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.out.println("<tr>");
        this.out.println("<td>"); 
        this.out.println("<b>" + localName + "</b>");
        
        if(attributes.getLength() > 0) {
        	this.out.println("<p><i>Atributos:</i></p>");
        	this.out.println("<ul>");
            for(int i = 0; i < attributes.getLength(); i++) {
            	this.out.println("<li><u>" + attributes.getLocalName(i) + "</u>: ");
            	this.out.println(attributes.getValue(i));
            	this.out.println("</li>");
            }
            this.out.println("</ul>");
        }
    }    
    
    @Override
    public void characters(char ch[], int start, int len) throws SAXException{
        super.characters(ch, start, len);
        this.out.println(new String(ch, start, len));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        this.out.println("</td>");
        this.out.println("</tr>");        
    }
	
}
