var jUtils = {

    executing: function(divId) {
        $("#" + divId).html("<p>Procesando...</p>").show();
    },

    showing: function(divId, html) {
        $("#" + divId).html(html !== null ? html : "").show();
    },

    hiding: function(divId, clean) {
        clean = (clean === false ? false : true);
        $("#" + divId).hide();
        if(clean) {
            $("#" + divId).empty();
        }
    },
    
    dialog: function(text, divId="message") {
    	$("#" + divId).html(
    		["<div class=\"modal fade\" role=\"dialog\" aria-hidden=\"true\">",
    		 "<div class=\"modal-dialog\">",
    		 "<div class=\"modal-content\">",
    		 "<div class=\"modal-header\">",
    		 "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>",
    		 "<h4 class=\"modal-title\">",
    		 "<span class=\"glyphicon glyphicon-warning-sign\" aria-hidden=\"true\"></span> Advertencia",
		     "</h4>",
    		 "</div>",
    		 "<div class=\"modal-body\">",
    		 text,
	         "</div>",
    		 "<div class=\"modal-footer\">",
    		 "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Aceptar</button>",
    		 "</div>",
    		 "</div>",
    		 "</div>",
    		 "</div>"].join(""));
    	$("#" + divId + " .modal").modal("show");
    },
    
    alert: function(text, divId="message") {
    	$("#" + divId).html(
    		["<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">",
    		 text,
    		 "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">",
    	     "<span aria-hidden=\"true\">&times;</span>",
    	     "</button>",
	         "</div>"].join(""));
    	$("#" + divId + " .alert").alert();
    }
};


