package domain.servicios;

import domain.comunidades.Miembro;
import domain.comunidades.Persona;
import domain.entidades.Entidad;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "miembro_id", referencedColumnName = "id")
  private Miembro miembro;

  public Interes(Servicio servicio, Entidad entidad, Miembro miembro) {
    this.servicio = servicio;
    this.entidad = entidad;
    this.miembro = miembro;
  }

  public Interes(){

  }
}
