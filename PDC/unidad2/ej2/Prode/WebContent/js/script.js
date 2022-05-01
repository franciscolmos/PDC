$(document).ready(function(){
	$(":checkbox").change(function(){
		if($(this).prop("checked")){
			var all = $(this).closest("tr")
			$(all).find(":checkbox").each(function(){
				$(this).prop("checked", false)
			})
			$(this).prop("checked", true)
		}
	})
	$("#result").click(function(e){
		var all = true;
		$(".team").each(function(){
			if($(this).find(":checkbox:checked").length == 0){
				all = false;
			}
		})
		if(!all){
			alert("Seleccione todas las casillas");
			e.preventDefault();
		}
	})
})
