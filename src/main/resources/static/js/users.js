// Call the dataTables jQuery plugin
$(document).ready(function() {
   uploadUsers();
  $('#users').DataTable();
});

async function uploadUsers(){
    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()
    });
    const users = await request.json();
    console.log(users);
    let htmlList = '';
    for (let user of users){
    let deleteButton = '<a href="#" onclick="deleteUser('+user.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let phone = user.phone == null ? '-': user.phone;
    let userHtml = '<tr><td>'+user.id+'</td><td>'+user.name+' '+ user.surName+'</td><td>'
    +user.email+'</td><td>'+phone
    +'</td><td>'+deleteButton+'</td></tr>'
    htmlList += userHtml;
    }



    document.querySelector('#users tbody').outerHTML = htmlList;
}

function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        'Authorization': localStorage.token
    };
}

async function deleteUser(id){

    if(!confirm('Desea eliminar este usuario?')){
        return;
    }

    const request = await fetch('api/usuario/'+id, {
            method: 'DELETE',
            headers: getHeaders()
    });

    location.reload();
}