package jdbc.ticket.ej1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegTicket")
public class RegTicketserverlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public RegTicketserverlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        String name = request.getCookies().length > 0 ? request.getCookies()[0].getName() : "" ;
        String email =request.getCookies().length > 0 ? request.getCookies()[0].getValue() : "" ;
        try {
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<head>");
        	out.println("<meta charset=\"UTF-8\">");
        	out.println("<title>Ejercicio 1</title>");
        	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/bootstrap.css\">");
        	out.println("</head>");
        	out.println("<body>");
        	out.println("<br>");
        	out.println("<form id=\"form\" class=\"container\" action=\"RegTicket\" method=\"post\">");
        	out.println("<div class=\"row form-group\">");
        	out.println("<div class=\"col-sm-1\">");
        	out.println("<label class=\"col-form-label\" for=\"isol\">Solicitante:</label>");
        	out.println("</div>");
        	out.println("<div class=\"col-sm-4\">");
        	out.println("<input class=\"form-control\" type=\"text\" id=\"isol\" name=\"solicitante\" value=\""+ name + "\" required>");
        	out.println("</div>");
        	out.println("<div class=\"col-sm-1\">");
        	out.println("<label class=\"col-form-label\" for=\"iemail\">Email:</label>");
        	out.println("</div>");
        	out.println("<div class=\"col-sm-4\">");
        	out.println("<input class=\"form-control\" type=\"email\" id=\"iemail\" name=\"email\" value=\""+ email +"\" required>");
        	out.println("</div>");
        	out.println("</div>");
        	out.println("<div class=\"row form-group\">");
        	out.println("<div class=\"col-sm-1\">");
        	out.println("<label class=\"col-form-label\" for=\"iasun\">Asunto:</label>");
        	out.println("</div>");
        	out.println("<div class=\"col-sm-9\">");
        	out.println("<input class=\"form-control\" type=\"text\" id=\"iasun\" name=\"asunto\" required>");
        	out.println("</div>");
        	out.println("</div>");
        	out.println("<div class=\"row form-group\">");
        	out.println("<div class=\"col-sm-10\">");
        	out.println("<textarea class=\"form-control\" rows=\"10\" cols=\"auto\" id= \"itext\" name=\"texto\" required></textarea>");
        	out.println("</div>");
        	out.println("</div>");
        	out.println("<button type=\"submit\">Registrar</button>");
        	out.println("<button onclick=\"window.location.replace('./index.jsp')\">Cancelar</button>");
        	out.println("</form>");
        	out.println("</body>");
        	out.println("</html>");
		}
        finally {
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			c.setMaxAge(0);
			response.addCookie(c);
		}
		Cookie cookie = new Cookie(request.getParameter("solicitante"), request.getParameter("email"));
		response.addCookie(cookie);

		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			CallableStatement stmt = conn.prepareCall("{CALL dbo.ins_ticket(?,?,?,?)}");
			stmt.setString(1, request.getParameter("asunto"));
			stmt.setString(2, request.getParameter("texto"));
			stmt.setString(3, request.getParameter("solicitante"));
			stmt.setString(4, request.getParameter("email"));
			stmt.execute();
			stmt.close();
			conn.close();
		}
		catch(ClassNotFoundException | SQLException e) {
			response.setStatus(400);
			request.setAttribute("error", e.getMessage());
			this.gotoPage("/error.jsp", request, response);
		}
		response.sendRedirect("./RegTicket");
		
	}
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
