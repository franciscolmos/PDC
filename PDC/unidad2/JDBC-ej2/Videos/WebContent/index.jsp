<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customs.tld" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Videos</title>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/videos.js"></script>
<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
	<h3>Videos</h3>
	<div id="message"></div>
	<div id="response"></div>
	<div id="main">
		<ct:critbusqvideo onClick="jVideos.buscar()"/>
		<br>
		<div id="result"></div>
	</div>	
</body>
</html>