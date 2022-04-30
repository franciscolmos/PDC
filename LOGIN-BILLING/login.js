// LISTA DE USUARIOS CHARRA
const users = [
    {
        "user": "francisco",
        "password": "123"
    },
    {
        "user": "kevin",
        "password": "123"
    },
    {
        "user": "felipe",
        "password": "123"
    }
]

// OCULTA EL LOGIN SOLO SI INGRESAS CON UN USUARIO PERTENENCIENTE A LA LISTA CHARRA DE ARRIBA
async function validateLogin(){
    const userName = $('#user').val()
    const password = $('#password').val()
    
    if(!users.find(user => {
        return user.user == userName && user.password == password
    })){
        alert("USER OR PASSWORD WRONG ! ! !")
    }else{
        $('#login-hidden').css({'display': 'none'})
        $('#billings-types').css({'display': 'block'})
    }


}

async function displayBilling(){
    $('#billings-types').css({'display':'none'})
    $('#billing-table').css({'display':'block'})
}

async function calculateBilling(){
    const sub1 = $('#product1-unit-price').val() * $('#product1-quantity').val()
    const sub2 = $('#product2-unit-price').val() * $('#product2-quantity').val()
    const sub3 = $('#product3-unit-price').val() * $('#product3-quantity').val()
    
    $('#sub1').val(`$${sub1}`)
    $('#sub2').val(`$${sub2}`)
    $('#sub3').val(`$${sub3}`)

    const total = sub1 + sub2 + sub3
    $('#total').val(`$${total}`)
}

