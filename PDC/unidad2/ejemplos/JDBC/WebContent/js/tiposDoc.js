jQuery(document).ready(function(){
	$("#nuevo").click(function() {
		jTiposDoc.nuevo();
	});
	
	$("a[name=eliminar]").click(function() {
		jTiposDoc.eliminar($(this));
	});

	$("a[name=editar]").click(function() {
		jTiposDoc.editar($(this).closest("tr").find("input[name=cod_tipo_documento]").val());
	});
}); 

var jTiposDoc = {
		
	nuevo: function() {
		jUtils.hiding("main", false);
		jUtils.executing("response");
		$.ajax({
			url: "./tiposDocNuevo.jsp",
			type: "get",
			datatype: "html",
			error: function(hr) {
				jUtils.showing("response", hr.responseText);
				jUtils.showing("main");
			},
			success: function(html) {
				jUtils.showing("response", html);
				fxTiposDoc.configEvent();
			}
		});
	},
	
	guardar: function(ev) {
		ev.preventDefault();
		
        jUtils.hiding("response", false);
		jUtils.executing("aux");
		
		$.ajax({
			url: "./tipoDocGuardar.jsp",
			type: "post",
			data: $.param($("input[type=hidden],input[type=text],input[type=checkbox]:checked,select", $("#form_nuevo"))),
			datatype: "html",		
			error: function(hr) {
				jUtils.showing("response");
				jUtils.showing("aux", hr.responseText);
			},
			success: function() {
				$(location).attr("href", "./index.jsp");
			}
		});
	},

	eliminar: function(obj) {
		if(confirm("¿Confirma la eliminación del registro?")) {
			jUtils.executing("aux");
			jUtils.hiding("main", false);
			
			var codigo = $(obj).closest("tr").find("input[name=cod_tipo_documento]").val();
			
			$.ajax({
				url: "./tiposDocEliminar.jsp",
				type: "post",
				data: $.param({"cod_tipo_documento": codigo}),
				datatype: "html",
				error: function(hr) {
					jUtils.showing("aux", hr.responseText);
					jUtils.showing("main");
				},
				success: function(html) {
					jUtils.hiding("aux");
					jUtils.showing("main");
					$(obj).closest("tr").remove();
				}
			});		
		}	
	},
	
	editar: function(codigo) {
		jUtils.hiding("main", false);
		jUtils.executing("response");
		$.ajax({
			url: "./tiposDocEditar.jsp",
			type: "post",
			data: $.param({"cod_tipo_documento": codigo}),
			datatype: "html",
			error: function(hr) {
				jUtils.showing("response", hr.responseText);
				jUtils.showing("main");
			},
			success: function(html) {
				jUtils.showing("response", html);
				fxTiposDoc.configEvent();
			}
		});
	},
	
	cancelar: function() {
		jUtils.hiding("response");
		jUtils.showing("main");
	}
}

var fxTiposDoc = {
		
	configEvent: function() {
		$("#form").submit(function(ev) {
			jTiposDoc.guardar(ev);
		});
		$("#cancelar").click(function() {
			jTiposDoc.cancelar();
		});
	}
		
};