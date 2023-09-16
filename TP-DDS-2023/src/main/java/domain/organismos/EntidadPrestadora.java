package domain.organismos;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "entidad_prestadora")
public class EntidadPrestadora {
  @Getter
  @Id
  @GeneratedValue
  private long id;
  @Getter
  @Column(name = "nombre")
  private String nombre;
  @Getter
  @OneToOne
  private Usuario usuario;
  @Column(name = "email_responsable")
  private String emailResponsable;
  @OneToMany
  @JoinColumn(name = "entidadPrestadora_id", referencedColumnName = "id")
  private List<Entidad> entidades;

  public EntidadPrestadora(int id, String nombre, Usuario usuario, String email, List<Entidad> entidades) {
    this.id = id;
    this.nombre = nombre;
    this.usuario = usuario;
    this.emailResponsable = email;
    this.entidades = entidades;
  }

  public EntidadPrestadora(){

  }
}
