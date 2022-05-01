<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parcial</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
<h3>Conectarse Como...</h3>
<div id="datos">
</div>
<form id="form" action="javascript:void(null)" method="post" onsubmit="JRol.buscar()">
	<div class="row">
		<div class="col form-group">
			<label for="irol"> Rol </label>
			<select class="form-control" id="irol" name="rol">
			</select>
		</div>
		<div class="col form-group">
			<label for="ibusqueda"> Apellido, Nombre | Tipo | Documento </label>
			<input class="form-control" type="text" id="ibusqueda" name="busqueda" max="255">
		</div>
	</div>
	<div class="row">
		<div class="col form-group">
			<button type="submit" class="btn btn-primary" id="buscar">Buscar</button>
		</div>
	</div>
</form>
<div id="response"></div>
</body>
</html>