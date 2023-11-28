function darDeAltaUsuario() {
    let registrarUsuarioForm = new FormData();
    registrarUsuarioForm.append('nombre', document.getElementById("nombre").value);
    registrarUsuarioForm.append('apellido', document.getElementById("apellido").value);
    registrarUsuarioForm.append('nombre_usuario', document.getElementById("nombre_usuario").value);
    registrarUsuarioForm.append('email', document.getElementById("email").value);
    registrarUsuarioForm.append('contrasenia', document.getElementById("contrasenia").value);
    registrarUsuarioForm.append('celular', document.getElementById("celular").value);
    fetch(`/usuarios`, {
        method: 'POST',
        body: registrarUsuarioForm
    }).then(r => {
        if (r.ok) {
            alert("Usuario creado correctamente")
            window.location.href = "/login"
        }
        else {
            alert("Contraseña débil. Respete las validaciones");
            window.location.href = "/usuarios/crear"
        }
    });
}