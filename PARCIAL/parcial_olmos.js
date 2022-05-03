function validarLogin() {
    const usuario = $('#usuario').val()
    const password = $('#password').val()
    const nombre = $('#nombre').val()
    const domicilio = $('#domicilio').val()
    const email = $('#email').val()
    const telefono = $('#telefono').val()

    // VALIDO QUE ESTEN TODOS LOS CAMPOS COMPLETOS, EN CASO DE QUE SI, SE VALIDA QUE EL PASSWORD TENGA AL MENOS 8 CARACTERES
    if(!usuario || !password || !nombre || !domicilio || !email || !telefono){
        alert('DEBE COMPLETAR TODOS LOS CAMPOS ANTES DE PASAR A LA SECCIO DE LIBROS')
        return false
    }else{
        if(password.length < 8){
            alert('EL PASSWORD DEBE TENER AL MENOS 8 CARACTERES')
            return false
        }
    }
    
    // MUESTRO EL APARTADO LIBROS
    $('#libros').css({'display': 'flex'})
}

function validarLibros() {
    const isbn = $('#isbn').val()
    const titulo = $('#titulo').val()
    const autor = $('#autor').val()
    const descripcion = $('#descripcion').val()
    if(!isbn && !titulo && !autor && !descripcion){
        alert('DEBE INGRESAR AL MENOS UNO DE LOS CAMPOS DE LA SECCION DE LIBROS PARA PODER BUSCAR')
        $('#formulario').submit(function(e){
            return false;
        });
    }else{
        $('#formulario').submit(function(e){
            return true;
        });
    }
}

function aplicar() {
    $("#descripcion").css({ "font-size": $("#V").val() + $("#U").val() });
  }
  