<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customs.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ejercicio 2</title>
</head>
<body>
	<select>
		<ct:calendar>
			<option value="<%= num %>" <%= value == "S" ? "Selected": "" %>><%= mes %></option>
		</ct:calendar>
	</select>
</body>
</html>