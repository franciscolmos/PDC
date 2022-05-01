<%@page import="ar.edu.ubp.pdc.classes.Juego"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "ar.edu.ubp.pdc.classes.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="./css/theme.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
<title>Ejercicio 1</title>
</head>
<body>
		<h3>Te invito a jugar... :)</h3>
		<label>Selecciona una Opcion: </label>
		<select name="ppt" id="ippt">
			<option value=""></option>
			<option value="piedra"> Piedra </option>
			<option value="papel"> Papel </option>
			<option value="tijera"> Tijera </option>
		</select>
		<button type="submit" id="play">Jugar</button>
</body>
</html>