package ar.edu.ubp.pdc.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SaludoTagHandler extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		// TODO: Auto-generated method stub
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		out.println("Hola Mundo");
	}

}
