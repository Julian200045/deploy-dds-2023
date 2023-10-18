
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
            usuarioAEditar = response.data[0]
            document.getElementById("nombreUsuarioAEditar").value = usuarioAEditar.nombre
            document.getElementById("mailUsuarioAEditar").value = usuarioAEditar.mail
            document.getElementById("telefonoUsuarioAEditar").value = usuarioAEditar.telefono
        }))
        .catch(function (error){
            console.log(error)
        })
}

function editarUsuario(){
    axios.put("/administracion/usuario",{
        params: {
                  id:usuarioAEditar.id,
                  nombre: document.getElementById("nombreUsuarioAEditar").value ,
                  mail: document.getElementById("mailUsuarioAEditar").value ,
                  telefono: document.getElementById("telefonoUsuarioAEditar").value ,
            }
    })
    .then((response => {
            console.log(response.data);
            location.reload()
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
        location.reload()
    }))
    .catch(function (error){
        console.log(error)
    })
}