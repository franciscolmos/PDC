<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<tr>
	<td>
		<%= request.getParameter("cookieName") %>
		<input type="hidden" name="hCookieName" value="<%= request.getParameter("cookieName")  %>"> 
	</td>
	<td><%= request.getParameter("cookieValue") %></td>
	<td><a href="#" onclick="jForm.remove(this)">Eliminar</a></td>
</tr>
