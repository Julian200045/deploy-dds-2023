package models.services.importadorDatosCSV;

import java.util.List;

public interface ImportadorDatosCSV {

	public void cargarDatos()throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException ;
	public List<Integer> getIds(String[] datos);
}
