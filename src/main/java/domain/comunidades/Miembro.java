package domain.comunidades;

import domain.localizaciones.Localizacion;
import domain.usuarios.Usuario;
import lombok.Setter;

public class Miembro {
  String nombre;
  String apellido;
  String email;
  Usuario usuario;

  @Setter
  Localizacion localizacionDeInteres;

  public Miembro(String nombre, String apellido, String email, Usuario usuario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.usuario = usuario;
  }
}
