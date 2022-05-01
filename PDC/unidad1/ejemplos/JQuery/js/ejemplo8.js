var jUtils = {
	setTmpCount: function() {
		if (typeof(Storage) !== undefined) {
			if (sessionStorage.clickcount) {
				sessionStorage.clickcount = Number(sessionStorage.clickcount) + 1;
			}
			else {
				sessionStorage.clickcount = 1;
			}
			$("#tmp").html(sessionStorage.clickcount);
		}
	},
	setGlobalCount: function() {
		if (typeof(Storage) !== undefined) {
			if (localStorage.clickcount) {
				localStorage.clickcount = Number(localStorage.clickcount) + 1;
			}
			else {
				localStorage.clickcount = 1;
			}
			$("#global").html(localStorage.clickcount);
		}
	}
};

$(document).ready(function(){
	if (typeof(Storage) !== undefined) {
		$("#global").html(localStorage.clickcount ? localStorage.clickcount : "");
		$("#tmp").html(sessionStorage.clickcount ? sessionStorage.clickcount : "");
	}
	else {
		$(document.body).html("<p>Su navegador no soporta almacenamiento Web</p>");
	}
});