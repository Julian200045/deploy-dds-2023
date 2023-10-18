package server.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class ServiciosPorEstablecimientoDto {
  String establecimiento;
  List<String> servicios;

  public ServiciosPorEstablecimientoDto(String establecimiento, List<String> servicios) {
    this.establecimiento = establecimiento;
    this.servicios = servicios;
  }
}
