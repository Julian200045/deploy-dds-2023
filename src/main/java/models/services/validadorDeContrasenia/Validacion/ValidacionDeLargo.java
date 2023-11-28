package models.services.validadorDeContrasenia.Validacion;

public class ValidacionDeLargo implements Validacion {

  private final Integer min;
  private final Integer max;

  public ValidacionDeLargo(Integer min, Integer max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public Boolean valida(String contrasenia) {
    return min <= contrasenia.length() && contrasenia.length() <= max;
  }
}
