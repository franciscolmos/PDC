<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<h3>Nuevo tipo de documento</h3>
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
					<input type="text" name="cod_tipo_documento" value="" maxlength="6" size="8" required>
					<input type="hidden" name="operacion" value="I">
				</td>
			</tr>
			<tr>
				<th width="20%" class="ar">Nombre:</th>
				<td colspan="5">
					<input type="text" name="tipo_documento" value="" maxlength="100" size="40" required>
				</td>
			</tr>
			<tr>
				<th width="20%" class="ar">M&aacute;scara:</th>
				<td>
					<input type="text" name="mascara" value="" maxlength="50" size="40" required>
				</td>
				<th width="20%" class="ar">Num&eacute;rico:</th>
				<td>
					<input type="checkbox" name="numerico">
				</td>
				<th width="20%" class="ar">Tipo Persona:</th>
				<td>
					<select name="tipo_persona" required>
						<option value="">Debe seleccionar...</option>
						<option value="F">F&iacute;sica</option>
						<option value="J">Jur&iacute;dica</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<button type="submit">Aceptar</button>&nbsp;&nbsp;<a id="cancelar" href="#">Cancelar</a>
</form>