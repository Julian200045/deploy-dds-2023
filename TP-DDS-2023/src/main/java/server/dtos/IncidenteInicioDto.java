package server.dtos;

import lombok.Getter;
import models.entities.comunidades.Comunidad;
import models.entities.establecimientos.Establecimiento;
import models.entities.incidentes.EstadoIncidente;
import models.entities.servicios.Servicio;

@Getter
public class IncidenteInicioDto {
  public long id;
  public String servicio;
  public String establecimiento;
  public String comunidad;
  public String observaciones;
  public String estado;

  public IncidenteInicioDto(long id, String servicio, String establecimiento, String comunidad, String observaciones,String estado) {
    this.id = id;
    this.servicio = servicio;
    this.establecimiento = establecimiento;
    this.comunidad = comunidad;
    this.observaciones = observaciones;
    this.estado = estado;
  }
}
