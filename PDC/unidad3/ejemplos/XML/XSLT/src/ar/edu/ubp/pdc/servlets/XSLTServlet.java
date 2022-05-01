package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Servlet implementation class XSLTServlet
 */
@WebServlet("/index.jsp")
public class XSLTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XSLTServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
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
	        String path    = this.getServletContext().getRealPath("/WEB-INF/xml/") + System.getProperty("file.separator");
	        String xsdFile = path + "stock.xsd";
	        String xmlFile = path + "stock.xml";
	        String xslFile = path + "stock.xsl";
	
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
	                               
	        Document doc = builder.parse(new InputSource(xmlFile));      
	        
	        StreamResult result = new StreamResult(out);
	        
	        TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer(new StreamSource(xslFile));
                        transformer.setParameter("titulo", "Productos Faltantes");
 		                transformer.transform(new DOMSource(doc), result);
        }
		catch (TransformerException | SAXException | ParserConfigurationException ex) {
            this.printMessage(out, ex.getMessage());
        } 
        finally {            
            out.close();
        }
	}

    private void printMessage(PrintWriter out, String message) throws IOException {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"./css/style.css\" />");
        out.println("<title>Ejemplo XSLT</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"error\">" + message + "</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
