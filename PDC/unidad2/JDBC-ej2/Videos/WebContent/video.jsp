<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<p><b><%= request.getParameter("titulo") %></b><i><%= request.getParameter("cantante") %></i></p>
<iframe width="560" height="315" src="<%= request.getParameter("link_video") %>"></iframe>
<br>
<a href="#" onclick="jVideos.volver()">Volver</a>
