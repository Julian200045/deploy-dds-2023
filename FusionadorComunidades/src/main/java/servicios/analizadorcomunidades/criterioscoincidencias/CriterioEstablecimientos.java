package servicios.analizadorcomunidades.criterioscoincidencias;

import containers.Comunidad;
import containers.PrestacionDeServicio;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;

public class CriterioEstablecimientos implements CriterioCoincidencia {
  @Setter
  private Double porcentajeCoincidencia;

  public CriterioEstablecimientos(Double porcentajeCoincidencia) {
    this.porcentajeCoincidencia = porcentajeCoincidencia;
  }

  @Override
  public Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2) {

    List<Long> establecimientosComunidad1 = comunidad1.getPrestacionesDeServicio().stream().map(PrestacionDeServicio::getEstablecimiento).toList();
    List<Long> establecimientosComunidad2 = comunidad2.getPrestacionesDeServicio().stream().map(PrestacionDeServicio::getEstablecimiento).toList();

    List<Long> establecimientosEnComun = new ArrayList<>(establecimientosComunidad1);
    establecimientosEnComun.retainAll(establecimientosComunidad2);

    Set<Long> establecimientosTotales = new HashSet<>(establecimientosComunidad1);
    establecimientosTotales.addAll(establecimientosComunidad2);

    return ((double) establecimientosEnComun.size() / establecimientosTotales.size()) > porcentajeCoincidencia;
  }
}
