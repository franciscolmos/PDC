<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cookies</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/cookies.js"></script>
</head>
<body>
<h3>Cookies</h3>
<div id="message"></div>
<form id="form" method="post" action="javascript:void(null)" onsubmit="jForm.add(event)">
	<table>
		<colgroup>
			<col width="220px"/>
			<col width="220px"/>
			<col width="100px"/>
		</colgroup>
		<thead>
			<tr>
				<td>Nombre</td>
				<td>Valor</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<tbody>
		<%
            if(request.getCookies() != null) {
	            for(Cookie c : request.getCookies()) {
	    %>    
		    <jsp:include page="cookie.jsp">
		    	<jsp:param name="cookieName" value="<%= c.getName() %>"/>
		    	<jsp:param name="cookieValue" value="<%= c.getValue() %>"/>	    	
		    </jsp:include>    	
	    <%	
	            }
            }
		%>
			<tr>
				<td><input type="text" name="cookieName"  value="" maxlength="255" size="30" required/></td>
				<td><input type="text" name="cookieValue" value="" maxlength="255" size="30" required/></td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<button>Agregar</button>
</form>
</body>
</html>