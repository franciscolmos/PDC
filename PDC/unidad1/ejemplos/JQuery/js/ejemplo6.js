jQuery(document).ready(function() {
	//Sentencias similares
	$("#bloque p").append(" A1");

	$("p", $("#bloque")).append(" A2");

	$("#bloque").find("p").append(" A3");
	//Fin sentencias similares

	//Sentencias similares
	$("#bloque span p").append(" B1");

	$("p", $("#bloque span")).append(" B2");

	$("#bloque").find("span").find("p").append(" B3");
	//Fin sentencias similares

	$("span p:eq(0),span p:eq(1)").css("background-color", "#ff0").css("color", "#000")
	                 .closest("div").css({backgroundColor: "#00f", color: "#fff"});

});