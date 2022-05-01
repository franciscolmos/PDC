package customs.ticket.ej1;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomTagBuscador extends SimpleTagSupport {
	String function;
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-3\">");
		out.println("<label for=\"isbus\"> Solicitante | Asunto | Texto </label><br>");
		out.println("<input type=\"text\" id=\"isbus\" name=\"string_busqueda\" maxlength=\"255\">");
		out.println("</div>");
		out.println("<div class=\"col-4 right\">");
		out.println("<label> Ordenar Por </label><br>");
		out.println("<label for=\"ordS\"><input type=\"radio\" id=\"ordS\" name=\"ordenar_por\" value=\"S\">Solicitante</label>");
		out.println("<label for=\"ordF\"><input type=\"radio\" id=\"ordF\" name=\"ordenar_por\" value=\"F\" checked>Fecha</label>");
		out.println("<label for=\"ordN\"><input type=\"radio\" id=\"ordN\" name=\"ordenar_por\" value=\"N\">Nro. de Ticket</label>");
		out.println("</div>");
		out.println("</div>");
		out.println("<br>");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col\">");
		out.println("<button id=\"ibus\" name=\"busqueda\" onclick=\""+this.function+"\">Buscar</button>");
		out.println("</div>");
		out.println("</div>");
	}
	public void setFunction(String function) {
		this.function = function;
	}
}
