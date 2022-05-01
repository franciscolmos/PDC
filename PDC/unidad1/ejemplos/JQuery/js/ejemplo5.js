var jUtils = {

  cambiarColores: function() {
	  $("#bloque p:eq(0)").css("color", "#ff0000");
	  $("#bloque p:eq(1)").css("color", "#00ff00");
	  $("#bloque p:eq(2)").css("color", "#0000ff");
  },

  cambiarTextoParrafosHijos: function() {
      $("#bloque p").append("/");
  },

  cambiarFondoPadre: function() {
	  $("#parrafo").parent().css("background-color", "#ff00ff");
	  $("#parrafo").css("background-color", "#00ffff");
  },

  cambiarTextoParrafos: function() {
      $("p").append(".");
  },

  mostrarDatosBoton: function(boton) {
	  $("#parrafo").text(["Id: ", boton.id, " - Nombre: ", boton.name, " - Label: ", boton.value].join(""));
  },

  cambiarColor: function() {
      $("#titulo").css("color", "#ff0000");
  },

  cambiarTexto: function() {
      $("#titulo").text("Ahora tengo un nuevo título");
  },

  cambiarTamLetra: function() {
      $("#titulo").css("font-size", "40pt");
  },

  crearLinks: function() {
	  if($.trim($("#texto1").val()) == "") {
		  alert("Debe informar la URL 1");
		  $("#texto1").focus();
		  return;
	  }
	  else if($.trim($("#texto2").val()) == "") {
		  alert("Debe informar la URL 2");
		  $("#texto2").focus();
		  return;
	  }

      var links1 = $("#links1");
          links1.html(["<h5>Propiedad innerHTML</h5>",
                       "<a href=\"", $("#texto1").val(), "\" target=\"_blank\">Hiperv&iacute;nculo 1</a><br/>",
                       "<a href=\"", $("#texto2").val(), "\" target=\"_blank\">Hiperv&iacute;nculo 2</a><br/>"].join(""));

      var links2 = $("#links2");
	      links2.append($("<h5></h5>").text("Métodos del DOM"));
	      links2.append($("<a></a>").text("Hipervínculo 1")
 	                                .attr({"href": $("#texto1").val(), "target": "_blank"}));
	      links2.append($("<br/>"));

	  var a = $("<a></a>");

	  $(a).text("Hipervínculo 2");
	  $(a).attr({"href": $("#texto2").val(), "target": "_blank"});

	  links2.append(a);
	  links2.append($("<br/>"));
  },

  aplicar: function() {
	  $(".poesia").css({"font-size": $("#V").val() + $("#U").val()});
  }

};
