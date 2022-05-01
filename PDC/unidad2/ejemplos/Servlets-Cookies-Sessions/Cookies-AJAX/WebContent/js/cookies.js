jQuery(document).ready(function() {
	 $("input:visible:enabled:first").focus();
});

var jForm = {
	remove: function(td) {
		var tr = $(td).closest("tr");
		$.ajax({
			url: "./saveCookie.jsp",
			type: "post",
			data: $.param({"delCookieName": $(tr).find("input[name=hCookieName]").val()}),
			datatype: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(html) {
				$(tr).remove();
			}
		});
	},
	add: function(e) {
		e.preventDefault();
		$.ajax({
			url: "./saveCookie.jsp",
			type: "post",
			data: $.param($("input[type=text]", $("#form"))),
			datatype: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(tr) {
	   			$("#form input[type=text]").val("");
	   			$("table tbody tr").last().before(tr);
	    	}
		});
	}
};