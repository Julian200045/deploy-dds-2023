function buscarIncidentes(){
    let establecimientoSeleccionado = document.getElementById("establecimiento").value;
    let servicioSeleccionado = document.getElementById("servicio").value;
    let comunidadSeleccionada = document.getElementById("comunidad").value;
    let estado = document.getElementById("abiertos").value;

    if(establecimientoSeleccionado == "" && servicioSeleccionado == "" && comunidadSeleccionada == ""){
        window.location.href = "/inicio"
    }
    else{
        window.location.href = `/inicio?establecimiento_id=${establecimientoSeleccionado}&servicio_id=${servicioSeleccionado}&comunidad_id=${comunidadSeleccionada}`
    }

}