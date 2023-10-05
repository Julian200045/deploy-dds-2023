package models.services.localizacionesService;

import models.entities.localizaciones.Localidad;
import models.entities.localizaciones.Municipio;
import models.entities.localizaciones.Provincia;

import java.io.IOException;

public interface LocalizacionesService {

  Provincia provincia(Integer id) throws IOException;
  Municipio municipio(Integer id) throws IOException;
  Localidad localidad(Integer id) throws IOException;
}
