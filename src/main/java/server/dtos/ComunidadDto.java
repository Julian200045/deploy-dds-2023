package server.dtos;

import java.util.List;
import lombok.Getter;

@Getter
public class ComunidadDto {
  public Long id;
  public String nombre;
  public List<PrestacionDto> prestaciones;
  public Double gradoConfianza;

  public ComunidadDto (Long id, String nombre, List<PrestacionDto> prestaciones, Double gradoConfianza) {
    this.id = id;
    this.nombre = nombre;
    this.prestaciones = prestaciones;
    this.gradoConfianza = gradoConfianza;
  }
}
