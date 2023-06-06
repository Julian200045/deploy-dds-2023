package domain.usuarios;

import java.io.IOException;

import domain.roles.Rol;
import lombok.Getter;

public class Usuario {
  @Getter
  int id;
  @Getter
  String nombre;
  String contrasenia;
  Rol rol;
  public Usuario(int id, String nombre, String contrasenia) throws IOException {
    this.id = id;
    this.contrasenia = contrasenia;
    this.nombre = nombre;
  }
}
