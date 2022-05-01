<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Random"%>
<%
if(request.getParameterMap().size() > 0) {
	String values = request.getParameter("index");
	Random r = new Random();
	int loc = r.nextInt(Integer.parseInt(values) - 1);
%>
<%= loc %>
<%}%>