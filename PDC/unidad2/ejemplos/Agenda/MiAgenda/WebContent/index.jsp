<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Date,java.util.HashMap,java.util.ArrayList,java.util.Arrays" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Agenda</title>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/styles.css">
	
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/agenda.js"></script>	
</head>
<body class="container">
<%
SimpleDateFormat fingles = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat fesp = new SimpleDateFormat("dd-MM-yyyy");
String[] fecha, descripcion, prioridad, eliminado;
String email, eliminadoClass;
ArrayList<String> hiemail = new ArrayList<String>();

HashMap<String,String> prioridades = new HashMap<String,String>();
prioridades.put("A", "Alta");
prioridades.put("M", "Media");
prioridades.put("B", "Baja");
%>
<h1>Mi Agenda</h1>
<form id="form" action="index.jsp" method="post">
	<div class="row">
		<div class="col form-group">
			<label for="ifecha">Fecha</label>
			<input class="form-control" type="date" id="ifecha" name="fecha" value="<%= fingles.format(new Date()) %>" required>
		</div>
		<div class="col form-group">
			<label for="iprioridad">Prioridad</label>
			<select class="form-control" id="iprioridad" name="prioridad" required>
				<option value="A">Alta</option>
				<option value="M" selected>Media</option>
				<option value="B">Baja</option>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="col form-group">
			<label for="idescripcion">Descripci&oacute;n</label>
			<textarea class="form-control" id="idescripcion" name="descripcion" rows="5" cols="10" placeholder="Ingrese la descripci&oacute;n del evento" required></textarea>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<label for="inotificarS">Notificar</label>
			<div class="form-group">
				<label><input type="radio" id="inotificarS" name="notificar" value="S" checked>Si</label>
				<label><input type="radio" id="inotificarN" name="notificar" value="N">No</label>
			</div> 
		</div>
		<div class="col form-group">
			<label for="">E-mail a notificar</label>
			<input class="form-control" type="email" id="iemail" name="email" placeholder="Ingrese el e-mail a notificar" required>
		</div>
	</div>
	<div class="row">
		<input type="hidden" name="eliminado" value="N">
		<button type="submit" class="btn btn-primary" id="agendar">Agendar</button>
		<button type="reset" class="btn btn-default" id="limpiar">Limpiar</button>
	</div>
	<%
	if(request.getParameterMap().size() > 0) {
		fecha = request.getParameterValues("fecha");
		prioridad = request.getParameterValues("prioridad");
		descripcion = request.getParameterValues("descripcion");
		email = request.getParameter("email") == null ? "" : request.getParameter("email");
		if(request.getParameterValues("hiemail") != null) {
			hiemail.addAll(Arrays.asList(request.getParameterValues("hiemail")));
		}
		hiemail.add(0, email);
		eliminado = request.getParameterValues("eliminado");
	%>
	<br>
	<table class="table">
		<tr>
			<th>#</th>
			<th>Fecha</th>
			<th>Descripci&oacute;n</th>
			<th>Prioridad</th>
			<th>Notificar a</th>
			<th></th>
		</tr>
		<%
		for(int i = 0; i < fecha.length; i++) {
			eliminadoClass = eliminado[i].equals("S") ? "eliminado" : "";
		%>
		<tr>
			<td class="<%= eliminadoClass %>"><%= i + 1 %></td>
			<td class="<%= eliminadoClass %>">
				<%= fesp.format(fingles.parse(fecha[i])) %>
				<input type="hidden" name="fecha" value="<%= fecha[i] %>">
			</td>
			<td class="<%= eliminadoClass %>">
				<%= descripcion[i] %>
				<input type="hidden" name="descripcion" value="<%= descripcion[i] %>">
			</td>
			<td class="<%= eliminadoClass %>">
				<%= prioridades.get(prioridad[i]) %>
				<input type="hidden" name="prioridad" value="<%= prioridad[i] %>">
			</td>
			<td class="<%= eliminadoClass %>">
				<%= hiemail.get(i) %>
				<input type="hidden" name="hiemail" value="<%= hiemail.get(i) %>">
			</td>
			<td>
				<a href="#"><%= eliminado[i].equals("S") ? "Recuperar" : "Descartar" %></a>
				<input type="hidden" name="eliminado" value="<%= eliminado[i] %>">
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<%	
	}	
	%>
</form>
</body>
</html>