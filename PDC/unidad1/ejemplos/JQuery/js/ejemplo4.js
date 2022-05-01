var vector1 = new Array();
    vector1[0] = 12;
    vector1[1] = 1;
    vector1[2] = 3;

var vector2 = new Array(5), j = 0;
for(var i = vector2.length - 1; i > 0; i --) {
	vector2[j ++] = i;
}

function compareTo(a, b) {
	return (a == b ? 0 : (a > b ? 1 : -1));
}

function strpad(str, width, char) {
  char = char || "0";
  str  = str.toString();
  return str.length >= width ? str : new Array(width - str.length + 1).join(char) + str;
}

var months     = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
var serverdate = new Date();

function displayTime() {
	serverdate.setSeconds(serverdate.getSeconds() + 1);
    var datestring = [months[serverdate.getMonth()], " ", strpad(serverdate.getDate(), 2, "0"), ", ", serverdate.getFullYear()].join("");
    var timestring = [strpad(serverdate.getHours(), 2, "0"), ":", strpad(serverdate.getMinutes(), 2, "0"), ":", strpad(serverdate.getSeconds(), 2, "0")].join("");
    $("#servertime").html(["<nobr>", datestring, "</nobr><br>", timestring, "&nbsp; (GMT -3)"].join(""));
}

function startClock() {
  var angle = 360/60;
  var date = new Date();
  var hour = date.getHours();
  if(hour > 12) {
    hour = hour - 12;
  }
  var minute = date.getMinutes();
  var second = date.getSeconds();
  var hourAngle = (360/12)*hour + (360/(12*60))*minute;
  $("#minute").css("-webkit-transform", "rotate(" + angle * minute + "deg)");
  $("#second").css("-webkit-transform", "rotate(" + angle * second + "deg)");
  $("#hour").css("-webkit-transform", "rotate(" + hourAngle + "deg)");
}

function med(params) {
	if(Object.prototype.toString.call(params) !== "[object Array]") {
		alert("El parámetro especificado no es un vector");
		return false;
	}

	var suma = 0;
	for(var i in params) {
		suma += params[i];
	}
  	return suma / params.length;
}

function varianza(params) {
	var suma = 0, me = med(params);
	if(!me) {
		return false;
	}

	for(var i in params) {
		suma += Math.pow(params[i] - me, 2);
	}
  	return suma / (params.length - 1);
}

function desvest(params) {
	var v = varianza(params);
	if(!v) {
		return false;
	}
	return Math.sqrt(v);
}
