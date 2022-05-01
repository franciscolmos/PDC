$(document).ready(function () {
	if($("#istart").prop("disabled")){
		setTimeout(function() {
			$("form").submit();
		}, 2000);
	}
})