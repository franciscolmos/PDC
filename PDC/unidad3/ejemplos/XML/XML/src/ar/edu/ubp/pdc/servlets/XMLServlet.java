package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ar.edu.ubp.pdc.xml.IXML;
import ar.edu.ubp.pdc.xml.XMLDOM;
import ar.edu.ubp.pdc.xml.XMLSAX;

/**
 * Servlet implementation class XMLServlet
 */
@WebServlet("/loadXML.jsp")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            String path = this.getServletContext().getRealPath("/WEB-INF/xml/") + System.getProperty("file.separator");
            IXML   xml  = null;
            if(request.getParameter("lib") == null || request.getParameter("lib").equals("DOM")) {
                xml = new XMLDOM(out);
            }
            else {
                xml = new XMLSAX(out);
            }
            xml.load(path + "alumnos.xml", path + "alumnos.xsd");
        }
        catch (ParserConfigurationException ex) {
            this.printMessage(out, ex.getMessage());
        }
        catch (SAXException ex) {
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
        out.println("<title>Ejemplos con XML</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"error\">" + message + "</div>");
        out.println("<a href=\"index.jsp\" target=\"_self\">Volver al index principal</a>");
        out.println("</body>");
        out.println("</html>");
    }

}
