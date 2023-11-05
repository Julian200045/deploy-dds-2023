package models.services.csv;

import java.io.Reader;
import java.util.List;

public interface CSVService {
  List<String[]> leerCSV(Reader file);
}
