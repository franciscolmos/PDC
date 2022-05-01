$(document).ready(function() {
	$("#form").submit(function() {
		JPart.add()
	})
	$("#trought").click(function() {
		JPart.start()
	})
})

JPart = {
	add: function() {
		$("#response").html("")
		$.ajax({
			url: "./jsp/table.jsp",
			type: "post",
			data: $.param($("#inombre")), 
			datatype: "html",
			error: function(hr) {
				$("#response").html(hr.responseText).show()
			},
			success: function(html){
				$("thead").append(html)
				if($("thead > tr").length - 1 == 3) $("#trought").prop("hidden", false) 
				$("#inombre").val("")
			}
		})
	},
	start: function() {
		$("tbody").remove()
		$("#adder").remove()
		$.ajax({
			url: "./jsp/number.jsp",
			type: "post",
			data: {index: $("thead > tr.none").length},
			datatype: "text",
			error: function(hr) {
				$("#response").html(hr.responseText).show()
			},
			success: function(text){
				console.log(text)
				$("thead > tr.none").eq(text).attr("class", "eliminado")
				if ($("thead > tr.none").length == 2) {
					$("#init").attr("hidden", false)
					$("#trought").attr("hidden", true)
				}
			}
		})	
	}
}