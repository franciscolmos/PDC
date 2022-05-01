package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.pdc.beans.TipoDocumentoBean;

/**
 * Servlet implementation class TipoDocumentoServlet
 */
@WebServlet("/index.jsp")
public class TiposDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TiposDocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=iso-8859-1");
		Connection conn;
		Statement stmt;
		ResultSet result;
		
		LinkedList<TipoDocumentoBean> tiposDoc = new LinkedList<TipoDocumentoBean>();
		TipoDocumentoBean td;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			
			stmt = conn.createStatement();
			stmt.executeQuery("select * from dbo.tipos_documentos");
			
			result = stmt.getResultSet();
			while(result.next()) {
				td = new TipoDocumentoBean();
				td.setCodTipoDocumento(result.getString("cod_tipo_documento"));
				td.setTipoDocumento(result.getString("tipo_documento"));
				td.setMascara(result.getString("mascara"));
				td.setNumerico(result.getString("numerico"));
				td.setTipoPersona(result.getString("tipo_persona"));
				tiposDoc.add(td);
			}
			
			stmt.close();
			conn.close();
			
			request.setAttribute("tiposDoc", tiposDoc);
			this.gotoPage("/tiposDoc.jsp", request, response);
		} 
		catch (ClassNotFoundException | SQLException e) {
			this.printError(e.getMessage(), request, response);
		}
	
	}
	
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
	}
	
}
