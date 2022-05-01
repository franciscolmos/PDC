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
	
	$("#ipos").val( (parseInt($("tbody tr:last").find("td:first").text()) || 0) + 1)
	$.ajax({
		url: "./tablelet.jsp",
		type: "post",
		data: $("#form").serialize(), 
		datatype: "html",
		error: function(hr) {
			$("#message").html(hr.responseText);
		},
		success: function(html){
			console.log("OK")
			$("#result").attr("hidden", false)
			$("#result tbody").append(html)
		}
	});
}

function check(link) {
	var eliminado = $(link).closest("tr").find("input[name=eliminado]");
	if($(eliminado).val() == "N") {
		if(confirm("¿Está seguro que desea eliminar el evento?")) {
			$(eliminado).val("S")
			$(link).text("Recuperar")
			$(link).closest("tr").find("td:not(:last)").addClass("eliminado")
			
			$.ajax({
				url: "./tablelet.jsp",
				type: "post",
				data: {del:$(link).closest("tr").find("td:first").text()}, 
				datatype: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
				},
				success: function(html){
					console.log("deleted")
				}
			});
			
		}
	}	
	else {
		$(eliminado).val("N");
		$(link).text("Descartar");
		$(link).closest("tr").find("td:not(:last)").removeClass("eliminado");
		
		$.ajax({
			url: "./tablelet.jsp",
			type: "post",
			data: {
				pos:$(link).closest("tr").find("td:eq(0)").text(),
				fecha:$(link).closest("tr").find("td:eq(1)").text(),
				descripcion:$(link).closest("tr").find("td:eq(2)").text(),
				prioridad:$(link).closest("tr").find("td:eq(3)").text(),
				email:$(link).closest("tr").find("td:eq(4)").text()
				}, 
			datatype: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(html){
				console.log("restored")
			}
		});
	}
}