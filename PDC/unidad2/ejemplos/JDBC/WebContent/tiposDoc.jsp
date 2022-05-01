<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList,ar.edu.ubp.pdc.beans.TipoDocumentoBean" %>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Tipos de Documentos</title>
   	<link type="text/css" rel="stylesheet" href="./css/style.css" />
   	<script type="text/javascript" src="./js/jquery.js"></script>
   	<script type="text/javascript" src="./js/utils.js"></script>
   	<script type="text/javascript" src="./js/tiposDoc.js"></script>
</head>
<body>
	<div id="aux"></div>
	<div id="response"></div>
	<div id="main">
	<h3>Tipos de Documentos</h3>
	<table>
		<tr>
			<th>C&oacute;digo</th>
			<th>Nombre</th>
			<th>M&aacute;scara</th>
			<th>Num&eacute;rico</th>
			<th>Tipo de Persona</th>
			<th></th>
		</tr>	
		<%
		LinkedList<TipoDocumentoBean> tiposDoc = (LinkedList<TipoDocumentoBean>)request.getAttribute("tiposDoc");
		for(TipoDocumentoBean td : tiposDoc) {
		%>
		<tr>
			<td>
				<%= td.getCodTipoDocumento() %>
				<input type="hidden" name="cod_tipo_documento" value="<%= td.getCodTipoDocumento() %>"/>
			</td>
			<td><%= td.getTipoDocumento() %></td>
			<td><%= td.getMascara() %></td>
			<td><%= td.getNumerico().equals("S") ? "Sí" : "No" %></td>
			<td><%= td.getTipoPersona().equals("F") ? "F&iacute;sica" : "Jur&iacute;dica" %></td>
			<td>
				<a name="editar" href="#">Editar</a>
				<a name="eliminar" href="#">Eliminar</a>
			</td>
		</tr>
		<%	
		}
		%>	
	</table>
	<br/>
	<a id="nuevo" href="#">Nuevo</a>
	</div>
</body>
</html>