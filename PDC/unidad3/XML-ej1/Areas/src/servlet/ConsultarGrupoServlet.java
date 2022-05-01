package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GrupoBean;

@WebServlet("/Consultar")
public class ConsultarGrupoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ConsultarGrupoServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection conn;
		PreparedStatement stmt;
		ResultSet res;
		LinkedList<GrupoBean> grupos = new LinkedList<GrupoBean>();
		GrupoBean grupo;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement("select cod_area,\r\n" + 
					"       nro_grupo,\r\n" + 
					"       nom_grupo,\r\n" + 
					"       exclusivo,\r\n" + 
					"       vigente\r\n" + 
					"  from dbo.grupos_contactos\r\n" + 
					" where cod_area = ? \r\n");
			stmt.setString(1, request.getParameter("areas"));
			res = stmt.executeQuery();
			while(res.next()) {
				grupo = new GrupoBean();
				grupo.setCod_area(res.getString("cod_area"));
				grupo.setNro_grupo(res.getString("nro_grupo"));
				grupo.setNom_grupo(res.getString("nom_grupo"));
				grupo.setExclusivo(res.getString("exclusivo"));
				grupo.setVigente(res.getString("vigente"));
				grupos.add(grupo);
			}
			request.setAttribute("grupos", grupos);
			this.gotoPage("/grupos.jsp", request, response);
			
		}catch(SQLException | ClassNotFoundException ex) {
			response.setStatus(400);
			request.setAttribute("error", ex.getMessage());
			this.gotoPage("/error.jsp", request, response);
		}
	}
	
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
