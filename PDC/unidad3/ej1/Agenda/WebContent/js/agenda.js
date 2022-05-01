jQuery(document).ready(function() {
	$("#iprioridad").change(function() {
		if($(this).val() == "A") {
			$("#inotificarS").prop("checked", true);
			$("input[name=notificar]").prop("disabled", true);
			$("#iemail").prop("disabled", false);
		}
		else {
			$("input[name=notificar]").prop("disabled", false);
		}
	});

	$("input[name=notificar]").click(function() {
		if($(this).val() == "N") {
			$("#iemail").prop("disabled", true);
			$("#iemail").val("");
		}
		else {
			$("#iemail").prop("disabled", false);
		}
	});
});


function send(event) {
	$.ajax({
		url: "./jsp/table.jsp",
		type: "post",
		data: $("#form").serialize(), 
		datatype: "html",
		error: function(hr) {
			alert("ERROR")
		},
		success: function(html){
			html = $(html)
			html.find("#count").text($("tbody").find("tr").length + 1)
			$("#result").attr("hidden", false)
			$("#result tbody").append(html)
		} 			
	});
}

function check(link) {
	var eliminado = $(link).closest("tr").find("input[name=eliminado]");
	if($(eliminado).val() == "N") {
		if(confirm("¿Está seguro que desea eliminar el evento?")) {
			$(eliminado).val("S");
			$(link).text("Recuperar");
			$(link).closest("tr").find("td:not(:last)").addClass("eliminado");
		}
	}	
	else {
		$(eliminado).val("N");
		$(link).text("Descartar");
		$(link).closest("tr").find("td:not(:last)").removeClass("eliminado");
	}
}