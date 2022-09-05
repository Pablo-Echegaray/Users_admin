// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});

async function createUser(){
    let datos = {}
    datos.name = document.getElementById('txtName').value;
    datos.surName = document.getElementById('txtSurName').value;
    datos.phone = document.getElementById('txtPhone').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    let repeatPassword = document.getElementById('txtRepeatPassword').value;
    if(repeatPassword != datos.password){
        alert("La contraseña que escribiste es diferente.")
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("La cuenta fue creada con exito!");
    window.location.href = 'login.html';
}
