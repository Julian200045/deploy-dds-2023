package analizadorComunidades;

import analizadorComunidades.criteriosCoincidencias.CriterioCoincidencia;
import containers.Comunidad;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnalizadorComunidades {
  @Setter
  private List<CriterioCoincidencia> criteriosCoincidencia;

  public AnalizadorComunidades(List<CriterioCoincidencia> criteriosCoincidencia){
    this.criteriosCoincidencia = criteriosCoincidencia;
  }

  public List<List<Comunidad>> generarPropuestasFusion(List<Comunidad> comunidades) {
    List<Coincidencia> coincidencias = new ArrayList<>();
    comunidades.forEach(comunidad -> coincidencias.add(new Coincidencia(comunidad)));

    coincidencias.forEach(coincidencia -> {
      coincidencia.setCoincidencias(coincidencias.stream().filter(c -> coinciden(c.comunidad, coincidencia.comunidad)).toList());
    });

    Set<Coincidencia> coincidenciasValidas = new HashSet<>(coincidencias.stream().filter(c -> c.esValida() && c.coincidencias.size() > 1).toList());

    return coincidenciasValidas.stream().map(c -> c.coincidencias.stream().map(c1 -> c1.comunidad).toList()).toList();
  }

  private Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2){
    return criteriosCoincidencia.stream().allMatch(criterioCoincidencia -> criterioCoincidencia.coinciden(comunidad1,comunidad2));
  }

}
