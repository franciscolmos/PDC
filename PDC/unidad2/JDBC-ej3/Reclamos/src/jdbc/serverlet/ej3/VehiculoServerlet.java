package jdbc.serverlet.ej3;

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



@WebServlet("/VehiculoServerlet")
public class VehiculoServerlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public VehiculoServerlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(request.getParameterMap().size() > 0) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
				conn.setAutoCommit(true);
				CallableStatement stmt = conn.prepareCall("{CALL dbo.val_vehiculos(?,?,?)}");
				if(request.getParameter("chasis") != null) stmt.setString(1,request.getParameter("chasis"));
				else stmt.setNull(1, Types.VARCHAR);
				if(request.getParameter("patente") != null) stmt.setString(2,request.getParameter("patente"));
				else stmt.setNull(2, Types.VARCHAR);
				stmt.registerOutParameter(3, Types.CHAR);
				stmt.execute();
				request.setAttribute("existe", stmt.getString("existe")); 
				gotoPage("/image.jsp", request, response);
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
