package models.services.validadorDeContrasenia.Validacion;

public class ValidacionSimilitudUsuario implements Validacion{

  String nombreUsuario;
  public ValidacionSimilitudUsuario(String nombreUsuario){
    this.nombreUsuario = nombreUsuario;
  }
  @Override
  public Boolean valida(String contrasenia) {
    return !contrasenia.contains(nombreUsuario);
  }
}
