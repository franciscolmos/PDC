$(document).ready(function(){
    $("form").submit(function(e){
        send = true
        if($(':checkbox:checked').length == 0){
            alert("Carrera de Interes no Seleccionada")
            send = false
        }
        if($(':checkbox:checked').length > 5){
            alert("Demasiadas Carreras Seleccionadas")
            send = false
        }
        if (send) $(this).attr("action", "mailto:pepe@gmail.com")
        else e.preventDefault()
    })
    $("#clean").click(function(){
        $(':checkbox:checked').each(function(){
            var select = $(this).closest("td").next().find("select")
            $(select).prop("disabled", true)
            $(select).val("")
        })
    })
});

function modal(check){
    if($(check).is(":checked")){
        var select = $(check).closest("td").next().find("select")
        $(select).prop("disabled", false)
    }
    else{
        var select = $(check).closest("td").next().find("select")
        $(select).prop("disabled", true)
        $(select).val("")
    }
}

function blank(){
    $(':checkbox').each(function(){
        var select = $(this).closest("td").next().find("select")
        $(select).val("")
    })
}