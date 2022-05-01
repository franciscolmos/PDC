<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
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
   
String [] resEq = request.getParameterValues("resultado");

List<List<Short>> resultados = new ArrayList<List<Short>>();
for(i = 0; i < equipos.size(); i++) {
	resultados.add(Arrays.asList(Double.class.cast(Math.random() * 6).shortValue(), Double.class.cast(Math.random() * 6).shortValue()));	
}
%>	
<div id="datos">
	<h3>Resultados</h3>
	<table>
		<colgroup>
			<col width="100px"/>
			<col width="30px"/>
			<col width="30px"/>
			<col width="100px"/>
		</colgroup>
		<thead>
			<tr>
				<th class="al">Equipo</th>
				<th>L</th>
				<th>V</th>
				<th class="ar">Equipo</th>
			</tr>		
		</thead>
		<tbody>
		<%
		List<String> equipo;
		int correctos = 0, incorrectos = 0;
		
		for(i = 0; i < equipos.size(); i++) {
			equipo = equipos.get(i);	
			
			if(resultados.get(i).get(0) == resultados.get(i).get(1) && resEq[i].equals("E") ||
			   resultados.get(i).get(0)  > resultados.get(i).get(1) && resEq[i].equals("L") ||
			   resultados.get(i).get(0)  < resultados.get(i).get(1) && resEq[i].equals("V")) {
				correctos ++;
				out.println("<tr class=\"correcto\">");
			}
			else {
				incorrectos ++;
				out.println("<tr class=\"incorrecto\">");
			}					
			out.println("<td class=\"al\">" + equipo.get(0) + "</td>");
			out.println("<td class=\"ac\">" + resultados.get(i).get(0) + "</td>");
			out.println("<td class=\"ac\">" + resultados.get(i).get(1) + "</td>");
			out.println("<td class=\"ar\">" + equipo.get(1) + "</td>");
			out.println("</tr>");
		}		
		%>
		</tbody>		
	</table>			
</div>
<div id="estadisticas">
<h3>Estad&iacute;sticas</h3>
<p>Resultados Correctos: <b><%= correctos %></b></p>
<p>Resultados Incorrectos: <b><%= incorrectos %></b></p>
</div>


   