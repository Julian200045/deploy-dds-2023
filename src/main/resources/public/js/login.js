function iniciarSesion() {
    let iniciarSesionForm = new FormData();
    iniciarSesionForm.append('nombre_usuario', document.getElementById("nombre_usuario").value);
    iniciarSesionForm.append('contrasenia', document.getElementById("contrasenia").value);

    fetch(`/usuarios/login`, {
        method: 'POST',
        body: iniciarSesionForm
    }).then(r => {
        sleep(5000).then(() => { console.log('World!'); });
        if (r.ok) {
            window.location.href = "/incidentes"
        }
        else {
            alert("Credenciales incorrectas");
            window.location.href = "/login"
        }
    });
}