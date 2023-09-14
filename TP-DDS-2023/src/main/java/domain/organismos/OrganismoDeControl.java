package domain.organismos;

import domain.entidades.Entidad;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "organismosDeControl")
public class OrganismoDeControl {
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
  @JoinColumn(name = "organismoDeControl_id", referencedColumnName = "id")
  List<EntidadPrestadora> entidadesPrestadoras;
  @ManyToOne
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  Servicio servicio;

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
