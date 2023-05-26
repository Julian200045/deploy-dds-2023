package services.validadorDeContrasenia.Validacion;

import services.LectorPropiedades;

import java.io.IOException;

public class ValidacionDeLargo implements Validacion {

  private Integer min;
  private Integer max;

  public ValidacionDeLargo(String path) throws IOException {
    LectorPropiedades lectorPropiedades = new LectorPropiedades(path);

    this.min = Integer.parseInt(lectorPropiedades.getPropiedad("min"));
    this.max = Integer.parseInt(lectorPropiedades.getPropiedad("max"));
  }

  @Override
  public Boolean valida(String contrasenia){
    return min <= contrasenia.length() && contrasenia.length() <= max;
  }
}
