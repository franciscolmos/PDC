<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedList,ubp.pdc.beans.PersonaBean" %>
<!DOCTYPE html>
<html>
<br>
<div>
<table class="table table-striped">
<thead>
<tr>
	<th> # </th>
	<th> Apellido, Nombre </th>
	<th> Documento </th>
	<th> Rol </th>
	<th> </th>
</tr>
</thead>
<tbody>
<%if(request.getParameterMap().size() > 0){ 
	LinkedList<PersonaBean> personas = (LinkedList<PersonaBean>) request.getAttribute("personas");
	for(int i=0; i<personas.size(); i++){
%>
<tr>
	<td><%= i+1 %></td>
	<td id="nombre"><%= personas.get(i).getNombre() %></td>
	<td id="doc"><%= personas.get(i).getDocumento() %></td>
	<td id="rol"><%= personas.get(i).getRol() %></td>
	<td>
		<a href="#" onclick="JRol.conectar(this)">Conectar</a>
		<input type="hidden" id="usuario" value="<%=personas.get(i).getUsuario()%>">
		<input type="hidden" id="clave" value="<%=personas.get(i).getContra()%>">
	</td>
</tr>
<% }
} %>
</tbody>
</table>
</div>
</html>