package ar.edu.ubp.pdc.tags;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LugaresTagHandler extends SimpleTagSupport {

	private String codOrigen;
	private String codDestino;
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		
		try {
			ServletContext servletContext = ((PageContext)this.getJspContext()).getServletContext();
			
			SchemaFactory          schema   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(servletContext.getResourceAsStream("/WEB-INF/xml/lugares.xsd"))}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(servletContext.getResourceAsStream("/WEB-INF/xml/lugares.xml"));
		
			String codOrigen = this.codOrigen == null ? "" : this.codOrigen.toLowerCase();
			String codDestino = this.codDestino == null ? "" : this.codDestino.toLowerCase();
			String codigo;
			
			NodeList origenes = NodeList.class.cast(xPath.compile("/lugares/origenes/origen").evaluate(document, XPathConstants.NODESET));
			out.println("<div class=\"fl\"><b>Origen:</b>");
			out.println("<select name=\"origen\">");
			for (int i = 0, len = origenes.getLength(); i < len; i++) {
			    codigo = xPath.compile("codigo").evaluate(origenes.item(i), XPathConstants.STRING).toString().toLowerCase();
				out.println("<option " + (codOrigen.equals(codigo) ? "selected" : "") + " value=\"" + codigo + "\">" + xPath.compile("nombre").evaluate(origenes.item(i), XPathConstants.STRING) + "</option>");		
			}			
			out.println("</select>");
			out.println("</div>");

			NodeList destinos = NodeList.class.cast(xPath.compile("/lugares/destinos/destino").evaluate(document, XPathConstants.NODESET));
			out.println("<div class=\"fl\"><b>Destino:</b>");
			out.println("<select name=\"destino\">");
			for (int i = 0, len = destinos.getLength(); i < len; i++) {
				codigo = xPath.compile("codigo").evaluate(destinos.item(i), XPathConstants.STRING).toString().toLowerCase();
			    out.println("<option " + (codDestino.equals(codigo) ? "selected" : "") + " value=\"" + codigo + "\">" + xPath.compile("nombre").evaluate(destinos.item(i), XPathConstants.STRING) + "</option>");		
			}			
			out.println("</select>");				
		}
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
        	out.println(ex.getMessage());
		}	
	}

	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}

	public void setCodDestino(String codDestino) {
		this.codDestino = codDestino;
	}
	
}
