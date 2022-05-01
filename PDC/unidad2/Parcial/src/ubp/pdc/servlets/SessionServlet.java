package ubp.pdc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ubp.pdc.beans.PersonaBean;

@WebServlet("/Conectar")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SessionServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(request.getParameterMap().size() > 0) {
			PersonaBean p = new PersonaBean();
			p.setNombre(request.getParameter("nombre"));
			p.setDocumento(request.getParameter("doc"));
			p.setRol(request.getParameter("rol"));
			p.setUsuario(request.getParameter("usuario"));
			p.setContra(request.getParameter("clave"));
			session.setAttribute("usuario", p);
			this.gotoPage("/usuario.jsp", request, response);
		}
	}
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
		rd.forward(request, response);
	}
}
