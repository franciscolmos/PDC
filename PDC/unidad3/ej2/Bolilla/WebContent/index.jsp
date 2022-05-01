<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/theme.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
<title>Ejercio 2</title>
</head>
<body>
<h1> Participantes </h1>
<div id="response"></div>
<form id="form" action="javascript:void(null)" method="post">
	<table>
		<thead>
			<tr>
				<th>Participante</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="inombre" name="nombre" required></td>
			</tr>
		</tbody>
	</table>
	<button id="adder" type="submit"> Agregar </button>
	<button id="trought" type="button" hidden> Extraer Bolilla </button>
	<button id="init" onclick="location.reload()" hidden> Iniciar </button>
</form>
</body>
</html>