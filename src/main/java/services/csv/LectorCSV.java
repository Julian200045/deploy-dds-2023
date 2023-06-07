package services.csv;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import services.LectorPropiedades;

public class LectorCSV implements CSVService {
  String csvEntidadesPrestadoras;
  String csvOrganismosDeControl;
  @Getter
  List<String[]> datosEntidadesPrestadoras = new ArrayList<>();
  @Getter
  List<String[]> datosOrganismosDeControl = new ArrayList<>();
  CSVReader csvReader;

  public LectorCSV(String pathPropiedades) throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException {

    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);
    csvEntidadesPrestadoras = lectorPropiedades.getPropiedad("entidades-prestadoras-csv-path");
    csvOrganismosDeControl = lectorPropiedades.getPropiedad("organismos-de-control-csv-path");

    csvReader = new CSVReader(new FileReader(csvOrganismosDeControl));
    retirarDatos(datosOrganismosDeControl);
    csvReader.close();

    csvReader = new CSVReader(new FileReader(csvEntidadesPrestadoras));
    retirarDatos(datosEntidadesPrestadoras);
    csvReader.close();
  }

  public void retirarDatos(List<String[]> datos) throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {
    String[] fila = null;
    while ((fila = csvReader.readNext()) != null) {
      datos.add(fila);
    }
  }

}
