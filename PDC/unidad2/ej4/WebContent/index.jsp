<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejercicio 4</title>
<link rel="stylesheet" type="text/css" href="./css/theme.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<form action="" method="post">
		<table id="matrix">
			<%
			int counter = 0;
			for(int i = 0; i <3; i++) { 
			%>
			<tr>
				<% for(int j = 0; j <3; j++) {%>
				<td>
					<div class="container">
						<img src="./images/sin-seleccion.png">
						<input type="hidden" name="<%= ++counter %>" value="<%= Double.class.cast(Math.random() * 10).intValue() %>">
						<h2 class="centered">2</h2>
					</div>
				</td>
				<% } %>
			</tr>
			<% } %>
		</table>
		<label for="ima"> Mayor <input type="radio" name="mom" id="ima" value="mayor"></label>
		<label for="ime"> Menor o Igual <input type="radio" name="mom" id="ime" value="menor" checked></label>
		<input type="text" name="number" required>
		<button type="submit"> Proba Suerte </button>
	</form>
</body>
</html>