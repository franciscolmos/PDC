package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.pdc.beans.TipoDocumentoBean;

/**
 * Servlet implementation class TiposDocEditarServlet
 */
@WebServlet("/tiposDocEditar.jsp")
public class TiposDocEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TiposDocEditarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		CallableStatement stmt;		
		ResultSet result;
		TipoDocumentoBean tipoDoc = new TipoDocumentoBean();
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			
			stmt = conn.prepareCall("{CALL dbo.get_datos_tipo_documento(?)}");
			stmt.setString(1, request.getParameter("cod_tipo_documento"));
			result = stmt.executeQuery();
			
			if(result.next()) {
				tipoDoc.setCodTipoDocumento(result.getString("cod_tipo_documento"));
				tipoDoc.setTipoDocumento(result.getString("tipo_documento"));
				tipoDoc.setMascara(result.getString("mascara"));
				tipoDoc.setNumerico(result.getString("numerico"));
				tipoDoc.setTipoPersona(result.getString("tipo_persona"));
			}
			
			stmt.close();
			conn.close();
			
			request.setAttribute("tipo_documento", tipoDoc);
			this.gotoPage("/tiposDocDatos.jsp", request, response);
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
