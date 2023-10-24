package models.services.validadorDeContrasenia.Validacion;

import java.io.IOException;
import models.services.LectorPropiedades;

public class ValidacionDeLargo implements Validacion {

  private Integer min;
  private Integer max;

  public ValidacionDeLargo(String path) throws IOException {
    LectorPropiedades lectorPropiedades = new LectorPropiedades(path);

    this.min = lectorPropiedades.getPropiedadInt("min");
    this.max = lectorPropiedades.getPropiedadInt("max");
  }

  @Override
  public Boolean valida(String contrasenia) {
    return min <= contrasenia.length() && contrasenia.length() <= max;
  }
}
