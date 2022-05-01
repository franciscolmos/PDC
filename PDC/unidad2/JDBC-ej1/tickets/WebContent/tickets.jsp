<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedList,beans.ticket.ej1.TicketBean" %>
<!DOCTYPE html>
<html>
<br>
<div>
<table class="table">
<colgroup>
	<col width="auto"/>
	<col width="auto"/>
	<col width="auto"/>
	<col width="auto"/>
	<col width="300px"/>
</colgroup>
<thead>
<tr>
	<th>Ticket</th>
	<th>Fecha</th>
	<th>Solicitante</th>
	<th>Asunto</th>
	<th>Texto</th>
</tr>
</thead>
<tbody>
<%if(request.getParameterMap().size() > 0){ 
	LinkedList<TicketBean> tickets = (LinkedList<TicketBean>) request.getAttribute("tickets");
	for(int i=0; i<tickets.size(); i++){
%>
<tr>
	<td><%= tickets.get(i).getTicket() %></td>
	<td><%= tickets.get(i).getFecha() %></td>
	<td><%= tickets.get(i).getSolicitante() %></td>
	<td><%= tickets.get(i).getAsunto() %></td>
	<td><%= tickets.get(i).getTexto() %></td>
</tr>
<% }
} %>
</tbody>
</table>
</div>
</html>