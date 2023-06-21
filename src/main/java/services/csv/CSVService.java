package services.csv;

import java.util.List;

public interface CSVService {
  public void retirarDatos(List<String[]> datos) throws java.io.IOException, com.opencsv.exceptions.CsvValidationException;
}
