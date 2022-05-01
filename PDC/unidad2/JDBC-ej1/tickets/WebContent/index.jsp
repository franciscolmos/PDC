<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/customs" prefix="ct" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejercicio 1</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>

<div class="container" id="busqueda">
	<ct:busc function="JTicket.buscar()"/>
	<br>
	<button onclick="window.location.replace(&quot;./RegTicket&quot;)"> Registrar </button>
	<div id="response">
	</div>
</div>

</body>
</html>