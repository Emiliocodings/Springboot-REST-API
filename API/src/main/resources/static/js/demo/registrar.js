$(document).ready(function() {

});

async function registrarUsuarios() {
  let datos = {};
  datos.nombre = document.getElementById('txtNombre').value;
  datos.apellido = document.getElementById('txtApellido').value;
  datos.telefono = document.getElementById('txtTelefono').value;
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtContraseña').value;

  let repetircontraseña = document.getElementById('txtRepetirContraseña').value;

  if(repetircontraseña != datos.password){
  alert('Las contraseñas no coinciden');
  return;
  }

  alert("Usuario registrado correctamente");
  window.location.href = 'login.html';

  const request = await fetch('usuarios/', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  const usuarios = await request.json();
}