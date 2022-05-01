<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%
	if(request.getParameterMap().size() > 0) {
%>
<p style="text-align: right;"> Conectado como: <%= request.getParameter("nombre") %> (<%= request.getParameter("rol") %>) <a href="#"> Desconectar </a> </p>
<% 
}
%>