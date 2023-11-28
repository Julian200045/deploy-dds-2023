package models.services.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class LectorCSV implements CSVService {
  public List<String[]> leerCSV(Reader file) {
    try {
      CSVReader csvReader = new CSVReader(file);
      List<String[]> datos = csvReader.readAll();
      csvReader.close();
      return datos;
    } catch (IOException | CsvException e) {
      throw new RuntimeException(e);
    }
  }
}
