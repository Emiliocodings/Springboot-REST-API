$(document).ready(function() {
    cargarUsuarios();
    mostrarEmailDelUsuario();
  $('#usuarios').DataTable();
});

async function cargarUsuarios() {

  const request = await fetch('/usuarios/', {
    method: 'GET',
    headers: autorizacionToken()
  });
  const usuarios = await request.json();

  console.log(usuarios);

  let listadeusuariosHTML = '';
  for (let usuario of usuarios) {
  let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'

  let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+usuario.telefono+'</td><td>' + botonEliminar + '</td></tr>'
  listadeusuariosHTML += usuarioHTML;
  }
document.querySelector('#usuarios tbody').outerHTML = listadeusuariosHTML;
}

async function eliminarUsuario(id) {

    if (!confirm('Quiere eliminar a este usuario?')) {
        return;
    }

  const request = await fetch('usuarios/' + id, {
    method: 'DELETE',
    headers: autorizacionToken()
  });

  location.reload()
}

function autorizacionToken() {
    return {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    };
}

function mostrarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}