package models.services.validadorDeContrasenia;

import models.services.validadorDeContrasenia.Validacion.Validacion;

public interface ValidadorDeContrasenias {
  Boolean esValida(String contrasenia);
  void agregarValidacion(Validacion validacion);
}
