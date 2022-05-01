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

	$("a").click(function() {
		var eliminado = $(this).closest("tr").find("input[name=eliminado]");
		if($(eliminado).val() == "N") {
			if(confirm("¿Está seguro que desea eliminar el evento?")) {
				$(eliminado).val("S");
				$(this).text("Recuperar");
				$(this).closest("tr").find("td:not(:last)").addClass("eliminado");
			}
		}	
		else {
			$(eliminado).val("N");
			$(this).text("Descartar");
			$(this).closest("tr").find("td:not(:last)").removeClass("eliminado");
		}
	});
});
