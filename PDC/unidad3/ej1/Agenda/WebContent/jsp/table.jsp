<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%
String fecha, descripcion, prioridad, email;
if(request.getParameterMap().size() > 0) {
	fecha = request.getParameter("fecha");
	prioridad = request.getParameter("prioridad");
	descripcion = request.getParameter("descripcion");
	email = request.getParameter("email") == null ? "" : request.getParameter("email");
%>
 <tr>
 	<td id="count"></td>
 	<td><%= fecha %></td>
	<td><%= descripcion %></td>
	<td><%= prioridad %></td>
	<td><%= email %></td>
	<td>
		<a href="#" onclick="check(this)"> Descartar</a>
		<input type="hidden" name="eliminado" value="N">
	</td>
 </tr>
<% } %>