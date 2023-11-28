package models.services.importadorDatosCSV;

import java.util.List;

public interface ImportadorDatosCSV {

	void cargarDatos(List<String[]> datosACargar);
}
