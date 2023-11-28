package server.dtos;

import lombok.Getter;

@Getter
public class IncidenteInicioDto {
  public long id;
  public String servicio;
  public String establecimiento;
  public String comunidad;
  public String observaciones;
  public Boolean estaAbierto;

  public IncidenteInicioDto(long id, String servicio, String establecimiento, String comunidad, String observaciones,Boolean estado) {
    this.id = id;
    this.servicio = servicio;
    this.establecimiento = establecimiento;
    this.comunidad = comunidad;
    this.observaciones = observaciones;
    this.estaAbierto = estado;
  }
}
