package domain.usuarios;

import java.io.IOException;

public class Administrador implements Usuario {
  String nombre;
  String contrasenia;
  ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
  public Administrador(String nombre, String contrasenia) throws IOException {

    if(validadorDeContrasenia.EsValida(contrasenia))
      this.contrasenia = validadorDeContrasenia.Hashear(contrasenia);
    else throw new IllegalArgumentException("La contraseña no cumple con los estandares establecidos");

    this.nombre = nombre;
  }
}
