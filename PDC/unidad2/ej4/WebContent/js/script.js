$(document).ready(function() {
	$('img').click(function() {
		if($(this).next().prop("name").split("-")[1] != "done") {
			$("img").each(function() {
				if($(this).next().prop("name").split("-")[1] != "done") {
					var name = $(this).next().prop("name").split("-")[0]
					$(this).prop("src", "./images/sin-seleccion.png")
					$(this).next().prop("name",name)
				}
			})
			var name = $(this).next().prop("name").split("-")[0] 
			$(this).prop("src", "./images/seleccionado.png")
			$(this).next().prop("name",name+"-selected")
		}
	})
})