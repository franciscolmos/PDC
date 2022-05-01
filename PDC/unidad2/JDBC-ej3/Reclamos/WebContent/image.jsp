<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% if(request.getAttribute("existe").equals("S")){ %>
	<img id="ok" src="./im/icon_ok.png">
<% } else {%>
	<img id="wrong" src="./im/icon_wrong.png">
<% } %>
