$(document).ready(function () {
  $("#bloque p").append(" A1");
  $("p", $("#bloque")).append(" A2");
  $("#bloque").find("p").append(" A3");
  $("#bloque span p").append(" B1");
  $("p", $("#bloque span")).append(" B2");
  $("#bloque").find("span").find("p").append(" B3");
  $("#bloque").find("div").find("p").append(" C1");
  $("#bloque p:eq(0)").css("background-color", "#02f");
  $("#bloque p:eq(1)").css("background-color", "#ff5");
  $("#bloque p:eq(2)")
    .css("background-color", "#888")
    .closest("div")
    .css({ "background-color": "#000", color: "#000" });
  $("span p:eq(0), span p:eq(1)")
    .css("background-color", "#09f")
    .css("color", "#000")
    .closest("div")
    .css({ "background-color": "#0f0", color: "#000" });
});

function allWhite() {
    $("#bloque p:eq(2)")
    .css("background-color", "#fff")
    .closest("div")
    .css({ "background-color": "#fff", color: "#fff" });
  $("span p:eq(0), span p:eq(1)")
    .css("background-color", "#fff")
    .css("color", "#fff")
    .closest("div")
    .css({ "background-color": "#fff", color: "#fff" });
}
