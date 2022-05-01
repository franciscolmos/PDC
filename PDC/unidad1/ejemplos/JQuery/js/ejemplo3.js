//Objetos
//Declaración 1
function toString() {
    with($(document.body)) {
        append(["<h3>Datos del auto</h3>",
                "<p>", this.marca, "</p>\n",
                "<p>", this.modelo, "</p>\n",
                "<p>", this.año, "</p>\n"].join(""));
    }
}

function Auto(marca, modelo, año) {
    this.marca    = marca;
    this.modelo   = modelo;
    this.año      = año;
    this.toString = toString;
}

//Declaración 2
var jAuto = {
    marca:  "Ford",
    modelo: "Ka",
    año:  "2007",
    toString: function() {
        with($(document.body)) {
            append(["<h3>Datos del auto</h3>",
                    "<p>", this.marca, "</p>\n",
                    "<p>", jAuto.modelo, "</p>\n",
                    "<p>", this.año, "</p>\n"].join(""));
        }
    }
};

//Declaración 3
var miAuto = new Object();
    miAuto.marca = "Ford";
    miAuto.modelo = "Mustang";
    miAuto.año = 1969;
    miAuto.toString = toString;
