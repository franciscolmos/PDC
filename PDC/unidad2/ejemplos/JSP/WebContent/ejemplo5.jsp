<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ page import="java.util.HashMap, java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Ejemplo 5</title>
    <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap-theme.min.css">
    <script type="text/javascript" src="./bootstrap.3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/ejemplo45.js"></script>
</head>
<body>
    <h3>Formulario de carga de datos</h3>
	<%
	if(request.getParameterMap().size() == 0) {
	%>
    <form id="form" action="ejemplo5.jsp" method="post" class="col-md-12 order-md-1" onsubmit="jForm.valid(event)">
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
	<%
	}
	else {		
		HashMap<String,String> nacionalidades = new HashMap<String,String>();
		HashMap<String,String> equipos        = new HashMap<String,String>();

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		Date fecha_nacimiento = parser.parse(request.getParameter("fecha_nacimiento"));
		
		nacionalidades.put("AR", "Argentina");
	    nacionalidades.put("BO", "Boliviana");
	    nacionalidades.put("BR", "Brasileña");
	    nacionalidades.put("CH", "Chilena");
	    nacionalidades.put("PY", "Paraguaya");
	    nacionalidades.put("UY", "Uruguaya");
	    
	    equipos.put("BE", "Belgrano");
	    equipos.put("BC", "Boca");
	    equipos.put("L", "Lanús");
	    equipos.put("RA", "Racing");
	    equipos.put("RV", "River");
	    equipos.put("SL", "San Lorenzo");
	%>
    <table class="table table-striped">
       	<colgroup>
       		<col width="15%" />
       	</colgroup>
    	<tr>
             <th align="right">Apellido:</th>
             <td><%= request.getParameter("apellido") %></td>
        </tr>
		<tr>
             <th align="right">Nombre:</th>
             <td><%= request.getParameter("nombre") %></td>
		</tr>
		<tr>
             <th align="right">Clave:</th>
             <td><%= request.getParameter("clave") %></td>
		</tr>
		<tr>
             <th align="right">Confirmaci&oacute;n Clave:</th>
             <td><%= request.getParameter("confirmar_clave") %></td>
		</tr>
		<tr>
             <th align="right">E-mail:</th>
             <td><%= request.getParameter("email") %></td>
		</tr>
		<tr>
             <th align="right">Sexo:</th>
             <td><%= (request.getParameter("sexo").equals("F") ? "Femenino" : "Masculino") %></td>
		</tr>
		<tr>
             <th align="right">Fecha de Nacimiento:</th>
             <td><%= formatter.format(fecha_nacimiento) %></td>
		</tr>
		<tr>
             <th align="right">Nacionalidad:</th>
             <td><%= (request.getParameter("otra_nacionalidad") == null || request.getParameter("otra_nacionalidad").equals("") ? nacionalidades.get(request.getParameter("nacionalidad")) : request.getParameter("otra_nacionalidad")) %></td>
		</tr>
		<tr>
             <th align="right" valign="top">Equipo Favorito:</th>
             <td>
             <% 
             if(request.getParameterValues("equipo") != null) {
	             String eq[] = request.getParameterValues("equipo");
	             for(int i = 0, l = eq.length; i < l; i ++) {
	            	 out.println(equipos.get(eq[i]) + "; ");
	             }
             }
             else {
            	 out.println("Ninguno");
             }
             %>
             </td>
		</tr>
		<tr>
            <th align="right" valign="top">Hobbies:</th>
            <td>
            <% 
            String h[] = request.getParameterValues("hobbies");
            for(int i = 0, l = h.length; i < l; i ++) {
           	 	out.println(h[i] + "; ");
            }
            %>
            </td>
		</tr>
		<tr>
            <th align="right" valign="top">Otras Actividades:</th>
            <td><%= request.getParameter("actividades") %></td>
		</tr>
	</table>
	<br/>
	<a href="./ejemplo5.jsp">Volver</a>
	<%
	}
	%>
</body>
</html>