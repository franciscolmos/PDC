package ubp.pdc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ubp.pdc.beans.RolBean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet("/VerRol")
public class VerRolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public VerRolServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		LinkedList<RolBean> roles = new LinkedList<RolBean>();
		RolBean rol;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://bilbo;databaseName=pdc", "ecigoyenechenunez", "39623053");
			conn.setAutoCommit(true);
			CallableStatement stmt = conn.prepareCall("{CALL dbo.get_roles}");
			ResultSet res = stmt.executeQuery();
			rol = new RolBean();
			rol.setNro_rol("-1");
			rol.setNom_rol("Todos");
			roles.add(rol);
			while (res.next()) {
				rol = new RolBean();
				rol.setNro_rol(res.getString("nro_rol"));
				rol.setNom_rol(res.getString("nom_rol"));
				roles.add(rol);
			}
			request.setAttribute("roles", roles);
			this.gotoPage("/roles.jsp", request, response);
		}
		catch(ClassNotFoundException | SQLException e) {
			this.printError(e.getMessage(), request, response);
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
