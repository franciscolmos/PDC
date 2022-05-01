$(document).ready(function(){
    const rectangulo = $('.rectangulo')
    const originalRectHeight = $(".rectangulo").height();
    const originalRectWidth = $(".rectangulo").width();
    let increaseSize = true;
    rectangulo.click(function() {
        const windowWidth = $(window).width();
        const windowHeight = $(window).height();

        const rectHeigth = $(".rectangulo").height();
        const rectWidth = $(".rectangulo").width();

        const rectVW = (rectWidth * 100) / windowWidth;
        const rectVH = (rectHeigth * 100) / windowHeight;

        let newWidth;
        let newHeight;

        // ajusto el vw y vh del rectangulo agregandole un 10%
        if(!increaseSize){
            newWidth = rectVW * 0.5
            newHeight = rectVH * 0.5
            rectangulo.css({'width': `${newWidth}vw`, 'height': `${newHeight}vh`})
            console.log('originalRectHeight', originalRectHeight)
            console.log('rectHeigth', $(".rectangulo").height())
            console.log('originalRectWidth', originalRectWidth)
            console.log('rectWidth', $(".rectangulo").width())
            console.log('if height: ', $(".rectangulo").height() <= originalRectHeight)
            console.log('if width: ', $(".rectangulo").width() <= originalRectWidth)
            if($(".rectangulo").height() <= originalRectHeight && $(".rectangulo").width() <= originalRectWidth){
                increaseSize = true;
            }
        }else{
            newWidth = rectVW * 1.5
            newHeight = rectVH * 1.5
            rectangulo.css({'width': `${newWidth}vw`, 'height': `${newHeight}vh`})
            console.log('windowHeight', windowHeight)
            console.log('rectHeigth', $(".rectangulo").height())
            console.log('windowWidth', windowWidth)
            console.log('rectWidth', $(".rectangulo").width())
            console.log('if height: ', $(".rectangulo").height() >= windowHeight)
            console.log('if width: ', $(".rectangulo").width() >= windowWidth)
            if($(".rectangulo").height() >= windowHeight && $(".rectangulo").width() >= windowWidth){
                increaseSize = false
            }
        }
    })
})