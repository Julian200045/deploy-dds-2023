package services.localizacionesService;

import domain.localizaciones.Departamento;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;

import java.io.IOException;

public interface LocalizacionesService {

  Provincia provincia(Integer id) throws IOException;
  Municipio municipio(Integer id) throws IOException;
  Departamento departamento(Integer id) throws IOException;
}
