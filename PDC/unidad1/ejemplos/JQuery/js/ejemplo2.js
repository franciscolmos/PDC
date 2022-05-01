//Variables declaradas globalmente al script
var variable1 = 2, variable2 = '2';
var booleano  = true;
var numerico  = 22;
var flotante  = 13.56;
var texto     = "Mi texto";
var fecha     = new Date();

jQuery(document).ready(function() {
	var html = ["<p>El tipo de booleano es: ", typeof booleano, "</p>\n",
	            "<p>El tipo de numerico es: ", typeof numerico, "</p>\n",
	            "<p>El tipo de flotante es: ", typeof flotante, "</p>\n",
	            "<p>El tipo de texto es: ", typeof texto, "</p>\n",
	            "<p>El tipo de fecha es: ", typeof fecha, "</p>\n",
	            "<p>Parseo a Entero: ", parseInt(flotante), "</p>\n",
	            "<p>Parseo a Flotante: ", parseFloat(texto), "</p><br/>\n"];
	$(document.body).prepend(html.join(""));
});

function mostrarVariables() {
    //Variable declarada localmente
    var html = ["<h3>Resultado</h3>\n",
                "<p>Variable 1: Tipo ", typeof variable1, " / Valor ", variable1, "</p>\n",
                "<p>Variable 2: Tipo ", typeof variable2, " / Valor ", variable2, "</p>\n"].join("");

    if(variable1 == variable2) {
        html = [html, "<p>Variable 1 es igual a Variable 2</p>\n"].join("");
    }

    if(variable1 != variable2) {
        html = [html, "<p>Variable 1 NO es igual a Variable 2</p>\n"].join("");
    }

    if(variable1 === variable2) {
        html = [html, "<p>Variable 1 es exactamente igual a Variable 2</p>\n"].join("");
    }

    if(variable1 !== variable2) {
        html = [html, "<p>Variable 1 NO es exactamente igual a Variable 2</p>\n"].join("");
    }

    switch(parseInt(variable2)) {
        case 1:
        case 3:
            html = [html, "<p>La variable 2 vale 1 o 3</p>\n"].join("");
            break;
        case 2:
            html = [html, "<p>La variable 2 vale 2</p>\n"].join("");
            break;
        default:
            html = [html, "<p>La variable 2 vale cualquier otra cosa</p>\n"].join("");
    }

	$("#resultado1").html(html);
	$("#resultado1").append("<br/>");
    //Sentencia equivalente
    //$("#resultado1").html(html).append("<br/>");
}

function getMensaje() {
	alert("Mensaje!");
}

function saludo(nombre) {
	nombre = nombre == undefined ? "que tal" : nombre;
	alert("Hola " + nombre + "!");
}

function calculos(param1, param2) {
	if(isNaN(param1)) {
		alert("El parámetro 1 no es un número");
		return;
	}
	else if(isNaN(param2)) {
		alert("El parámetro 2 no es un número");
		return;
	}

	with($(document.body)) {
		append(["<h3>Resultados Par&aacute;metro 1: ", param1, " // Par&aacute;metro 2: ", param2, "</h3>\n"].join(""));
		append(["<p>Multiplicaci&oacute;n: ", (param1 * param2), "</p>\n"].join(""));
		append(["<p>Divisi&oacute;n: ", (param2 == 0 ? "-" : (param1 / param2)), "</p>\n"].join(""));
		append(["<p>Resto: ", (param2 == 0 ? "-" : (param1 % param2)), "</p>\n"].join(""));
		append(["<p>Suma: ", (param1 + param2), "</p>\n"].join(""));
		append(["<p>Resta: ", (param1 - param2), "</p>\n"].join(""));
		append(["<p>Incremeto Par&aacute;metro 1: ", (param1 ++), "</p>\n"].join(""));
		append(["<p>Incremeto Par&aacute;metro 2: ", (++ param2), "</p>\n"].join(""));
		append(["<p>Decremento Par&aacute;metro 1: ", (-- param1), "</p>\n"].join(""));
		append(["<p>Decremento Par&aacute;metro 2: ", (param2 --),"</p><br/>\n"].join(""));
	}
}

function valAccion(mensaje) {
    try {
        mensaje = mensaje == undefined ? "" : mensaje + "\n\n";

        var sentencia = prompt(mensaje + "Ingrese una sentencia Javascript");
        if(sentencia == "") {
            throw new Error("No informó sentencia a ejecutar");
        }
        eval(sentencia);
    }
    catch(ex) {
        valAccion(ex.description == undefined ? ex : ex.description);
    }
}

function getMarcas() {
    var marcas  = ["Fiat", "Ford", "Chevrolet", "VW", "Citroen", "Jeep", "Renault"];
    var html    = "<h3>Listas ascendentes</h3>\n";
        html   += "<h5>for</h5>\n";
    for(var i = 0; i < marcas.length; i ++) {
        html += "<p>" + marcas[i] + "</p>\n";
    }

    html += "<h5>do...while</h5>\n";
    i = 0;
    do {
        html += "<p>" + marcas[i++] + "</p>\n";
    }
    while(i < marcas.length);

    html += "<h5>foreach</h5>\n";
    for(i in marcas) {
        html += "<p>" + marcas[i] + "</p>\n";
    }

    html += "<h3>Listas descendentes</h3>\n"
    html += "<h5>for</h5>\n";
    for(i = marcas.length - 1; i >= 0; i --) {
        html += "<p>" + marcas[i] + "</p>\n";
    }

    html += "<h5>while</h5>\n";
    i = marcas.length - 1;
    while(i >= 0) {
        html += "<p>" + marcas[i--] + "</p>\n";
    }

    $("#resultado2").html(html).append("<br/>");
}

function getParamsValues() {
    with($(document.body)) {
        if(arguments.length > 0) {
            for(var i = 0; i < arguments.length; i++) {
                append("<p>Parámetro " + i + ": " + arguments[i] + "</p>");
            }
        }
        else {
            append("<p>Función ejecutada sin parámetros</p>");
        }
    }
}
