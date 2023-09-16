package domain.informes;

import domain.informes.rankings.Rankeador;
import services.exportadoresDeInformes.InformeExportable;

import java.time.LocalDate;
import java.util.List;

public class Informe implements InformeExportable {
  private Integer numeroInforme;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private List<Rankeador> rankings;

  public Informe(Integer numeroInforme, LocalDate fechaInicio, LocalDate fechaFin, List<Rankeador> rankeadores) {
    this.numeroInforme = numeroInforme;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.rankings = rankeadores;
  }

  public DatosInforme getDatos(){
    return new DatosInforme(numeroInforme,fechaInicio,fechaFin,rankings);
  }
}
