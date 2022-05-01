<%@page import="java.util.concurrent.ThreadLocalRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejercicio 2</title>
<link rel="stylesheet" type="text/css" href="./css/theme.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
<%
List<List<String>> equipos = new ArrayList<List<String>>();
HashMap<String, String> res = new HashMap<String, String>();
int correct = 0;
int incorrect = 0;

if(equipos.size() == 0) {
	equipos.add(Arrays.asList("EQUIPO A", "EQUIPO B"));
	equipos.add(Arrays.asList("EQUIPO C", "EQUIPO D"));
	equipos.add(Arrays.asList("EQUIPO E", "EQUIPO F"));
	equipos.add(Arrays.asList("EQUIPO G", "EQUIPO H"));
	equipos.add(Arrays.asList("EQUIPO I", "EQUIPO J"));
	equipos.add(Arrays.asList("EQUIPO K", "EQUIPO L"));
	equipos.add(Arrays.asList("EQUIPO M", "EQUIPO N"));
	equipos.add(Arrays.asList("EQUIPO O", "EQUIPO P"));
	equipos.add(Arrays.asList("EQUIPO Q", "EQUIPO R"));
	equipos.add(Arrays.asList("EQUIPO S", "EQUIPO T"));
}
// res.containsKey(Integer.toString(i)) ? res.get(Integer.toString(i)) : ""
for(int i = 0; i < equipos.size(); ++i){
	if(request.getParameter(Integer.toString(i)) != null){
		res.put(Integer.toString(i), request.getParameter(Integer.toString(i)));
	}
}
%>
</head>
<body>
	<form action="index.jsp" method="post">
		<div>
			<h2>Prode</h2>
			<table>
				<tr>
					<th style="text-align: left;">Equipo</th>
					<th>L</th>
					<th>E</th>
					<th>V</th>
					<th style="text-align: right;">Equipo</th>
				</tr>
				<% for(int i = 0; i < equipos.size(); ++i) { 
					String value = res.containsKey(Integer.toString(i)) ? res.get(Integer.toString(i)) : "";
				%>
				<tr class="team">
					<td><%= equipos.get(i).get(0) %></td>
					<td><input type="checkbox" name="<%= i %>" value="local" <%= value.equals("local") ? "checked" : ""%>></td>
					<td><input type="checkbox" name="<%= i %>" value="tie" <%= value.equals("tie") ? "checked" : "" %>></td>
					<td><input type="checkbox" name="<%= i %>" value="visitor" <%= value.equals("visitor") ? "checked" : "" %>></td>
					<td><%= equipos.get(i).get(1) %></td>
				</tr>
				<%}%>
			</table>
			<br>
			<%if (res.size()==0) {%>
			<input type="submit" id="result" value="Resultados">
			<%} else {%>
			<input type="button" onclick="window.location.replace('index.jsp')" value="Volver a Jugar">
			<%}%>
		</div>
	</form>
	<div>
		<%if (res.size()>0) {%>
		<h2>Resultados</h2>
		<table>
			<tr>
				<th style="text-align: left;">Equipo</th>
				<th>L</th>
				<th>V</th>
				<th style="text-align: right;">Equipo</th>
			</tr>
			<% 
				for(int i = 0; i < equipos.size(); ++i) {
					int v = ThreadLocalRandom.current().nextInt(0, 6);
					int l = ThreadLocalRandom.current().nextInt(0, 6);
					String result = v > l ? "local" : v < l ? "visitor" : "tie";
					String theme = res.containsKey(Integer.toString(i)) ? res.get(Integer.toString(i)).equals(result) ? "win" : "lose" : "";
					if (theme.equals("win")) correct++;
					else incorrect++;				
			%>
			<tr class="<%= theme %>">
				<td><%= equipos.get(i).get(0) %></td>
				<td><%= v %></td>
				<td><%= l %></td>
				<td><%= equipos.get(i).get(1) %></td>
			</tr>
			<%}%>
		</table>
		<br>
		<%}%>
	</div>
	<div>
		<%if (res.size()>0) {%>
			<h2>Estadisticas</h2>
			<label>Resultados Correctos: <%= correct %></label>
			<br><br>
			<label>Resultados Incorrectos: <%= incorrect %></label>
		<%}%>
	</div>
	<div>
	</div>
</body>
</html>