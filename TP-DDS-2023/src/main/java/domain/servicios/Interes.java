package domain.servicios;

import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "interes")
@Data
public class Interes {
  @Id
  @GeneratedValue
  long id;
  @ManyToOne
  private Servicio servicio;
  @ManyToOne
  private Entidad entidad;
  @ManyToOne
  private Miembro miembro;

  public Interes(Servicio servicio, Entidad entidad, Miembro miembro) {
    this.servicio = servicio;
    this.entidad = entidad;
    this.miembro = miembro;
  }
}
