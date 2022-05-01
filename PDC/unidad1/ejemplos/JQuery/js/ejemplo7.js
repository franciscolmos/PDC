var jForm = {

	cantMaxCAct: 0,

	valid: function(evt) {
		if($("#iclave").val().length < 6) {
			alert("La clave debe contener al menos 6 caracteres");
			$("#iclave").focus();
			evt.preventDefault();
			return;
		}

		if($("#iclave").val() != $("#iconfirmar_clave").val()) {
			alert("Las claves no coinciden");
			$("#iclave").focus();
			evt.preventDefault();
			return;
		}

		if($("#inacionalidad").val() == "-1" && $.trim($("#iotranac").val()) == "") {
			alert("Debe informar otra nacionalidad");
			$("#iotranac").focus();
			evt.preventDefault();
			return;
		}

		if($("#iequipo :selected").length > 2) {
			alert("Debe informar hasta dos equipos");
			$("#iequipo").focus();
			evt.preventDefault();
			return;
		}

		if($("input[name=hobbies]:checked").length == 0) {
			alert("Debe informar al menos un hobby");
			$("input[name=hobbies]:first").focus();
			evt.preventDefault();
			return;
		}
	},

	validActLen: function(txtA) {
		$("#icact").html(this.cantMaxCAct - $(txtA).val().length);
		if($(txtA).val().length > this.cantMaxCAct) {
  			$(txtA).val($(txtA).val().substring(0, this.cantMaxCAct));
		}
	},

	setActLen: function(init) {
		with(document) {
			this.cantMaxCAct = $("#iactividades").attr("cantCaracteres") != undefined ? $("#iactividades").attr("cantCaracteres") : 300;
			$("#icact").html(this.cantMaxCAct - (init ? 0 : $("#iactividades").val().length));
		}
	},

	selNacionalidad: function(selObj) {
		if($(selObj).val() == "-1") {
			$("#iotranac").closest("div").removeClass("hide").addClass("show");
			$("#iotranac").prop("disabled", false);
			$("#iotranac").focus();
		}
		else {
			$("#iotranac").closest("div").removeClass("show").addClass("hide");
			$("#iotranac").prop("disabled", true);
			$("#iotranac").val("");
		}
	}

};
