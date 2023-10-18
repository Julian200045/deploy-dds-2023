const inputEstablecimiento = document.getElementById('establecimiento');
const suggestionsList = document.getElementById('suggestions');
const selectServicio = document.getElementById('servicio')

const establecimientos = serviciosPorEstablecimiento.map(spe => spe.establecimiento);

const serviciosEnEstablecimiento = {}
serviciosPorEstablecimiento.forEach((spe) => serviciosEnEstablecimiento[spe.establecimiento] = spe.servicios)

console.log(serviciosPorEstablecimiento)
console.log(serviciosEnEstablecimiento);

function showSuggestions(input) {
    const inputValue = input.value.toLowerCase();

    if (inputValue === '') {
        suggestionsList.innerHTML = '';
        return;
    }

    const filteredEstablecimientos = establecimientos.filter(establecimiento =>
        establecimiento.toLowerCase().startsWith(inputValue)
    );

    suggestionsList.innerHTML = '';

    filteredEstablecimientos.forEach(establecimiento => {
        const suggestionItem = document.createElement('li');
        suggestionItem.textContent = establecimiento;
        suggestionsList.appendChild(suggestionItem);

        suggestionItem.addEventListener('click', () => {
            inputEstablecimiento.value = establecimiento;
            suggestionsList.innerHTML = '';
            selectServicio.disabled = false;

            cargarOpcionesDeServicios(establecimiento);
        });
    });
}

function cargarOpcionesDeServicios(establecimientoSeleccionado) {
    const servicios = serviciosEnEstablecimiento[establecimientoSeleccionado] || [];
    selectServicio.innerHTML = '';

    servicios.forEach(servicio => {
        const option = document.createElement('option');
        option.value = servicio;
        option.textContent = servicio;
        selectServicio.appendChild(option);
    });
}

inputEstablecimiento.addEventListener('input', () => {
    showSuggestions(inputEstablecimiento);
    console.log("hola");
    if(inputEstablecimiento.value === '') {
        selectServicio.innerHTML = '<option value="" selected>Selecciona un establecimiento primero</option>';
        selectServicio.disabled = true;
    }
});

function navegarAInicio(){
    window.location.href="/inicio"
}