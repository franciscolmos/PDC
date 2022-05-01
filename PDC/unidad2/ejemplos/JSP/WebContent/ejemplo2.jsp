<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Ejemplo 2</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<% int multiplo = 10; %>
	<h3>M&uacute;ltiplos</h3>
	<table>
		<colgroup>
		<%
		for(int i = 1; i <= multiplo; i ++) {
		    out.println("<col width=\"50px\"/>");
		}
		%>
		</colgroup>
		<%
		for(int i = 1; i <= multiplo; i ++) {
		    out.println("<tr>");
		    for(int j = 1; j <= multiplo; j ++) {
		        out.println("<td align=\"right\">" + (i * j) + "</td>");
		    }
		    out.println("</tr>");
		}
		%>
	</table>
	<br/>
	<a href="./index.html">Volver al index principal</a>
</body>
</html>