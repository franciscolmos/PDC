<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Ejemplo 6</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<h3>La fecha del d&iacute;a es:&nbsp; <%= request.getParameter("date") %></h3>
	<a href="./index.html">Volver al index principal</a>
</body>
</html>