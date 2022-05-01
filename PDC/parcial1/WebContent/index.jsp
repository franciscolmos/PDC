<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ct" uri="/WEB-INF/tld/ctags.tld" %>

<%
String nums = Long.toString((long)(Math.random() * 100000000L));
String pnums = request.getParameter("nums");
String pnro = request.getParameter("nro");
Integer lost = 0;
Integer won = 0;
StringBuilder placeholder = new StringBuilder("        ");
String[] message = {"A", "HOR", "CA", "DO"};

if(pnro != null) {
	placeholder = new StringBuilder(request.getParameter("placeholder"));
	lost = Integer.parseInt(request.getParameter("lost"));
	won = Integer.parseInt(request.getParameter("won"));
	boolean found = false;
	nums = pnums;
	for (int i=0; i<8; i++) {
		char n = pnro.toCharArray()[0];
		if(nums.charAt(i) == n && placeholder.charAt(i) == ' ') {
			placeholder.setCharAt(i, n);
			found = true;
			won++;
		}
	}
	
	if (!found) {
		lost++;
	}
}


%>

<html>
  <head>
    <title>Parcial 1</title>
    
    <link rel="stylesheet" href="./css/index.css">
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script type="text/javascript" src="./js/index.js"></script>
  </head>
  <body>
  	<form action="index.jsp" method="post" id="gameform">
	  	<input type="hidden" name="nums" value="<%= nums %>">
	  	<input type="hidden" name="placeholder" id="placeholder" value="<%= placeholder %>">
	  	<input type="hidden" name="lost" value="<%= lost %>">
	  	<input type="hidden" name="won" value="<%= won %>">
	  	<div class="container">
	  		<h1 class="title">Juguemos al Ahorcado</h1>
	  		<table>
	  			<tr>
	  				<td><%= placeholder.charAt(0) %></td>
	  				<td><%= placeholder.charAt(1) %></td>
	  				<td><%= placeholder.charAt(2) %></td>
	  				<td><%= placeholder.charAt(3) %></td>
	  				<td><%= placeholder.charAt(4) %></td>
	  				<td><%= placeholder.charAt(5) %></td>
	  				<td><%= placeholder.charAt(6) %></td>
	  				<td><%= placeholder.charAt(7) %></td>
	  			</tr>
	  		</table>
	  		<table>
	  			<tr>
  				<%
  					for (int i=0; i<lost; i++) {
  						out.print("<td>"+ message[i] +"</td>");
  					}
  				%>
	  			</tr>
	  		</table>
	  		<%if (lost >= message.length ) { %>
	  		<div class="message lost">Perdiste! :(</div>
	  		<%} %>
	  		
	  		<%if (won >= placeholder.length() ) { %>
	  		<div class="message won">Ganaste! :)</div>
	  		<%} %>
	  		
	  		<%if (lost < message.length && won < placeholder.length() ) { %>
	  		<ct:number min="0" max="9" name="nro" id="nro" btn="Probar Suerte"/>
	  		<%} %>
	  		
	  		<%if (lost >= message.length || won >= placeholder.length() ) { %>
	  		<button onclick="location.href = index.jsp">Volver a jugar</button>
	  		<%} %>
	  	</div>
  	</form>
  </body>
</html>
