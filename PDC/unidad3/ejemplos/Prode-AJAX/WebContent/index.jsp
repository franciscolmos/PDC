<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Prode</title>

	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/utils.js"></script>
	<script type="text/javascript" src="./js/prode.js"></script>
</head>
<body>
<%
	int i = 0;
	
	List<List<String>> equipos = new ArrayList<List<String>>();
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
%>
	<form id="form" action="javascript:void(null)" method="post">
		<div id="message"></div>
		<div id="prode">
			<h3>Prode</h3>
			<table>
				<colgroup>
					<col width="100px"/>
					<col width="30px"/>
					<col width="30px"/>
					<col width="30px"/>
					<col width="100px"/>
				</colgroup>
				<thead>
					<tr>
						<th class="al">Equipo</th>
						<th>L</th>
						<th>E</th>
						<th>V</th>
						<th class="ar">Equipo</th>
					</tr>		
				</thead>
				<tbody>
				<%
				for(List<String> equipo : equipos) {
					out.println("<tr id=\"" + equipo.hashCode() + "\">");
					out.println("<td class=\"al\">" + equipo.get(0) + "</td>");
					out.println("<td class=\"ac\"><input type=\"checkbox\" id=\"iresultado-" + equipo.hashCode() + "-L\" name=\"resultado\" value=\"L\" /></td>");
					out.println("<td class=\"ac\"><input type=\"checkbox\" id=\"iresultado-" + equipo.hashCode() + "-E\" name=\"resultado\" value=\"E\" /></td>");
					out.println("<td class=\"ac\"><input type=\"checkbox\" id=\"iresultado-" + equipo.hashCode() + "-V\" name=\"resultado\" value=\"V\" /></td>");
					out.println("<td class=\"ar\">" + equipo.get(1) + "</td>");
					out.println("</tr>");
					i++;
				}				
				%>
				</tbody>
			</table>
			<div id="botones">
				<br/><br/>
				<input id="bresultados" type="button" name="resultados-prode" value="Resultados"/>
				<input id="bvolver" type="button" name="volver" value="Volver a Jugar"/>
			</div>
		</div>		
		<div id="resultados">
		</div>
	</form>
</body>
</html>