$(document).ready(function(){
    var size = true;
    $("#rect").click(function(){
        var w = $(this).width();
        var h = $(this).height();
        if (size){
            if (w < $(window).width()) $(this).width(w+100);
            if (h < $(window).height()) $(this).height(h+100);
            if (w >= $(window).width() && h >= $(window).height()) size = false
        }
        else{
            if (w > 300) $(this).width(w-100);
            if (h > 100) $(this).height(h-100);
            if (w <= 300 && h <= 100) size = true
        }
    })
})