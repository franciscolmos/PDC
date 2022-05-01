package tags;

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

public class AreasTagHandler extends SimpleTagSupport {
	private String nombre;
	private String tipo;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void doTag() throws JspException,IOException{
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		try {
			ServletContext servletContext= ((PageContext)this.getJspContext()).getServletContext();
			SchemaFactory schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);
			factory.setSchema(schema.newSchema(new Source[] {new StreamSource(servletContext.getResourceAsStream("/WEB-INF/xml/areas.xsd"))}));
			DocumentBuilder builder = factory.newDocumentBuilder();
			XPath xPath = XPathFactory.newInstance().newXPath();
			Document document = builder.parse(servletContext.getResourceAsStream("/WEB-INF/xml/areas.xml"));
			
			NodeList areas = NodeList.class.cast(xPath.compile("/areas/area").evaluate(document, XPathConstants.NODESET));
			
			String codigo;
			String nombre;
			if(this.tipo == null || !this.tipo.equals("lista")) {
				out.println("<div id=\"iareas\">");
				for (int i = 0; i < areas.getLength(); i++) {
					codigo = xPath.compile("codigo").evaluate(areas.item(i), XPathConstants.STRING).toString();
					nombre = xPath.compile("nombre").evaluate(areas.item(i), XPathConstants.STRING).toString();
					out.println("<label>"+nombre+"<input type=\"radio\" name="+this.nombre+" id="+codigo+" value="+codigo+"></label>");
				}
				out.println("</div>");
			}
			else {
				out.println("<ul id=\"iareas\">");
				for (int i = 0; i < areas.getLength(); i++) {
					nombre = xPath.compile("nombre").evaluate(areas.item(i), XPathConstants.STRING).toString();
					out.println("<li> "+nombre+" </li>");
				}
				out.println("</ul>");
			}
			
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
			out.println(ex.getMessage());
		}
	}
}
