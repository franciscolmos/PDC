let contador = 1;

$(document).ready(function(){
    $("#formulario").hide();
    $("#comprobante").change(function() {
        if($("#comprobante option:selected").text() == "-"){
            $("#formulario").hide();
          }
          else {
            $("#formulario").show();
          };
    });
  });

function agregarFila() {
  $("#filas").append(
    `<tr>
        <td><input id="cantidad${contador}" type="text" size="3"></td>
        <td><input id="bien${contador}" type="text" size="10"></td>
        <td><input id="preciounitario${contador}" type="text" size="5"></td>
        <td><input id="descuento${contador}" type="text" size="2"></td>
        <td><input id="impuesto${contador}" type="text" size="4"></td>
        <td><input disabled id="monto${contador}" type="text" size="4" value="0"></td>
    </tr>`
  )
  contador++;
}

function calcularMonto() {
  let count = 0;
  $("#total").val(0);
  $('[id^="monto"]').each(function(value) {
    var precio = ($(`#cantidad${count}`).val() * $(`#preciounitario${count}`).val());
    var descuento = precio - (precio * ($(`#descuento${count}`).val() / 100));
    var monto = descuento - $(`#impuesto${count}`).val();
    $(`#monto${count}`).val(monto);
    count++;
    $("#total").val(parseInt(parseInt($("#total").val())+parseInt($(this).val())));
  });
}
