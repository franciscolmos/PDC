<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "ar.edu.ubp.pdc.classes.Juego, java.util.ArrayList, java.util.Arrays, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="./css/theme.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
<title>Ejercicio 1</title>
</head>
<body>
<form id="form" action="index.jsp" method="post">
<% 
	Juego p = Juego.getJuego();
	ArrayList<String> users = new ArrayList<String>();
	ArrayList<String> pcs = new ArrayList<String>();
	ArrayList<String> results = new ArrayList<String>();
	
	int wins = 0, lose = 0;
	if(request.getParameter("wins") != null) wins = Integer.parseInt(request.getParameter("wins"));
	if(request.getParameter("lose") != null) lose = Integer.parseInt(request.getParameter("lose"));
	if(request.getParameterValues("users") != null) users.addAll(Arrays.asList(request.getParameterValues("users")));
	if(request.getParameterValues("pcs") != null) pcs.addAll(Arrays.asList(request.getParameterValues("pcs")));
	if(request.getParameterValues("results") != null) results.addAll(Arrays.asList(request.getParameterValues("results")));
	if(request.getParameterMap().size() > 0){
		int e = Integer.parseInt(request.getParameter("ppt"));
		p.jugar(e);
		users.add(p.getEleccionUsuario());
		pcs.add(p.getEleccionDispositivo());
		results.add(p.getResultado() == 1 ? "Ganaste" : p.getResultado() == 0 ? "Empate" : "Perdiste");
		if(p.getResultado() == 1) ++wins;
		if(p.getResultado() == -1) ++lose;
	}
%>
	<h3>Te invito a jugar... :)</h3>
	<% if(wins != 3 && lose != 3) {%>
		<label>Selecciona una Opcion: </label>
		<select name="ppt" id="ippt" required>
			<option value=""></option>
			<option value="0"> Piedra </option>
			<option value="1"> Papel </option>
			<option value="2"> Tijera </option>
		</select>
		<button type="submit" id="play">Jugar</button>
		<br>
	<% } %>
	<% if(!users.isEmpty()){%>
	<h3>Resultados</h3>
	<table class="table">
		<tr>
			<th>Usuario</th>
			<th>Computadora</th>
			<th>Resultado</th>
		</tr>
		<% for(int i = 0; i < users.size(); ++i) { %>
		<tr class="<%= results.get(i).equals("Ganaste") ? "win": results.get(i).equals("Perdiste") ? "lose": "tie"%>">
			<td>
				<%= users.get(i) %>
				<input type="hidden" name="users" value="<%= users.get(i) %>">
			</td>
			<td>
				<%= pcs.get(i) %>
				<input type="hidden" name="pcs" value="<%= pcs.get(i) %>">
			</td>
			<td>
				<%= results.get(i) %>
				<input type="hidden" name="results" value="<%= results.get(i) %>">
			</td>
		</tr>
		<% } %>
	</table>
	<% } %>
	<input type="hidden" name="wins" id="iw" value="<%= wins %>">
	<input type="hidden" name="lose" id="il" value="<%= lose %>">
	<% if(wins == 3 || lose == 3){ %>
	<div>
		<br>
		<% if(wins == 3){ %>
		<label class="msg"> Felicitaciones, Ganaste! :D </label>
		<%} if(lose == 3) { %>
		<label class="msg"> Perdiste :( </label>
		
		<% } %>
		<br>
		<br>
		<button type="button" id="again">Volver a Jugar</button>
	</div>
	<% } %>
</form>
</body>
</html>