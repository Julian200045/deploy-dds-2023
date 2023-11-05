function cargarArchivo() {
    const formData = new FormData(document.getElementById("organism-form"));
    fetch("/", {
        method: "POST",
        body: formData,
    }).then(response => {
    });
}