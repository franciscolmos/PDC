const carreras = [
    {
        name: 'Ingenieria informatica',
    },
    {
        name: 'Economia',
    },
]

const materias = [
    {
        name: 'PDC',
        carrera: carreras[0].name,
        ano: '2022'
    },
    {
        name: 'IS2',
        carrera: carreras[0].name,
        ano: '2022'
    },
    {
        name: 'TC',
        carrera: carreras[0].name,
        ano: '2022'
    },
    {
        name: 'Economia 1',
        carrera: carreras[1].name,
        ano: '2021'
    },
    {
        name: 'Negocios 1',
        carrera: carreras[1].name,
        ano: '2022'
    },
    {
        name: 'De onda 2',
        carrera: carreras[1].name,
        ano: '2021'
    }
]

const aulas = [
    {
        name: "PDC",
        inicio: "10:15 hs",
        fin: "10:45 hs",
        aula: "lab 1",
        ano: "2022",
        carrera: "Ingenieria informatica",
        dia: 'lunes',
    },
    {
        name: "PDC",
        inicio: "10:15 hs",
        fin: "10:45 hs",
        aula: "lab 1",
        ano: "2022",
        carrera: "Ingenieria informatica",
        dia: 'lunes',
    },
    {
        name: "IS2",
        inicio: "13:15 hs",
        fin: "14:15 hs",
        aula: "lab 2",
        ano: "2022",
        carrera: "Ingenieria informatica",
        dia: 'martes',
    },
    {
        name: "Economia 1",
        inicio: "15:15 hs",
        fin: "16:15 hs",
        aula: "lab 3",
        ano: "2022",
        carrera: "Economia",
        dia: 'miercoles',
    },
    {
        name: "Negocios 1",
        inicio: "19:15 hs",
        fin: "20:15 hs",
        aula: "lab 4",
        ano: "2022",
        carrera: "Economia",
        dia: 'viernes',
    },
]

// SE CARGA PRIMERO EL SELECT DE CARRERAS, Y EMPIEZA A ESCUCHAR CAMBIOS EN EL MISMO
$(document).ready(function(){
    const carreraOptions = carreras.map(carrera =>{
        return `<option value="${carrera.name}">${carrera.name}</option>`
    })
    
    $('#carrera').append(carreraOptions)
    // CUANDO SELECCIONO ALGUN CARRERA, ME LLENA EL SELECT DE MATERIAS
    $('#carrera').on('change',  (function() {
        // BORRO LOS OPTIONS VALUES DE MATERIAS
        $('#materia option').each(function(){
            $(this).remove();
        })
        const materiasOptions = materias.map(materia =>{
            if($('#carrera').val() == materia.carrera )
                return `<option value="${materia.name}">${materia.name}</option>`
        })
        $('#materia').append(materiasOptions)
    }));
    // CUANDO SELECCIONO ALGUNA DE CARRERA, ME LLEVA EL SELECT DE AÑO
    $('#materia').on('change',  (function() {
        // BORRO LOS OPTIONS VALUES DE AÑO
        $('#ano option').each(function(){
            $(this).remove();
        })
        const anoOptions = materias.map(materia =>{
            if($('#materia').val() == materia.name )
                return `<option value="${materia.ano}">${materia.ano}</option>`
        })
        $('#ano').append(anoOptions)
    }));
})

function mostrarMaterias(){
    let aulasEncontradas = [];
    let trValues;
    aulas.find(aula =>{

        if (aula.carrera == $('#carrera').val() &&
        aula.ano == $('#ano').val() &&
        aula.name == $('#materia').val() ){
            aulasEncontradas.push(aula)
        }
    })
    if(aulasEncontradas.length){
        trValues = aulasEncontradas.map(aula =>{ 
            return `<tr>
                        <td><input disabled type="text" name=""  value="${aula.aula}"></td>
                        <td><input disabled type="text" name="" value="${aula.dia}"></td>
                        <td><input disabled type="text" name="" value="Inicio: ${aula.inicio} - Fin: ${aula.fin}"></td>
                    </tr>`
        })
        $('#filas').append(trValues)
        $('#aulas').css({'display':'block'})
    }else{
        alert('NO SE ENCONTRARON AULAS')
    }
}

function aplicar() {
    $("#aulas").css({ "font-size": $("#V").val() + $("#U").val() });
}