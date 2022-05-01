$(document).ready(function() {
	JRol.rol()
})


var JRol = {
	rol: function() {
		$.ajax({
			url: "./VerRol",
			type: "post",
			datatype: "html",
			error: function(er) {
				$("#response").html(er.responseText).show()
			},
			success: function(res) {
				$("#irol").html(res)
			}
		})
	},
	buscar: function() {
		$.ajax({
			url: "./VerPersona",
			type: "post",
			data: $.param($("input[name=busqueda],select[name=rol]")),
			datatype: "html",
			error: function(er) {
				$("#response").html(er.responseText).show()
			},
			success: function(res) {
				$("#response").html(res).show()
			}
		})
	},
	conectar: function(elem) {
		var datos =$(elem).parent().parent()
		$.ajax({
			url: "./Conectar",
			type: "post",
			data: {nombre:datos.find("#nombre").text(),
				doc: datos.find("#doc").text(), 
				rol: datos.find("#rol").text(), 
				usuario: datos.find("#usuario").val(),
				clave: datos.find("#clave").val()},
			datatype: "html",
			error: function(er) {
				$("#response").html(er.responseText).show()
			},
			success: function(res) {
				$("#datos").html(res).show()
				$("#response").html("").show()
				$("#ibusqueda").val("")
				$("#irol").val("-1")
			}
		})
	}
}