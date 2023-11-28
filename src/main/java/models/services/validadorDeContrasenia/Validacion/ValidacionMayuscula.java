package models.services.validadorDeContrasenia.Validacion;

import java.util.Objects;

public class ValidacionMayuscula implements Validacion{


  @Override
  public Boolean valida(String contrasenia) {
   return !Objects.equals(contrasenia, contrasenia.toLowerCase());
  }
}
