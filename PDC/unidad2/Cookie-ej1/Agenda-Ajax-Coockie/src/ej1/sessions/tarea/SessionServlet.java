package ej1.sessions.tarea;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CookiesServlet
 */
@WebServlet("/tablelet.jsp")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(request.getParameterMap().size() > 0) {
			if(request.getParameter("del") != null) {
				session.removeAttribute(request.getParameter("del"));
			}
			else {
				Tarea t = new Tarea(request.getParameter("fecha"), request.getParameter("prioridad"), request.getParameter("descripcion"), request.getParameter("prioridad"), request.getParameter("email") == null ? "" : request.getParameter("email"));
				session.setAttribute(request.getParameter("pos"), t);
			}
		}
		this.gotoPage("/./jsp/table.jsp", request, response);
	}
	
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
		rd.forward(request, response);
	}
}