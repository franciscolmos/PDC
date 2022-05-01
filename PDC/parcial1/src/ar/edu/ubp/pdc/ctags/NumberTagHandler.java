package ar.edu.ubp.pdc.ctags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class NumberTagHandler extends SimpleTagSupport {
	private String id;
	private String name;
	private int min;
	private int max;
	private String btn;
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		
		String is = "<input id=\"%s\" name=\"%s\" type=\"number\" min=\"%d\" max=\"%d\" required>";
		String bs = "<button value=\"submit\">%s</button>";
		
		JspWriter out = this.getJspContext().getOut();
		out.print(String.format(is, id, name, min, max));
		out.print(String.format(bs, btn));
	}
	
	public void setId(String x) {
		this.id = x;
	}
	
	public void setName(String x) {
		this.name = x;
	}
	
	public void setMin(int x) {
		this.min = x;
	}
	
	public void setMax(int x) {
		this.max = x;
	}
	
	public void setBtn(String x) {
		this.btn = x;
	}
}
