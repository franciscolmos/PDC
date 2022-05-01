<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ar.edu.ubp.pdc.beans.TipoDocumentoBean" %>    
<%
TipoDocumentoBean tipoDoc = (TipoDocumentoBean) request.getAttribute("tipo_documento");
%>      
<h3>Editar tipo de documento</h3>
<form id="form" action="javascript:void(null)" method="post">
	<table>
		<thead>
			<tr>
				<th colspan="6" class="al">Datos del Tipo de Documento</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="20%" class="ar">C&oacute;digo:</th>
				<td colspan="5">
					<%= tipoDoc.getCodTipoDocumento() %>
					<input type="hidden" name="cod_tipo_documento" value="<%= tipoDoc.getCodTipoDocumento() %>">
					<input type="hidden" name="operacion" value="U">
				</td>
			</tr>
			<tr>
				<th width="20%" class="ar">Nombre:</th>
				<td colspan="5">
					<input type="text" name="tipo_documento" value="<%= tipoDoc.getTipoDocumento() %>" maxlength="100" size="40" required>
				</td>
			</tr>
			<tr>
				<th width="20%" class="ar">M&aacute;scara:</th>
				<td>
					<input type="text" name="mascara" value="<%= tipoDoc.getMascara() %>" maxlength="50" size="40" required>
				</td>
				<th width="20%" class="ar">Num&eacute;rico:</th>
				<td>
					<input type="checkbox" name="numerico" <%= tipoDoc.getNumerico().equals("S") ? "checked" : "" %>>
				</td>
				<th width="20%" class="ar">Tipo Persona:</th>
				<td>
					<select name="tipo_persona" required>
						<option value="">Debe seleccionar...</option>
						<option value="F" <%= tipoDoc.getTipoPersona().equals("F") ? "selected" : "" %>>F&iacute;sica</option>
						<option value="J" <%= tipoDoc.getTipoPersona().equals("J") ? "selected" : "" %>>Jur&iacute;dica</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<button type="submit">Aceptar</button>&nbsp;&nbsp;<a id="cancelar" href="#">Cancelar</a>
</form>