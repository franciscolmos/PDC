var jVideos = {
	buscar: function() {
		$("#result").html("Procesando...").show();
		
		$.ajax({
			url: "./videos.jsp",
			data: $.param($("input[type=text],input[type=radio]:checked")),
			type: "post",
			datatype: "html",
			error: function(hr) {
				$("#result").html("").hide();
				$("#message").html(hr.responseText).show();
			},
			success: function(html) {
				$("#result").html(html);
			}			
		});
	},
	
	ver: function(link) {
		$(link).closest("p").find("span").text("[ Visto ]");
		
		$("#response").html("Procesando...").show();
		$("#main").hide();
		
		$.ajax({
			url: "./ver_video.jsp",
			data: $.param($(link).closest("p").find("input[type=hidden]")),
			type: "post",
			datatype: "html",
			error: function(hr) {
				$("#response").html("").hide();
				$("#main").show();
				$("#message").html(hr.responseText);
			},
			success: function(html) {
				$("#response").html(html);
			}
		});
	},
	
	volver: function() {
		$("#response").html("").hide();
		$("#main").show();
	}
};