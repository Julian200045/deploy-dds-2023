package domain.organismos;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "entidadPrestadora")
public class EntidadPrestadora {
  @Getter
  @Id
  @GeneratedValue
  long id;
  @Getter
  @Column
  String nombre;
  @Getter
  @OneToOne
  Usuario usuario;
  @Column
  String emailResponsable;
  @OneToMany
  List<Entidad> entidades;

  public EntidadPrestadora(int id, String nombre, Usuario usuario, String email, List<Entidad> entidades) {
    this.id = id;
    this.nombre = nombre;
    this.usuario = usuario;
    this.emailResponsable = email;
    this.entidades = entidades;
  }
}
