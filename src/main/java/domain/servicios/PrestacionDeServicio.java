package domain.servicios;

import domain.comunidades.Miembro;
import domain.establecimientos.Establecimiento;
import lombok.Getter;

import java.util.List;

public class PrestacionDeServicio {
  public Servicio servicio;
  public Establecimiento establecimiento;
  @Getter
  public boolean estaHabilitado;
  private List<Miembro> miembrosInteresados;

  public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento) {
    this.servicio = servicio;
    this.establecimiento = establecimiento;
    this.estaHabilitado = true;
  }
}
