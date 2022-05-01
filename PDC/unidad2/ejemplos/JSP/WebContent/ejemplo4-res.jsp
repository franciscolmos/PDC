<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ page import="java.util.HashMap, java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Ejemplo 4</title>
    <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap-theme.min.css">
    <script type="text/javascript" src="./bootstrap.3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%
	HashMap<String,String> nacionalidades = new HashMap<String,String>();
	HashMap<String,String> equipos        = new HashMap<String,String>();

	SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	Date fechaNacimiento = parser.parse(request.getParameter("fecha_nacimiento"));
	
	nacionalidades.put("AR", "Argentina");
    nacionalidades.put("BO", "Boliviana");
    nacionalidades.put("BR", "Brasileña");
    nacionalidades.put("CH", "Chilena");
    nacionalidades.put("PY", "Paraguaya");
    nacionalidades.put("UY", "Uruguaya");
    
    equipos.put("BE", "Belgrano");
    equipos.put("BC", "Boca");
    equipos.put("L", "Lanús");
    equipos.put("RA", "Racing");
    equipos.put("RV", "River");
    equipos.put("SL", "San Lorenzo");
	%>
	<h3>Formulario de carga de datos</h3>
    <table class="table table-striped">
       	<colgroup>
       		<col width="15%" />
       	</colgroup>
    	<tr>
             <th align="right">Apellido:</th>
             <td><%= request.getParameter("apellido") %></td>
        </tr>
		<tr>
             <th align="right">Nombre:</th>
             <td><%= request.getParameter("nombre") %></td>
		</tr>
		<tr>
             <th align="right">Clave:</th>
             <td><%= request.getParameter("clave") %></td>
		</tr>
		<tr>
             <th align="right">Confirmaci&oacute;n Clave:</th>
             <td><%= request.getParameter("confirmar_clave") %></td>
		</tr>
		<tr>
             <th align="right">E-mail:</th>
             <td><%= request.getParameter("email") %></td>
		</tr>
		<tr>
             <th align="right">Sexo:</th>
             <td><%= (request.getParameter("sexo").equals("F") ? "Femenino" : "Masculino") %></td>
		</tr>
		<tr>
             <th align="right">Fecha de Nacimiento:</th>
             <td><%= formatter.format(fechaNacimiento) %></td>
		</tr>
		<tr>
             <th align="right">Nacionalidad:</th>
             <td><%= (!request.getParameter("nacionalidad").equals("-1") ? nacionalidades.get(request.getParameter("nacionalidad")) : request.getParameter("otra_nacionalidad")) %></td>
		</tr>
		<tr>
             <th align="right" valign="top">Equipo Favorito:</th>
             <td>
             <% 
             if(request.getParameterValues("equipo") != null) {
	             String eq[] = request.getParameterValues("equipo");
	             for(int i = 0, l = eq.length; i < l; i ++) {
	            	 out.println(equipos.get(eq[i]) + "; ");
	             }
             }
             else {
            	 out.println("Ninguno");
             }
             %>
             </td>
		</tr>
		<tr>
            <th align="right" valign="top">Hobbies:</th>
            <td>
            <% 
            String h[] = request.getParameterValues("hobbies");
            for(int i = 0, l = h.length; i < l; i ++) {
           	 	out.println(h[i] + "; ");
            }
            %>
            </td>
		</tr>
		<tr>
            <th align="right" valign="top">Otras Actividades:</th>
            <td><%= request.getParameter("actividades") %></td>
		</tr>
	</table>
	<br/>
	<a href="./ejemplo4.jsp">Volver</a>
</body>
</html>