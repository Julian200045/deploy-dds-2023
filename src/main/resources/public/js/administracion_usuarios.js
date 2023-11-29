
let usuarioAEditar = {}


function buscarUsuariosPorNombre(){
    let nombre = document.getElementById("busquedaNombreUsuario").value;
    axios.get("/administracion/usuarios",{
    params: {
              nombre: nombre,
        }
    })
    .then((response => {
        location.href = "/administracion/usuarios?nombre="+nombre;
    }))
    .catch(function (error){
        console.log(error)
    })
}

function buscarUsuarioAEditarUsuario(identificador){
        axios.get("/administracion/usuario",{
        params: {
                  id: identificador,
                  tipoBusqueda: "id"
            }
        })
        .then((response => {
            console.log(response.data);
            usuarioAEditar = response.data
            document.getElementById("nombreUsuarioAEditar").value = usuarioAEditar.nombre
            document.getElementById("mailUsuarioAEditar").value = usuarioAEditar.mail
            document.getElementById("telefonoUsuarioAEditar").value = usuarioAEditar.telefono
        }))
        .catch(function (error){
            console.log(error)
        })
}

function editarUsuario(){
    let formData = new FormData();
    formData.append("id",usuarioAEditar.id)
    formData.append("nombre",document.getElementById("nombreUsuarioAEditar").value )
    formData.append("mail",document.getElementById("mailUsuarioAEditar").value )
    formData.append("telefono", document.getElementById("telefonoUsuarioAEditar").value )

    axios.put("/administracion/usuario",formData)
    .then((response => {
            console.log(response.data);
            if(!alert(response.data)){window.location.reload();}
//            location.reload()
        }))
    .catch(function (error){
        console.log(error)
    })
}

function borrarUsuario(identificador){

    axios.delete("/administracion/usuario",{
    params: {
              id: identificador
        }
    })
    .then((response => {
        console.log(response.data);
        if(!alert(response.data)){window.location.reload();}
//        location.reload()
    }))
    .catch(function (error){
        console.log(error)
    })
}
function crearUsuario(){
    let nombre = document.getElementById("nombreeUsuarioNuevo").value;
    let nombreUsuario = document.getElementById("nombreUsuarioNuevo").value;
    let apellido = document.getElementById("apellidoUsuarioNuevo").value;
    let mail = document.getElementById("mailUsuarioNuevo").value;
    let contrasenia = document.getElementById("contraseniaUsuarioNuevo").value;
    let telefono = document.getElementById("telefonoUsuarioNuevo").value;
    let rol = document.querySelector("select").value;
    let formData = new FormData()
    formData.append("nombre_usuario",nombreUsuario);
    formData.append("nombre",nombre);
    formData.append("apellido",apellido);
    formData.append("mail",mail);
    formData.append("contrasenia",contrasenia);
    formData.append("telefono",telefono);
    formData.append("rol",rol);

    axios.post("/administracion/usuario",formData)
    .then((response) => {
        console.log(response.data);
        if(!response.data){
            alert("Usuario creado correctamente");
            window.location.reload();
        }
    })
    .catch((error) => {
        console.log(error)
    })
}


