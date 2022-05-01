package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TipoDocumentoGuardarNuevoServlet
 */
@WebServlet("/tipoDocGuardar.jsp")
public class TiposDocGuardarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TiposDocGuardarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		PreparedStatement stmt;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(false);
			
			try {
				if(request.getParameter("operacion").equals("I")) {
					stmt = conn.prepareStatement("insert into dbo.tipos_documentos(cod_tipo_documento, tipo_documento, mascara, numerico, tipo_persona) values(?, ?, ?, ?, ?)");
					stmt.setString(1, request.getParameter("cod_tipo_documento"));
					stmt.setString(2, request.getParameter("tipo_documento"));
					stmt.setString(3, request.getParameter("mascara"));
					stmt.setString(4, request.getParameter("numerico") == null ? "N" : "S");
					stmt.setString(5, request.getParameter("tipo_persona"));
				}
				else {
					stmt = conn.prepareStatement("update dbo.tipos_documentos set tipo_documento = ?, mascara = ?, numerico = ?, tipo_persona = ? where cod_tipo_documento = ?");
					stmt.setString(1, request.getParameter("tipo_documento"));
					stmt.setString(2, request.getParameter("mascara"));
					stmt.setString(3, request.getParameter("numerico") == null ? "N" : "S");
					stmt.setString(4, request.getParameter("tipo_persona"));
					stmt.setString(5, request.getParameter("cod_tipo_documento"));
				}
				stmt.executeUpdate();
				conn.commit();
				stmt.close();
			}
			catch(SQLException e) {
				conn.rollback();
				this.printError(e.getMessage(), request, response);
			}
			finally {
				conn.setAutoCommit(true);
				conn.close();
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
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
