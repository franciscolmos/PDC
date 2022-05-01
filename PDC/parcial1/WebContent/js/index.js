$( document ).ready(function() {
  $("form").on("submit", function(e) {
	 if($.inArray($("#nro").val(), $("#placeholder").val().split("")) !== -1) {
		 alert("Numero ya ingresado, elija otro");
		 $("#nro").val("");
		 e.preventDefault();
	 }
  });
});