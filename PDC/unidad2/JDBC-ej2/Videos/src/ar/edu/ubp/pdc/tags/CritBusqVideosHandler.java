package ar.edu.ubp.pdc.tags;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CritBusqVideosHandler extends SimpleTagSupport{

	private String onClick;
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		Connection conn;
		CallableStatement stmt;
		ResultSet result;
		
		out.println("<fieldset>");
		out.println("<input id=\"string_busqueda\" type=\"text\" name=\"string_busqueda\" value=\"\" maxlength=\"255\" size=\"100\"><br>");
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			
			stmt = conn.prepareCall("{CALL dbo.get_categorias_videos}");
			result = stmt.executeQuery();
			
			out.println("<input type=\"radio\" id=\"T\" name=\"nro_categoria\" value=\"-1\" checked>");
			out.println("<label for=\"T\">Todos</label>");
			while(result.next()) {
				out.println("<input type=\"radio\" id=\"" + result.getShort("nro_categoria") + "\" name=\"nro_categoria\" value=\"" + result.getShort("nro_categoria") + "\">");
				out.println("<label for=\"" + result.getShort("nro_categoria") + "\">" + result.getString("nom_categoria") + "</label>");
			}
			
			out.println("<br><button onClick=\"" + this.onClick + "\">Buscar</button>");
			
			stmt.close();
			conn.close();
		}
		catch(SQLException | ClassNotFoundException ex) {
			out.println(ex.getMessage());
		}
		
		out.println("</fieldset>");
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	
}
