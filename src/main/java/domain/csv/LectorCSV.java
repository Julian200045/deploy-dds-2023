package domain.csv;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class LectorCSV {
	String csvOrganismos = "src/main/resources/organismos.csv";
	@Getter
	List<String[]> datosOrganismos = new ArrayList<>();
	CSVReader csvReader;

	public LectorCSV() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		csvReader = new CSVReader(new FileReader(csvOrganismos));
		retirarDatosOrganismos();
		csvReader.close();
		//System.out.println(Arrays.stream(datosOrganismos.get(0)).toList());
		//System.out.println(Arrays.stream(datosOrganismos.get(1)).toList());
	}

	public void retirarDatosOrganismos() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		String[] fila = null;
		while((fila = csvReader.readNext()) != null) {
			datosOrganismos.add(fila);
		}
	}

	public List<Integer> getIds(String ids){
		//List<String> newIds = Arrays.stream(ids.split("|")).toList();
		List<String> newIds = Arrays.stream(ids.split("|")).toList();
		System.out.println(newIds);
		return newIds.stream().map(string->Integer.parseInt(string)).collect(Collectors.toList());
	}
}
