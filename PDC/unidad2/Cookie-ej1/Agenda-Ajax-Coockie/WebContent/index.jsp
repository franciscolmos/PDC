<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Date, ej1.sessions.tarea.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda AJAX</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/styles.css">

<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/agenda.js"></script>
</head>
<body>
<h1>Mi Agenda</h1>
<div id="message"></div>
<form id="form" action="javascript:void(null)" method="post" onsubmit="send(event)">
	<div class="row">
		<div class="col form-group">
			<label for="ifecha">Fecha</label>
			<input class="form-control" type="date" id="ifecha" name="fecha" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" required>
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
			<label for="idescripcion">Descripción</label>
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
	<input type="hidden" name="pos" id="ipos" value="">
	<button type="submit" class="btn btn-primary" id="agendar">Agendar</button>
	<button type="reset" class="btn btn-warning" id="limpiar">Limpiar</button>
	<%
    String attrName;
    Enumeration<String> attrNames = session.getAttributeNames();
	List<String> p = Collections.list(session.getAttributeNames());
	%>
	<table id="result" class="table" <%= attrNames.hasMoreElements() ? "": "hidden" %>>
		<thead>
			<tr>
				<td>#</td>
				<td>Fecha</td>
				<td>Descripción</td>
				<td>Prioridad</td>
				<td>Notificar a</td>
			</tr>
		</thead>
		<tbody>
		<%            
        while (attrNames.hasMoreElements()) {
        	attrName = attrNames.nextElement();
        	Tarea t = (Tarea) session.getAttribute(attrName);
        %>
		 <tr>
		 	<td><%= attrName %></td>
		 	<td><%= t.getFecha() %></td>
			<td><%= t.getDesc() %></td>
			<td><%= t.getPrio() %></td>
			<td><%= t.getEmail() %></td>
			<td>
				<a href="#" onclick="check(this)"> Descartar</a>
				<input type="hidden" name="eliminado" value="N">
			</td>
		 </tr>
        <%
        }
		%>
		</tbody>
	</table>
</form>
</body>
</html>