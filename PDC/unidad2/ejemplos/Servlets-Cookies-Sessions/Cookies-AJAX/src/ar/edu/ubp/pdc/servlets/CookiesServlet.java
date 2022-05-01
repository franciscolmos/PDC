package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookiesServlet
 */
@WebServlet("/saveCookie.jsp")
public class CookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookiesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
		Cookie cookie = null;
		if(request.getParameter("delCookieName") != null && request.getParameter("delCookieName") != "") {
			cookie = new Cookie(request.getParameter("delCookieName"), "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		else {
			cookie = new Cookie(request.getParameter("cookieName"), request.getParameter("cookieValue"));
			//cookie.setMaxAge(24 * 60 * 60);  // 24 horas
			response.addCookie(cookie);
		    this.gotoPage("/cookie.jsp", request, response);
		}
	}
	
	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(address);
		rd.forward(request, response);
	}
	
}
