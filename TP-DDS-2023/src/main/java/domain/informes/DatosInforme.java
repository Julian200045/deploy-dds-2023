package domain.informes;

import domain.informes.rankings.Rankeador;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DatosInforme {
  Integer numeroInforme;
  LocalDate fechaInicio;
  LocalDate fechaFin;
  List<Rankeador> rankeadores;

  public DatosInforme(Integer numeroInforme, LocalDate fechaInicio, LocalDate fechaFin, List<Rankeador> rankeadores) {
    this.numeroInforme = numeroInforme;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.rankeadores = rankeadores;
  }
}
