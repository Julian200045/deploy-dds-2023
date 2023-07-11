package domain.informes;

import domain.informes.rankings.Ranking;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DatosInforme {
  Integer numeroInforme;
  LocalDate fechaInicio;
  LocalDate fechaFin;
  List<Ranking> rankings;

  public DatosInforme(Integer numeroInforme, LocalDate fechaInicio, LocalDate fechaFin, List<Ranking> rankings) {
    this.numeroInforme = numeroInforme;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.rankings = rankings;
  }
}
