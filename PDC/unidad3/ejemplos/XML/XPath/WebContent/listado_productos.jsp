<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList,ar.edu.ubp.pdc.beans.ProductoBean" %>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listado de Productos Faltantes</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css" />
</head>
<body>
	<h3>Productos Faltantes</h3>
	<table>
	    <colgroup>
	        <col width="300px"/>
	    </colgroup>
	    <thead>
	         <tr>
	             <td>Nombre</td>
	         </tr>
	    </thead>
	    <tbody>
	<%
	LinkedList<ProductoBean> productos = (LinkedList<ProductoBean>)request.getAttribute("listado");
	for(ProductoBean p : productos) {
	%>
			<tr>
				<td><%= p.getNomProducto() %></td>
			</tr>
	<%	
	}
	%>	
		</tbody>
	</table>
	<br/>
	<a href="index.html" target="_self">Volver al index principal</a>
</body>
</html>