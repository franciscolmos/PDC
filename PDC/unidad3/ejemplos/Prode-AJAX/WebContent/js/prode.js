$(document).ready(function() {
	$("input[type=checkbox]").click(function() {
		$("input[type=checkbox]:checked", $(this).closest("tr")).not($(this)).prop("checked", false);
	});	

	$("input[name=resultados-prode]").click(function() {
		if($("input[type=checkbox]:checked").size() != $("tbody tr").size()) {
			alert("Debe indicar todos los resultados");
			return false;
		}
		
		jUtils.hiding("prode", false);
		jUtils.executing("resultados");
		
		$.ajax({
			url: "./resultados.jsp",
			type: "post",
			data: $.param($("input[type='checkbox']:checked", $("#form"))),
			datatype: "html",
			error: function(hr) {
				jUtils.hiding("resultados");
				jUtils.showing("message", hr.responseText);
				jUtils.showing("prode");
			},
			success: function(html) {
				jUtils.showing("prode");
				jUtils.showing("resultados", html);
				$("#bresultados").hide();
				$("#bvolver").show();
			}			
		});		
	});	

	$("input[name=volver]").click(function() {
		window.location.href="index.jsp";
	});	
});
