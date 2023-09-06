package analizadorComunidades.criteriosCoincidencias;

import containers.Comunidad;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CriterioServicios implements CriterioCoincidencia{
  @Setter
  private Double porcentajeCoincidencia;
  public CriterioServicios(Double porcentajeCoincidencia) {
    this.porcentajeCoincidencia = porcentajeCoincidencia;
  }

  @Override
  public Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2) {

    List<Long> serviciosComunidad1 = comunidad1.getPrestacionesDeServicio().stream().map(p -> p.getServicio()).toList();
    List<Long> serviciosComunidad2 = comunidad2.getPrestacionesDeServicio().stream().map(p -> p.getServicio()).toList();

    List<Long> serviciosEnComun = new ArrayList<>(serviciosComunidad1);
    serviciosEnComun.retainAll(serviciosComunidad2);

    Set<Long> serviciosTotales = new HashSet<>(serviciosComunidad1);
    serviciosTotales.addAll(serviciosComunidad2);

    return ((double) serviciosEnComun.size() / serviciosTotales.size()) > porcentajeCoincidencia;
  }
}
