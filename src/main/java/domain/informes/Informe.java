package domain.informes;

import domain.informes.rankings.Ranking;
import services.exportadoresDeInformes.InformeExportable;

import java.time.LocalDate;
import java.util.List;

public class Informe implements InformeExportable {
  Integer numeroInforme;
  LocalDate fechaInicio;
  LocalDate fechaFin;
  List<Ranking> rankings;

  public Informe(Integer numeroInforme, LocalDate fechaInicio, LocalDate fechaFin, List<Ranking> rankings) {
    this.numeroInforme = numeroInforme;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.rankings = rankings;
  }

  public DatosInforme getDatos(){
    return new DatosInforme(numeroInforme,fechaInicio,fechaFin,rankings);
  }
}
