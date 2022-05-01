package ubp.pdc.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ubp.pdc.beans.PersonaBean;

@WebServlet("/VerPersona")
public class VerPersonasServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public VerPersonasServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection conn;
		PreparedStatement stmt;
		ResultSet res;
		LinkedList<PersonaBean> personas = new LinkedList<PersonaBean>();
		PersonaBean persona;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://bilbo;databaseName=pdc", "ecigoyenechenunez", "39623053");
			conn.setAutoCommit(true);
			
			stmt = conn.prepareStatement("select nom_persona = upper(p.apellido + ', ' + p.nombre),\r\n" + 
					"	   documento   = p.cod_tipo_documento + '-' + p.nro_documento,\r\n" + 
					"	   nom_rol     = upper(r.nom_rol),\r\n" + 
					"	   usuario     = r.usuario_bd,\r\n" + 
					"	   clave       = r.clave_bd,	 \r\n" + 
					"	   nro_persona = rp.nro_persona,       	 \r\n" + 
					"       nro_rol     = rp.nro_rol\r\n" + 
					"  from dbo.roles_personas rp (nolock)\r\n" + 
					"       join dbo.roles r (nolock)\r\n" + 
					"	     on rp.nro_rol     = r.nro_rol\r\n" + 
					"	   join dbo.personas p (nolock)\r\n" + 
					"	     on rp.nro_persona = p.nro_persona\r\n" + 
					" where (?   = -1\r\n" + 
					"    or  rp.nro_rol = ?)\r\n" + 
					"   and (upper(p.apellido + ', ' + p.nombre)          like '%' + isnull(ltrim(rtrim(?)), '') + '%'\r\n" + 
					"    or  p.cod_tipo_documento + '-' + p.nro_documento like '%' + isnull(ltrim(rtrim(?)), '') + '%'\r\n" + 
					"    or  p.cod_tipo_documento                         like '%' + isnull(ltrim(rtrim(?)), '') + '%'\r\n" + 
					"    or  p.nro_documento                              like '%' + isnull(ltrim(rtrim(?)), '') + '%')\r\n" + 
					" order by nom_rol,\r\n" + 
					"          nom_persona");
			
			stmt.setString(1, request.getParameter("rol"));
			stmt.setString(2, request.getParameter("rol"));
			
			if(request.getParameter("busqueda") == null || request.getParameter("busqueda") == "") {
				stmt.setNull(3, Types.VARCHAR);
				stmt.setNull(4, Types.VARCHAR);
				stmt.setNull(5, Types.VARCHAR);
				stmt.setNull(6, Types.VARCHAR);
			}
			else {
				stmt.setString(3, request.getParameter("busqueda"));
				stmt.setString(4, request.getParameter("busqueda"));
				stmt.setString(5, request.getParameter("busqueda"));
				stmt.setString(6, request.getParameter("busqueda"));
			}
			
			res = stmt.executeQuery();
			while (res.next()) {
				persona = new PersonaBean();
				persona.setNombre(res.getString("nom_persona"));
				persona.setDocumento(res.getString("documento"));
				persona.setRol(res.getString("nom_rol"));
				persona.setUsuario(res.getString("usuario"));
				persona.setContra(res.getString("clave"));
				personas.add(persona);
			}
			request.setAttribute("personas", personas);
			this.gotoPage("/personas.jsp", request, response);
		} 
		catch (ClassNotFoundException | SQLException e) {
			this.printError(e.getMessage(), request, response);
		}
	}
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(400);
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
	}
}
