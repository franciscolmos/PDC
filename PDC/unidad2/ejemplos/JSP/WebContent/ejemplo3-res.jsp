<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Ejemplo 3</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<%
	double variable1, variable2;    

	variable1 = Double.parseDouble(request.getParameter("variable1")); 
    variable2 = Double.parseDouble(request.getParameter("variable2")); 
    %>
  	<h3>C&aacute;lculos entre los n&uacute;meros &quot;<%= variable1 %>&quot; y &quot;<%= variable2 %>&quot;</h3>
    <table>
  		<colgroup>
  			<col width="20%"/>
  			<col width="20%"/>
  			<col width="20%"/>
  			<col width="20%"/>
  			<col width="20%"/>
  		</colgroup>
   		<thead>
   			<tr>
   				<th align="right">Suma</th>
   				<th align="right">Resta</th>
   				<th align="right">Multiplicaci&oacute;n</th>
   				<th align="right">Divisi&oacute;n</th>
   				<th align="right">Resto</th>
   			</tr>
   		</thead>			
   		<tbody>
   			<tr>
   				<td align="right"><%= variable1 + variable2 %></td>
   				<td align="right"><%= variable1 - variable2 %></td>
   				<td align="right"><%= variable1 * variable2 %></td>
   				<td align="right"><%= (variable2 == 0 ? "-" : variable1 / variable2) %></td>
   				<td align="right"><%= (variable2 == 0 ? "-" : variable1 % variable2) %></td>
   			</tr>
   		</tbody>
   		<%
   		if(variable2 == 0) {
   		%>
   		<tfoot>
   			<tr>
   				<th colspan="5" align="left">Nota: No se puede dividir por cero!</th>
   			</tr>
   		</tfoot>
   		<%
   		}
   		%>
   		<%--
   		//Programación similar a lo anterior
   		if(variable2 == 0) {
   			out.println("<tfoot>");
   			out.println("<tr>");
   			out.println("<th colspan=\"5\" align=\"left\">Nota: No se puede dividir por cero!</th>");
   			out.println("</tr>");
   			out.println("</tfoot>");
   		}
   		--%>
    </table>
    <br/>
	<a href="./ejemplo3.jsp">Volver</a>
</body>
</html>