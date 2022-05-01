package tags.ej2;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.io.IOException;
import java.util.Calendar;


public class Cal extends BodyTagSupport {
	private static final long serialVersionUID = 3154148448049573092L;
	private String[] MonthName;
	private int index;
	
	public Cal() throws JspException{
		super();
		this.index = 0;
		this.MonthName = new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	}
	
	@Override
	public int doStartTag() throws JspException {
		if (index <= 11) {
			add();
			return EVAL_BODY_BUFFERED;
		}
		return SKIP_BODY;
	}
	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = this.bodyContent.getEnclosingWriter();
		try {
			this.bodyContent.writeOut(out);
			this.bodyContent.clearBody();
			if(index <= 11) {
					add();
					return EVAL_BODY_AGAIN;
			}
			return SKIP_BODY;
		}
		catch (IOException ex) {
			throw new JspException(ex);
		}
	}
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	
	private void add() {
		String month = this.MonthName[index];
		String num = Integer.toString(index + 1);
		String actual = Calendar.getInstance().get(Calendar.MONTH) == index ? "S" : "N";
		
		this.pageContext.setAttribute("mes", month);
		this.pageContext.setAttribute("num", num);
		this.pageContext.setAttribute("value", actual);
		
		this.index++;
	}
}
