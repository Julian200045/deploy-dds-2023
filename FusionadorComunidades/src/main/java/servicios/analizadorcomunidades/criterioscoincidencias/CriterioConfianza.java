package servicios.analizadorcomunidades.criterioscoincidencias;

import containers.Comunidad;
import java.util.Objects;

public class CriterioConfianza implements CriterioCoincidencia {
  @Override
  public Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2) {
    return Objects.equals(comunidad1.getGradoConfianza(), comunidad2.getGradoConfianza());
  }
}
