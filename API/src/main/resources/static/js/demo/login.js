$(document).ready(function() {

});

async function iniciarSesion() {
  let datos = {};
  datos.email = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtContraseña').value;

  const request = await fetch('/login/', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });

  const respuesta = await request.text();
  if (respuesta != 'ACCESS FAILED')
  {
    localStorage.token = respuesta;
    localStorage.email = datos.email;
    window.location.href = 'tables.html'
  }
  else
  {
    alert("Intente nuevamente, email o contraseña incorrectos");
  }
}