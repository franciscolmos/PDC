package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScriptsServlet
 */
@WebServlet("/ejemplo3.jsp")
public class ScriptsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScriptsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
        	out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\">");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Servlet - Ejemplo 3</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./StyleSheet.do?load=noexiste1,style\"/>");
            out.println("<script type=\"text/javascript\" src=\"./Javascript.do?load=noexiste1,jquery,ejemplo3.1,noexiste2,ejemplo3.2\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Load Scripts</h3>");
            out.println("<div id=\"content\"></div>");
            out.println("<br/>");
            out.println("<a href=\"./index.jsp\">Volver al index principal</a>");
		} 
        finally {            
            out.close();
        }
	}

}
