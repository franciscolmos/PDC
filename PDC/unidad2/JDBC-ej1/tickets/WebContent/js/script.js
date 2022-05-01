$(document).ready(function() {
	JTicket.buscar()
})

var JTicket = {
	buscar: function() {
		$.ajax({
			url: "./Verticket",
			data: $.param($("input[name=string_busqueda],input[name=ordenar_por]:checked")),
			type: "post",
			datatype: "html",
			error: function(er) {
				$("#response").html(er.responseText).show();
			},
			success: function(res) {
				$("#response").html(res).show();
			}
		})
	}
}