function valid(event) {
  if ($("#password").val() !== $("#password_r").val()) {
    alert("Las claves no coinciden");
    console.log("event", event);
    // event.preventDefault(); // para cortar la evaluación
    return false;
  }

  if ($("#inac").val() == "-1") {
    alert("OTRA NACIONALIDAD");
  }
}

$(document).ready(function () {
  $("#inac").change(function () {
    if ($("#inac").val() == "-1") {
      alert("OTRA NACIONALIDAD");
    }
  });
});
