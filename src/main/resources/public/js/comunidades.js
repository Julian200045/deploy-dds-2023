function darDeAltaMiembro(idComunidad) {
    if (confirm("¿Seguro que quiere darse de alta en esta comunidad?") === true) {
        fetch(`/comunidades/${idComunidad}`, {
            method: 'PATCH', headers: {
                'Content-Type': 'application/json'
            }
        }).then(r => {
            if (r.ok) {
                console.log("OK");
                window.location.href="/comunidades"
            }
        });
    } else {
        alert("Operacion cancelada.");
    }
}