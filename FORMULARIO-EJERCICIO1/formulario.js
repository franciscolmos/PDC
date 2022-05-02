let datos = [
    {
        apellido: 'olmos',
        nombre: 'Francisco',
        parentesco: 'Padre',
        vive: 'Sí',
        nacimiento: 1994,
        ocupacion: 'Jubilado',
        email: 'francisco.olmos.ubp@gmail.com'
    },
    {
        apellido: 'apellido 1',
        nombre: 'nombre 1',
        parentesco: 'Madre',
        vive: 'Sí',
        nacimiento: 1996,
        ocupacion: 'Empleado',
        email: 'mail1@gmail.com'
    },

]

$(document).ready(function(){
    sessionStorage.setItem('datos', JSON.stringify(datos))
    /* APENAS CARGUE EL DOCUMENTO SE PINTAN EN CADA FILA LOS DATOS DEL SESSIONSTORAGE */
    cargarDatosIniciales(sessionStorage.getItem('datos'))
})

/* FUNCION PARA CARGAR LA INFORMACION DEL SESSIONSTORAGE */
function cargarDatosIniciales() {
    datos.map(dato =>{
        agregarFila(dato)
    })
}

let id = 0;
/* 
   RECIBE UN DATO, SI ES INVOCADA POR EL BOTON AGREGAR FILA EL DATO ES NULLO Y LA FILA SE AGREGA VACIA
   SI ES INVOCADA POR LA FUNCION CARGARDATOSINICIALES ENTONCES SE LE PASA UN DATO EN CADA ITERACION
*/
function agregarFila(dato) {
    $('#filas').append(
        `
            <div class="row" id="${id}">
                <!-- APELLIDO -->
                <div class="col-1 celdas">
                    <input value="${dato == null ? '' : dato.apellido}" type="text" required maxlength="40" id="apellido">
                </div>
                <!-- NOMBRE -->
                <div class="col-1 celdas">
                    <input value="${dato == null ? '' : dato.nombre}" type="text" required max="100" id="nombre">
                </div>
                <!-- PARENTESCO -->
                <div class="col-2 celdas">
                    <select class="form-select" required id="parentesco">
                        <option ${dato == null ? 'selected' : ''} disabled value=""></option>
                        <option ${dato == null ? '' : dato.parentesto == 'Padre' ? 'selected' : ''} value="Padre">Padre</option>
                        <option ${dato == null ? '' : dato.parentesto == 'Madre' ? 'selected' : ''} value="Madre">Madre</option>
                        <option ${dato == null ? '' : dato.parentesto == 'Hermano' ? 'selected' : ''} value="Hermano">Hermano/a</option>
                        <option ${dato == null ? '' : dato.parentesto == 'Hijo' ? 'selected' : ''} value="Hijo">Hijo</option>
                        <option ${dato == null ? '' : dato.parentesto == 'Conyuge' ? 'selected' : ''} value="Conyuge">Cónyuge</option>
                    </select>
                </div>
                <!-- VIVE -->
                <div class="col-2 celdas">
                    <div class="col-9" style="display: flex;">
                        <div class="form-check">
                            <label class="form-check-label" for="si">
                                Sí
                            </label>
                            <input class="form-check-input" type="radio" name="vive" id="si" ${dato == null ? 'checked' : dato.vive == 'Sí' ? 'checked' : ''}>
                        </div>
                        <div class="form-check" style="margin-left: 5%;">
                            <label class="form-check-label" for="no">
                                No
                            </label>
                            <input class="form-check-input" type="radio" name="vive" id="no" ${dato == null ? '' : dato.vive == 'No' ? 'checked' : ''}>
                        </div>  
                    </div>
                </div>
                <!-- NACIMIENTO -->
                <div class="col-2 celdas">
                    <input type="number" maxlength="4" required value="${dato == null ? '' : dato.nacimiento}">
                </div>
                <!-- OCUPACION -->
                <div class="col-1 celdas">
                    <select class="form-select" id="ocupacion">
                        <option ${dato == null ? 'selected' : ''} disabled value=""></option>
                        <option ${dato == null ? '' : dato.ocupacion == 'Empleado' ? 'selected' : ''} value="Empleado">Empleado</option>
                        <option ${dato == null ? '' : dato.ocupacion == 'Empresario' ? 'selected' : ''} value="Empresario">Empresario</option>
                        <option ${dato == null ? '' : dato.ocupacion == 'Independiente' ? 'selected' : ''} value="Independiente">Independiente</option>
                        <option ${dato == null ? '' : dato.ocupacion == 'Jubilado' ? 'selected' : ''} value="Jubilado">Jubilado</option>
                        <option ${dato == null ? '' : dato.ocupacion == 'Notrabaja' ? 'selected' : ''} value="Notrabaja">No trabaja</option>
                    </select>
                </div>
                <!-- EMAIL -->
                <div class="col-2 celdas">
                    <input type="email" maxlength="255" value="${dato == null ? '' : dato.email}">
                </div>
                <!-- ELIMINAR -->
                <div class="col-1 celdas">
                    <img src="./assets/trash.png" alt="delete" onclick=eliminarFila(this)>
                </div>
            </div> 
        `
    )
    id++;
    pintarFilas();
}

function eliminarFila(fila) {
    const idFila = fila.closest('div[class=row]').id
    $(`#${idFila}`).remove()
    pintarFilas()
}

function pintarFilas() {
    const filas = $('#filas').children()
    filas.map((i, fila) => {
        i % 2 == 0 ? $(fila).css({'background-color': 'white'}) : $(fila).css({'background-color': 'lightgrey'})
    })
}