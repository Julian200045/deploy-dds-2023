package models.entities.organismos;

import models.entities.entidades.Entidad;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "organismo_control")
public class OrganismoDeControl {
  @Id
  @GeneratedValue
  private long id;
  @Getter
  @Column(name = "nombre")
  private String nombre;
  @Getter
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
  private Usuario usuario;
  @Column(name = "email_responsable")
  private String emailResponsable;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "organismoDeControl_id", referencedColumnName = "id")
  private List<EntidadPrestadora> entidadesPrestadoras;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;

  public OrganismoDeControl(String nombre, Usuario usuario, String email, List<EntidadPrestadora> entidades, Servicio servicio) {
    this.nombre = nombre;
    this.usuario = usuario;
    this.emailResponsable = email;
    this.entidadesPrestadoras = entidades;
    this.servicio = servicio;
  }
  public OrganismoDeControl(){

  }
}
