package tags.ej2;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class CalTagExtraInfo extends TagExtraInfo {
	public VariableInfo [] getVariableInfo(TagData tagData) {
		VariableInfo month = new VariableInfo("mes", "String", true, VariableInfo.NESTED);
		VariableInfo num = new VariableInfo("num", "String", true, VariableInfo.NESTED);
		VariableInfo value = new VariableInfo("value", "String", true, VariableInfo.NESTED);
		VariableInfo variableinfo [] = {month, num, value};
		return variableinfo;
	}
}
