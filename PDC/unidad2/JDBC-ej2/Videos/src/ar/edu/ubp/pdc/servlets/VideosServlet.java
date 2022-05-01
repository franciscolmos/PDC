package ar.edu.ubp.pdc.servlets;

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

import ar.edu.ubp.pdc.beans.VideoBean;

/**
 * Servlet implementation class VideosServlet
 */
@WebServlet("/videos.jsp")
public class VideosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection conn;
		PreparedStatement stmt;
		ResultSet result;
		LinkedList<VideoBean> videos = new LinkedList<VideoBean>();
		VideoBean video;
	
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			
			stmt = conn.prepareStatement("select c.nom_categoria," + 
										 "       v.nro_categoria," + 
										 "       v.titulo," + 
										 "	     v.cantante," + 
										 "	     v.link_video," + 
										 "	     v.nro_video" + 
										 "  from dbo.videos v (nolock)" + 
										 "       join dbo.categorias_videos c (nolock)" + 
										 "	     on v.nro_categoria = c.nro_categoria" + 
										 " where (? is null" + 
										 "    or  v.nro_categoria = ?)" + 
										 "   and  v.titulo + ' ' + v.cantante + ' ' + v.titulo like '%' + isnull(ltrim(rtrim(?)), '') + '%' " + 
										 " order by c.nom_categoria," + 
										 "          v.titulo;");
			
			if(request.getParameter("nro_categoria").equals("-1")) {
				stmt.setNull(1, Types.TINYINT);
				stmt.setNull(2, Types.TINYINT);
			}
			else {
				stmt.setShort(1, Short.parseShort(request.getParameter("nro_categoria")));
				stmt.setShort(2, Short.parseShort(request.getParameter("nro_categoria")));				
			}
			stmt.setString(3, request.getParameter("string_busqueda"));
			
			result = stmt.executeQuery();
			while(result.next()) {
				video = new VideoBean();
				video.setNomCategoria(result.getString("nom_categoria"));
				video.setNroCategoria(result.getShort("nro_categoria"));
				video.setCantante(result.getString("cantante"));
				video.setTitulo(result.getString("titulo"));
				video.setLinkVideo(result.getString("link_video"));
				video.setNroVideo(result.getInt("nro_video"));
				videos.add(video);
			}
			
			request.setAttribute("videos", videos);
			this.gotoPage("/listado.jsp", request, response);
		}
		catch(SQLException | ClassNotFoundException ex) {
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
