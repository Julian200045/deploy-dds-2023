package servicios.analizadorcomunidades.criterioscoincidencias;

import containers.Comunidad;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;

public class CriterioUsuarios implements CriterioCoincidencia {
  @Setter
  private Double porcentajeCoincidencia;

  public CriterioUsuarios(Double porcentajeCoincidencia) {
    this.porcentajeCoincidencia = porcentajeCoincidencia;
  }

  @Override
  public Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2) {

    List<Long> miembrosComunidad1 = comunidad1.getMiembros();
    List<Long> miembrosComunidad2 = comunidad2.getMiembros();

    List<Long> miembrosEnComun = new ArrayList<>(miembrosComunidad1);
    miembrosEnComun.retainAll(miembrosComunidad2);

    Set<Long> miembrosTotales = new HashSet<>(miembrosComunidad1);
    miembrosTotales.addAll(miembrosComunidad2);

    return ((double) miembrosEnComun.size() / miembrosTotales.size()) > porcentajeCoincidencia;
  }
}
