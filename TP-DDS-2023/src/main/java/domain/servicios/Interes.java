package domain.servicios;

import domain.comunidades.Miembro;
import domain.comunidades.Persona;
import domain.entidades.Entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "interes")
@Data
public class Interes {
  @Id
  @GeneratedValue
  private long id;
  @ManyToOne
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;
  @ManyToOne
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;
  @ManyToOne
  @JoinColumn(name = "miembro_id", referencedColumnName = "id")
  private Miembro miembro;

  public Interes(Servicio servicio, Entidad entidad, Miembro miembro) {
    this.servicio = servicio;
    this.entidad = entidad;
    this.miembro = miembro;
  }
}
