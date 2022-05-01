package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookiesServlet
 */
@WebServlet("/index.jsp")
public class CookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookiesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
        	Cookie cookies[] = request.getCookies();

            out.println("<!DOCTYPE html>");
        	out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html\">");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Cookies</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/style.css\"/>");
            out.println("<script type=\"text/javascript\" src=\"./js/jquery.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"./js/cookies.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Cookies</h3>");
            out.println("<form id=\"form\" action=\"index.jsp\" method=\"post\">");
            out.println("<input type=\"hidden\" id=\"delCookieName\" name=\"delCookieName\" value=\"\"/></td>");
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
            if(cookies != null) {
	            for(Cookie c : cookies) {
	                out.println("<tr>");
	                out.println("<td>");
	                out.println(c.getName());
	                out.println("<input type=\"hidden\" name=\"hCookieName\" value=\"" + c.getName() + "\">"); 
	                out.println("</td>");
	                out.println("<td>" + c.getValue() + "</td>");
	                out.println("<td><a href=\"#\">Eliminar</a></td>");
	                out.println("</tr>");
	            }
            }
            out.println("<tr>");
            out.println("<td><input type=\"text\" name=\"cookieName\"  value=\"\" maxlength=\"255\" size=\"30\" required/></td>");
            out.println("<td><input type=\"text\" name=\"cookieValue\" value=\"\" maxlength=\"255\" size=\"30\" required/></td>");
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
		Cookie cookie = null;
		if(request.getParameter("delCookieName") != null && request.getParameter("delCookieName") != "") {
			cookie = new Cookie(request.getParameter("delCookieName"), "");
			cookie.setMaxAge(0);
		}
		else {
			cookie = new Cookie(request.getParameter("cookieName"), request.getParameter("cookieValue"));
			//cookie.setMaxAge(24 * 60 * 60);  // 24 horas
		}
		response.addCookie(cookie);
	    response.sendRedirect("./index.jsp");
	}

}
