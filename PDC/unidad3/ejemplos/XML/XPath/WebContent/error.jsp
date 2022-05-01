<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Reporte de Error</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
</head>
<body>
	<div id="error"><%= request.getAttribute("error") %></div>
</body>
</html>