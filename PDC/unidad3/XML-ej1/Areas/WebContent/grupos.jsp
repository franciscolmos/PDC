<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedList, bean.GrupoBean" %>
<!DOCTYPE html>
<html>
<br>
<table>
<thead>
<tr>
	<th>Nombre</th>
	<th>Exclusivo</th>
	<th>Vigente</th>
	<th></th>
	<th></th>
</tr>
</thead>
<tbody>
<% 
LinkedList<GrupoBean> grupos = (LinkedList<GrupoBean>) request.getAttribute("grupos");
int i = 0;
for(i=0; i<grupos.size(); i++){
%>
<tr class=<%= i % 2 == 0 ? "even" : "odd" %>>
	<td>
		<input type="text" class="form-control" name="nom_grupo" id="inom_grupo" value="<%= grupos.get(i).getNom_grupo() %>">
	</td>
	<td>
		<select class="form-control" name="exclusivo" id="iexclusivo">
			<option value="S" <%= grupos.get(i).getExclusivo().equals("S") ? "selected" : "" %> > S </option>
			<option value="N" <%= grupos.get(i).getExclusivo().equals("N") ? "selected" : "" %>> N </option>
		</select>
	</td>
	<td>
		<select class="form-control" name="vigente" id="ivigente">
			<option value="S" <%= grupos.get(i).getVigente().equals("S") ? "selected" : "" %> > S </option>
			<option value="N" <%= grupos.get(i).getVigente().equals("N") ? "selected" : "" %>> N </option>
		</select>
	</td>
	<td><a href="#" onclick="JGrupo.add(this)">Editar</a></td>
	<td>
		<a href="#" onclick="JGrupo.reset(this)">
			Cancelar
			<input type="hidden" name="hnro_grupo" id="ihnro_grupo" value="<%= grupos.get(i).getNro_grupo() %>" >
			<input type="hidden" name="hnom_grupo" id="ihnom_grupo" value="<%= grupos.get(i).getNom_grupo() %>" >
			<input type="hidden" name="hexclusivo" id="ihexclusivo" value="<%= grupos.get(i).getExclusivo() %>" >
			<input type="hidden" name="hvigente" id="ihvigente" value="<%= grupos.get(i).getVigente() %>" >
		</a>
	</td>
</tr>
<% } %>
<tr class=<%= i % 2 == 0 ? "even" : "odd" %>>
	<td>
		<input type="text" class="form-control" name="nom_grupo" id="inom_grupo">
	</td>
	<td>
		<select class="form-control" name="exclusivo" id="iexclusivo">
			<option value="S"> S </option>
			<option value="N"> N </option>
		</select>
	</td>
	<td>
		<select class="form-control" name="vigente" id="ivigente">
			<option value="S"> S </option>
			<option value="N"> N </option>
		</select>
	</td>
	<td>
		<a href="#" onclick="JGrupo.add(this)">
			Guardar
		</a>
	</td>
	<td><a href="#" onclick="JGrupo.cancel(this)">Cancelar</a></td>
</tr>
</tbody>
</table>
</html>