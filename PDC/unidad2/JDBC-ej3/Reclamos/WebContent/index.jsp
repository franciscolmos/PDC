<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ejercicio 3</title>
	
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/script.js"></script>
	
</head>
<body>
<div id="response"></div>
<div id="main">
	<h3>Reclamos</h3>
	<h4>¿Quiere hacer un Reclamo?</h4>
	<br>
	<form id="form" action="javascript:void(null)" method="post">
		<div class="row">
			<div class="col-4"><label for="ichasS">¿Conoces tu numero de chasis? *</label></div>
			<div class="col-4">
				<label><input type="radio" id="ichasS" name="chas" value="S" checked>Si</label>
				<label><input type="radio" id="ichasN" name="chas" value="N" >No</label>
			</div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="ichasis">N° de Chasis *</label></div>
			<div class="col-4">
				<input class="form-control" type="text" id="ichasis" name="chasis" required>
			</div>
			<div class="col-1" id="image"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="ipat">Patente</label></div>
			<div class="col-4"><input class="form-control" type="text" id="ipat" name="patente"></div>
			<div class="col-1" id="image"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="ikil">Kilomentros</label></div>
			<div class="col-4"><input class="form-control" type="number" id="ikil" name="kilo"></div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="ipa">Apellido *</label></div>
			<div class="col-4"><input class="form-control" type="text" id="ipa" name="apellido" required></div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="inom">Nombre *</label></div>
			<div class="col-4"><input class="form-control" type="text" id="inom" name="nombre" required></div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="iemail">E-mail *</label></div>
			<div class="col-4"><input class="form-control" type="email" id="iemail" name="email" required></div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="itel">Telefono *</label></div>
			<div class="col-4"><input class="form-control" type="text" id="itel" name="tel" required></div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="ivenS">¿Desea ser contactado por su vendedor? *</label></div>
			<div class="col-4">
				<label><input type="radio" id="ivenS" name="ven" value="S" checked>Si</label>
				<label><input type="radio" id="ivenN" name="ven" value="N" >No</label>
			</div>
			<div class="col-1"></div>
		</div>
		<div class="row">
			<div class="col-4"><label for="ire">Reclamo (4000 caracteres Max) *</label></div>
		</div>
		<div class="row">
			<div class="col-8">
				<textarea class="form-control" id="ire" name="reclamo" required></textarea>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-8 text-center"> <button class="btn btn-danger" id="ireg" name="register">Registar</button> </div>
		</div>
	</form>
</div>
</body>
</html>