package jdbc.ticket.ej1;

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

import beans.ticket.ej1.TicketBean;

@WebServlet("/Verticket")
public class VerTicketServerlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public VerTicketServerlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection conn;
		PreparedStatement stmt;
		ResultSet res;
		LinkedList<TicketBean> tickets = new LinkedList<TicketBean>();
		TicketBean ticket;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "pyxis");
			conn.setAutoCommit(true);
			
			stmt = conn.prepareStatement("select ticket = convert(varchar(4), t.año_ticket) + '-' + replicate('0', 5 - len(convert(varchar(5), t.nro_ticket))) + convert(varchar(5), t.nro_ticket), "
											+ "fecha_ticket = convert(varchar(10), t.fecha_ticket, 103) + ' ' + convert(varchar(5), t.fecha_ticket, 108), "
											+ "solicitante = s.nom_solicitante, "
											+ "asunto_ticket = t.asunto_ticket, "
											+ "texto_ticket = t.texto_ticket "
										+ "from dbo.tickets t (nolock) "
											+ "join dbo.solicitantes s (nolock) "
											+ "on t.nro_solicitante = s.nro_solicitante "
										+ "where (t.asunto_ticket   like '%' + isnull(ltrim(rtrim(?)), '') + '%' "
											+ "or  t.texto_ticket    like '%' + isnull(ltrim(rtrim(?)), '') + '%' "
											+ "or  s.nom_solicitante like '%' + isnull(ltrim(rtrim(?)), '') + '%') "
										+ "order by case ? "
											+ "when 'F' "
											+ "then convert(varchar(10), t.fecha_ticket, 112) + ' ' + convert(varchar(5), t.fecha_ticket, 108) "
											+ "when 'S' "
											+ "then s.nom_solicitante "
											+ "when 'T' "
											+ "then convert(varchar(4), t.año_ticket) + '-' + replicate('0', 5 - len(convert(varchar(5), t.nro_ticket))) + convert(varchar(5), t.nro_ticket) "
										+ "end ");
			if(request.getParameter("string_busqueda") == null || request.getParameter("string_busqueda") == "") {
				stmt.setNull(1, Types.VARCHAR);
				stmt.setNull(2, Types.VARCHAR);
				stmt.setNull(3, Types.VARCHAR);
			}
			else {
				stmt.setString(1, request.getParameter("string_busqueda"));
				stmt.setString(2, request.getParameter("string_busqueda"));
				stmt.setString(3, request.getParameter("string_busqueda"));
			}
			stmt.setString(4, request.getParameter("ordenar_por"));
			
			res = stmt.executeQuery();
			while (res.next()) {
				ticket = new TicketBean();
				ticket.setTicket(res.getString("ticket"));
				ticket.setFecha(res.getString("fecha_ticket"));
				ticket.setSolicitante(res.getString("solicitante"));
				ticket.setAsunto(res.getString("asunto_ticket"));
				ticket.setTexto(res.getString("texto_ticket"));
				tickets.add(ticket);
			}
			request.setAttribute("tickets", tickets);
			this.gotoPage("/tickets.jsp", request, response);
			
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
