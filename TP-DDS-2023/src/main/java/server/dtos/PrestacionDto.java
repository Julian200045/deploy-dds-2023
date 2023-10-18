package server.dtos;

import lombok.Getter;

@Getter
public class PrestacionDto {
  Long id;
  String establecimiento;
  String servicio;

  public PrestacionDto(Long id, String establecimiento, String servicio) {
    this.id = id;
    this.establecimiento = establecimiento;
    this.servicio = servicio;
  }
}
