package models.services.validadorDeContrasenia.Validacion;

import java.io.IOException;
import models.services.LectorPropiedades;

public class ValidacionDeLargo implements Validacion {

  private Integer min;
  private Integer max;

  private LectorPropiedades lectorPropiedades;

  public ValidacionDeLargo(LectorPropiedades lectorPropiedades) {
    this.lectorPropiedades = lectorPropiedades;
    try {
      this.min = lectorPropiedades.getPropiedadInt("min");
      this.max = lectorPropiedades.getPropiedadInt("max");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Boolean valida(String contrasenia) {
    return min <= contrasenia.length() && contrasenia.length() <= max;
  }
}
