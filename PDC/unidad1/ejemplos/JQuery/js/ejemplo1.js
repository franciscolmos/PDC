$("#parrafo1").css("background-color", "#0f0");
$("#parrafo2").css({color: "#ff0", backgroundColor: "#000"});

var textos = $("[name=texto]");

$(document.body).append(["<b>Cantidad de elementos con nombre &quot;texto&quot;: ", textos.length, "</b><br/>"].join(""));
for(var i = 0; i < textos.length; i ++) {
	console.log(textos[i]);
	$(textos[i]).append(".");
}
// Más óptima!
$("[name=texto]").append("%");

$("[name=parrafos] p").append("&");
$("#div p").append("$");

var parrafos = $("p");
$(document.body).append(["<b>Cantidad de p&aacute;rrafos: ", parrafos.length, "</b><br/>"].join(""));
for(var i = 0; i < parrafos.length; i ++) {
	$(parrafos[i]).append("///");
}
// Más óptima!
$("p").append("\\\\\\");

eval("$(document.body).append(\"<p>P&aacute;rrafo 2 construido din&aacute;micamente</p>\");");
