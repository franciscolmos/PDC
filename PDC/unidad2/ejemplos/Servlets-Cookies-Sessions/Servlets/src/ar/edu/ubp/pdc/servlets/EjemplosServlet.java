package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EjemplosServlet
 */
@WebServlet("/index.jsp")
public class EjemplosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EjemplosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
        	out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\">");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Servlet</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Ejemplos de Servlets</h2>");
            out.println("<ul>");
            out.println("<li><a href=\"./ejemplo1.jsp\" target=\"_self\">Ejemplo 1</a></li>");
            out.println("<li><a href=\"./ejemplo2.jsp\" target=\"_self\">Ejemplo 2</a></li>");
            out.println("<li><a href=\"./ejemplo3.jsp\" target=\"_self\">Ejemplo 3</a></li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        } 
        finally {            
            out.close();
        }
	}

}
