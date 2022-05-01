jQuery(document).ready(function() {
    $("input:visible:enabled:first").focus();
});

var jForm = {

	cantMaxCAct: 0,

	valid: function(evt) {
		if($("#iclave").val().length < 6) {
			jUtils.alert("La clave debe contener al menos 6 caracteres");
			$("#iclave").focus();
			evt.preventDefault();
			return;
		}

		if($("#iclave").val() != $("#iconfirmar_clave").val()) {
			jUtils.alert("Las claves no coinciden");
			$("#iclave").focus();
			evt.preventDefault();
			return;
		}

		if($("#iequipo :selected").length > 2) {
			jUtils.alert("Debe informar hasta dos equipos");
			$("#iequipo").focus();
			evt.preventDefault();
			return;
		}

		if($("input[name=hobbies]:checked").length == 0) {
			jUtils.alert("Debe informar al menos un hobby");
			$("input[name=hobbies]:first").focus();
			evt.preventDefault();
			return;
		}
		
		evt.preventDefault();
		
		jUtils.executing("result");
		jUtils.hiding("message");
		jUtils.hiding("main", false);
		
		$.ajax({
			url: "./resultado.jsp",
			type: "post",
			data: $("#form").serialize(), 
			datatype: "html",
			error: function(hr) {
				jUtils.hiding("result");
				jUtils.alert(hr.responseText);
				jUtils.showing("main");
			},
			success: function(html){
				jUtils.showing("result", html);
			} 			
		});
	},
	
	volver: function() {
		jUtils.hiding("result");
		jUtils.showing("main");
	},

	validActLen: function(txtA) {
		$("#icact").html(this.cantMaxCAct - $(txtA).val().length);
		if($(txtA).val().length > this.cantMaxCAct) {
  			$(txtA).val($(txtA).val().substring(0, this.cantMaxCAct));
		}
	},

	setActLen: function() {
		this.cantMaxCAct = $("#iactividades").attr("maxlength") != undefined ? $("#iactividades").attr("maxlength") : 300;
		$("#icact").html(this.cantMaxCAct);
	},

	selNacionalidad: function(selObj) {
		if($(selObj).val() == "-1") {
			$("#iotranac").prop("disabled", false);
			$("#iotranac").focus();
		}
		else {
			$("#iotranac").prop("disabled", true);
			$("#iotranac").val("");
		}
	}

};

jQuery(document).ready(function() {
	jForm.setActLen();
});
