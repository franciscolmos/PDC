
function changeSelectDisableState(check) {
    let disableds = $('select[disabled]').length - 1;
    switch(check.id){
        case 'arquitectura':
            $('#arquitectura-select').prop('disabled') == true && disableds >= 2 ?
                $('#arquitectura-select').prop('disabled', false) : 
                $('#arquitectura-select').prop('disabled', true)
            break;
        case 'contador':
            $('#contador-select').prop('disabled') == true && disableds >= 2 ?
                $('#contador-select').prop('disabled', false) : 
                $('#contador-select').prop('disabled', true)
            break;
        case 'tele':
            $('#tele-select').prop('disabled') == true && disableds >= 2 ?
                $('#tele-select').prop('disabled', false) : 
                $('#tele-select').prop('disabled', true)
            break;
        case 'info':
            $('#info-select').prop('disabled') == true && disableds >= 2 ?
                $('#info-select').prop('disabled', false) : 
                $('#info-select').prop('disabled', true)
            break;
        case 'admin':
            $('#admin-select').prop('disabled') == true && disableds >= 2 ?
                $('#admin-select').prop('disabled', false) : 
                $('#admin-select').prop('disabled', true)
            break;
        case 'periodismo':
            $('#periodismo-select').prop('disabled') == true && disableds >= 2 ?
                $('#periodismo-select').prop('disabled', false) : 
                $('#periodismo-select').prop('disabled', true)
            break;
        case 'turismo':
            $('#turismo-select').prop('disabled') == true && disableds >= 2 ?
                $('#turismo-select').prop('disabled', false) : 
                $('#turismo-select').prop('disabled', true)
            break;
        case 'periodismo':
            $('#periodismo-select').prop('disabled') == true && disableds >= 2 ?
                $('#periodismo-select').prop('disabled', false) : 
                $('#periodismo-select').prop('disabled', true)
            break;
        default:
            break;
    }
}