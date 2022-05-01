let id = 0;
function agregarFila() {
    $('#filas').append(
        `
            <div class="row" id="${id}">
                <!-- APELLIDO -->
                <div class="col-1 celdas">
                    <input type="text" required maxlength="40" id="apellido">
                </div>
                <!-- NOMBRE -->
                <div class="col-1 celdas">
                    <input type="text" required max="100" id="nombre">
                </div>
                <!-- PARENTESCO -->
                <div class="col-2 celdas">
                    <select class="form-select" required id="parentesco">
                        <option disabled selected value=""></option>
                        <option value="padre">Padre</option>
                        <option value="madre">Madre</option>
                        <option value="hermano">Hermano/a</option>
                        <option value="hijo">Hijo</option>
                        <option value="conyuge">Cónyuge</option>
                    </select>
                </div>
                <!-- VIVE -->
                <div class="col-2 celdas">
                    <div class="col-9" style="display: flex;">
                        <div class="form-check">
                            <label class="form-check-label" for="si">
                                Sí
                            </label>
                            <input class="form-check-input" type="radio" name="vive" id="si" checked>
                        </div>
                        <div class="form-check" style="margin-left: 5%;">
                            <label class="form-check-label" for="no">
                                No
                            </label>
                            <input class="form-check-input" type="radio" name="vive" id="no">
                        </div>  
                    </div>
                </div>
                <!-- NACIMIENTO -->
                <div class="col-2 celdas">
                    <input type="number" maxlength="4" required>
                </div>
                <!-- OCUPACION -->
                <div class="col-1 celdas">
                    <select class="form-select" id="ocupacion">
                        <option disabled selected value=""></option>
                        <option value="empleado">Empleado</option>
                        <option value="empresario">Empresario</option>
                        <option value="independiente">Independiente</option>
                        <option value="jubilado">Jubilado</option>
                        <option value="notrabaja">No trabaja</option>
                    </select>
                </div>
                <!-- EMAIL -->
                <div class="col-2 celdas">
                    <input type="email" maxlength="255">
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