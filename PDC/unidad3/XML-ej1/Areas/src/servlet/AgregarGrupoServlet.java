package servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Agregar")
public class AgregarGrupoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public AgregarGrupoServlet() {
		super();
	}
	// Cuando se Agregan datos siempre se programa de manera que tenga Manejo Transaccional que es este codigo con varios Try Catch  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(request.getParameterMap().size() > 0) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
				conn.setAutoCommit(false);
				try {
					CallableStatement stmt = conn.prepareCall("{CALL dbo.act_grupo_contacto(?,?,?,?,?)}");
					stmt.setString(1, request.getParameter("areas"));
					if(request.getParameter("hnro_grupo") != null) stmt.setString(2, request.getParameter("hnro_grupo"));
					else stmt.setNull(2, Types.SMALLINT);
					stmt.setString(3, request.getParameter("nom_grupo"));
					stmt.setString(4, request.getParameter("exclusivo"));
					stmt.setString(5, request.getParameter("vigente"));
					stmt.execute();
					conn.commit();
					stmt.close();
				} catch (SQLException e) {
					conn.rollback();
					this.printError(e.getMessage(), request, response);
				}
				finally {
					conn.setAutoCommit(true);
					conn.close();
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
