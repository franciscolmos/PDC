const materias = [
    {
        "name": "materia 1",
        "inicio": "10:15 hs",
        "fin": "10:45 hs",
        "aula": "lab 1",
        "ano": "2021",
        "carrera": "CARRERA 1",
        "dia": 'lunes',
    },
    {
        "name": "materia 2",
        "inicio": "13:15 hs",
        "fin": "14:15 hs",
        "aula": "lab 2",
        "ano": "2021",
        "carrera": "CARRERA 1",
        "dia": 'martes',
    },
    {
        "name": "materia 3",
        "inicio": "15:15 hs",
        "fin": "16:15 hs",
        "aula": "lab 3",
        "ano": "2022",
        "carrera": "CARRERA 2",
        "dia": 'miercoles',
    },
    {
        "name": "materia 4",
        "inicio": "19:15 hs",
        "fin": "20:15 hs",
        "aula": "lab 4",
        "ano": "2021",
        "carrera": "CARRERA 2",
        "dia": 'viernes',
    },
]

function mostrarMaterias(){
    let aulasEncontradas = [];
    materias.find(materia =>{

        if (materia.carrera == $('#carrera').val() &&
            materia.ano == $('#ano').val() &&
            materia.name == $('#materia').val() ){
            aulasEncontradas.push(materia)
        }
    })
    if(aulasEncontradas.length){
        aulasEncontradas.map(aula =>{
            $('#lugar').val(aula.aula)
            $('#dia').val(aula.dia)
            $('#hora').val(`Inicio: ${aula.inicio} - Fin: ${aula.fin}`)
        })
        $('#aulas').css({'display':'block'})
    }else{
        alert('NO SE ENCONTRARON AULAS')
    }
    
}

function aplicar() {
    $("#aulas").css({ "font-size": $("#V").val() + $("#U").val() });
  }