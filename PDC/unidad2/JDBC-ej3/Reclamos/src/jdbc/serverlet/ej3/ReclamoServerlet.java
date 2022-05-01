package jdbc.serverlet.ej3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ReclamoServerlet")
public class ReclamoServerlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ReclamoServerlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().size() > 0) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
				conn.setAutoCommit(false);
				try {
					PreparedStatement stmt = conn.prepareStatement("insert into dbo.reclamos(nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
					if(request.getParameter("chasis") != null) stmt.setString(1, request.getParameter("chasis"));
					else stmt.setNull(1, Types.VARCHAR);
					if(request.getParameter("patente") != null && request.getParameter("patente") != "") stmt.setString(2, request.getParameter("patente"));
					else stmt.setNull(2, Types.VARCHAR);
					if(request.getParameter("kilo") != null && request.getParameter("kilo") != "") stmt.setString(3, request.getParameter("kilo"));
					else stmt.setNull(3, Types.INTEGER);
					stmt.setString(4, request.getParameter("apellido"));
					stmt.setString(5, request.getParameter("nombre"));
					stmt.setString(6, request.getParameter("email"));
					stmt.setString(7, request.getParameter("tel"));
					stmt.setString(8, request.getParameter("ven"));
					stmt.setString(9, request.getParameter("reclamo"));
					stmt.executeUpdate();
					conn.commit();
					stmt.close();
				}
				catch (SQLException e) {
					conn.rollback();
					this.printError(e.getMessage(), request, response);
				}
				finally {
					conn.setAutoCommit(true);
					conn.close();
					this.gotoPage("/reclamo.jsp", request, response);
				}
			}
			catch(ClassNotFoundException | SQLException e) {
				this.printError(e.getMessage(), request, response);
			}
		}
	}
	
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(400);
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
	}
}
