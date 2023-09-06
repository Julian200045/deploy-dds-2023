package analizadorComunidades.criteriosCoincidencias;

import containers.Comunidad;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CriterioEstablecimientos implements CriterioCoincidencia{
  @Setter
  private Double porcentajeCoincidencia;

  public CriterioEstablecimientos(Double porcentajeCoincidencia) {
    this.porcentajeCoincidencia = porcentajeCoincidencia;
  }

  @Override
  public Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2) {

    List<Long> establecimientosComunidad1 = comunidad1.getPrestacionesDeServicio().stream().map(p -> p.getEstablecimiento()).toList();
    List<Long> establecimientosComunidad2 = comunidad2.getPrestacionesDeServicio().stream().map(p -> p.getEstablecimiento()).toList();

    List<Long> establecimientosEnComun = new ArrayList<>(establecimientosComunidad1);
    establecimientosEnComun.retainAll(establecimientosComunidad2);

    Set<Long> establecimientosTotales = new HashSet<>(establecimientosComunidad1);
    establecimientosTotales.addAll(establecimientosComunidad2);

    return ((double) establecimientosEnComun.size() / establecimientosTotales.size()) > porcentajeCoincidencia;
  }
}
