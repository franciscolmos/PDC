$(document).ready(function() {
	$("input[type=radio]").change(function() {
		JGrupo.consult()
	})
})

JGrupo = {
	consult: function() {
		$("#error").html("")
		$.ajax({
			url: "./Consultar",
			data: $.param($("input[name=areas]:checked")),
			type: "post",
			datatype: "html",
			error: function(er) {
				$("#error").html(er.responseText).show();
			},
			success: function(res) {
				$("#response").html(res).show();
			}
		})
	},
	add: function(elem) {
		$("#error").html("")
		length = 0 
		$(elem).parent().parent().find("input[type=text]").each(function() {
			if($(this).val() == ""){
				length++
			}
		})
		if(length > 0) {
			alert("Elementos Vacios")
		}
		else{
			data = {nom_grupo : $(elem).parent().parent().find("#inom_grupo").val(),
					vigente : $(elem).parent().parent().find("#ivigente").val(),
					exclusivo : $(elem).parent().parent().find("#iexclusivo").val(),
					hnro_grupo : $(elem).parent().parent().find("#ihnro_grupo").val(),
					areas : $("input[name=areas]:checked").val()}
			$.ajax({
				url: "./Agregar",
				data: data,
				type: "post",
				datatype: "html",
				error: function(er) {
					$("#error").html(er.responseText).show();
				},
				success: function(res) {
					alert("ingresado correctamente")
					JGrupo.consult()
					
				}
			})
		}
	},
	reset: function(elem) {
		$(elem).parent().parent().find("#inom_grupo").val($(elem).find("#ihnom_grupo").val())
		$(elem).parent().parent().find("#iexclusivo").val($(elem).find("#ihexclusivo").val())
		$(elem).parent().parent().find("#ivigente").val($(elem).find("#ihvigente").val())
	},
	cancel: function(elem) {
		$(elem).parent().parent().find("#inom_grupo").val("")
		$(elem).parent().parent().find("#iexclusivo").val("S")
		$(elem).parent().parent().find("#ivigente").val("S")
	}	
}