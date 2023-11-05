package models.entities.organismos;

import models.entities.entidades.Entidad;
import models.entities.usuarios.Usuario;
import java.util.List;
import javax.persistence.CascadeType;
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
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private Usuario usuario;
  @Column(name = "email_responsable")
  private String emailResponsable;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "entidadPrestadora_id", referencedColumnName = "id")
  private List<Entidad> entidades;

  public EntidadPrestadora(String nombre, Usuario usuario, String email, List<Entidad> entidades) {
    this.nombre = nombre;
    this.usuario = usuario;
    this.emailResponsable = email;
    this.entidades = entidades;
  }

  public EntidadPrestadora(){

  }
}
