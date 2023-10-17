function buscarIncidentes() {
    let establecimientoSeleccionado = document.getElementById("establecimiento").value;
    let servicioSeleccionado = document.getElementById("servicio").value;
    let comunidadSeleccionada = document.getElementById("comunidad").value;
    let estado = document.getElementById("abiertos").checked ? "ABIERTO" : "RESUELTO";

    if (establecimientoSeleccionado == null && servicioSeleccionado == null && comunidadSeleccionada == null) {
        window.location.href = `/inicio?estado=?${estado}`
    } else {
        console.log(estado)
        window.location.href = `/inicio?establecimiento=${establecimientoSeleccionado}&servicio=${servicioSeleccionado}&comunidad=${comunidadSeleccionada}&estado=${estado}`
    }
}

function darDeBaja(idIncidente) {
    if (confirm("¿Seguro que quiere dar de baja el incidente?") === true) {
        fetch(`/inicio/incidentes/${idIncidente}/dar-de-baja`, {
            method: 'PATCH', headers: {
                'Content-Type': 'application/json' // Establece el tipo de contenido si estás enviando datos en formato JSON
            }// Convierte los datos en formato JSON si es necesario
        })
            .then(response => {
                if (response.ok) {
                    console.log("OK");
                } else {
                    // La solicitud falló
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