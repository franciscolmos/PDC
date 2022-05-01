<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejercicio 3</title>
<link rel="stylesheet" type="text/css" href="./css/theme.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
<%
String disabled = "";
if(request.getParameter("start") != null) disabled = request.getParameter("start");
int number = 0;
ArrayList<String> numbers = new ArrayList<String>();

if (!disabled.equals("")) {
	number = Double.class.cast(Math.random() * 45).intValue();
	if(request.getParameterValues("numbers") != null) numbers.addAll(Arrays.asList(request.getParameterValues("numbers")));
	while(numbers.contains(Integer.toString(number))){
		number = Double.class.cast(Math.random() * 45).intValue();
	}
	numbers.add(Integer.toString(number));
}
if(numbers.size()==6){disabled="";}
%>
<form id="quini" action="" method="post">
	<h2>Quini 6</h2>
	<%if (numbers.size()>0) { %>
	<table>
		 <tr>
		 	<% for(int i = 0; i < numbers.size(); ++i) { %>
			 	<td>
			 		<%= numbers.get(i) %>
			 		<%if(numbers.size()<6) { %>
			 		<input type="hidden" name="numbers" value="<%= numbers.get(i) %>">
			 		<% } %>
			 	</td>
		 	<% } %>
		 </tr>
	</table>
	<%if(numbers.size()<6) {%>
		<label><%= number %></label>
	<% }} %>
	<input type="hidden" name="start" value="disabled">
	<br>
	<button type="submit" id="istart" <%= disabled.equals("disabled") ? disabled : disabled%>>Iniciar Sorteo</button>
</form>
</body>
</html>