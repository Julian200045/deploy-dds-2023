function buscarIncidentes() {
    let establecimientoSeleccionado = document.getElementById("establecimiento").value;
    let servicioSeleccionado = document.getElementById("servicio").value;
    let comunidadSeleccionada = document.getElementById("comunidad").value;
    let estado = document.getElementById("abiertos").checked ? "ABIERTO" : "RESUELTO";

    console.log(establecimientoSeleccionado + " " + servicioSeleccionado  + " " + comunidadSeleccionada + " " + estado)

    if ((establecimientoSeleccionado == null || establecimientoSeleccionado == "") && 
        (servicioSeleccionado == null || servicioSeleccionado == "") && 
        (comunidadSeleccionada == null || comunidadSeleccionada == "")) {
        window.location.href = `/inicio?estado=${estado}`
    } else {
        window.location.href = `/inicio?establecimiento=${establecimientoSeleccionado}&servicio=${servicioSeleccionado}&comunidad=${comunidadSeleccionada}&estado=${estado}`
    }
}

function darDeBaja(idIncidente) {
    if (confirm("¿Seguro que quiere dar de baja el incidente?") === true) {
        fetch(`/inicio/incidentes/${idIncidente}/dar-de-baja`, {
            method: 'PATCH', headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    console.log("OK");
                    window.location.href = "/inicio"
                } else {
                    console.error('Error en la solicitud PATCH:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Error en la solicitud PATCH:', error);
            });
    } else {
        alert("Operación cancelada.");
    }
}