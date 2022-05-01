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
 * Servlet implementation class StyleSheetServlet
 */
@WebServlet("/StyleSheet.do")
public class StyleSheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StyleSheetServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/css;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
        	if(request.getParameter("load") != null) {
        		String scripts[] = request.getParameter("load").split(",");
	        	for(String script : scripts) {
	        		try {
	        			List<String>  file  = Files.readAllLines(Paths.get(this.getServletContext().getRealPath("/css/") + "/" + script + ".css"), Charset.defaultCharset());
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
