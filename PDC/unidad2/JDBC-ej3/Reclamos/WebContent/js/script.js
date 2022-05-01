$(document).ready(function() {
	$("#ichasis").change(function() {
		if($(this).val() == "") {
			$(this).removeClass("wrong")
			$(this).parent().next().html("").show()
		}
		else {
			JVehiculo.buscar(this, this)
			if($(this).parent().next().find("img") != null && $("#ipat").val() != ""){
				JVehiculo.buscar($("#ipat"), $("#ichasis,#ipat"))
			}
		}
	})
	$("#ipat").change(function() {
		if($(this).val() == "") {
			$(this).removeClass("wrong")
			$(this).parent().next().html("").show()
		}
		else {
			JVehiculo.buscar(this, $("#ichasis,#ipat"));
		}
	})
	$("input[name=chas]").click(function() {
		if($(this).val() == "N"){
			hideAll("ichasis")
			hideAll("ipat")
			hideAll("ikil")
		}
		else{
			showAll("ichasis")
			showAll("ipat")
			showAll("ikil")
		}
	})
	$("#form").submit(function() {
		if($("#ire").val().length > 4000){
			alert("Texto mayor a 4000 letras")
			return false
		}
		$.ajax({
			url: "./ReclamoServerlet",
			data: $(this).serialize(),
			type: "post",
			datatype: "html",
			error: function(er) {
				$("#response").html(er.responseText).show()
			},
			success: function(res) {
				$("#response").html(res).show()
				$("#main").hide()
			}
		})
	})
})

function hideAll(id) {
	$("#"+id).prop("disabled", true)
	$("#"+id).val("")
	$("#"+id).parent().parent().find("#image").html("")
	$("#"+id).parent().parent().hide()
}

function showAll(id) {
	$("#"+id).prop("disabled", false)
	$("#"+id).val("")
	$("#"+id).parent().parent().show()
}

function volver() {
	$("#response").html("").hide();
	$("#main").show();
}

var JVehiculo = {
	buscar: function(elem, data) {
		$(elem).parent().parent().find("#image").html("Espere.....")
		$.ajax({
			url: "./VehiculoServerlet",
			data: $.param($(data)),
			type: "post",
			datatype: "html",
			error: function(er) {
				$("#response").html(er.responseText).show()
			},
			success: function(res) {
				$("#response").html("").hide()
				$(elem).parent().parent().find("#image").html(res)
				if($(res).attr("id") == "wrong"){
					$(elem).addClass("wrong")
				}
				else{
					$(elem).removeClass("wrong")
				}
			}
		})
	}
}