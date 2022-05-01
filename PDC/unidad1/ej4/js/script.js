$(document).ready(function(){
    $(".col").on("click", ".list-group-item", function(){
        var aux = $(this).clone();
        var id = $(this).parent().attr("id");
        if (id == "left"){
            $("#right").append(aux)
        }
        else{
            $("#left").append(aux)
        }
        $(this).remove()
    })
})