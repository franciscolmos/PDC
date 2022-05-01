<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
boolean fromAjax = request.getHeader("X-Requested-With") != null;
if(!fromAjax) {
%>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<meta charset=UTF-8 />
    <title>Reporte de Error</title>            
    <link type="text/css" rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div id="error">
<%
}
out.println(request.getAttribute("error"));
%>
<br>
<a href="#" onclick="volver()"> Regresar </a>
<%
if(!fromAjax) { 
%>	
	</div>
</body>
</html>
<%
}
%>