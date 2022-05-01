var jUtils = {

    executing: function(divId) {
        $('#' + divId).html("<p>Procesando...</p>").show();
    },

    showing: function(divId, html) {
        $('#' + divId).html(html !== null ? html : '').show();
    },

    hiding: function(divId, clean) {
        clean = (clean === false ? false : true);
        $('#' + divId).hide();
        if(clean) {
            $('#' + divId).html('&nbsp;');
        }
    }

};


