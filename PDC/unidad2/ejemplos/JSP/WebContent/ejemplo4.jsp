<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Ejemplo 4</title>
    <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap-theme.min.css">
    <script type="text/javascript" src="./bootstrap.3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/ejemplo45.js"></script>
</head>
<body>
    <h3>Formulario de carga de datos</h3>
    <form id="form" action="ejemplo4-res.jsp" method="post" class="col-md-12 order-md-1" onsubmit="jForm.valid(event)">
       <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="iapellido">Apellido</label>
				<input type="text" id="iapellido" name="apellido" class="form-control" required  placeholder="Ingrese su apellido" value=""  maxlength="100">
            </div>
         	<div class="col-md-6 mb-3">
             	<label for="inombre">Nombre</label>
				<input type="text" id="inombre" name="nombre" class="form-control" required  placeholder="Ingrese su nombre" value="" maxlength="255">
			</div>
       	</div>
        <div class="row">
         	<div class="col-md-6 mb-3">
             	<label for="iemail">E-mail</label>
				<input type="email" id="iemail" name="email" class="form-control" required  placeholder="Ingrese su e-mail" value="" maxlength="255">
          	</div>
         	<div class="col-md-3 mb-3">
             	<label for="iclave">Ingrese Clave</label>
				<input type="password" id="iclave" name="clave" class="form-control" value="" maxlength="32">
            </div>
         	<div class="col-md-3 mb-3">
             	<label for="iconfirmar_clave">Repetir Clave</label>
				<input type="password" id="iconfirmar_clave" class="form-control" name="confirmar_clave" value="" maxlength="32">
			</div>
       	</div>
        <div class="row">
         	<div class="col-md-2 mb-3">
				<label for="isexoF">Sexo</label>
				<div class="radio">
					<label><input type="radio" name="sexo" value="F" id="isexoF" checked="checked">Femenino</label><br>
				</div>
				<div class="radio">
					<label><input type="radio" name="sexo" value="M" id="isexoM">Masculino</label>
				</div>
			</div>
            <div class="col-md-4 mb-3">
                <label for="ifecha_nacimiento">Fecha de Nac.</label>
				<input type="date" id="ifecha_nacimiento" name="fecha_nacimiento" class="form-control" required value="">
			</div>
			<div class="col-md-3 mb-3">
				<label for="inacionalidad">Nacionalidad</label>
				<select id="inacionalidad" name="nacionalidad" class="form-control" required onchange="jForm.selNacionalidad(this)">
					<option value="">Debe seleccionar una nacionalidad</option>
					<option value="AR" selected="selected">Argentina</option>
					<option value="BO">Boliviana</option>
					<option value="BR">Brasile&ntilde;a</option>
					<option value="CH">Chilena</option>
					<option value="PY">Paraguaya</option>
					<option value="UY">Uruguaya</option>
					<option value="-1">Otra</option>
				</select>
			</div>
			<div class="col-md-3 iotranac">
				<label for="iotranac">Otra Nacionalidad</label>
				<input type="text" id="iotranac" name="otra_nacionalidad" class="form-control" maxlength="255" size="40" disabled="disabled"/>
			</div>
		</div>
        <div class="row">
         	<div class="col-md-6 mb-3">
				<label for="iequipo">Equipo Favorito</label>
				<select id="iequipo" name="equipo" size="5" multiple class="form-control">
					<option value="BE">Belgrano</option>
					<option value="BC">Boca</option>
					<option value="L">Lan&uacute;s</option>
					<option value="RA">Racing</option>
					<option value="RV">River</option>
					<option value="SL">San Lorenzo</option>
				</select>
			</div>
   			<div class="col-md-6 mb-3">
				<label>Hobbies</label>
				<div class="checkbox">
					<label><input type="checkbox" name="hobbies" value="Bailar" id="bailar">Bailar</label>
				</div>
				<div class="checkbox">
					<label><input type="checkbox" name="hobbies" value="Cantar" id="cantar">Cantar</label>
				</div>
				<div class="checkbox">
					<label><input type="checkbox" name="hobbies" value="Deportes" id="deportes">Deportes</label>
				</div>
				<div class="checkbox">
					<label><input type="checkbox" name="hobbies" value="Leer" id="leer">Leer</label>
				</div>
				<div class="checkbox">
					<label><input type="checkbox" name="hobbies" value="Leer" id="leer">Ver televisi&oacute;n</label>
				</div>
        	</div>
		</div>
    	<div class="row">
     		<div class="col-md-12 mb-3">
				<label for="iactividades">Otras Actividades</label>
                <textarea cols="50" rows="5" id="iactividades" name="actividades" class="form-control" placeholder="Mis actividades son..." onkeyup="jForm.validActLen(this)"></textarea>
				<h5><span id="icact"></span> caracteres</h5>
       		</div>
   		</div>
   	 	<div class="row">
			<div class="col-lg-12">
				<button type="submit" name="boton1" class="btn btn-primary">Mostrar Datos</button>
				<button type="reset"  name="boton2" class="btn btn-danger">Limpiar formulario</button>
				<a href="./index.html">Volver al index principal</a>
			</div>
    	</div>
	</form>
</body>
</html>