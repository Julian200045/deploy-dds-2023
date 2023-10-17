function buscarIncidentes(){
    let establecimientoSeleccionado = document.getElementById("establecimiento").value;
    let servicioSeleccionado = document.getElementById("servicio").value;
    let comunidadSeleccionada = document.getElementById("comunidad").value;
    let estado = document.getElementById("abiertos").checked? "ABIERTO" : "CERRADO";

    if(establecimientoSeleccionado == null && servicioSeleccionado == null && comunidadSeleccionada == null){
        window.location.href = `/inicio`
    }
    else{
        console.log(estado)
        window.location.href = `/inicio?establecimiento=${establecimientoSeleccionado}&servicio=${servicioSeleccionado}&comunidad=${comunidadSeleccionada}&estado=${estado}`
    }

}