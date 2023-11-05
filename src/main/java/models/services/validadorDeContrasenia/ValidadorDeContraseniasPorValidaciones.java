package models.services.validadorDeContrasenia;

import java.util.ArrayList;
import java.util.List;
import models.services.validadorDeContrasenia.Validacion.Validacion;

public class ValidadorDeContraseniasPorValidaciones implements ValidadorDeContrasenias {
  private static List<Validacion> validaciones;

  public ValidadorDeContraseniasPorValidaciones() {
    validaciones = new ArrayList<>();
  }

  public Boolean esValida(String contrasenia) {
    return validaciones.stream().allMatch(validacion -> validacion.valida(contrasenia));
  }

  public void agregarValidacion(Validacion validacion) {
    validaciones.add(validacion);
  }
}
