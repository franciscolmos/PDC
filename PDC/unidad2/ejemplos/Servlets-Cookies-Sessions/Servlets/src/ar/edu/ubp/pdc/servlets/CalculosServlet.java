package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculosServlet
 */
@WebServlet("/ejemplo2.jsp")
public class CalculosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculosServlet() {
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
            out.println("<title>Servlet - Ejemplo 2</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"/>");
            out.println("<script type=\"text/javascript\" src=\"./js/jquery.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>C&aacute;lculos</h3>");
            out.println("<form id=\"form\" name=\"form\" action=\"ejemplo2.jsp\" method=\"post\">");
            out.println("<p>Variable 1: <input type=\"number\" name=\"variable1\" value=\"\" size=\"10\" maxlength=\"10\" required></p>");
            out.println("<p>Variable 2: <input type=\"number\" name=\"variable2\" value=\"\" size=\"10\" maxlength=\"10\" required></p>");
            out.println("<input type=\"submit\" value=\"Calcular\">");
            out.println("</form>");
            out.println("<br/>");
            out.println("<a href=\"./index.jsp\">Volver al index principal</a>");
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
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> operations = new LinkedHashMap<String, String>();
                                operations.put("Suma", "+");
                                operations.put("Resta", "-");
                                operations.put("Multiplicaci&oacute;n", "*");
                                operations.put("Divisi&oacute;n", "/");
                                operations.put("Resto", "%");

            ScriptEngineManager scm      = new ScriptEngineManager();
            ScriptEngine        jsEngine = scm.getEngineByName("JavaScript");                        
                            
            Double variable1 = Double.parseDouble(request.getParameter("variable1")); 
            Double variable2 = Double.parseDouble(request.getParameter("variable2")); 

            StringBuilder colgroup = new StringBuilder(), thead = new StringBuilder(), tbody = new StringBuilder();

            out.println("<!DOCTYPE html>");
        	out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\">");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Servlet - Ejemplo 2</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"/>");
            out.println("<script type=\"text/javascript\" src=\"./js/jquery.js\"></script>");
            out.println("</head>");
            out.println("<body>");

			for(int i = 0, s = operations.size(); i < s; i ++) {
				colgroup.append("<col width=\"20%\"/>");
			}

			for(String key : operations.keySet()) {
				thead.append("<th align=\"right\">").append(key).append("</th>");              		
				tbody.append("<td align=\"right\">");
				if(operations.get(key).matches("/|%") && variable2 == 0) {
					tbody.append("-");
				}
				else {
					tbody.append(jsEngine.eval(variable1 + operations.get(key) + variable2));
				}
				tbody.append("</td>");
			}

			out.println("<h3>C&aacute;lculos entre los n&uacute;meros &quot;" + variable1 + "&quot; y &quot;" + variable2 + "&quot;</h3>");
			out.println("<table>");
			out.println("<colgroup>" + colgroup + "</colgroup>");
			out.println("<thead>" + thead + "</thead>");
			out.println("<tbody>" + tbody + "</tbody>");

			if(variable2 == 0) {
				out.println("<tfoot>");
				out.println("<tr>");
				out.println("<th colspan=\"" + operations.size() + "\" align=\"left\">Nota: No se puede dividir por cero!</th>");
				out.println("</tr>");
				out.println("</tfoot>");              		
			}

			out.println("</table>");
			out.println("<br/>");
			out.println("<a href=\"./ejemplo2.jsp\">Volver</a>");
            out.println("</body>");
            out.println("</html>");
		} 
		catch (ScriptException e) {
            out.println("<!DOCTYPE html>");
        	out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\">");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Servlet - Ejemplo 2</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"/>");
            out.println("</head>");
            out.println("<body>");
			out.print("<p>Error: " + e.getMessage() + "</p>");
            out.println("</body>");
            out.println("</html>");
		} 
		finally {            
			out.close();
		}
	}
	
}
