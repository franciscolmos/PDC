package tags.ej1;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EstadoCivil extends SimpleTagSupport {
	String name, value, func;
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		
		out.println("<select name=\""+ (this.name.equals("") ? "cod_estado_civil": this.name) +"\" onchange= "+this.func+">"); 
		out.println("<option value=\"\" "+(this.value.equals("")? "selected": "")+">Debe Seleccionar un Estado Civil</option>"); 
		out.println("<option value=\"SO\" "+(this.value.equals("SO")? "selected": "")+">Soltero</option>"); 
		out.println("<option value=\"CA\" "+(this.value.equals("CA")? "selected": "")+">Casado</option>"); 
		out.println("<option value=\"SEP\" "+(this.value.equals("SEP")? "selected": "")+">Seperado</option>"); 
		out.println("<option value=\"DIV\" "+(this.value.equals("DIV")? "selected": "")+">Divorciado</option>");
		out.println("<option value=\"VI\" "+(this.value.equals("VI")? "selected": "")+">Viuda</option>");
		out.println("<option value=\"OTR\" "+(this.value.equals("OTR")? "selected": "")+">Otro</option>");
		out.println("</select>");
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setFunc(String func) {
		this.func = func;
	}
}
