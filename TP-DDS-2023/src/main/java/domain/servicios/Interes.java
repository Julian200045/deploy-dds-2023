package domain.servicios;

import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import lombok.Data;

@Data
public class Interes {
  private Servicio servicio;
  private Entidad entidad;
  private Miembro miembro;

  public Interes(Servicio servicio, Entidad entidad, Miembro miembro) {
    this.servicio = servicio;
    this.entidad = entidad;
    this.miembro = miembro;
  }
}
