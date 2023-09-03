package services.validadorDeContrasenia.Validacion;

public class ValidacionMayuscula implements Validacion{


  @Override
  public Boolean valida(String contrasenia) {
   return contrasenia == contrasenia.toLowerCase();
  }
}
