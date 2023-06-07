package services.csv;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import services.LectorPropiedades;

public class LectorCSV implements CSVService{
	String csvEntidadesPrestadoras;
	@Getter
	List<String[]> datosEntidadesPrestadoras = new ArrayList<>();
	CSVReader csvReader;

	public LectorCSV(String pathPropiedades) throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException{

		LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);
		csvEntidadesPrestadoras = lectorPropiedades.getPropiedad("entidades-prestadoras-csv-path");

		System.out.println(csvEntidadesPrestadoras);

		csvReader = new CSVReader(new FileReader(csvEntidadesPrestadoras));
		retirarDatosEntidadesPrestadoras();
		csvReader.close();
	}

	public void retirarDatosEntidadesPrestadoras() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		String[] fila = null;
		while((fila = csvReader.readNext()) != null) {
			datosEntidadesPrestadoras.add(fila);
		}
	}

}
