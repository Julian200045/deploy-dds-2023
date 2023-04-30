package domain.usuarios;

import java.io.IOException;

public class Usuario {

  String nombre;
  String contrasenia;
  public Usuario(String nombre, String contrasenia) throws IOException {

    this.contrasenia = contrasenia;
    this.nombre = nombre;
  }
}
