package models.services.validadorDeContrasenia.Validacion;

public class ValidacionSimilitudUsuario implements Validacion{

  String nombreUsuario;
  public ValidacionSimilitudUsuario(String nombreUsuario){
    this.nombreUsuario = nombreUsuario;
  }
  @Override
  public Boolean valida(String contrasenia) {
    String contraseniaNormal = contrasenia.toLowerCase();
    String nombreNormal = nombreUsuario.toLowerCase();
    return !contraseniaNormal.contains(nombreNormal);
  }
}
