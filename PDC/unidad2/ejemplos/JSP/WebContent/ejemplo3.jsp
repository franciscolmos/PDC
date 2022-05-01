<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Ejemplo 3</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/ejemplo3.js"></script>
</head>
<body>
	<h3>C&aacute;lculos</h3>
	<form id="form" name="form" action="ejemplo3-res.jsp" method="post">
	    <p>Variable 1: <input type="number" name="variable1" value="" size="10" maxlength="10" placeholder="Ingrese un valor" required onkeydown="jNumber.allowOnlyNumbers(event)"></p>
	    <p>Variable 2: <input type="number" name="variable2" value="" size="10" maxlength="10" placeholder="Ingrese un valor" required onkeydown="jNumber.allowOnlyNumbers(event)"></p>
	    <button type="submit">Calcular</button>
	</form>
	<br/>
	<a href="./index.html">Volver al index principal</a>
</body>
</html>