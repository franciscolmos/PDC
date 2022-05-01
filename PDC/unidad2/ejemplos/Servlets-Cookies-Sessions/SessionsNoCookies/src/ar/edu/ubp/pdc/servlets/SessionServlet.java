package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/index.jsp")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
        	HttpSession session = request.getSession(true);   
        	
            out.println("<!DOCTYPE html>");
        	out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\">");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Sesi&oacute;n</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"/>");
            out.println("<script type=\"text/javascript\" src=\"./js/jquery.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"./js/sessions.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Atributos de Sesi&oacute;n</h3>");
            out.println("<form id=\"form\" action=\"" + response.encodeURL(request.getContextPath() + request.getServletPath()) + "\" method=\"post\">");
            out.println("<input type=\"hidden\" id=\"delAttrName\" name=\"delAttrName\" value=\"\"/></td>");
            out.println("<table>");
            out.println("<colgroup>");
            out.println("<col width=\"220px\"/>");
            out.println("<col width=\"220px\"/>");
            out.println("<col width=\"100px\"/>");
            out.println("</colgroup>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Nombre</td><td>Valor</td><td>&nbsp;</td>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            String attrName;
            Enumeration<String> attrNames = session.getAttributeNames();            
            while (attrNames.hasMoreElements()) {
            	attrName = attrNames.nextElement();
            	
                out.println("<tr>");
                out.println("<td>");
                out.println(attrName);
                out.println("<input type=\"hidden\" name=\"hAttrName\" value=\"" + attrName + "\">"); 
                out.println("</td>");
                out.println("<td>" + session.getAttribute(attrName) + "</td>");
                out.println("<td><a href=\"#\">Eliminar</a></td>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td><input type=\"text\" name=\"attrName\"  value=\"\" maxlength=\"255\" size=\"30\" required/></td>");
            out.println("<td><input type=\"text\" name=\"attrValue\" value=\"\" maxlength=\"255\" size=\"30\" required/></td>");
            out.println("<td>&nbsp;</td>");
            out.println("</tr>");
            out.println("</tbody>");
            out.println("</table>");
            out.println("<br/>");
            out.println("<button>Agregar</button>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
		}
        finally {
            out.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);   
		if(request.getParameter("delAttrName") != null && request.getParameter("delAttrName") != "") {
			session.removeAttribute(request.getParameter("delAttrName"));
		}
		else {
			session.setAttribute(request.getParameter("attrName"), request.getParameter("attrValue"));
		}
		response.sendRedirect(response.encodeURL(request.getContextPath() + request.getServletPath()));
	}

}
