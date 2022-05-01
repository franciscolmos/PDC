<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
String pos,fecha, descripcion, prioridad, email;
if(request.getParameterMap().size() > 0) {
	pos = request.getParameter("pos");
	fecha = request.getParameter("fecha");
	prioridad = request.getParameter("prioridad");
	descripcion = request.getParameter("descripcion");
	email = request.getParameter("email") == null ? "" : request.getParameter("email");
%>
 <tr>
 	<td><%= pos %></td>
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