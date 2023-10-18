package servicios.analizadorcomunidades;

import containers.Propuesta;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioCoincidencia;
import containers.Comunidad;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class AnalizadorComunidades {
  @Setter
  private List<CriterioCoincidencia> criteriosCoincidencia;

  public AnalizadorComunidades(List<CriterioCoincidencia> criteriosCoincidencia) {
    this.criteriosCoincidencia = criteriosCoincidencia;
  }

  /**
   * Genera propuestas de fusión en base a los criterios de coincidencia de la clase.
   *
   * @param comunidades comunidades que buscan ser fusionadas.
   * @return lista de propuestas formadas, siendo una propuesta una lista de comunidades
   */
  public List<Propuesta> generarPropuestasFusion(List<Comunidad> comunidades) {
    List<Coincidencia> coincidencias = new ArrayList<>();
    comunidades.forEach(comunidad -> coincidencias.add(new Coincidencia(comunidad)));

    coincidencias.forEach(coincidencia -> coincidencia.setCoincidencias(coincidencias.stream().filter(c -> coinciden(c.comunidad, coincidencia.comunidad)).toList()));

    List<Coincidencia> coincidenciasValidas =
        new ArrayList<>(coincidencias.stream().filter(c -> c.esValida() && c.coincidencias.size() > 1).toList());

    List<List<Comunidad>> coincidenciasValidasUnicas = coincidenciasValidas.stream().map(c -> c.coincidencias.stream().map(c1 -> c1.comunidad).toList()).distinct().toList();



    return coincidenciasValidasUnicas.stream().map(listaComunidades ->
        new Propuesta(listaComunidades)).toList();
  }

  private Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2) {
    return criteriosCoincidencia.stream().allMatch(criterioCoincidencia -> criterioCoincidencia.coinciden(comunidad1, comunidad2));
  }

}
