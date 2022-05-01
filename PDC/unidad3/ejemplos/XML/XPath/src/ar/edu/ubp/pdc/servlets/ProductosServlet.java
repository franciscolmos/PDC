package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import ar.edu.ubp.pdc.beans.ProductoBean;

/**
 * Servlet implementation class ProductosServlet
 */
@WebServlet("/productos.jsp")
public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
		try {
			SchemaFactory          schema   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(this.getServletContext().getResourceAsStream("/WEB-INF/xml/stock.xsd"))}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(this.getServletContext().getResourceAsStream("/WEB-INF/xml/stock.xml"));
			
			LinkedList<ProductoBean> beans = new LinkedList<ProductoBean>();
			ProductoBean bean = null;
			
			NodeList productos = NodeList.class.cast(xPath.compile("/productos/producto/cant[text()=0]").evaluate(document, XPathConstants.NODESET));
			for (int i = 0, len = productos.getLength(); i < len; i++) {
				bean = new ProductoBean();
				bean.setNomProducto(xPath.compile("../nom").evaluate(productos.item(i), XPathConstants.STRING).toString());
				beans.add(bean);
			}	

			request.setAttribute("listado", beans);
			this.gotoPage("/listado_productos.jsp", request, response);
		}
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
        	request.setAttribute("error", ex.getMessage());
        	this.gotoPage("/error.jsp", request, response);
		}	
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
	}

}
