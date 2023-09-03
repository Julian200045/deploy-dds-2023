package domain.organismos;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import java.util.List;
import lombok.Getter;

public class EntidadPrestadora {
  @Getter
  int id;
  @Getter
  String nombre;
  @Getter
  Usuario usuario;
  String emailResponsable;
  List<Entidad> entidades;

  public EntidadPrestadora(int id, String nombre, Usuario usuario, String email, List<Entidad> entidades) {
    this.id = id;
    this.nombre = nombre;
    this.usuario = usuario;
    this.emailResponsable = email;
    this.entidades = entidades;
  }
}
