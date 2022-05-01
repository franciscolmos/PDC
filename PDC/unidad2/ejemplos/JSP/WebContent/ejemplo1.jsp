<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Ejemplo 1</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<%-- Declaraciones (Scope application) --%>
	<%!
	int contador = 0;
	%>
	<%-- Scriptlet 1 --%>
	<b>Bloque de instrucciones Java 1: Contiene una expresi&oacute;n HTML</b>
	<% 
	out.println("<p>Hola Mundo! :)</p>");
	%>
	<%-- Expresión --%>
	<b>Expresi&oacute;n 1: Contiene una expresi&oacute;n HTML</b>
	<%= "<p>Hola Mundo! :)</p>" %>
	<%-- Scriptlet 2 --%>
	<p>
		<%
	    double a, b;
		%>
	    <b>Bloque de instrucciones Java 2: Contiene una expresi&oacute;n cuyo resultado se reemplazar&aacute; dentro de la etiqueta HTML</b><br/><br/>            
	    <% 
	    a = Math.random();
	    b = Math.random();
	    out.println("Suma de dos n&uacute;meros random: " + (a + b));
	    %>            
	</p>
	<p>
	    <b>Expresi&oacute;n 2: Contiene una expresi&oacute;n cuyo resultado se reemplazar&aacute; dentro de la etiqueta HTML</b><br/><br/>
	    <%= "Hola Mundo! :)" %>
	</p>
	<%-- Scriptlet 3 --%>
	<b>Bloque de instrucciones Java 3: Uso de la variable de instancia declarada anteriormente</b>
	<p>
	<%
	out.println("Valor del contador: " + (contador ++) + "&nbsp;&nbsp;");
	%>
	<a href="./ejemplo1.jsp">Recargar</a>
	</p>
	<br/>
	<a href="./index.html">Volver al index principal</a>
</body>
</html>