<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Ejemplo 7</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<div id="error">
		<h3>Error</h3>
		<p><%= exception.toString() %></p>
		<a href="./index.html">Volver al index principal</a>
	</div>	
</body>
</html>