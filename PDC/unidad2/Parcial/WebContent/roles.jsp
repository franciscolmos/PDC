<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedList, ubp.pdc.beans.RolBean"%>

<% 
	LinkedList<RolBean> roles = (LinkedList<RolBean>) request.getAttribute("roles");
	for(int i=0; i<roles.size(); i++){
%>
	<option value="<%= roles.get(i).getNro_rol() %>" <%= roles.get(i).getNro_rol().equals("-1") ? "checked" : "" %>><%= roles.get(i).getNom_rol() %></option>
<% } %>