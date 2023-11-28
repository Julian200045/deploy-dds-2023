function confirmSubmit() {
  var confirmacion = confirm("¿Dar de alta con este archivo?");

    if (confirmacion) {
      alert("Solicitud de alta procesada");

      document.getElementById('myForm').submit();
    }

    return confirmacion;
}

function validateForm() {
  var csvFile = document.getElementById('csv-file').value;
  var tipoOrganismo = document.getElementById('tipo-organismo').value;

  if (csvFile === "" || tipoOrganismo === "") {
    alert("Por favor, completa todos los campos antes de enviar el formulario.");

    return false;
  }
  return confirmSubmit();
}