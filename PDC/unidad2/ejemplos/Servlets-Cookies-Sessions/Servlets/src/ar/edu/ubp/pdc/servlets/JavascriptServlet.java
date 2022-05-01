package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavascriptServlet
 */
@WebServlet("/Javascript.do")
public class JavascriptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavascriptServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
        	if(request.getParameter("load") != null) {
        		String scripts[] = request.getParameter("load").split(",");
	        	for(String script : scripts) {
	        		try {
	        			List<String>  file  = Files.readAllLines(Paths.get(this.getServletContext().getRealPath("/js/") + "/" + script + ".js"), Charset.defaultCharset());
	        			StringBuilder lines = new StringBuilder();	        		
				        for(String line : file) {
		        			lines.append(line);
		        		}
		        		out.println(lines);
	        		}
	        		catch(NoSuchFileException ex) { } 
	        	}
        	}
		} 
        finally {            
            out.close();
        }
	}

}
